package com.ijse.gdse.bookstore.bo;

import com.ijse.gdse.bookstore.dto.EmployeeDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeBO extends SuperBO{
    boolean save(EmployeeDTO employeeDTO) throws SQLException;
    boolean delete(String text) throws SQLException;
    boolean update(EmployeeDTO employeeDTO) throws SQLException;
    String generateNewId() throws SQLException;
    ArrayList<EmployeeDTO> getAll() throws SQLException;
}
