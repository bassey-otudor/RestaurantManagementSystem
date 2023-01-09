module org.restaurant {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;
    requires java.sql;
    requires mysql.connector.java;


    opens org.restaurant to javafx.fxml;
    exports org.restaurant;
}