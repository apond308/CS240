package dao;

import models.Event;
import models.Person;

import java.io.File;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EventDao {

    /**
     * Database string
     */
    private static String url = "jdbc:sqlite:" + new File("").getAbsolutePath() + "/data/family_map.db";

    /**
     * Add an event to the database
     * @param event_in event to be added
     * @return whether the operation succeeded or not
     */
    public static boolean addEvent(Event event_in){
        if (event_in == null)
            return false;
        try {
            String sql_operation = "INSERT INTO events(id,username,person_id,latitude,longitude," +
                    "country,city,type,year) values("
                    + event_in.toString() + ")";
            PreparedStatement statement = DriverManager.getConnection(url).prepareStatement(sql_operation);
            statement.executeUpdate();
            statement.close();
            DriverManager.getConnection(url).close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Get an event from the database
     * @param id event id
     * @return the event found, null if not found
     */
    public static Event getEvent(String id){
        try {
            String sql_operation = "SELECT * FROM events WHERE id = '" + id + "'";
            PreparedStatement statement = DriverManager.getConnection(url).prepareStatement(sql_operation);
            ResultSet rs = statement.executeQuery();
            DriverManager.getConnection(url).close();
            if (rs.next()){
                Event event_out = new Event(rs.getString("id"), rs.getString("username"), rs.getString("person_id"),
                        rs.getString("latitude"), rs.getString("longitude"), rs.getString("country"),
                        rs.getString("city"), rs.getString("type"), rs.getString("year"));
                statement.close();
                return event_out;
            }
            else
                return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Get all events associated with a username
     * @param username username provided
     * @return ArrayList of events
     */
    public static ArrayList<Event> getAllEvents(String username){
        try {
            String sql_operation = "SELECT * FROM events WHERE username = '" + username + "'";
            PreparedStatement statement = DriverManager.getConnection(url).prepareStatement(sql_operation);
            ResultSet rs = statement.executeQuery();
            DriverManager.getConnection(url).close();

            ArrayList<Event> event_list = new ArrayList<>();
            while (rs.next()){
                Event event_out = new Event(rs.getString("id"), rs.getString("username"), rs.getString("person_id"),
                        rs.getString("latitude"), rs.getString("longitude"), rs.getString("country"),
                        rs.getString("city"), rs.getString("type"), rs.getString("year"));
                event_list.add(event_out);
            }
            statement.close();
            return event_list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Delete events of a user from the database
     * @param username user name
     */
    public static void deleteUserEvents(String username){
        try {
            String sql_operation = "DELETE FROM events WHERE username = '" + username + "'";
            PreparedStatement statement = DriverManager.getConnection(url).prepareStatement(sql_operation);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Clear events from the database
     */
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
