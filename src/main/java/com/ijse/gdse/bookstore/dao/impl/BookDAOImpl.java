package com.ijse.gdse.bookstore.dao.impl;

import com.ijse.gdse.bookstore.dao.CrudDAO;
import com.ijse.gdse.bookstore.entity.Book;
import com.ijse.gdse.bookstore.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookDAOImpl implements CrudDAO<Book> {
@Override
    public ArrayList<Book> getAll() throws SQLException,ClassNotFoundException{
        ResultSet rst = CrudUtil.execute("SELECT * FROM book");
        ArrayList<Book> bookArrayList = new ArrayList<>();

        while (rst.next()){
            Book entity = new Book(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getInt(7),
                    rst.getInt(8),
                    rst.getDouble(9)
            );
            bookArrayList.add(entity);
        }
        return bookArrayList;
    }
@Override
    public boolean save(Book entity) throws SQLException,ClassNotFoundException{
    return CrudUtil.execute("INSERT INTO book VALUES (?,?,?,?,?,?,?,?,?)",
            entity.getColID(),
            entity.getColISBN(),
            entity.getColTitle(),
            entity.getColWriter(),
            entity.getColCategory(),
            entity.getColPublisher(),
            entity.getColYear(),
            entity.getColQty(),
            entity.getColPrice()
    );
}
@Override
    public boolean update(Book entity) throws SQLException,ClassNotFoundException{
    return CrudUtil.execute("update book set isbn=?, title=?, writer=?, category_name=?, publisher=?, year=?, qty=?, price=? where book_id=?",
            entity.getColID(),
            entity.getColISBN(),
            entity.getColTitle(),
            entity.getColWriter(),
            entity.getColCategory(),
            entity.getColPublisher(),
            entity.getColYear(),
            entity.getColQty(),
            entity.getColPrice()
    );
}

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("delete from book where book_id=?", id);
    }
    @Override
    public String generateNewId() throws SQLException,ClassNotFoundException{
        ResultSet resultSet = CrudUtil.execute("select book_id from book order by book_id desc limit 1");
        if (resultSet.next()) {
            String lastId = resultSet.getString(1);
            String substring = lastId.substring(3);
            int i = Integer.parseInt(substring);
            int newIdIndex = i+1;
            return String.format("EMP%03d",newIdIndex);
        }
        return null;
    }
    public Book findById(String selectedBookId) throws SQLException{
        ResultSet rst = CrudUtil.execute("select * from book where book_id=?", selectedBookId);

        if (rst.next()) {
            Book book = new Book(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getInt(7),
                    rst.getInt(8),
                    rst.getDouble(9)
            );
            return book;
        }
        return null;
    }
    public ArrayList<String> getAllBookIds() throws SQLException{
        ResultSet rst = CrudUtil.execute("select book_id from book");

        ArrayList<String> bookIds = new ArrayList<>();

        while (rst.next()) {
            bookIds.add(rst.getString(1));
        }

        return bookIds;
    }
}
