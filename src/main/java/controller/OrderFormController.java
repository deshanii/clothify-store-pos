package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import service.custom.CustomerService;
import service.custom.ItemService;
import service.custom.OrderDetailsService;
import service.custom.OrderService;
import service.custom.impl.CustomerServiceImpl;
import service.custom.impl.ItemServiceImpl;
import service.custom.impl.OrderDetailsServiceImpl;
import service.custom.impl.OrderServiceImpl;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class OrderFormController implements Initializable {

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnPlaceOrder;

    @FXML
    private JFXComboBox<String> cmbCustomerID;

    @FXML
    private JFXComboBox<String> cmbItemCode;

    @FXML
    private TableColumn<?, ?> colCustID;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colOrderID;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblNetTotal;

    @FXML
    private Label lblTime;

    @FXML
    private Label lblTotal;

    @FXML
    private TableView<CartTM> tblOrders;

    @FXML
    private JFXTextField txtCustomerName;

    @FXML
    private JFXTextField txtDescription;

    @FXML
    private JFXTextField txtOrderID;

    @FXML
    private JFXTextField txtQty;

    @FXML
    private JFXTextField txtSearch;

    @FXML
    private JFXTextField txtStock;

    @FXML
    private JFXTextField txtUnitPrice;



    final OrderService orderService = new OrderServiceImpl();
    final OrderDetailsService orderdetailsservice = new OrderDetailsServiceImpl();
    ObservableList<CartTM> cartTMS = FXCollections.observableArrayList();
    List<Order> orderlist = new ArrayList<>();

    @FXML
    void btnAddOnAction(ActionEvent event) {
        double placetotal = Double.parseDouble(txtUnitPrice.getText())*Integer.parseInt(txtQty.getText());
        Order order = new Order(
                txtOrderID.getText(),
                cmbCustomerID.getSelectionModel().getSelectedItem().toString(),
                cmbItemCode.getSelectionModel().getSelectedItem().toString(),
                txtDescription.getText(),
                txtUnitPrice.getText(),
                txtQty.getText(),
                placetotal+""
        );
        orderlist.add(order);

        try{
            boolean isAdd = orderService.addOrder(order);
            if (isAdd){
                new Alert(Alert.AlertType.INFORMATION,"Order Added Successfully !").show();
                loadTable();
                lblTotal.setText((Double.parseDouble(lblTotal.getText())+placetotal)+"");
            }else{
                new Alert(Alert.AlertType.ERROR,"Something went wrong !").show();
            }
        }catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).show();
        }

        clearText();


        colOrderID.setCellValueFactory(new PropertyValueFactory<>("orderID"));
        colCustID.setCellValueFactory(new PropertyValueFactory<>("custID"));
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        String orderID = txtOrderID.getText();
        String custID = cmbCustomerID.getValue();
        String itemCode = cmbItemCode.getValue();
        String description = txtDescription.getText();
        Double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        Integer qty = Integer.parseInt(txtQty.getText());
        Double total = unitPrice*qty;

        if (Integer.parseInt(txtStock.getText())<qty){
            new Alert(Alert.AlertType.WARNING,"Invalid QTY").show();
        }else{
            cartTMS.add(new CartTM(orderID,custID,itemCode,description,unitPrice.toString(),qty.toString(),total.toString()));
            calcNetTotal();
        }

        tblOrders.setItems(cartTMS);

    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearText();

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        try {
            boolean delete = orderService.deleteOrder(String.valueOf(txtOrderID.getText()));
            if(delete){
                new Alert(Alert.AlertType.INFORMATION,"Order Deleted Successfully !").show();
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
        Order order=new Order(
                txtOrderID.getText(),
                cmbCustomerID.getSelectionModel().getSelectedItem().toString(),
                cmbItemCode.getSelectionModel().getSelectedItem().toString(),
                txtDescription.getText(),
                txtUnitPrice.getText(),
                txtQty.getText(),
                lblTotal.getText()
        );

        try {
            boolean isUpdate = orderService.updateOrder(order);
            if(isUpdate){
                new Alert(Alert.AlertType.INFORMATION,"Order Updated Successfully !").show();

                loadTable();
            }else {
                new Alert(Alert.AlertType.ERROR,"Something went wrong !").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {

        LocalDate orderDate = LocalDate.now();
        for (Order order : orderlist) {
        orderdetailsservice.addOrderDetails(new OrderDetails(order.getOrderID(),orderDate,order.getCustID()));
        }

    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colOrderID.setCellValueFactory(new PropertyValueFactory<>("orderID"));
        colCustID.setCellValueFactory(new PropertyValueFactory<>("custID"));
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        loadTable();
        loadDateAndTime();
        loadCustomerIds();
        loadItemCodes();


        tblOrders.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue)->{
            if(null != newValue){
                setValues( newValue);
            }
        });
    }

    private void setValues(CartTM newValue){
        txtOrderID.setText(newValue.getOrderID());
        cmbCustomerID.getSelectionModel().select(Integer.parseInt(newValue.getCustID()));
        cmbItemCode.getSelectionModel().select(Integer.parseInt(newValue.getItemCode()));
        txtDescription.setText(newValue.getDescription());
        txtUnitPrice.setText(newValue.getUnitPrice());
        txtQty.setText(newValue.getQty());


    }

    public void loadTable(){
        List<Order> orderlist = orderService.getOrder();
        List<CartTM> cart = new ArrayList<>();
        for (Order order : orderlist) {
            cart.add(new CartTM(
                    order.getOrderID(),
                    order.getCustID(),
                    order.getItemCode(),
                    order.getDescription(),
                    order.getUnitPrice(),
                    order.getQty(),
                    order.getTotal()
            ));
        }
        tblOrders.setItems(FXCollections.observableArrayList(cart));
    }

    public void clearText(){
        txtQty.setText("");
        txtDescription.setText("");
        txtUnitPrice.setText("");
        txtOrderID.setText("");
        cmbItemCode.setValue(null);
        cmbCustomerID.setValue(null);
    }

    private void loadDateAndTime(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateNow = simpleDateFormat.format(date);

        lblDate.setText(dateNow);

        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime now = LocalTime.now();
            lblTime.setText(now.getHour() + ":" + now.getMinute() + ":" + now.getSecond());
        }),
                new KeyFrame(Duration.seconds(1))
        );

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void loadCustomerIds(){
        CustomerService customerService = new CustomerServiceImpl();
        List<Customer> list = customerService.getCustomer();
        List<String> idlist = new ArrayList<>();
        for (Customer obj : list) {
            idlist.add(obj.getCustID());

        }
        cmbCustomerID.setItems(FXCollections.observableArrayList(idlist));
    }

    private void loadItemCodes(){
        ItemService itemService = new ItemServiceImpl();
        List<Item> list = itemService.getItem();
        List<String> idlist = new ArrayList<>();
        for (Item obj : list) {
            idlist.add(obj.getItemCode());

        }
        cmbItemCode.setItems(FXCollections.observableArrayList(idlist));
    }

    private void calcNetTotal() {
        Double total=0.0;
        for (CartTM cartTM: cartTMS){
            total+=Double.parseDouble(cartTM.getTotal());
        }
        lblTotal.setText(total.toString()+"/=");
    }

}



