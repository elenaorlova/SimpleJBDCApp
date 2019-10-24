package connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class ConnectionFactory {
    public static Connection getConnection(String file_path) {
        Properties properties = new Properties();
        String dbUser = null;
        String dbPassword = null;
        String dbUrl = null;

        try {
            properties.load(new FileInputStream(file_path));
            dbUser = properties.getProperty("user");
            dbPassword = properties.getProperty("password");
            dbUrl = properties.getProperty("dburl");
            print("User " + dbUser + " is connecting to " + dbUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (dbUrl == null || dbUser == null || dbPassword == null) {
            print("Check properties file. Can't create database connection");
            return null;
        }

        print("Creating database connection...");
        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            print("Connection successfully created");
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database", e);
        }
    }

    private static void print(String str) {
        System.out.println(str);
    }
}
