package com.ijse.gdse.bookstore.dao.impl;

import com.ijse.gdse.bookstore.dao.LoginDAO;
import com.ijse.gdse.bookstore.entity.Login;

import java.sql.SQLException;
import java.util.ArrayList;

public class LoginDAOImpl implements LoginDAO {
    @Override
    public boolean update(Login entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<Login> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Login entity) throws SQLException, ClassNotFoundException {
        return false;
    }
}
