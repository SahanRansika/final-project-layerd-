package com.ijse.gdse.bookstore.bo.impl;

import com.ijse.gdse.bookstore.bo.CategoryBO;
import com.ijse.gdse.bookstore.dao.DAOFactory;
import com.ijse.gdse.bookstore.dao.impl.CategoryDAOImpl;
import com.ijse.gdse.bookstore.dto.CategoryDTO;
import com.ijse.gdse.bookstore.entity.Category;
import com.ijse.gdse.bookstore.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryBOImpl implements CategoryBO {
    CategoryDAOImpl categoryDAO = (CategoryDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.CATEGORY);
    @Override
    public boolean save(CategoryDTO categoryDTO) throws SQLException,ClassNotFoundException{
        return categoryDAO.save(new Category(categoryDTO.getColName(),categoryDTO.getColStatus(),categoryDTO.getColCode()));
    }
    @Override
    public boolean update(CategoryDTO categoryDTO) throws SQLException,ClassNotFoundException{
        return categoryDAO.update(new Category(categoryDTO.getColStatus(),categoryDTO.getColCode(),categoryDTO.getColName()));
    }
    @Override
    public boolean delete(String id) throws SQLException,ClassNotFoundException{
        return categoryDAO.delete(id);
    }
    @Override
    public String generateNewId() throws SQLException,ClassNotFoundException{
        return categoryDAO.generateNewId();
    }
    @Override
   public ArrayList<CategoryDTO> getAll() throws SQLException,ClassNotFoundException{
    ArrayList<Category> categories = categoryDAO.getAll();
    ArrayList<CategoryDTO> categoryDTOS = new ArrayList<>();
    for (Category category : categories){
        categoryDTOS.add(new CategoryDTO(
                category.getColName(),
                category.getColStatus(),
                category.getColCode())
        );
    }
    return categoryDTOS;
   }
}
