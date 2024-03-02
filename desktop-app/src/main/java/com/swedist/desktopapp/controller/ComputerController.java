package com.swedist.desktopapp.controller;

import com.swedist.desktopapp.model.Computer;
import com.swedist.desktopapp.repository.CLMRepository;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class ComputerController {

    @FXML
    TextField computer_t1, computer_t2, computer_t3;

    @FXML
    public void onFetchButtonClickComputer() throws SQLException {
        CLMRepository repository = new CLMRepository();
        Computer computer;
        computer = repository.getComputerById(Integer.parseInt(computer_t1.getText()));
        computer_t2.setText(computer.getBrand());
        computer_t3.setText(computer.getModel());
    }

    @FXML
    public void onSaveButtonClickComputer() throws SQLException {
        CLMRepository repository = new CLMRepository();
        Computer computer = new Computer();
        computer.setBrand(computer_t2.getText());
        computer.setModel(computer_t3.getText());
        repository.addComputer(computer);
        computer_t1.setText("" + computer.getId());
    }

    @FXML
    public void onUpdateButtonClickComputer() throws SQLException {
        CLMRepository repository = new CLMRepository();
        Computer computer = new Computer();
        computer.setId(Integer.parseInt(computer_t1.getText()));
        computer.setBrand(computer_t2.getText());
        computer.setModel(computer_t3.getText());
        repository.updateComputer(computer);
    }

    @FXML
    public void onDeleteButtonClickComputer() throws SQLException {
        CLMRepository repository = new CLMRepository();
        Computer computer = new Computer();
        computer.setId(Integer.parseInt(computer_t1.getText()));
        repository.deleteComputer(computer);
    }

    @FXML
    public void onCloseButtonClickComputer() {
        System.exit(0);
    }
}
