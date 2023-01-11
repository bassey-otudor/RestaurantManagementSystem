package org.restaurant;

import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

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

    // Assigning behaviour

    public void close() {
        System.exit(0);
    }

    public void minimise() {
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
