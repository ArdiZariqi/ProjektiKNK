package service;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionUtil {
    public static Connection getConnection() {
        String databaseName = "knk_projekti";
        String databaseUser = "";
        String databasePassword = "";
        String url = "jdbc:mysql://localhost/" + databaseName;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, databaseUser, databasePassword);
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return null;
    }
}