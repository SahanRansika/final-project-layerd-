package com.ijse.gdse.bookstore.bo.impl;

import com.ijse.gdse.bookstore.bo.OrderDetailsBO;
import com.ijse.gdse.bookstore.dao.DAOFactory;
import com.ijse.gdse.bookstore.dao.impl.BookDAOImpl;
import com.ijse.gdse.bookstore.dao.impl.OrderDetailsDAOImpl;
import com.ijse.gdse.bookstore.dto.OrderDetailsDTO;
import com.ijse.gdse.bookstore.entity.OrderDetails;

import java.sql.SQLException;

public class OrderDetailsBOImpl implements OrderDetailsBO {
    OrderDetailsDAOImpl orderDetailsDAO = (OrderDetailsDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ORDER_DETAILS);

//    @Override
//    public boolean save(OrderDetailsDTO orderDetailsDTO) throws SQLException {
//        return orderDetailsDAO.save(new OrderDetails(
//                orderDetailsDTO.getOrderId(),
//                orderDetailsDTO.getBookId(),
//                orderDetailsDTO.getQuantity(),
//                orderDetailsDTO.getPrice()
//        ));
//    }
}
