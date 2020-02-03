package dao;

import models.Person;
import models.User;

import java.io.File;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class PersonDao {

    private static String url = "jdbc:sqlite:" + new File("").getAbsolutePath() + "/data/family_map.db";

    public static boolean addPerson(Person p){
        try {
            String sql_operation = "INSERT INTO persons(id,username,first_name,last_name,gender," +
                    "father_id,mother_id,spouse_id) values("
                    + p.toString() + ")";
            PreparedStatement statement = DriverManager.getConnection(url).prepareStatement(sql_operation);
            statement.executeUpdate();
            statement.close();
            DriverManager.getConnection(url).close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static Person getUserPerson(String username){
        try {
            String id = Objects.requireNonNull(UserDao.getUser(username)).personID;

            String sql_operation = "SELECT * FROM persons WHERE id = '" + id + "'";
            PreparedStatement statement = DriverManager.getConnection(url).prepareStatement(sql_operation);
            ResultSet rs = statement.executeQuery();
            DriverManager.getConnection(url).close();
            if (rs.next()){
                Person p = new Person(rs.getString("id"), rs.getString("username"), rs.getString("first_name"),
                        rs.getString("last_name"), rs.getString("gender"), rs.getString("father_id"),
                        rs.getString("mother_id"), rs.getString("spouse_id"));
                statement.close();
                return p;
            }
            else
                return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Person getPerson(String id){
        try {
            String sql_operation = "SELECT * FROM persons WHERE id = '" + id + "'";
            PreparedStatement statement = DriverManager.getConnection(url).prepareStatement(sql_operation);
            ResultSet rs = statement.executeQuery();
            DriverManager.getConnection(url).close();
            if (rs.next()){
                Person p = new Person(rs.getString("id"), rs.getString("username"), rs.getString("first_name"),
                        rs.getString("last_name"), rs.getString("gender"), rs.getString("father_id"),
                        rs.getString("mother_id"), rs.getString("spouse_id"));
                statement.close();
                return p;
            }
            else
                return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ArrayList<Person> getAllPersons(String username){
        try {
            String sql_operation = "SELECT * FROM persons WHERE username = '" + username + "'";
            PreparedStatement statement = DriverManager.getConnection(url).prepareStatement(sql_operation);
            ResultSet rs = statement.executeQuery();
            DriverManager.getConnection(url).close();

            ArrayList<Person> person_list = new ArrayList<>();
            while (rs.next()){
                Person p = new Person(rs.getString("id"), rs.getString("username"), rs.getString("first_name"),
                        rs.getString("last_name"), rs.getString("gender"), rs.getString("father_id"),
                        rs.getString("mother_id"), rs.getString("spouse_id"));
                person_list.add(p);
            }
            return person_list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void deleteUserPerson(String username){
        try {
            String id = Objects.requireNonNull(UserDao.getUser(username)).personID;

            String sql_operation = "DELETE FROM persons WHERE id = '" + id + "'";
            PreparedStatement statement = DriverManager.getConnection(url).prepareStatement(sql_operation);
            statement.executeUpdate();
            statement.close();
            DriverManager.getConnection(url).close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void clear(){
        try {
            String sql_operation = "DELETE FROM persons";
            PreparedStatement statement = DriverManager.getConnection(url).prepareStatement(sql_operation);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
