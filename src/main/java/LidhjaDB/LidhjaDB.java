package LidhjaDB;

import java.sql.Connection;
import java.sql.DriverManager;
public class LidhjaDB {
    public Connection databaseLink;
    public Connection getConnection(){
        String databaseName = "knk_projekti";
        String databaseUser = "root";
        String databasePassword = "Abz130203?!$";
        String url = "jdbc:mysql://localhost/" + databaseName;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
        } catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }

        return databaseLink;
    }
}
