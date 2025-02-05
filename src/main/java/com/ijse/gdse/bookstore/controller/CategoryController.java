package com.ijse.gdse.bookstore.controller;


import com.ijse.gdse.bookstore.bo.BOFactory;
import com.ijse.gdse.bookstore.bo.impl.CategoryBOImpl;
import com.ijse.gdse.bookstore.dao.DAOFactory;
import com.ijse.gdse.bookstore.dto.CategoryDTO;
import com.ijse.gdse.bookstore.dto.CustomerDTO;
import com.ijse.gdse.bookstore.dto.tm.CategoryTM;
import com.ijse.gdse.bookstore.dto.tm.CustomerTM;
import com.ijse.gdse.bookstore.model.CategoryModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class CategoryController implements Initializable {

    @FXML
    private AnchorPane ancCategory;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnReset;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private TableColumn<?, ?> colCode;

    @FXML
    private Label lblCategory;

    @FXML
    private TableView<CategoryTM> tblCategory;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtStatus;

    CategoryBOImpl categoryBO = (CategoryBOImpl) BOFactory.getInstance().getBO(BOFactory.BOType.CATEGORY);

    public void initialize(URL url, ResourceBundle resourceBundle) {
        colName.setCellValueFactory(new PropertyValueFactory<>("colName"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("colStatus"));
        colCode.setCellValueFactory(new PropertyValueFactory<>("colCode"));

        try {
            refreshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load category data").show();
        }
    }
    private void refreshPage() throws SQLException,ClassNotFoundException {
        loadNextCategoryId();
        loadTableData();
        btnSave.setDisable(false);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);

        txtName.clear();
        txtStatus.clear();
    }

    public void loadNextCategoryId() throws SQLException,ClassNotFoundException {
        String nextCustomerId = categoryBO.generateNewId();
        lblCategory.setText(nextCustomerId);
    }

    private void loadTableData() throws SQLException,ClassNotFoundException {
        ArrayList<CategoryDTO> categoryDTOS = categoryBO.getAll();
        ObservableList<CategoryTM> categoryTMS = FXCollections.observableArrayList();

        for (CategoryDTO categoryDTO : categoryDTOS) {
            CategoryTM categoryTM = new CategoryTM(
                    categoryDTO.getColName(),
                    categoryDTO.getColStatus(),
                    categoryDTO.getColCode()
            );
            categoryTMS.add(categoryTM);
        }

        tblCategory.setItems(categoryTMS);
    }

    @FXML
    void onClickTable(MouseEvent event) {
        CategoryTM categoryTM = tblCategory.getSelectionModel().getSelectedItem();
        if (categoryTM != null) {
            txtName.setText(categoryTM.getColName());
            txtStatus.setText(categoryTM.getColStatus());
            lblCategory.setText(categoryTM.getColCode());

            btnSave.setDisable(true);

            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException,ClassNotFoundException{
        String categoryId = lblCategory.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {

            boolean isDeleted = categoryBO.delete(categoryId);
            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Category deleted...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete category...!").show();
            }
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException,ClassNotFoundException{
        CategoryDTO categoryDTO = new CategoryDTO(
                txtName.getText(),
                txtStatus.getText(),
                lblCategory.getText()
        );

        try {
            boolean isSaved = categoryBO.save(categoryDTO);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Category Saved.").show();
                refreshPage();
            } else {
                new Alert(Alert.AlertType.ERROR, "Category is not saved. Something went wrong.").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error saving category data.").show();
        }
    }


    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException,ClassNotFoundException {
        CategoryDTO categoryDTO = new CategoryDTO(
                lblCategory.getText(),
                txtName.getText(),
                txtStatus.getText()
        );

        boolean isUpdate = categoryBO.update(categoryDTO);
        if (isUpdate) {
            new Alert(Alert.AlertType.CONFIRMATION, "Category Update.").show();
            refreshPage();
        } else {
            new Alert(Alert.AlertType.ERROR, "Category is not update. Something went wrong.").show();
        }
    }

    @FXML
    void btnResetOnAction(ActionEvent event) throws SQLException,ClassNotFoundException {
        refreshPage();
    }

}
