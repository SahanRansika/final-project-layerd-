package com.ijse.gdse.bookstore.bo;

import com.ijse.gdse.bookstore.dto.BookDTO;
import com.ijse.gdse.bookstore.dto.CustomerDTO;
import com.ijse.gdse.bookstore.dto.OrderDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrdersBO extends SuperBO{
    boolean save(OrderDTO orderDTO) throws SQLException,ClassNotFoundException;
    String getNextOrderId() throws SQLException,ClassNotFoundException;
    ArrayList<CustomerDTO> getAllCustomerIds() throws SQLException,ClassNotFoundException;
    ArrayList<BookDTO> loadBookIds() throws SQLException,ClassNotFoundException;

}
