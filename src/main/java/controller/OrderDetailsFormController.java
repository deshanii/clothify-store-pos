package controller;

import com.jfoenix.controls.JFXTextField;
import dto.Employee;
import dto.OrderDetails;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import service.custom.OrderDetailsService;
import service.custom.impl.OrderDetailsServiceImpl;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class OrderDetailsFormController implements Initializable {

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnEdit;

    @FXML
    private TableColumn<?, ?> colCustomerID;

    @FXML
    private TableColumn<?, ?> colOrderID;


    @FXML
    private TableColumn<?, ?> colOrerDate;

    @FXML
    private TableView<OrderDetails> tblOrderDetails;

    @FXML
    private JFXTextField txtCustID;

    @FXML
    private JFXTextField txtOrderID;

    @FXML
    private DatePicker dOrderDate;

    @FXML
    private JFXTextField txtSearch;

    final OrderDetailsService orderDetailsService = new OrderDetailsServiceImpl();

    public void loadTable(){
        tblOrderDetails.setItems(FXCollections.observableArrayList(orderDetailsService.getOrderDetails()));

    }

    public void setValues(OrderDetails newValue){
        txtOrderID.setText(newValue.getOrderID());
        LocalDate initialDate = newValue.getOrderDate();
        dOrderDate.setValue(initialDate);
        txtCustID.setText(newValue.getCustID());

    }

    public void clearText(){
        dOrderDate.setValue(null);
        txtOrderID.setText("");
        txtCustID.setText("");
    }

//    @FXML
//    void btnAddOnAction(ActionEvent event) {
//
//        OrderDetails orderDetails = new OrderDetails(
//                txtOrderID.getText(),
//                dOrderDate.getValue().toString(),
//                txtCustID.getText()
//        );
//
//        System.out.println("Adding Order Details: " + orderDetails);
//
//        try {
//            boolean isAdd = orderDetailsService.addOrderDetails(orderDetails);
//            if (isAdd){
//                new Alert(Alert.AlertType.INFORMATION,"Order Details Added Successfully !").show();
//                loadTable();
//            }else{
//                new Alert(Alert.AlertType.ERROR,"Something went wrong !").show();
//            }
//        } catch (Exception e) {
//            new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).show();
//        }
//
//        clearText();
//    }



    @FXML
    void btnBackOnAction(ActionEvent event) {

    }

    @FXML
    void btnClearOnAction(ActionEvent event) {

        clearText();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

        try {
            boolean delete = orderDetailsService.deleteOrderDetails(String.valueOf(txtOrderID.getText()));
            if(delete){
                new Alert(Alert.AlertType.INFORMATION,"Order Details Deleted Successfully !").show();
                loadTable();
                btnClearOnAction( event);
            }else {
                new Alert(Alert.AlertType.ERROR,"Something went wrong !").show();
            }

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void btnEditOnAction(ActionEvent event) {

        OrderDetails orderDetails = new OrderDetails(
                txtOrderID.getText(),
                dOrderDate.getValue(),
                txtCustID.getText()
        );

        try {
            boolean isUpdate = orderDetailsService.updateOrderDetails(orderDetails);
            if(isUpdate){
                new Alert(Alert.AlertType.INFORMATION,"Order Details Updated Successfully !").show();
                loadTable();
            }else {
                new Alert(Alert.AlertType.ERROR,"Something went wrong !").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }



    @FXML
    void txtSearchOnAction(ActionEvent event) {
        try {
            OrderDetails orderDetails = orderDetailsService.findOrderDetails(txtSearch.getText());
            if (orderDetails != null) {
                setValues(orderDetails);
            } else {
                new Alert(Alert.AlertType.ERROR, "Order Details not found").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colOrderID.setCellValueFactory(new PropertyValueFactory<>("orderID"));
        colOrerDate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        colCustomerID.setCellValueFactory(new PropertyValueFactory<>("custID"));

        loadTable();

        tblOrderDetails.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue)->{
            if(null != newValue){
                setValues(newValue);
            }
        });

    }
}
