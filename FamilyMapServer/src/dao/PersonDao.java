package dao;

import models.Person;

import java.io.File;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PersonDao {

    private static String url = "jdbc:sqlite:" + new File("").getAbsolutePath() + "/db/family_map.db";

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
