package com.ijse.gdse.bookstore.model;

import com.ijse.gdse.bookstore.bo.impl.BookBOImpl;
import com.ijse.gdse.bookstore.dao.impl.BookDAOImpl;
import com.ijse.gdse.bookstore.dto.OrderDetailsDTO;
import com.ijse.gdse.bookstore.util.CrudUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailsModel {
    private final BookDAOImpl bookDAO = new BookDAOImpl();

    public boolean saveOrderDetailsList(ArrayList<OrderDetailsDTO> orderDetailsDTOS) throws SQLException {

        for (OrderDetailsDTO orderDetailsDTO : orderDetailsDTOS) {
            boolean isOrderDetailsSaved = saveOrderDetail(orderDetailsDTO);
            if (!isOrderDetailsSaved) {
                return false;
            }

//            boolean isBookUpdated = bookDAO.reduceQty(orderDetailsDTO);
//            if (!isBookUpdated) {
//                return false;
//            }
        }
        return true;
    }

    private boolean saveOrderDetail(OrderDetailsDTO orderDetailsDTO) throws SQLException {
        return CrudUtil.execute(
                "insert into order_details values (?,?,?,?)",
                orderDetailsDTO.getOrderId(),
                orderDetailsDTO.getBookId(),
                orderDetailsDTO.getQuantity(),
                orderDetailsDTO.getPrice()
        );
    }

}
