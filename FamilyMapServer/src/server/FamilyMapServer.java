package server;

import com.sun.net.httpserver.*;
import handlers.*;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class FamilyMapServer {

    /**
     * maximum connection queue size
     */
    private static final int MAX_PENDING_CONNECTIONS = 12;

    /**
     * Run the server
     * @param port port number to run the server on
     * @throws IOException if can't start server
     */
    public void run(int port) throws IOException {
        InetSocketAddress serverAddress = new InetSocketAddress(port);
        HttpServer server = HttpServer.create(serverAddress, 10);
        createHandlers(server);
        initializeTables();
        server.start();
        System.out.println("FamilyMapServer listening on port " + port);
    }

    /**
     * Create all of the HTTP handlers
     * @param server server you create them for
     */
    private void createHandlers(HttpServer server){
        server.createContext("/", new DefaultHandler());
        server.createContext("/user/register", new RegisterHandler());
        server.createContext("/user/login", new LoginHandler());
        server.createContext("/clear", new ClearHandler());
        server.createContext("/fill", new FillHandler());
        server.createContext("/load", new LoadHandler());
        server.createContext("/person", new PersonHandler());
        server.createContext("/event", new EventHandler());
    }

    /**
     * Init the database tables
     */
    private void initializeTables() {
        // SQLite connection string
        String url = "jdbc:sqlite:"  + new File("").getAbsolutePath() + "/data/family_map.db";

        // SQL statement for creating user table
        String user_sql = "CREATE TABLE IF NOT EXISTS users(\n"
                + "    person_id TEXT,\n"
                + "    username TEXT,\n"
                + "    password TEXT,\n"
                + "    email TEXT,\n"
                + "    first_name TEXT,\n"
                + "    last_name TEXT,\n"
                + "    gender TEXT\n"
                + ");";

        // SQL statement for creating Person table
        String person_sql = "CREATE TABLE IF NOT EXISTS persons(\n"
                + "    id TEXT NULL,\n"
                + "    username TEXT NULL,\n"
                + "    first_name TEXT NULL,\n"
                + "    last_name TEXT NULL,\n"
                + "    gender TEXT NULL,\n"
                + "    father_id TEXT NULL,\n"
                + "    mother_id TEXT NULL,\n"
                + "    spouse_id TEXT NULL\n"
                + ");";

        // SQL statement for creating Event table
        String event_sql = "CREATE TABLE IF NOT EXISTS events(\n"
                + "    id TEXT,\n"
                + "    username TEXT,\n"
                + "    person_id TEXT,\n"
                + "    latitude TEXT,\n"
                + "    longitude TEXT,\n"
                + "    country TEXT,\n"
                + "    city TEXT,\n"
                + "    type TEXT,\n"
                + "    year TEXT\n"
                + ");";

        // SQL statement for creating Auth table
        String auth_sql = "CREATE TABLE IF NOT EXISTS auth(\n"
                + "    username TEXT,\n"
                + "    token TEXT\n"
                + ");";

        try {
            Connection conn = DriverManager.getConnection(url);
            Statement user_statement = conn.createStatement();
            Statement person_statement = conn.createStatement();
            Statement event_statement = conn.createStatement();
            Statement auth_statement = conn.createStatement();

            // create new tables
            user_statement.execute(user_sql);
            person_statement.execute(person_sql);
            event_statement.execute(event_sql);
            auth_statement.execute(auth_sql);

            conn.close();
            user_statement.close();
            person_statement.close();
            event_statement.close();
            auth_statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
