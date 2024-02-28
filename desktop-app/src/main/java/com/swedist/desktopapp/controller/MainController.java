package com.swedist.desktopapp.controller;

import com.swedist.desktopapp.model.Student;
import com.swedist.desktopapp.repository.CLMRepository;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class MainController {
    @FXML
    private Label welcomeText;
    @FXML
    private TextField t1;

    @FXML
    private TextField t2;
    @FXML
    private TextField t3;


//    @FXML

    // Read Operation
    @FXML
    protected void onFetchButtonClick() throws SQLException {
        CLMRepository repository = new CLMRepository();
        Student student;
        student = repository.getStudentById(Integer.parseInt(t1.getText()));
        t2.setText(student.getName());
        t3.setText(student.getDepartment());
    }


    // Create Operation
    @FXML
    protected void onSaveButtonClick() throws Exception{
        CLMRepository repository = new CLMRepository();
        Student student = new Student();
//        student.setId(Integer.parseInt(t1.getText()));
        student.setName(t2.getText());
        student.setDepartment(t3.getText());
        repository.addStudent(student);
    }

    //Update Operation
    @FXML
    protected void onUpdateButtonClick() throws SQLException{
        CLMRepository repository = new CLMRepository();
        Student student = new Student();
        student.setId(Integer.parseInt(t1.getText()));
        student.setName(t2.getText());
        student.setDepartment(t3.getText());
        repository.updateStudent(student);
    }


    //Delete Operation
    @FXML
    protected void onDeleteButtonClick() throws SQLException{
        CLMRepository repository = new CLMRepository();
        Student student = new Student();
        student.setId(Integer.parseInt(t1.getText()));
        student.setName(t2.getText());
        student.setDepartment(t3.getText());
        repository.deleteStudent(student);
    }
}