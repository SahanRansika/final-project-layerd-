package com.ijse.gdse.bookstore.bo;

import com.ijse.gdse.bookstore.dto.OrderDetailsDTO;

import java.sql.SQLException;

public interface OrderDetailsBO extends SuperBO{
    boolean reduceQty(OrderDetailsDTO orderDetailsDTO);
    boolean save(OrderDetailsDTO orderDetailsDTO) throws SQLException,ClassNotFoundException;
}
