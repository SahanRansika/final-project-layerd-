package com.ijse.gdse.bookstore.model;

import com.ijse.gdse.bookstore.db.DBConnection;
import com.ijse.gdse.bookstore.dto.CustomerDTO;
import com.ijse.gdse.bookstore.dto.EmployeeDTO;
import com.ijse.gdse.bookstore.util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeModel {

    public String getNextEmployeeId() throws SQLException {
        ResultSet rst = CrudUtil.execute("select employee_id from employee order by employee_id desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1);
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return String.format("E%03d", newIdIndex);
        }
        return null;
    }

    public boolean saveEmployee(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("insert into employee values (?,?,?,?,?)",
                employeeDTO.getColID(),
                employeeDTO.getColName(),
                employeeDTO.getColPhone(),
                employeeDTO.getColPosition(),
                employeeDTO.getColSalary());
    }

    public boolean EmployeeUpdate(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException{
        return CrudUtil.execute("update employee set employee_name=?, phone=?, position=?, salary=? where employee_id=?  ",
                employeeDTO.getColName(),
                employeeDTO.getColPhone(),
                employeeDTO.getColPosition(),
                employeeDTO.getColSalary(),
                employeeDTO.getColID()
                );
    }

    public boolean DeleteEmployee(String employeeId) throws SQLException {
        return CrudUtil.execute("delete from employee where employee_Id=?", employeeId);
    }

    public ArrayList<EmployeeDTO> getAllEmployeeIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM employee");
        ArrayList<EmployeeDTO> employeeDTOS = new ArrayList<>();

        while (rst.next()) {
            EmployeeDTO employeeDTO = new EmployeeDTO(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)
            );
            employeeDTOS.add(employeeDTO);
        }
        return employeeDTOS;
    }
}
