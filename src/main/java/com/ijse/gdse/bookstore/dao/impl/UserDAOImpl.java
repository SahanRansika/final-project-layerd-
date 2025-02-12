package com.ijse.gdse.bookstore.dao.impl;

import com.ijse.gdse.bookstore.dao.UserDAO;
import com.ijse.gdse.bookstore.entity.User;
import com.ijse.gdse.bookstore.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAOImpl implements UserDAO {
    @Override
    public ArrayList<User> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM user");
        ArrayList<User> userArrayList = new ArrayList<>();

        while (rst.next()) {
            User entity = new User(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)
            );
            userArrayList.add(entity);
        }
        return userArrayList;
    }

    @Override
    public boolean save(User entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("insert into user values (?,?,?,?,?)",
                entity.getColID(),
                entity.getColName(),
                entity.getColEmail(),
                entity.getColPassword(),
                entity.getColPosition()
        );
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select user_id from user order by user_id desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1);
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return String.format("U%03d", newIdIndex);
        }
        return "U001";
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
       return CrudUtil.execute("delete from user where user_id=?", id);
    }

    @Override
    public boolean update(User entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("update user set username=?, email=?, position=? ,password=? where user_id=? ",
                entity.getColName(),
                entity.getColEmail(),
                entity.getColPassword(),
                entity.getColPosition(),
                entity.getColID()
        );
    }
}
