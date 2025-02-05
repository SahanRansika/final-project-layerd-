package com.ijse.gdse.bookstore.bo.impl;

import com.ijse.gdse.bookstore.bo.LoginBO;
import com.ijse.gdse.bookstore.dao.DAOFactory;
import com.ijse.gdse.bookstore.dao.impl.LoginDAOImpl;

public class LoginBOImpl implements LoginBO {
    LoginDAOImpl loginDAO = (LoginDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.LOGIN);
}
