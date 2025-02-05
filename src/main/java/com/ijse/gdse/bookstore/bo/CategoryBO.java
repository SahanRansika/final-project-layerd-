package com.ijse.gdse.bookstore.bo;

import com.ijse.gdse.bookstore.dto.CategoryDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CategoryBO extends SuperBO{
    boolean save(CategoryDTO categoryDTO) throws SQLException,ClassNotFoundException;
    boolean update(CategoryDTO categoryDTO) throws SQLException,ClassNotFoundException;
    boolean delete(String text) throws SQLException,ClassNotFoundException;
    String generateNewId() throws SQLException,ClassNotFoundException;
    ArrayList<CategoryDTO>getAll() throws SQLException,ClassNotFoundException;


}
