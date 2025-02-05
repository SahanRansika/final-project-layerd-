package com.ijse.gdse.bookstore.bo.impl;

import com.ijse.gdse.bookstore.bo.BookBO;
import com.ijse.gdse.bookstore.dao.DAOFactory;
import com.ijse.gdse.bookstore.dao.impl.BookDAOImpl;
import com.ijse.gdse.bookstore.dto.BookDTO;
import com.ijse.gdse.bookstore.entity.Book;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookBOImpl implements BookBO {
    BookDAOImpl bookDAO = (BookDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.BOOK);

    @Override
    public ArrayList<BookDTO> getAll() throws SQLException,ClassNotFoundException {
        ArrayList<Book> books = bookDAO.getAll();
        ArrayList<BookDTO> bookDTOS = new ArrayList<>();
        for (Book book:books){
            bookDTOS.add(new BookDTO(
                    book.getColID(),
                    book.getColISBN(),
                    book.getColTitle(),
                    book.getColWriter(),
                    book.getColCategory(),
                    book.getColPublisher(),
                    book.getColYear(),
                    book.getColQty(),
                    book.getColPrice()
            ));
        }
        return bookDTOS;
    }

    @Override
    public boolean delete(String id) throws SQLException,ClassNotFoundException{
        return false;
    }

    @Override
    public ArrayList<String> getAllBookIds() throws SQLException {
        return bookDAO.getAllBookIds();
    }

    @Override
    public BookDTO findById(String selectedBookId) throws SQLException {
        Book byId = bookDAO.findById(selectedBookId);
        return new BookDTO(byId.getColID(),byId.getColISBN(),byId.getColTitle(),byId.getColWriter(),byId.getColCategory(),byId.getColPublisher(),byId.getColYear(),byId.getColQty(),byId.getColPrice());
    }

    @Override
    public boolean save(BookDTO bookDTO) throws SQLException,ClassNotFoundException{
        return bookDAO.save(new Book(
                bookDTO.getColID(),
                bookDTO.getColISBN(),
                bookDTO.getColTitle(),
                bookDTO.getColWriter(),
                bookDTO.getColCategory(),
                bookDTO.getColPublisher(),
                bookDTO.getColYear(),
                bookDTO.getColQty(),
                bookDTO.getColPrice()
        ));
    }

    @Override
    public boolean update(BookDTO bookDTO) throws SQLException,ClassNotFoundException{
        return bookDAO.update(new Book(
                bookDTO.getColID(),
                bookDTO.getColISBN(),
                bookDTO.getColTitle(),
                bookDTO.getColWriter(),
                bookDTO.getColCategory(),
                bookDTO.getColPublisher(),
                bookDTO.getColYear(),
                bookDTO.getColQty(),
                bookDTO.getColPrice()
        ));
    }

    @Override
    public String getNextBookId() throws SQLException,ClassNotFoundException {
        return bookDAO.generateNewId();
    }
}
