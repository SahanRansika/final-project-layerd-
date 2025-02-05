package com.ijse.gdse.bookstore.dao;

import com.ijse.gdse.bookstore.bo.impl.SiginBOImpl;
import com.ijse.gdse.bookstore.dao.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory(){

    }
    public static DAOFactory getInstance(){return (daoFactory==null) ? daoFactory=new DAOFactory():daoFactory;}
    public enum DAOType{
        SIGIN,LOGIN,BOOK,CUSTOMER,CATEGORY,EMPLOYEE,ORDERS,ORDER_DETAILS,USER
    }
    public SuperDAO getDAO(DAOType daoType){
        switch (daoType){
            case SIGIN:
                return new SiginDAOImpl();
            case LOGIN:
                return new LoginDAOImpl();
            case BOOK :
                return new BookDAOImpl();
            case CUSTOMER:
                return new CustomerDAOImpl();
            case CATEGORY:
                return new CategoryDAOImpl();
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case ORDERS:
                return new OrdersDAOImpl();
            case ORDER_DETAILS:
                return new OrderDetailsDAOImpl();
            case USER:
                return new UserDAOImpl();
            default:
                return null;
        }
    }
}
