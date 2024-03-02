package com.swedist.desktopapp.controller;

import com.swedist.desktopapp.CLMApp;
import com.swedist.desktopapp.model.Student;
import com.swedist.desktopapp.repository.CLMRepository;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class StudentController {
    @FXML
    private TextField studentIdField, studentNameField, studentDepartmentField;

    //FXML
    // Read Operation
    @FXML
    protected void onFetchButtonClickStudent() throws SQLException {
        CLMRepository repository = new CLMRepository();
        Student student;
        student = repository.getStudentById(Integer.parseInt(studentIdField.getText()));
        studentNameField.setText(student.getName());
        studentDepartmentField.setText(student.getDepartment());
    }

    // Create Operation
    @FXML
    protected void onSaveButtonClickStudent() throws Exception {
        CLMRepository repository = new CLMRepository();
        Student student = new Student();
//        student.setId(Integer.parseInt(t1.getText()));
        student.setName(studentNameField.getText());
        student.setDepartment(studentDepartmentField.getText());
        repository.addStudent(student);
    }

    //Update Operation
    @FXML
    protected void onUpdateButtonClickStudent() throws SQLException {
        CLMRepository repository = new CLMRepository();
        Student student = new Student();
        student.setId(Integer.parseInt(studentIdField.getText()));
        student.setName(studentNameField.getText());
        student.setDepartment(studentDepartmentField.getText());
        repository.updateStudent(student);
    }

    //Delete Operation
    @FXML
    protected void onDeleteButtonClickStudent() throws SQLException {
        CLMRepository repository = new CLMRepository();
        Student student = new Student();
        student.setId(Integer.parseInt(studentIdField.getText()));
        student.setName(studentNameField.getText());
        student.setDepartment(studentDepartmentField.getText());
        repository.deleteStudent(student);
    }

    @FXML
    protected void onCloseButtonClickStudent() {
        System.exit(0);
    }

    @FXML
    protected void  onButtonClickComputerView() throws Exception
    {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(CLMApp.class.getResource("computer-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Computer");
        stage.setScene(scene);
        stage.show();
    }
}
