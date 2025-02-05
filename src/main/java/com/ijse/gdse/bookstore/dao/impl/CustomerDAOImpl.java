package com.ijse.gdse.bookstore.dao.impl;

import com.ijse.gdse.bookstore.dao.CustomerDAO;
import com.ijse.gdse.bookstore.entity.Customer;
import com.ijse.gdse.bookstore.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM customer");
        ArrayList<Customer> customerArrayList = new ArrayList<>();

        while (rst.next()) {
            Customer customer = new Customer(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            );
            customerArrayList.add(customer);
        }
        return customerArrayList;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select customer_id from customer order by customer_id desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1);
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return String.format("C%03d", newIdIndex);
        }
        return "C001";
    }

    @Override
    public boolean delete(String id){
        return CrudUtil.execute("delete from customer where customer_id=?", id);
    }

    @Override
    public boolean update(Customer entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("update customer set customer_name=?, email=?, phone=? where customer_id=?",
                entity.getColID(),
                entity.getColName(),
                entity.getColEmail(),
                entity.getColPhone()
        );
    }

    @Override
    public boolean save(Customer entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("insert into customer values (?,?,?,?)",
                entity.getColID(),
                entity.getColName(),
                entity.getColEmail(),
                entity.getColPhone()
        );
    }
    public Customer findById(String selectedCustomerId) throws SQLException{
        ResultSet rst = CrudUtil.execute("select * from customer where customer_id=?", selectedCustomerId);

        if (rst.next()) {
            Customer customer= new Customer(
                    rst.getString(1),  // Customer ID
                    rst.getString(2),  // Name
                    rst.getString(3),  // Email
                    rst.getString(4)   // Phone
            );
            return customer;
        }
        return null;
    }
}
