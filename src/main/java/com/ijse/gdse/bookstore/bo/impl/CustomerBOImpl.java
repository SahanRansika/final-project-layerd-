package com.ijse.gdse.bookstore.bo.impl;

import com.ijse.gdse.bookstore.bo.CustomerBO;
import com.ijse.gdse.bookstore.dao.DAOFactory;
import com.ijse.gdse.bookstore.dao.impl.CustomerDAOImpl;
import com.ijse.gdse.bookstore.dto.CustomerDTO;
import com.ijse.gdse.bookstore.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO{
    CustomerDAOImpl customerDAO = (CustomerDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.CUSTOMER);

    @Override
//    public CustomerDTO findById(String selectedCustomerId) throws SQLException{
//        Customer customer = customerDAO.findById(selectedCustomerId);
//        return new CustomerDTO(
//                customer.getColID(),
//                customer.getColName(),
//                customer.getColEmail(),
//                customer.getColPhone()
//        );
//    }

    public CustomerDTO findById(String selectedCustomerId) throws SQLException{
        Customer customer = customerDAO.findById(selectedCustomerId);

        if (customer == null) {
            System.out.println("Customer is null for ID: " + selectedCustomerId);
            return null; // Prevent further null pointer issues
        }

        return new CustomerDTO(customer.getColID(), customer.getColName(), customer.getColEmail(),customer.getColPhone());
    }

    @Override
    public ArrayList<CustomerDTO> getAll() throws SQLException{
        ArrayList<Customer> customers = customerDAO.getAll();
        ArrayList<CustomerDTO> customerDTOS = new ArrayList<>();
        for (Customer customer:customers){
            customerDTOS.add(new CustomerDTO(
                    customer.getColID(),
                    customer.getColName(),
                    customer.getColEmail(),
                    customer.getColPhone()
            ));
        }
        return customerDTOS;
    }

    @Override
    public boolean delete(String id) throws SQLException{
        return customerDAO.delete(id);
    }

    @Override
    public String generateNewId() throws SQLException {
        return customerDAO.generateNewId();
    }

    @Override
    public boolean save(CustomerDTO customerDTO) throws SQLException,ClassNotFoundException{
        return customerDAO.save(new Customer(
                customerDTO.getColID(),
                customerDTO.getColName(),
                customerDTO.getColEmail(),
                customerDTO.getColPhone()
        ));
    }

    @Override
    public boolean update(CustomerDTO customerDTO) throws SQLException{
        return customerDAO.update(new Customer(
                customerDTO.getColID(),
                customerDTO.getColName(),
                customerDTO.getColEmail(),
                customerDTO.getColPhone()
        ));
    }
}
