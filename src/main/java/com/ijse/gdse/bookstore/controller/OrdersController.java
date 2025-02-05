package com.ijse.gdse.bookstore.controller;

import com.ijse.gdse.bookstore.bo.BOFactory;
import com.ijse.gdse.bookstore.bo.impl.BookBOImpl;
import com.ijse.gdse.bookstore.bo.impl.CustomerBOImpl;
import com.ijse.gdse.bookstore.bo.impl.OrdersBOImpl;
import com.ijse.gdse.bookstore.dto.BookDTO;
import com.ijse.gdse.bookstore.dto.CustomerDTO;
import com.ijse.gdse.bookstore.dto.OrderDTO;
import com.ijse.gdse.bookstore.dto.OrderDetailsDTO;
import com.ijse.gdse.bookstore.dto.tm.CartTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class OrdersController implements Initializable {

    @FXML
    private AnchorPane ancOrder;

    @FXML
    private AnchorPane ancPrint;


    @FXML
    private ComboBox<String> cmbBookId;

    @FXML
    private ComboBox<String> cmbCustomerId;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<CartTM, String> colBookId;

    @FXML
    private TableColumn<CartTM, String> colName;

    @FXML
    private TableColumn<CartTM, String> colPrice;

    @FXML
    private TableColumn<CartTM, String> colQuantity;

    @FXML
    private TableColumn<CartTM, String> colTotal;

    @FXML
    private Label lblBookName;

    @FXML
    private Label lblBookPrice;

    @FXML
    private Label lblBookQty;

    @FXML
    private Label lblCustomerName;

    @FXML
    private Label lblOrderId;

    @FXML
    private Label orderDate;

    @FXML
    private TableView<CartTM> tblCart;

    @FXML
    private TextField txtAddToCartQty;

    OrdersBOImpl ordersBO = (OrdersBOImpl) BOFactory.getInstance().getBO(BOFactory.BOType.ORDERS);
    CustomerBOImpl customerBO = (CustomerBOImpl) BOFactory.getInstance().getBO(BOFactory.BOType.CUSTOMER);
    BookBOImpl bookBO = (BookBOImpl) BOFactory.getInstance().getBO(BOFactory.BOType.BOOK);


    private final ObservableList<CartTM> cartTMS = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)  {
        setCellValues();


        try {
            refreshPage();
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Fail to load data..!").show();
        }
    }
    private void setCellValues() {

        colBookId.setCellValueFactory(new PropertyValueFactory<>("BookId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("BookName"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("cartQuantity"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("removeBtn"));


        tblCart.setItems(cartTMS);
    }

    private void refreshPage() throws SQLException,ClassNotFoundException {
        lblOrderId.setText(ordersBO.getNextOrderId());

        orderDate.setText(LocalDate.now().toString());

        loadCustomerIds();
        loadBookIds();

        cmbCustomerId.getSelectionModel().clearSelection();
        cmbBookId.getSelectionModel().clearSelection();
        lblBookName.setText("");
        lblBookQty.setText("");
        lblBookPrice.setText("");
        txtAddToCartQty.setText("");
        lblCustomerName.setText("");
        cartTMS.clear();
        tblCart.refresh();
    }

    private void loadBookIds() throws SQLException,ClassNotFoundException{
           ArrayList<BookDTO> bookDTOArrayList = ordersBO.loadBookIds();
           for (BookDTO b : bookDTOArrayList){
               cmbBookId.getItems().add(b.getColID());
           }
    }

    private void loadCustomerIds() throws SQLException,ClassNotFoundException{
            ArrayList<CustomerDTO> customerDTOArrayList = ordersBO.getAllCustomerIds();
            for (CustomerDTO c : customerDTOArrayList){
                cmbCustomerId.getItems().add(c.getColID());
            }
    }
    @FXML
    void btnAddToCartOnAction(ActionEvent event) {
        String selectedBookId = cmbBookId.getValue();


        if (selectedBookId == null) {
            new Alert(Alert.AlertType.ERROR, "Please select book..!").show();
            return;
        }

        String cartQtyString = txtAddToCartQty.getText();

        String qtyPattern = "^[0-9]+$";

        if (!cartQtyString.matches(qtyPattern)){
            new Alert(Alert.AlertType.ERROR, "Please enter valid quantity..!").show();
            return;
        }

        String bookName = lblBookName.getText();
        int cartQty = Integer.parseInt(cartQtyString);
        int qtyOnHand = Integer.parseInt(lblBookQty.getText());


        if (qtyOnHand < cartQty) {
            new Alert(Alert.AlertType.ERROR, "Not enough book..!").show();
            return;
        }


        txtAddToCartQty.setText("");

        double unitPrice = Double.parseDouble(lblBookPrice.getText());
        double total = unitPrice * cartQty;


        for (CartTM cartTM : cartTMS) {


            if (cartTM.getBookId().equals(selectedBookId)) {

                int newQty = cartTM.getCartQuantity() + cartQty;
                cartTM.setCartQuantity(newQty);
                cartTM.setTotal(unitPrice * newQty);


                tblCart.refresh();
                return;
            }
        }


        Button btn = new Button("Remove");

        CartTM newCartTM = new CartTM(
                selectedBookId,
                bookName,
                cartQty,
                unitPrice,
                total,
                btn
        );

        btn.setOnAction(actionEvent -> {

            cartTMS.remove(newCartTM);

            tblCart.refresh();
        });

        cartTMS.add(newCartTM);
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) throws SQLException,ClassNotFoundException{
        if (tblCart.getItems().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please add books to cart..!").show();
            return;
        }
        if (cmbCustomerId.getSelectionModel().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please select customer for place order..!").show();
            return;
        }

        String orderId = lblOrderId.getText();
        java.sql.Date dateOfOrder = Date.valueOf(orderDate.getText());
        String customerId = cmbCustomerId.getValue();

        ArrayList<OrderDetailsDTO> orderDetailsDTOS = new ArrayList<>();

        for (CartTM cartTM : cartTMS) {

            OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO(
                    orderId,
                    cartTM.getBookId(),
                    cartTM.getCartQuantity(),
                    cartTM.getUnitPrice()
            );

            orderDetailsDTOS.add(orderDetailsDTO);
        }

        OrderDTO orderDTO = new OrderDTO(
                orderId,
                customerId,
                dateOfOrder,
                orderDetailsDTOS
        );

        boolean isSaved = ordersBO.save(orderDTO);

        if (isSaved) {
            new Alert(Alert.AlertType.INFORMATION, "Order saved..!").show();


            refreshPage();
        } else {
            new Alert(Alert.AlertType.ERROR, "Order fail..!").show();
        }
    }

    @FXML
    void btnResetOnAction(ActionEvent event) throws SQLException,ClassNotFoundException{
        refreshPage();
    }

    @FXML
    void cmbCustomerOnAction(ActionEvent event) throws SQLException {
        String selectedCustomerId = cmbCustomerId.getSelectionModel().getSelectedItem();
        System.out.println("Selected Customer id" + selectedCustomerId);
        CustomerDTO customerDTO = customerBO.findById(selectedCustomerId);

        // If customer found (customerDTO not null)
        if (customerDTO != null) {

            // FIll customer related labels
            lblCustomerName.setText(customerDTO.getColName());
            System.out.println(customerDTO.getColName());
        }else {
            System.out.println("Customer Not Found");
        }
    }


    @FXML
    void cmbBookOnAction(ActionEvent event) throws SQLException{
        String selectedBookId = cmbBookId.getSelectionModel().getSelectedItem();
        BookDTO bookDTO = bookBO.findById(selectedBookId);

        if (bookDTO != null) {
            lblBookName.setText(bookDTO.getColTitle());
            lblBookQty.setText(String.valueOf(bookDTO.getColQty()));
            lblBookPrice.setText(String.valueOf(bookDTO.getColPrice()));
        }
    }

    public void btnPaymentOnAction(ActionEvent event) throws SQLException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Payment.fxml"));
            Parent load = loader.load();

            PaymentController paymentController = new PaymentController();

            Stage stage = new Stage();
            stage.setScene(new Scene(load));
            stage.setTitle("Payment");

            stage.showAndWait();
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Fail to load ui..!");
            e.printStackTrace();
        }
    }
}
