package com.ijse.gdse.bookstore.bo;

import com.ijse.gdse.bookstore.bo.impl.*;

public class BOFactory {
    private static BOFactory boFactory;
    private  BOFactory(){

    }

    public static BOFactory getInstance(){return (boFactory==null) ? boFactory=new BOFactory():boFactory;}

public enum BOType{
    SIGIN,LOGIN,BOOK,CUSTOMER,CATEGORY,EMPLOYEE,ORDERS,ORDER_DETAILS,USER
}
public SuperBO getBO(BOType boType){
        switch (boType){
            case SIGIN:
                return new SiginBOImpl();
            case LOGIN:
                return new LoginBOImpl();
            case BOOK :
                return new BookBOImpl();
            case CUSTOMER:
                return new CustomerBOImpl();
            case CATEGORY:
                return new CategoryBOImpl();
            case EMPLOYEE:
                return new EmployeeBOImpl();
            case ORDERS:
                return new OrdersBOImpl();
            case ORDER_DETAILS:
                return new OrderDetailsBOImpl();
            case USER:
                return new UserBOImpl();
            default:
                return null;
        }
    }
}
