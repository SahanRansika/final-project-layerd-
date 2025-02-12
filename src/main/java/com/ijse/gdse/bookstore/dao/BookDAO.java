package com.ijse.gdse.bookstore.dao;

import com.ijse.gdse.bookstore.dto.OrderDetailsDTO;
import com.ijse.gdse.bookstore.entity.Book;
import com.ijse.gdse.bookstore.entity.OrderDetails;
import com.ijse.gdse.bookstore.util.CrudUtil;

import java.sql.SQLException;

public interface BookDAO extends CrudDAO<Book>{
    boolean reduceQty(OrderDetails entity) throws SQLException;
}
