package controller;

import com.jfoenix.controls.JFXTextField;
import dto.Item;
import dto.Supplier;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.custom.ItemService;
import service.custom.impl.ItemServiceImpl;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddItemsFormController implements Initializable {

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
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colPackSize;

    @FXML
    private TableColumn<?, ?> colQtyOnHandsss;

    @FXML
    private TableColumn<?, ?> colSupID;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private TableView<Item> tblAddItems;

    @FXML
    private JFXTextField txtDescription;

    @FXML
    private JFXTextField txtItemCode;

    @FXML
    private JFXTextField txtPackSize;

    @FXML
    private JFXTextField txtQtyOnHand;

    @FXML
    private JFXTextField txtSearch;

    @FXML
    private JFXTextField txtSupID;

    @FXML
    private JFXTextField txtUnitPrice;


    final ItemService itemService = new ItemServiceImpl();


    @FXML
    void btnAddOnAction(ActionEvent event) {
        Item item = new Item(
                txtItemCode.getText(),
                txtSupID.getText(),
                txtDescription.getText(),
                txtPackSize.getText(),
                txtUnitPrice.getText(),
                txtQtyOnHand.getText()

        );

        System.out.println("Adding Item: " + item);

        try {
            boolean isAdd = itemService.addItem(item);
            if (isAdd){
                new Alert(Alert.AlertType.INFORMATION,"Item Added Successfully !").show();
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
        txtItemCode.setText("");
        txtDescription.setText("");
        txtSupID.setText("");
        txtUnitPrice.setText("");
        txtPackSize.setText("");
        txtQtyOnHand.setText("");
        txtSearch.setText("");
    }

    @FXML
    void btnBackOnAction(ActionEvent event) {
        try {
            System.out.println("hi");
            Stage stage = new Stage();
            stage.setScene(new Scene(
                    FXMLLoader.load(getClass().getResource("../view/dash_board_form.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    @FXML
    void btnClearOnAction(ActionEvent event) {

        clearText();
    }


    @FXML
    void btnDeleteOnAction(ActionEvent event) {

        try {
            boolean delete = itemService.deleteItem(String.valueOf(txtItemCode.getText()));
            if(delete){
                new Alert(Alert.AlertType.INFORMATION,"Item Deleted Successfully !").show();
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


    @FXML
    void txtSearchOnAction(ActionEvent event) {

        try {
            Item item = itemService.findItem(txtSearch.getText());
            if (item != null) {
                setTextValues(item);
            } else {
                new Alert(Alert.AlertType.ERROR, "Item not found").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colSupID.setCellValueFactory(new PropertyValueFactory<>("supplierID"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colPackSize.setCellValueFactory(new PropertyValueFactory<>("packSize"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitePrice"));
        colQtyOnHandsss.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));


        tblAddItems.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue)->{
            if(null != newValue){
                setTextValues(newValue);
            }
        });

        loadTable();

    }


    public void setTextValues(Item newValue){
        txtItemCode.setText(newValue.getItemCode());
        txtSupID.setText(newValue.getSupplierID());
        txtDescription.setText(newValue.getDescription());
        txtPackSize.setText(newValue.getPackSize());
        txtUnitPrice.setText(newValue.getUnitPrice());
        txtQtyOnHand.setText(newValue.getQtyOnHand());
    }


    public void loadTable(){
        tblAddItems.setItems(FXCollections.observableArrayList(itemService.getItem()));

    }
}
