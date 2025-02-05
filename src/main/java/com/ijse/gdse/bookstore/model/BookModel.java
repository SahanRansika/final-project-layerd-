package com.ijse.gdse.bookstore.model;

import com.ijse.gdse.bookstore.db.DBConnection;
import com.ijse.gdse.bookstore.dto.BookDTO;
import com.ijse.gdse.bookstore.dto.CategoryDTO;
import com.ijse.gdse.bookstore.dto.OrderDetailsDTO;
import com.ijse.gdse.bookstore.util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookModel {

    public String getNextBookId() throws SQLException {
        ResultSet rst = CrudUtil.execute("select book_id from book order by book_id desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1);
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return String.format("B%03d", newIdIndex);
        }
        return "B001";
    }

    public ArrayList<String> getAllBookIds() throws SQLException {
        ResultSet rst = CrudUtil.execute("select book_id from book");

        ArrayList<String> bookIds = new ArrayList<>();

        while (rst.next()) {
            bookIds.add(rst.getString(1));
        }

        return bookIds;
    }

    public ArrayList<BookDTO> getAll() throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM book");
        ArrayList<BookDTO> bookDTOS = new ArrayList<>();

        while (rst.next()) {
            BookDTO bookDTO = new BookDTO(
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
            bookDTOS.add(bookDTO);
        }
        return bookDTOS;
    }

    public boolean save(BookDTO bookDTO) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO book VALUES (?,?,?,?,?,?,?,?,?)";
        PreparedStatement pst = connection.prepareStatement(sql);

        pst.setObject(1, bookDTO.getColID());
        pst.setObject(2, bookDTO.getColISBN());
        pst.setObject(3, bookDTO.getColTitle());
        pst.setObject(4, bookDTO.getColWriter());
        pst.setObject(5, bookDTO.getColCategory());
        pst.setObject(6, bookDTO.getColPublisher());
        pst.setObject(7, bookDTO.getColYear());
        pst.setObject(8, bookDTO.getColQty());
        pst.setObject(9, bookDTO.getColPrice());

        int result = pst.executeUpdate();
        return result > 0;
    }

    public boolean update(BookDTO bookDTO) throws SQLException {
        return CrudUtil.execute(
                "update book set isbn=?, title=?, writer=?, category_name=?, publisher=?, year=?, qty=?, price=? where book_id=?",
                bookDTO.getColISBN(),
                bookDTO.getColTitle(),
                bookDTO.getColWriter(),
                bookDTO.getColCategory(),
                bookDTO.getColPublisher(),
                bookDTO.getColYear(),
                bookDTO.getColQty(),
                bookDTO.getColPrice(),
                bookDTO.getColID()
        );
    }

    public boolean delete(String bookId) throws SQLException {
        return CrudUtil.execute("delete from book where book_id=?", bookId);
    }

    public BookDTO findById(String selectedBookId) throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from book where book_id=?", selectedBookId);


        if (rst.next()) {
            return new BookDTO(
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
        }
        return null;
    }

    public boolean reduceQty(OrderDetailsDTO orderDetailsDTO) throws SQLException {
        return CrudUtil.execute(
                "update book set qty = qty - ? where book_id = ?",
                orderDetailsDTO.getQuantity(),
                orderDetailsDTO.getBookId()
        );
    }
}
