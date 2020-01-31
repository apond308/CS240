package dao;

import java.io.File;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EventDao {

    private static String url = "jdbc:sqlite:" + new File("").getAbsolutePath() + "/db/family_map.db";

    public static void clear(){
        try {
            String sql_operation = "DELETE FROM events";
            PreparedStatement statement = DriverManager.getConnection(url).prepareStatement(sql_operation);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
