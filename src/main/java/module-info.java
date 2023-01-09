module org.restaurant {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.restaurant to javafx.fxml;
    exports org.restaurant;
}