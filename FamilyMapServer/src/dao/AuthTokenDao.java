package dao;

import models.User;

import java.io.File;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthTokenDao {

    private static String url = "jdbc:sqlite:" + new File("").getAbsolutePath() + "/data/family_map.db";

    public static String getUserFromToken(String token){
        try {
            String sql_operation = "SELECT * FROM auth WHERE token = '" + token + "'";
            PreparedStatement statement = DriverManager.getConnection(url).prepareStatement(sql_operation);
            ResultSet rs = statement.executeQuery();
            DriverManager.getConnection(url).close();
            if (rs.next()){
                statement.close();
                return rs.getString("username");
            }
            else
                return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean updateToken(String username, String token){
        try {
            String sql_operation = "DELETE FROM auth\nWHERE username = '" + username + "'";
            PreparedStatement statement = DriverManager.getConnection(url).prepareStatement(sql_operation);
            statement.executeUpdate();
            sql_operation = "INSERT INTO auth(username,token) VALUES('"+username+"','"+token+"')";
            statement = DriverManager.getConnection(url).prepareStatement(sql_operation);
            statement.executeUpdate();
            statement.close();
            DriverManager.getConnection(url).close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void clear(){
        try {
            String sql_operation = "DELETE FROM auth";
            PreparedStatement statement = DriverManager.getConnection(url).prepareStatement(sql_operation);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
