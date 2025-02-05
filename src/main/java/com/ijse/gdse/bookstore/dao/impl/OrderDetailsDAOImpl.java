package com.ijse.gdse.bookstore.dao.impl;

import com.ijse.gdse.bookstore.dao.OrderDetailsDAO;
import com.ijse.gdse.bookstore.dto.OrderDetailsDTO;
import com.ijse.gdse.bookstore.entity.OrderDetails;
import com.ijse.gdse.bookstore.util.CrudUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailsDAOImpl implements OrderDetailsDAO {
    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<OrderDetails> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(OrderDetails entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(OrderDetails entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }
    public boolean reduceQty(OrderDetailsDTO orderDetailsDTO){
        return CrudUtil.execute(
                "update book set qty = qty - ? where book_id = ?",
                orderDetailsDTO.getQuantity(),
                orderDetailsDTO.getBookId()
        );
    }
}
