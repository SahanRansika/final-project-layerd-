package com.ijse.gdse.bookstore.bo;

import com.ijse.gdse.bookstore.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {
    boolean save(CustomerDTO customerDTO) throws SQLException,ClassNotFoundException;
    boolean update(CustomerDTO customerDTO) throws SQLException,ClassNotFoundException;
    boolean delete(String id) throws SQLException;
    String generateNewId() throws SQLException,ClassNotFoundException;
    CustomerDTO findById(String selectedCustomerId) throws SQLException,ClassNotFoundException;
    ArrayList<CustomerDTO> getAll() throws SQLException,ClassNotFoundException;

}
