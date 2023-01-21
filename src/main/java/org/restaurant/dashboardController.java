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
import javafx.scene.control.cell.PropertyValueFactory;
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
    private TableView<category> food_tableView;

    @FXML
    private TableColumn<category,String> food_col_price;

    @FXML
    private TableColumn<category,String> food_col_productID;

    @FXML
    private TableColumn<category,String> food_col_productName;

    @FXML
    private TableColumn<category,String> food_col_status;

    @FXML
    private TableColumn<category,String> food_col_type;

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


    // Adding a food to the database
    // configuring Add button
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

            // checking if the user filled all fields
            if(food_ID.getText().isEmpty()
                    || food_name.getText().isEmpty()
                    || food_type.getSelectionModel() == null
                    || food_price.getText().isEmpty()
                    || food_status.getSelectionModel() == null) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all fields");
                alert.showAndWait();

            } else {
                String checkData = "SELECT product_ID FROM category WHERE product_ID = '" + food_ID.getText() + "'";

                connect = database.connectDB();
                statement = connect.createStatement();
                result = statement.executeQuery(checkData);

                // checking if the food already exist
                if(result.next()) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Food ID: " + food_ID.getText() + "already exists.");
                    alert.showAndWait();
                }else {
                    prepare.executeUpdate();
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added!");
                    alert.showAndWait();

                    // show the data
                    showData();
                    // clear all fields
                    clearFood();
                }
            }

        } catch (Exception e) {e.printStackTrace();}
    }

    // configuring food_update button
    public void updateFood() {
        String sql = "UPDATE category SET product_name = '" + food_name.getText() + "', type = '"
                + food_type.getSelectionModel().getSelectedItem() + "', price = '"
                + food_price.getText() + "', status = '" + food_status.getSelectionModel().getSelectedItem()
                + "' WHERE product_id = '" + food_ID.getText() + "'";

        connect = database.connectDB();
        try {
            Alert alert;

            // making sure there are no empty fields
            if(food_ID.getText().isEmpty()
                || food_name.getText().isEmpty()
                || food_type.getSelectionModel() == null
                || food_price.getText().isEmpty()
                || food_status.getSelectionModel() == null) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all fields");
                alert.showAndWait();
            }else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE Food ID" + food_ID.getText() + "?");

                Optional<ButtonType> option  = alert.showAndWait();

                if(option.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executeUpdate(sql);

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();

                    // show the data
                    showData();
                    // clear all fields
                    clearFood();

                } else {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Cancelled");
                    alert.showAndWait();
                }
            }
        } catch (Exception e) { e.printStackTrace();}
    }

    // configuring food_delete button
    public void deleteFood() {
        String sql = "DELETE FROM category WHERE product_id = '" + food_ID.getText() + "'";
        connect = database.connectDB();

        try {
            Alert alert;

            // making sure there are no empty fields
            if(food_ID.getText().isEmpty()) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all fields");
                alert.showAndWait();
            }else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to DELETE Food ID: " + food_ID.getText() + "?");

                Optional<ButtonType> option  = alert.showAndWait();

                if(option.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executeUpdate(sql);

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted!");
                    alert.showAndWait();

                    // show the data
                    showData();
                    // clear all fields
                    clearFood();

                } else {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Cancelled");
                    alert.showAndWait();
                }
            }

        }catch (Exception e) {e.printStackTrace();}
    }

    // configuring food_clear button
    public void clearFood() {
        food_ID.setText("");
        food_name.setText("");
        food_type.getSelectionModel().clearSelection();
        food_price.setText("");
        food_status.getSelectionModel().clearSelection();
    }

    // Available food/drinks

    // configuring food_type field
    private String [] status = {"Available", "Not Available"};
    public void foodStatus() {
        List<String> listStatus = new ArrayList<>();

        for(String data:status) {
            listStatus.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listStatus);
        food_status.setItems(listData);

    }

    public ObservableList<category> listData() {
        ObservableList<category> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM category";
        connect = database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            category cat;
            while(result.next()) {
                cat = new category(result.getString("product_id"), result.getString("product_name")
                        , result.getString("type"), result.getDouble("price")
                        , result.getString("status"));

                listData.add(cat);

            }
        } catch (Exception e) {e.printStackTrace();}

        return listData;
    }

    private ObservableList<category> foodList;

    // show data on the table
    public void showData() {
        foodList = listData();

        food_col_productID.setCellValueFactory(new PropertyValueFactory<>("productId"));
        food_col_productName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        food_col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        food_col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        food_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        food_tableView.setItems(foodList);
;    }


    // available selection
    // show food values in their respective fields
    public void selectFood() {
        category catData = food_tableView.getSelectionModel().getSelectedItem();
        int num = food_tableView.getSelectionModel().getSelectedIndex();

        if((num -1) < -1) {
            return;
        }

        food_ID.setText(catData.getProductId());
        food_name.setText(catData.getProductName());
        food_price.setText(String.valueOf(catData.getPrice()));

    }

    // configuring food_type button
    private String [] types = {"Meals", "Drink"};
    public void foodType() {
        List<String> listTypes = new ArrayList<>();

        for(String data:types) {
            listTypes.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listTypes);
        food_type.setItems(listData);
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

            showData();
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
        } catch (Exception e) {e.printStackTrace();}
    }
    public void displayUsername() {
        String user = data.username;
        user = user.substring(0, 1).toUpperCase() + user.substring(1);
        username.setText(user);
    }

    public void close() {System.exit(0);}

    public void minimise() {
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayUsername();
        foodStatus();
        foodType();
        showData();

    }
}
