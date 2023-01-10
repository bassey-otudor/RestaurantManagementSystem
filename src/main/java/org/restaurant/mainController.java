package org.restaurant;

import com.mysql.jdbc.PreparedStatement;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Objects;

public class mainController {


    @FXML
    private Button close;

    @FXML
    private JFXButton loginbtn;

    @FXML
    private AnchorPane main_form;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXTextField username;

    // Database tools
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    // login button action
    public void login () {

        // SQL string to get select all users in the database
        String sql = "SELECT * FROM admin WHERE username = ? and password = ?";
        connect = database.connectDB();

        //  Prevent the application from crashing if error occurs
        try {
            prepare = (PreparedStatement) connect.prepareStatement(sql);
            prepare.setString(1, username.getText());
            prepare.setString(2, password.getText());

            result = prepare.executeQuery();

            Alert alert;

            // Prompt an alert if no username or password is entered
            if(username.getText().isEmpty() || password.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all fields");
                alert.showAndWait();

            } else {

                // Successful login
                if(result.next()) {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Login successful");
                    alert.showAndWait();

                    // Switch to the next scene - the dashboard
                    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("dashboard.fxml")));
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();


                }else {

                    // Prompt if wrong username or password is entered
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Wrong username or password");
                    alert.showAndWait();

                }
            }

        }catch (Exception e) {
            e.printStackTrace();
        }

    }
    // closes the program
    public void close() {
        System.exit(0);
    }
}