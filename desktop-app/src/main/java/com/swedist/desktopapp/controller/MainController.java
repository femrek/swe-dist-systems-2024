package com.swedist.desktopapp.controller;

import com.swedist.desktopapp.model.Student;
import com.swedist.desktopapp.repository.CLMRepository;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.sql.SQLException;

public class MainController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() throws SQLException {
        CLMRepository repository = new CLMRepository();
        Student student = new Student();
        student = repository.getStudentById(1);
        welcomeText.setText(student.getName());
    }
}