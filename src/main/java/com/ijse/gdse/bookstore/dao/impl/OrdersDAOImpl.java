package com.ijse.gdse.bookstore.dao.impl;

import com.ijse.gdse.bookstore.dao.OrdersDAO;
import com.ijse.gdse.bookstore.entity.Orders;
import com.ijse.gdse.bookstore.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrdersDAOImpl implements OrdersDAO {

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select order_id from orders order by order_id desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1);
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return String.format("O%03d", newIdIndex);
        }
        return "O001";
    }

    @Override
    public boolean save(Orders entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("insert into orders values (?,?,?)",
                entity.getOrderId(),
                entity.getOrderDate(),
                entity.getOrderDate()
        );
    }

    @Override
    public ArrayList<Orders> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Orders entity) throws SQLException, ClassNotFoundException {
        return false;
    }
}
