package org.restaurant;
import java.sql.Connection;
import java.sql.DriverManager;

public class database {
    public static Connection connectDB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            // Connect to the database
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/restaurant", "root", "");
            return connect;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
