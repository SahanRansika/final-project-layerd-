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
    public boolean save(EmployeeDTO employeeDTO) throws SQLException{
        return employeeDAO.save(new Employee(
                employeeDTO.getColID(),
                employeeDTO.getColName(),
                employeeDTO.getColPhone(),
                employeeDTO.getColPosition(),
                employeeDTO.getColSalary()
        ));
    }

    @Override
    public boolean delete(String text) throws SQLException {
        return employeeDAO.delete(text);
    }

    @Override
    public boolean update(EmployeeDTO employeeDTO) throws SQLException{
        return employeeDAO.update(new Employee(employeeDTO.getColID(),employeeDTO.getColName(),employeeDTO.getColPhone(),employeeDTO.getColPosition(),employeeDTO.getColSalary()));
    }

    @Override
    public String generateNewId() throws SQLException{
        return employeeDAO.generateNewId();
    }

    @Override
    public ArrayList<EmployeeDTO> getAll() throws SQLException {
        ArrayList<Employee> employees = employeeDAO.getAll();
        ArrayList<EmployeeDTO> employeeDTOS = new ArrayList<>();
        for (Employee employee:employees){
            employeeDTOS.add(new EmployeeDTO(
                    employee.getColID(),
                    employee.getColName(),
                    employee.getColPhone(),
                    employee.getColPosition(),
                    employee.getColSalary()
            ));
        }
        return employeeDTOS;
    }
}
