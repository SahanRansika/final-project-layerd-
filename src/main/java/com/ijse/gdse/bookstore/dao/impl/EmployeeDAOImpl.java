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
    public boolean save(Employee entity) throws SQLException{
        return CrudUtil.execute("insert into Employee values (?,?,?,?,?)",
                entity.getColID(),
                entity.getColName(),
                entity.getColPosition(),
                entity.getColPhone(),
                entity.getColSalary()
        );
    }

    @Override
    public boolean delete(String employeeId) throws SQLException {
        return CrudUtil.execute("delete from employee where employee_Id=?", employeeId);
    }

    @Override
    public boolean update(Employee entity) throws SQLException{
        return CrudUtil.execute("update employee set employee_name=?, phone=?, position=?, salary=? where employee_id=?",
                entity.getColName(),
                entity.getColPhone(),
                entity.getColPosition(),
                entity.getColSalary(),
                entity.getColID()
        );
    }

    @Override
    public String generateNewId() throws SQLException {
        ResultSet rst = null;
        rst = CrudUtil.execute("select employee_id from employee order by employee_id desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1); // Last book ID
            String substring = lastId.substring(1); // Extract the numeric part
            int i = Integer.parseInt(substring); // Convert the numeric part to integer
            int newIdIndex = i + 1; // Increment the number by 1
            return String.format("E%03d", newIdIndex); // Return the new book ID in format Cnnn
        }
        return "E001";
    }
}
