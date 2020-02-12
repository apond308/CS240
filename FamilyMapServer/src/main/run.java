package main;

import server.FamilyMapServer;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Run the server
 */
public class run {
    public static void main(String[] args) throws IOException {
        FamilyMapServer family_server = new FamilyMapServer();
        family_server.run(8080);
    }
}
