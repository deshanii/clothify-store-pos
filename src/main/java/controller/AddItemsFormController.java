package controller;

import com.jfoenix.controls.JFXTextField;
import dao.DaoFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import util.DaoType;

import java.net.URL;

public class AddItemsFormController {
    public AnchorPane LoadFormContent;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnClear;

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
    private TableView<?> tblAddItems;

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


//    ItemDao itemDao = new DaoFactory().getDaoType(DaoType.ITEM);




    @FXML
    void btnAddOnAction(ActionEvent event) {

    }

    @FXML
    void btnBackOnAction(ActionEvent event) {
        URL resource = this.getClass().getResource("view/dash_board_form.fxml");
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

}
