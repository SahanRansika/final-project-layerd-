package com.ijse.gdse.bookstore.controller;

import com.ijse.gdse.bookstore.bo.BOFactory;
import com.ijse.gdse.bookstore.bo.impl.EmployeeBOImpl;
import com.ijse.gdse.bookstore.dao.DAOFactory;
import com.ijse.gdse.bookstore.dto.CustomerDTO;
import com.ijse.gdse.bookstore.dto.EmployeeDTO;
import com.ijse.gdse.bookstore.dto.tm.CustomerTM;
import com.ijse.gdse.bookstore.dto.tm.EmployeeTM;
import com.ijse.gdse.bookstore.model.EmployeeModel;
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

public class EmployeeController implements Initializable {

    public void initialize(URL url, ResourceBundle resourceBundle) {
        colID.setCellValueFactory(new PropertyValueFactory<>("colID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("colName"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("colPhone"));
        colPosition.setCellValueFactory(new PropertyValueFactory<>("colPosition"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("colSalary"));

        try {
            refreshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load employee data").show();
        }
    }
    @FXML
    private AnchorPane ancEmployee;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnReset;

    @FXML
    private TableColumn<EmployeeTM, String> colID;

    @FXML
    private TableColumn<EmployeeTM, String> colName;

    @FXML
    private TableColumn<EmployeeTM, String> colPhone;

    @FXML
    private TableColumn<EmployeeTM, String> colPosition;

    @FXML
    private TableColumn<EmployeeTM, String> colSalary;

    @FXML
    private TableView<EmployeeTM> tblEmployee;

    @FXML
    private Label lblEmployee;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPhone;

    @FXML
    private TextField txtPosition;

    @FXML
    private TextField txtSalary;

    EmployeeBOImpl employeeBO =(EmployeeBOImpl) BOFactory.getInstance().getBO(BOFactory.BOType.EMPLOYEE);

    private void refreshPage() throws SQLException {
        loadNextBookId();
        loadTableData();
        btnSave.setDisable(false);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);

        txtName.clear();
        txtPhone.clear();
        txtPosition.clear();
        txtSalary.clear();
    }

    public void loadNextBookId() throws SQLException {
        String nextEmployeeId = employeeBO.generateNewId();
        lblEmployee.setText(nextEmployeeId);
    }

    private void loadTableData() throws SQLException {
        ArrayList<EmployeeDTO> employeeDTOS = employeeBO.getAll();
        ObservableList<EmployeeTM> employeeTMS = FXCollections.observableArrayList();

        for (EmployeeDTO employeeDTO : employeeDTOS) {
            EmployeeTM employeeTM = new EmployeeTM(
                    employeeDTO.getColID(),
                    employeeDTO.getColName(),
                    employeeDTO.getColPhone(),
                    employeeDTO.getColPosition(),
                    employeeDTO.getColSalary()
            );
            employeeTMS.add(employeeTM);
        }

        tblEmployee.setItems(employeeTMS);
    }

    @FXML
    void onClickTable(MouseEvent event) {
        EmployeeTM employeeTM = tblEmployee.getSelectionModel().getSelectedItem();
        if (employeeTM != null) {
            lblEmployee.setText(employeeTM.getColID());
            txtName.setText(employeeTM.getColName());
            txtPhone.setText(employeeTM.getColPhone());
            txtPosition.setText(employeeTM.getColPosition());
            txtSalary.setText(employeeTM.getColSalary());

            btnSave.setDisable(true);

            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);
        }
    }
    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException{
        String employeeId = lblEmployee.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {

            boolean isDeleted = employeeBO.delete(employeeId);
            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Employee deleted...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete employee...!").show();
            }
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException {
        EmployeeDTO employeeDTO = new EmployeeDTO(
                lblEmployee.getText(),
                txtName.getText(),
                txtPhone.getText(),
                txtPosition.getText(),
                txtSalary.getText()
        );

        boolean isSaved = employeeBO.save(employeeDTO);
        if (isSaved) {
            new Alert(Alert.AlertType.CONFIRMATION, "Employee Saved.").show();
            refreshPage();
        } else {
            new Alert(Alert.AlertType.ERROR, "Employee is not saved. Something went wrong.").show();
        }
    }



    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        EmployeeDTO employeeDTO = new EmployeeDTO(
                lblEmployee.getText(),
                txtName.getText(),
                txtPhone.getText(),
                txtPosition.getText(),
                txtSalary.getText()
        );

        boolean isUpdate = employeeBO.update(employeeDTO);
        if (isUpdate) {
            new Alert(Alert.AlertType.CONFIRMATION, "Employee Update.").show();
            refreshPage();
        } else {
            new Alert(Alert.AlertType.ERROR, "Employee is not update. Something went wrong.").show();
        }
    }

    @FXML
    void btnResetOnAction(ActionEvent event) throws SQLException {
        refreshPage();
    }
}
