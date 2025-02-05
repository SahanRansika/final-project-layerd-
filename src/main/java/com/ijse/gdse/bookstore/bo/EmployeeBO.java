package com.ijse.gdse.bookstore.bo;

import com.ijse.gdse.bookstore.dto.EmployeeDTO;
import com.ijse.gdse.bookstore.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeBO extends SuperBO{
    boolean save(EmployeeDTO employeeDTO);
    boolean delete(String text);
    boolean update(EmployeeDTO employeeDTO);
    String generateNewId() throws SQLException;
    ArrayList<EmployeeDTO> getAll() throws SQLException;
}
