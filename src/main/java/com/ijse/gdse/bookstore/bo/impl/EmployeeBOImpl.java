package com.ijse.gdse.bookstore.bo.impl;

import com.ijse.gdse.bookstore.bo.EmployeeBO;
import com.ijse.gdse.bookstore.dao.DAOFactory;
import com.ijse.gdse.bookstore.dao.impl.EmployeeDAOImpl;
import com.ijse.gdse.bookstore.dto.EmployeeDTO;
import com.ijse.gdse.bookstore.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBOImpl implements EmployeeBO {
    EmployeeDAOImpl employeeDAO = (EmployeeDAOImpl)DAOFactory.getInstance().getDAO(DAOFactory.DAOType.EMPLOYEE);

    @Override
    public boolean save(EmployeeDTO employeeDTO){
        return employeeDAO.save(new Employee(
                employeeDTO.getColID(),
                employeeDTO.getColName(),
                employeeDTO.getColPosition(),
                employeeDTO.getColPhone(),
                employeeDTO.getColSalary()
        ));
    }

    @Override
    public boolean delete(String text) {
        return false;
    }

    @Override
    public boolean update(EmployeeDTO employeeDTO) {
        return false;
    }

    @Override
    public String generateNewId() throws SQLException {
        return null;
    }

    @Override
    public ArrayList<EmployeeDTO> getAll() throws SQLException {
        ArrayList<Employee> employees = employeeDAO.getAll();
        ArrayList<EmployeeDTO> employeeDTOS = new ArrayList<>();
        for (Employee employee:employees){
            employeeDTOS.add(new EmployeeDTO(
                    employee.getColID(),
                    employee.getColName(),
                    employee.getColPosition(),
                    employee.getColPhone(),
                    employee.getColSalary()
            ));
        }
        return employeeDTOS;
    }
}
