package org.restaurant;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class dashboardController implements Initializable {

    @FXML
    private AnchorPane main_form;
    @FXML
    private Label dashbaord_NOC;

    @FXML
    private AnchorPane dashboard_IChart;

    @FXML
    private BarChart<?, ?> dashboard_NOCChart;

    @FXML
    private Label dashboard_TI;

    @FXML
    private JFXButton dashboard_btn;

    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private Label dashboardd_TIncome;

    @FXML
    private JFXTextField food_ID;

    @FXML
    private JFXButton food_add;

    @FXML
    private JFXButton food_btn;

    @FXML
    private JFXButton food_clear;

    @FXML
    private TableColumn<?, ?> food_col_price;

    @FXML
    private TableColumn<?, ?> food_col_productID;

    @FXML
    private TableColumn<?, ?> food_col_productName;

    @FXML
    private TableColumn<?, ?> food_col_status;

    @FXML
    private TableColumn<?, ?> food_col_type;

    @FXML
    private JFXButton food_delete;

    @FXML
    private JFXTextField food_name;

    @FXML
    private JFXTextField food_price;

    @FXML
    private JFXTextField food_search;

    @FXML
    private JFXComboBox<?> food_status;

    @FXML
    private JFXComboBox<?> food_type;

    @FXML
    private JFXButton food_update;

    @FXML
    private JFXButton logout;

    @FXML
    private JFXButton order_addBtn;

    @FXML
    private JFXTextField order_amount;

    @FXML
    private AnchorPane order_form;

    @FXML
    private JFXButton order_payBtn;

    @FXML
    private JFXComboBox<?> order_productID;

    @FXML
    private JFXComboBox<?> order_productName;

    @FXML
    private Spinner<?> order_quantity;

    @FXML
    private JFXButton order_receiptBtn;

    @FXML
    private TableView<?> order_tableView;

    @FXML
    private Label order_total;

    @FXML
    private JFXButton orders_btn;

    @FXML
    private AnchorPane product_form;

    @FXML
    private Label username;

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    public void addFood() {
        String sql = "INSERT INTO category (product_id, product_name, type, price, status)" + "VALUES (?,?,?,?,?)";

        connect = database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, food_ID.getText());
            prepare.setString(2, food_name.getText());
            prepare.setString(3, (String)food_type.getSelectionModel().getSelectedItem());
            prepare.setString(4, food_price.getText());
            prepare.setString(5, (String)food_status.getSelectionModel().getSelectedItem());

            Alert alert;

            if(food_ID.getText().isEmpty()
                    || food_name.getText().isEmpty()
                    || food_type.getSelectionModel() == null
                    || food_price.getText().isEmpty() || food_status.getSelectionModel() == null) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all fields");
                alert.showAndWait();

            } else {

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    // Available food/drinks
    private String [] status = {"Available", "Not Available"};
    private String [] categories = {"Meals", "Drink"};

    public void foodCategories() {
        List<String> listCategories = new ArrayList<>();

        for(String data:categories) {
            listCategories.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listCategories);
        food_type.setItems(listData);
    }

    public void foodStatus() {
        List<String> listStatus = new ArrayList<>();

        for(String data:status) {
            listStatus.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listStatus);
        food_status.setItems(listData);

    }

    // switch forms

    public void switchForm(ActionEvent event) {
        if(event.getSource() == dashboard_btn) {
            dashboard_form.setVisible(true);
            product_form.setVisible(false);
            order_form.setVisible(false);
        } else if(event.getSource() == food_btn) {
            dashboard_form.setVisible(false);
            product_form.setVisible(true);
            order_form.setVisible(false);
        } else if(event.getSource() == orders_btn) {
            dashboard_form.setVisible(false);
            product_form.setVisible(false);
            order_form.setVisible(true);
        }
    }


    // Stage coordinates
    private double x = 0;
    private double y = 0;

    // Assigning behaviour

    public void logout() {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to logout?");
            Optional<ButtonType> option = alert.showAndWait();

            if(option.get().equals(ButtonType.OK)) {

                // Logout
                logout.getScene().getWindow().hide();
                // Link the login form
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main-view.fxml")));
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                root.setOnMousePressed((MouseEvent event) -> {
                    x = event.getSceneX();
                    y = event.getSceneY();
                });

                root.setOnMouseDragged((MouseEvent event) -> {
                    stage.setX(event.getSceneX() - x);
                    stage.setY(event.getSceneY() - y);

                    stage.setOpacity(0.8f);
                });

                root.setOnMouseReleased((MouseEvent event) -> {
                    stage.setOpacity(1);
                });
                stage.initStyle(StageStyle.TRANSPARENT);




                stage.setScene(scene);
                stage.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void displayUsername() {
        String user = data.username;
        user = user.substring(0, 1).toUpperCase() + user.substring(1);
        username.setText(user);
    }

    public void close() {
        System.exit(0);
    }

    public void minimise() {
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayUsername();
        foodStatus();
        foodCategories();

    }
}
