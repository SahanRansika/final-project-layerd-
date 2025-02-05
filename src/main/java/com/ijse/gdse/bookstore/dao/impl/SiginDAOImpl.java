package com.ijse.gdse.bookstore.dao.impl;

import com.ijse.gdse.bookstore.dao.SiginDAO;
import com.ijse.gdse.bookstore.entity.Sigin;

import java.sql.SQLException;
import java.util.ArrayList;

public class SiginDAOImpl implements SiginDAO {
    @Override
    public ArrayList<Sigin> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(Sigin entity) throws SQLException, ClassNotFoundException {
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
    public boolean save(Sigin entity) throws SQLException, ClassNotFoundException {
        return false;
    }
}
