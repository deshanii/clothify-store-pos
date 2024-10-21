package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.Customer;
import dto.Employee;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.shape.Rectangle;
import service.custom.CustomerService;
import service.custom.impl.CustomerServiceImpl;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class CustomerFormController implements Initializable {

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private JFXComboBox<String> cmbTitle;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colBDay;

    @FXML
    private TableColumn<?, ?> colContactNumber;

    @FXML
    private TableColumn<?, ?> colCustID;

    @FXML
    private TableColumn<?, ?> colNIC;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPostalCode;

    @FXML
    private TableColumn<?, ?> colTitle;

    @FXML
    private DatePicker dDOB;

    @FXML
    private Rectangle empID;

    @FXML
    private TableView<Customer> tblCustomer;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtContactNumber;

    @FXML
    private JFXTextField txtCustID;

    @FXML
    private JFXTextField txtNIC;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtPostalCode;

    @FXML
    private JFXTextField txtSearch;

    final CustomerService customerService = new CustomerServiceImpl();

    @FXML
    void btnAddOnAction(ActionEvent event) {
        Customer customer = new Customer(
                txtCustID.getText(),
                // cmbTitle.getSelectionModel().getSelectedItem().toString(),
                (cmbTitle.getSelectionModel().getSelectedItem() != null) ? cmbTitle.getSelectionModel().getSelectedItem().toString() : "",
                txtName.getText(),
                txtAddress.getText(),
                dDOB.getValue().toString(),
                txtContactNumber.getText(),
                txtPostalCode.getText(),
                txtNIC.getText()
        );

        System.out.println("Adding Customer: " + customer);

        try {
            boolean isAdd = customerService.addCustomer(customer);
            if (isAdd){
                new Alert(Alert.AlertType.INFORMATION,"Customer Added Successfully !").show();
                loadTable();
            }else{
                new Alert(Alert.AlertType.ERROR,"Something went wrong !").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).show();
        }

        clearText();
    }


    public void clearText(){
        txtAddress.setText("");
        txtName.setText("");
        txtNIC.setText("");
        txtCustID.setText("");
        txtPostalCode.setText("");
        cmbTitle.setValue(null);
        txtCustID.setText("");
        txtContactNumber.setText("");
        dDOB.setValue(null);

    }


    public void loadTable(){
        tblCustomer.setItems(FXCollections.observableArrayList(customerService.getCustomer()));

    }

    public void setTextValues(Customer newValue){
        txtCustID.setText(newValue.getCustID());
        cmbTitle.getSelectionModel().select(newValue.getTitle());
        txtName.setText(newValue.getName());
        txtAddress.setText(newValue.getAddress());
        LocalDate initialDate =LocalDate.parse(newValue.getDDOB());
        dDOB.setValue(initialDate);
        txtContactNumber.setText(newValue.getContactNumber());
        txtPostalCode.setText(newValue.getPostalCode());
        txtNIC.setText(newValue.getNIC());

    }


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
            boolean delete = customerService.deleteCustomer(String.valueOf(txtCustID.getText()));
            if(delete){
                new Alert(Alert.AlertType.INFORMATION,"Customer Deleted Successfully !").show();
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
    void btnUpdateOnAction(ActionEvent event) {
        Customer customer=new Customer(
                txtCustID.getText(),
                cmbTitle.getSelectionModel().getSelectedItem().toString(),
                txtName.getText(),
                txtAddress.getText(),
                dDOB.getValue().toString(),
                txtContactNumber.getText(),
                txtPostalCode.getText(),
                txtNIC.getText()
        );

        try {
            boolean isUpdate = customerService.updateCustomer(customer);
            if(isUpdate){
                new Alert(Alert.AlertType.INFORMATION,"Customer Updated Successfully !").show();
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
            Customer customer = customerService.findCustomer(txtSearch.getText());
            if (customer != null) {
                setTextValues(customer);
            } else {
                new Alert(Alert.AlertType.ERROR, "Customer not found").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colCustID.setCellValueFactory(new PropertyValueFactory<>("custID"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colBDay.setCellValueFactory(new PropertyValueFactory<>("dDOB"));
        colContactNumber.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        colPostalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        colNIC.setCellValueFactory(new PropertyValueFactory<>("NIC"));

        cmbTitle.getItems().addAll("Mr", "Mrs", "Miss", "Master");

        loadTable();

        tblCustomer.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue)->{
            if(null != newValue){
                setTextValues(newValue);
            }
        });

    }
}
