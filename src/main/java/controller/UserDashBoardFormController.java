package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class UserDashBoardFormController {

    @FXML
    private Button btnEmployee;

    @FXML
    private Button btnItem;

    @FXML
    private Button btnOrder;

    @FXML
    private Button btnSupplier;

    @FXML
    void btnEmployeeOnAction(ActionEvent event) {

        try {
            Stage stage = new Stage();
            stage.setScene(new Scene(
                    FXMLLoader.load(getClass().getResource("../view/employee_form.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnItemOnAction(ActionEvent event) {
        try {
            Stage stage = new Stage();
            stage.setScene(new Scene(
                    FXMLLoader.load(getClass().getResource("../view/add_items_form.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnOrderOnAction(ActionEvent event) {
        try {
            Stage stage = new Stage();
            stage.setScene(new Scene(
                    FXMLLoader.load(getClass().getResource("../view/order_details_form.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSupplierOnAction(ActionEvent event) {
        try {
            Stage stage = new Stage();
            stage.setScene(new Scene(
                    FXMLLoader.load(getClass().getResource("../view/supplier_form.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void btnCustomerOnAction(ActionEvent actionEvent) {
        try {
            Stage stage = new Stage();
            stage.setScene(new Scene(
                    FXMLLoader.load(getClass().getResource("../view/customer_form.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnOrder1OnAction(ActionEvent actionEvent) {
        System.out.println("Start");
        try {
            Stage stage = new Stage();
            stage.setScene(new Scene(
                    FXMLLoader.load(getClass().getResource("../view/order_form.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("End");
    }

}
