package com.swedist.desktopapp.controller;

import com.swedist.desktopapp.model.Computer;
import com.swedist.desktopapp.repository.CLMRepository;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class ComputerController {
    @FXML
    TextField computerIdField, computerBrandField, computerModelField;

    @FXML
    public void onFetchButtonClickComputer() {
        CLMRepository repository = new CLMRepository();
        Computer computer;
        try {
            computer = repository.getComputerById(Integer.parseInt(computerIdField.getText()));
            computerBrandField.setText(computer.getBrand());
            computerModelField.setText(computer.getModel());
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Sql Error");
            alert.setContentText(e.toString());
            alert.showAndWait();
        }
    }

    @FXML
    public void onSaveButtonClickComputer() {
        CLMRepository repository = new CLMRepository();
        Computer computer = new Computer();
        computer.setId(Integer.parseInt(computerIdField.getText()));
        computer.setBrand(computerBrandField.getText());
        computer.setModel(computerModelField.getText());
        try {
            repository.addComputer(computer);
            computerIdField.setText("" + computer.getId());
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Sql Error");
            alert.setContentText(e.toString());
            alert.showAndWait();
        }
    }

    @FXML
    public void onUpdateButtonClickComputer() {
        CLMRepository repository = new CLMRepository();
        Computer computer = new Computer();
        computer.setId(Integer.parseInt(computerIdField.getText()));
        computer.setBrand(computerBrandField.getText());
        computer.setModel(computerModelField.getText());
        try {
            repository.updateComputer(computer);
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Sql Error");
            alert.setContentText(e.toString());
            alert.showAndWait();
        }
    }

    @FXML
    public void onDeleteButtonClickComputer() {
        CLMRepository repository = new CLMRepository();
        Computer computer = new Computer();
        computer.setId(Integer.parseInt(computerIdField.getText()));
        try {
            repository.deleteComputer(computer);
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Sql Error");
            alert.setContentText(e.toString());
            alert.showAndWait();
        }
    }

    @FXML
    public void onCloseButtonClickComputer() {
        System.exit(0);
    }
}
