package com.ijse.gdse.bookstore.dao.impl;

import com.ijse.gdse.bookstore.dao.EmployeeDAO;
import com.ijse.gdse.bookstore.entity.Employee;
import com.ijse.gdse.bookstore.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public ArrayList<Employee> getAll() throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Employee");

        ArrayList<Employee> employeeList = new ArrayList<>();
        while (rst.next()) {
            Employee employee = new Employee(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getString(5));
            employeeList.add(employee);
        }
        return employeeList;
    }

    @Override
    public boolean save(Employee entity){
        return CrudUtil.execute("insert into Employee values (?,?,?,?,?)",
                entity.getColID(),
                entity.getColName(),
                entity.getColPosition(),
                entity.getColPhone(),
                entity.getColSalary()
        );
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Employee entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return null;
    }
}
