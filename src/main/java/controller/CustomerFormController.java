//package controller;
//
//import com.jfoenix.controls.JFXComboBox;
//import com.jfoenix.controls.JFXTextField;
//import dto.Customer;
//import dto.Employee;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.control.*;
//import javafx.scene.shape.Rectangle;
//import service.custom.CustomerService;
//import service.custom.EmployeeService;
//import service.custom.impl.CustomerServiceImpl;
//import service.custom.impl.EmployeeServiceImpl;
//
//import java.net.URL;
//import java.util.ResourceBundle;
//
//public class CustomerFormController implements Initializable {
//
//    @FXML
//    private Button btnAdd;
//
//    @FXML
//    private Button btnBack;
//
//    @FXML
//    private Button btnClear;
//
//    @FXML
//    private Button btnDelete;
//
//    @FXML
//    private Button btnUpdate;
//
//    @FXML
//    private JFXComboBox<String> cmbTitle;
//
//    @FXML
//    private TableColumn<?, ?> colAddress;
//
//    @FXML
//    private TableColumn<?, ?> colBDay;
//
//    @FXML
//    private TableColumn<?, ?> colContactNumber;
//
//    @FXML
//    private TableColumn<?, ?> colCustID;
//
//    @FXML
//    private TableColumn<?, ?> colNIC;
//
//    @FXML
//    private TableColumn<?, ?> colName;
//
//    @FXML
//    private TableColumn<?, ?> colPostalCode;
//
//    @FXML
//    private TableColumn<?, ?> colTitle;
//
//    @FXML
//    private DatePicker dDOB;
//
//    @FXML
//    private Rectangle empID;
//
//    @FXML
//    private TableView<Customer> tblCustomer;
//
//    @FXML
//    private JFXTextField txtAddress;
//
//    @FXML
//    private JFXTextField txtContactNumber;
//
//    @FXML
//    private JFXTextField txtCustID;
//
//    @FXML
//    private JFXTextField txtNIC;
//
//    @FXML
//    private JFXTextField txtName;
//
//    @FXML
//    private JFXTextField txtPostalCode;
//
//    @FXML
//    private JFXTextField txtSearch;
//
//    final CustomerService customerService = new CustomerServiceImpl();
//
//    @FXML
//    void btnAddOnAction(ActionEvent event) {
//        Customer customer = new Customer(
//                txtCustID.getText(),
//                // cmbTitle.getSelectionModel().getSelectedItem().toString(),
//                (cmbTitle.getSelectionModel().getSelectedItem() != null) ? cmbTitle.getSelectionModel().getSelectedItem().toString() : "",
//                txtName.getText(),
//                txtAddress.getText(),
//                dDOB.getValue().toString(),
//                txtContactNumber.getText(),
//                txtPostalCode.getText(),
//                txtNIC.getText()
//        );
//
//        System.out.println("Adding Customer: " + customer);
//
//        try {
//            boolean isAdd = employeeservice.addEmployee(employee);
//            if (isAdd){
//                new Alert(Alert.AlertType.INFORMATION,"Employee Added Successfully !").show();
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
//
//
//}
//
//    @FXML
//    void btnBackOnAction(ActionEvent event) {
//
//    }
//
//    @FXML
//    void btnClearOnAction(ActionEvent event) {
//
//    }
//
//    @FXML
//    void btnDeleteOnAction(ActionEvent event) {
//
//    }
//
//    @FXML
//    void btnUpdateOnAction(ActionEvent event) {
//
//    }
//
//    @FXML
//    void txtSearchOnAction(ActionEvent event) {
//
//    }
//
//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//
//    }
//}
