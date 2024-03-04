package com.swedist.desktopapp.controller;

import com.swedist.desktopapp.model.Computer;
import com.swedist.desktopapp.repository.CLMRepository;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class ComputerController {
    @FXML
    TextField computerIdField, computerBrandField, computerModelField;

    @FXML
    public void onFetchButtonClickComputer() throws SQLException {
        CLMRepository repository = new CLMRepository();
        Computer computer;
        computer = repository.getComputerById(Integer.parseInt(computerIdField.getText()));
        computerBrandField.setText(computer.getBrand());
        computerModelField.setText(computer.getModel());
    }

    @FXML
    public void onSaveButtonClickComputer() throws SQLException {
        CLMRepository repository = new CLMRepository();
        Computer computer = new Computer();
        computer.setBrand(computerBrandField.getText());
        computer.setModel(computerModelField.getText());
        repository.addComputer(computer);
        computerIdField.setText("" + computer.getId());
    }

    @FXML
    public void onUpdateButtonClickComputer() throws SQLException {
        CLMRepository repository = new CLMRepository();
        Computer computer = new Computer();
        computer.setId(Integer.parseInt(computerIdField.getText()));
        computer.setBrand(computerBrandField.getText());
        computer.setModel(computerModelField.getText());
        repository.updateComputer(computer);
    }

    @FXML
    public void onDeleteButtonClickComputer() throws SQLException {
        CLMRepository repository = new CLMRepository();
        Computer computer = new Computer();
        computer.setId(Integer.parseInt(computerIdField.getText()));
        repository.deleteComputer(computer);
    }

    @FXML
    public void onCloseButtonClickComputer() {
        System.exit(0);
    }
}
