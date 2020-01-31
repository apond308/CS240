package dao;

import models.User;

import java.io.File;
import java.sql.*;

public class UserDao {

    private static String url = "jdbc:sqlite:" + new File("").getAbsolutePath() + "/db/family_map.db";

    public static boolean addUser(User user) {
        try {
            String sql_operation = "INSERT INTO users(id,username,password,email,first_name,last_name,gender) values("
                    + user.toString() + ")";
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

    public static User getUser(String username){
        try {
            String sql_operation = "SELECT * FROM users WHERE username = '" + username + "'";
            PreparedStatement statement = DriverManager.getConnection(url).prepareStatement(sql_operation);
            ResultSet rs = statement.executeQuery();
            DriverManager.getConnection(url).close();
            if (rs.next()){
                User new_user = new User(rs.getString("id"), rs.getString("username"), rs.getString("password"),
                        rs.getString("email"), rs.getString("first_name"), rs.getString("last_name"),
                        rs.getString("gender"));
                statement.close();
                return new_user;
            }
            else
                return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void clear(){
        try {
            String sql_operation = "DELETE FROM users";
            PreparedStatement statement = DriverManager.getConnection(url).prepareStatement(sql_operation);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
