package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.Supplier;
import entity.SupplierEntity;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import service.custom.SupplierService;
import service.custom.impl.SupplierServiceImpl;

import java.net.URL;
import java.util.ResourceBundle;

public class SupplierFormController implements Initializable {
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
    private Button btnEdit;

    @FXML
    private JFXComboBox<String> cmbTitle;

    @FXML
    private TableColumn<?, ?> colCompanyName;

    @FXML
    private TableColumn<?, ?> colContactNumber;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colSupID;

    @FXML
    private TableColumn<?, ?> colTitle;

    @FXML
    private TableView<SupplierEntity> tblSuppliers;

    @FXML
    private JFXTextField txtCompanyName;

    @FXML
    private JFXTextField txtContactNumber;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtSearch;

    @FXML
    private JFXTextField txtSupID;

    final SupplierService supplierService = new SupplierServiceImpl();

    @FXML
    void btnAddOnAction(ActionEvent event) {

        Supplier supplier = new Supplier(
                txtSupID.getText(),
                cmbTitle.getSelectionModel().getSelectedItem().toString(),
                txtName.getText(),
                txtContactNumber.getText(),
                txtCompanyName.getText(),
                txtEmail.getText()
        );

        try{
            boolean isAdd = supplierService.addSupplier(supplier);
            if (isAdd){
                new Alert(Alert.AlertType.INFORMATION,"Supplier Added Successfully !").show();
                loadTable();
            }else{
                new Alert(Alert.AlertType.ERROR,"Something went wrong !").show();
            }
        }catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).show();
        }

    }

    @FXML
    void btnBackOnAction(ActionEvent event) {

//        URL resource = this.getClass().getResource("view/dash_board_form.fxml");
//
//        assert resource != null;
//
//
//        Parent load = (Parent) FXMLLoader.load(resource);
//        this.LoadFormContent.getChildren().clear();
//        this.LoadFormContent.getChildren().add(load);
    }


    @FXML
    void btnClearOnAction(ActionEvent event) {

        txtSupID.setText("");
        txtEmail.setText("");
        txtSupID.setText("");
        cmbTitle.setValue(null);
        txtContactNumber.setText("");
        txtCompanyName.setText("");
        txtName.setText("");

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

        try {
            boolean delete = supplierService.deleteSupplier(String.valueOf(txtSupID.getText()));
            if(delete){
                new Alert(Alert.AlertType.INFORMATION,"Supplier Deleted Successfully !").show();
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

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       colSupID.setCellValueFactory(new PropertyValueFactory<>("supplierID"));
       colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
       colName.setCellValueFactory(new PropertyValueFactory<>("name"));
       colContactNumber.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
       colCompanyName.setCellValueFactory(new PropertyValueFactory<>("companyName"));
       colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        cmbTitle.getItems().addAll("Mr", "Mrs", "Miss", "Master");

        loadTable();

        tblSuppliers.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue)->{
            if(null != newValue){
                setValues(newValue);
            }
        });
    }

    private void setValues(SupplierEntity newValue){
        txtSupID.setText(newValue.getSupplierID());
        cmbTitle.getSelectionModel().select(newValue.getTitle());
        txtName.setText(newValue.getName());
        txtContactNumber.setText(newValue.getContactNumber());
        txtCompanyName.setText(newValue.getCompanyName());
        txtEmail.setText(newValue.getEmail());
    }

    public void loadTable(){
        tblSuppliers.setItems(FXCollections.observableList(supplierService.getSupplier()));
    }
}
