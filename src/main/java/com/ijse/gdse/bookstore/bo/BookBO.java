package com.ijse.gdse.bookstore.bo;

import com.ijse.gdse.bookstore.dto.BookDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BookBO extends SuperBO{
    boolean save(BookDTO bookDTO) throws SQLException,ClassNotFoundException;
    boolean delete(String id) throws SQLException,ClassNotFoundException;
    boolean update(BookDTO bookDTO) throws SQLException,ClassNotFoundException;
    String getNextBookId() throws SQLException,ClassNotFoundException;
    BookDTO findById(String selectedBookId) throws SQLException,ClassNotFoundException;
    ArrayList<BookDTO> getAll() throws SQLException,ClassNotFoundException;
}
