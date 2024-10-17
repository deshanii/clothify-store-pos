package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.Employee;
import entity.EmployeeEntity;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import service.custom.EmployeeService;
import service.custom.impl.EmployeeServiceImpl;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;


public class EmployeeFormController implements Initializable {
    public AnchorPane LoadFormContent;
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
    private TableColumn<?, ?> colAccNO;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colBDay;

    @FXML
    private TableColumn<?, ?> colContactNumber;

    @FXML
    private TableColumn<?, ?> colEmpID;

    @FXML
    private TableColumn<?, ?> colNIC;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colTitle;

    @FXML
    private DatePicker dDOB;

    @FXML
    private Rectangle empID;

    @FXML
    private TableView<EmployeeEntity> tblEmployee;

    @FXML
    private JFXTextField txtAccNo;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtContactNumber;

    @FXML
    private JFXTextField txtEmpID;

    @FXML
    private JFXTextField txtNIC;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtSearch;



final EmployeeService employeeservice = new EmployeeServiceImpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colEmpID.setCellValueFactory(new PropertyValueFactory<>("empID"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colBDay.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colContactNumber.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        colAccNO.setCellValueFactory(new PropertyValueFactory<>("bankAccNo"));
        colNIC.setCellValueFactory(new PropertyValueFactory<>("nic"));

        cmbTitle.getItems().addAll("Mr", "Mrs", "Miss", "Master");

        loadTable();

        tblEmployee.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue)->{
            if(null != newValue){
                setTextValues(newValue);
            }
        });


    }

    private void setTextValues(EmployeeEntity newValue) {
        txtEmpID.setText(newValue.getEmpID());
        cmbTitle.getSelectionModel().select(newValue.getTitle());
        txtName.setText(newValue.getName());
        txtAddress.setText(newValue.getAddress());
        LocalDate initialDate =LocalDate.parse(newValue.getDob());
        dDOB.setValue(initialDate);
        txtContactNumber.setText(newValue.getContactNumber());
        txtAccNo.setText(newValue.getBankAccNo());
        txtNIC.setText(newValue.getNic());
    }

    public void loadTable(){
tblEmployee.setItems(FXCollections.observableList(employeeservice.getEmployee()));

    }

    @FXML
    void btnAddOnAction(ActionEvent event) {

        Employee employee = new Employee(
                txtEmpID.getText(),
               // cmbTitle.getSelectionModel().getSelectedItem().toString(),
                 (cmbTitle.getSelectionModel().getSelectedItem() != null) ? cmbTitle.getSelectionModel().getSelectedItem().toString() : "",
                txtName.getText(),
                txtAddress.getText(),
                dDOB.getValue().toString(),
                txtContactNumber.getText(),
                txtAccNo.getText(),
                txtNIC.getText()
        );

        System.out.println("Adding Employee: " + employee);

        try {
            boolean isAdd = employeeservice.addEmployee(employee);
            if (isAdd){
                new Alert(Alert.AlertType.INFORMATION,"Employee Added Successfully !").show();
                loadTable();
            }else{
                new Alert(Alert.AlertType.ERROR,"Something went wrong !").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).show();
        }

    }



    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        URL resource = this.getClass().getResource("view/dash_board_form.fxml");

        assert resource != null;

        Parent load = (Parent) FXMLLoader.load(resource);
        this.LoadFormContent.getChildren().clear();
        this.LoadFormContent.getChildren().add(load);
    }
    @FXML
    void btnClearOnAction(ActionEvent event) {

        txtEmpID.setText("");
        cmbTitle.setValue(null);
        txtName.setText("");
        txtAddress.setText("");
        dDOB.setValue(null);
        txtContactNumber.setText("");
        txtAccNo.setText("");
        txtNIC.setText("");

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

        try {
            boolean delete = employeeservice.deleteEmployee(String.valueOf(txtEmpID.getText()));
            if(delete){
                new Alert(Alert.AlertType.INFORMATION,"Employee Deleted Successfully !").show();
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

        Employee employee=new Employee(
                txtEmpID.getText(),
                cmbTitle.getSelectionModel().getSelectedItem().toString(),
                txtName.getText(),
                txtAddress.getText(),
                dDOB.getValue().toString(),
                txtContactNumber.getText(),
                txtAccNo.getText(),
                txtNIC.getText()
        );

        try {
            boolean isUpdate = employeeservice.updateEmployee(employee);
            if(isUpdate){
                new Alert(Alert.AlertType.INFORMATION,"Employee Updated Successfully !").show();
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

//    try {
//        Employee employee = employeeservice.findEmployee(txtEmpID.getText());
//        if (employee != null) {
//            setTextValues(employee);
//        } else {
//            System.out.println(employee);
//            new Alert(Alert.AlertType.ERROR, "Employee not found").show();
//        }
//    } catch (Exception e) {
//        e.printStackTrace();
    }

  }





