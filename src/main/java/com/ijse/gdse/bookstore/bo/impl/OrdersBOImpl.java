package com.ijse.gdse.bookstore.bo.impl;

import com.ijse.gdse.bookstore.bo.OrdersBO;
import com.ijse.gdse.bookstore.dao.DAOFactory;
import com.ijse.gdse.bookstore.dao.OrdersDAO;
import com.ijse.gdse.bookstore.dao.impl.BookDAOImpl;
import com.ijse.gdse.bookstore.dao.impl.CustomerDAOImpl;
import com.ijse.gdse.bookstore.dao.impl.OrdersDAOImpl;
import com.ijse.gdse.bookstore.dto.BookDTO;
import com.ijse.gdse.bookstore.dto.CustomerDTO;
import com.ijse.gdse.bookstore.dto.OrderDTO;
import com.ijse.gdse.bookstore.entity.Book;
import com.ijse.gdse.bookstore.entity.Customer;
import com.ijse.gdse.bookstore.entity.Orders;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrdersBOImpl implements OrdersBO {
    OrdersDAOImpl ordersDAO = (OrdersDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ORDERS);
    CustomerDAOImpl customerDAO = (CustomerDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.CUSTOMER);
    BookDAOImpl bookDAO = (BookDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.BOOK);

    @Override
    public boolean save(OrderDTO orderDTO) throws SQLException,ClassNotFoundException{
        return ordersDAO.save(new Orders(
                orderDTO.getOrderId(),
                orderDTO.getCustomerId(),
                orderDTO.getOrderDate(),
                orderDTO.getOrderDetailsDTOS()
        ));
    }
    @Override
    public String getNextOrderId() throws SQLException,ClassNotFoundException {
        return ordersDAO.generateNewId();
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomerIds() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> customers = customerDAO.getAll();
        ArrayList<CustomerDTO> customerDTOS = new ArrayList<>();
        for (Customer customer : customers){
            customerDTOS.add(new CustomerDTO(
                    customer.getColID(),
                    customer.getColName(),
                    customer.getColEmail(),
                    customer.getColPhone()
            ));
        }
        return customerDTOS;
    }

    @Override
    public ArrayList<BookDTO> loadBookIds() throws SQLException, ClassNotFoundException {
        ArrayList<Book> books = bookDAO.getAll();
        ArrayList<BookDTO> bookDTOS = new ArrayList<>();
        for (Book book : books){
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
}
