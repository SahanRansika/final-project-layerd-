package com.ijse.gdse.bookstore.bo.impl;

import com.ijse.gdse.bookstore.bo.SiginBO;
import com.ijse.gdse.bookstore.bo.SuperBO;
import com.ijse.gdse.bookstore.dao.DAOFactory;
import com.ijse.gdse.bookstore.dao.impl.SiginDAOImpl;

public class SiginBOImpl implements SiginBO {
    SiginDAOImpl siginDAO = (SiginDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.SIGIN);
}
