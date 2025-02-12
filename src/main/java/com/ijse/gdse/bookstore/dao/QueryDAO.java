package com.ijse.gdse.bookstore.dao;

import com.ijse.gdse.bookstore.dto.CustomerDTO;

public interface QueryDAO extends SuperDAO{
    void OrderDetails(CustomerDTO customerDTO);
}
