package com.ijse.gdse.bookstore.dao.impl;

import com.ijse.gdse.bookstore.dao.CategoryDAO;
import com.ijse.gdse.bookstore.entity.Category;
import com.ijse.gdse.bookstore.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryDAOImpl implements CategoryDAO{
    public String generateNewId() throws SQLException,ClassNotFoundException{
        ResultSet resultSet = CrudUtil.execute("select code from category order by code desc limit 1");
        if (resultSet.next()) {
            String lastId = resultSet.getString(  1);
            String substring = lastId.substring(3);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return String.format("CAT%03d", newIdIndex);
        }
        return "CAT001";
    }
    public boolean save(Category entity) throws SQLException,ClassNotFoundException{
        return CrudUtil.execute("insert into category values (?,?,?)",
            entity.getColName(),
            entity.getColStatus(),
            entity.getColCode()
        );
    }
    public boolean update(Category entity) throws SQLException,ClassNotFoundException{
        return CrudUtil.execute("update category set status=?, code=? where category_name=? ",
            entity.getColName(),
            entity.getColStatus(),
            entity.getColCode()
        );
    }
    public boolean delete(String id)throws SQLException,ClassNotFoundException{
        return CrudUtil.execute("delete from category where category_name=?",id);
    }

    public ArrayList<Category> getAll() throws SQLException,ClassNotFoundException{
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM category");
        ArrayList<Category> categoryArrayList = new ArrayList<>();

        while (resultSet.next()) {
            Category category = new Category(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3)
            );
            categoryArrayList.add(category);
        }
        return categoryArrayList;
    }
}
