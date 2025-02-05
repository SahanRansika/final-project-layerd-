package com.ijse.gdse.bookstore.bo;

import com.ijse.gdse.bookstore.dto.UserDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserBO extends SuperBO{
    String generateNewId() throws SQLException,ClassNotFoundException;
    boolean save(UserDTO userDTO) throws SQLException,ClassNotFoundException;
    boolean update(UserDTO userDTO) throws SQLException,ClassNotFoundException;
    boolean delete(String id) throws SQLException,ClassNotFoundException;
    ArrayList<UserDTO> getAll() throws SQLException,ClassNotFoundException;
}
