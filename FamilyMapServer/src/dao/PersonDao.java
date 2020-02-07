package dao;

import models.Person;
import models.User;

import java.io.File;
import java.sql.*;
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<Person> getFamily(String username){
        ArrayList<Person> family = new ArrayList<>();

        User u = UserDao.getUser(username);
        assert u != null;
        Person p = PersonDao.getPerson(u.personID);

        if (p == null)
            return null;

        family.add(p);
        if (p.spouseID != null)
            family.add(getPerson(p.spouseID));

        family.addAll(getAncestors(p.personID));
        family.addAll(getChildren(p.personID));

        return family;
    }

    public static ArrayList<Person> getAncestors(String id){
        ArrayList<Person> ancestors = new ArrayList<>();
        if (id == null)
            return ancestors;

        // Get ancestors
        Person current_person = PersonDao.getPerson(id);
        assert current_person != null;
        if (current_person.fatherID != null && !current_person.fatherID.equals("") && !current_person.fatherID.equals("null")) {
            ancestors.add(getPerson(current_person.fatherID));
            if (current_person.motherID != null && !current_person.motherID.equals("") && !current_person.motherID.equals("null"))
                ancestors.add(getPerson(current_person.motherID));
            ancestors.addAll(getAncestors(current_person.fatherID));
        }
        if (current_person.motherID != null && !current_person.motherID.equals("") && !current_person.motherID.equals("null")) {
            ancestors.addAll(getAncestors(current_person.motherID));
        }

        return ancestors;
    }

    public static ArrayList<Person> getChildren(String id){
        ArrayList<Person> person_list = new ArrayList<>();
        Person p = getPerson(id);
        if (p == null || p.personID.equals("") || p.personID.equals("null"))
            return person_list;
        try {
            String sql_operation;
            if (Objects.requireNonNull(getPerson(id)).gender.equals("m"))
                sql_operation = "SELECT * FROM persons WHERE father_id = '" + id + "'";
            else
                sql_operation = "SELECT * FROM persons WHERE mother_id = '" + id + "'";
            PreparedStatement statement = DriverManager.getConnection(url).prepareStatement(sql_operation);
            ResultSet rs = statement.executeQuery();
            DriverManager.getConnection(url).close();
            while (rs.next()){
                p = new Person(rs.getString("id"), rs.getString("username"), rs.getString("first_name"),
                        rs.getString("last_name"), rs.getString("gender"), rs.getString("father_id"),
                        rs.getString("mother_id"), rs.getString("spouse_id"));
                person_list.add(p);
                person_list.addAll(getChildren(p.personID));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person_list;
    }

    public static ArrayList<Person> getAllPersons(String username){
        ArrayList<Person> person_list = new ArrayList<>();
        try {
            String sql_operation = "SELECT * FROM persons WHERE username = '" + username + "'";
            PreparedStatement statement = DriverManager.getConnection(url).prepareStatement(sql_operation);
            ResultSet rs = statement.executeQuery();
            DriverManager.getConnection(url).close();

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
