package com.ijse.gdse.bookstore.bo.impl;

import com.ijse.gdse.bookstore.bo.UserBO;
import com.ijse.gdse.bookstore.dao.DAOFactory;
import com.ijse.gdse.bookstore.dao.UserDAO;
import com.ijse.gdse.bookstore.dto.UserDTO;
import com.ijse.gdse.bookstore.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserBOImpl implements UserBO {
    UserDAO userDAO = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.USER);
    @Override
    public ArrayList<UserDTO> getAll() throws SQLException,ClassNotFoundException {
        ArrayList<User> users = userDAO.getAll();
        ArrayList<UserDTO> userDTOS = new ArrayList<>();
        for (User user:users){
            userDTOS.add(new UserDTO(
                    user.getColID(),
                    user.getColName(),
                    user.getColEmail(),
                    user.getColPassword(),
                    user.getColPosition()
            ));
        }
        return userDTOS;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean save(UserDTO userDTO) throws SQLException,ClassNotFoundException{
        return userDAO.save(new User(
                userDTO.getColID(),
                userDTO.getColName(),
                userDTO.getColEmail(),
                userDTO.getColPassword(),
                userDTO.getColPosition()
        ));
    }

    @Override
    public boolean update(UserDTO userDTO) throws SQLException,ClassNotFoundException{
        return userDAO.update(new User(
                userDTO.getColID(),
                userDTO.getColName(),
                userDTO.getColEmail(),
                userDTO.getColPassword(),
                userDTO.getColPosition()
        ));
    }

    @Override
    public String generateNewId() throws SQLException,ClassNotFoundException {
        return userDAO.generateNewId();
    }
}
