package org.restaurant;

import com.mysql.jdbc.PreparedStatement;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.layout.AnchorPane;

import java.sql.Connection;
import java.sql.ResultSet;

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
        String sql = "SELECT * FROM admin WHERE username = ? and password = ?";
        connect = database.connectDB();

    }
    // closes the program
    public void close() {
        System.exit(0);
    }
}