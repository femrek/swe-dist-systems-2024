package com.swedist.desktopapp.controller;

import com.swedist.desktopapp.CLMApp;
import com.swedist.desktopapp.model.Student;
import com.swedist.desktopapp.repository.CLMRepository;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class StudentController {
    @FXML
    private TextField studentIdField, studentNameField, studentDepartmentField;

    // Read Operation
    @FXML
    protected void onFetchButtonClickStudent() {
        CLMRepository repository = new CLMRepository();
        Student student;
        try {
            student = repository.getStudentById(Integer.parseInt(studentIdField.getText()));
            studentNameField.setText(student.getName());
            studentDepartmentField.setText(student.getDepartment());
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Sql Error");
            alert.setContentText(e.toString());
            alert.showAndWait();
        }
    }

    // Create Operation
    @FXML
    protected void onSaveButtonClickStudent() {
        CLMRepository repository = new CLMRepository();
        Student student = new Student();
        student.setId(Integer.parseInt(studentIdField.getText()));
        student.setName(studentNameField.getText());
        student.setDepartment(studentDepartmentField.getText());
        try {
            repository.addStudent(student);
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Sql Error");
            alert.setContentText(e.toString());
            alert.showAndWait();
        }
    }

    //Update Operation
    @FXML
    protected void onUpdateButtonClickStudent() {
        CLMRepository repository = new CLMRepository();
        Student student = new Student();
        student.setId(Integer.parseInt(studentIdField.getText()));
        student.setName(studentNameField.getText());
        student.setDepartment(studentDepartmentField.getText());
        try {
            repository.updateStudent(student);
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Sql Error");
            alert.setContentText(e.toString());
            alert.showAndWait();
        }
    }

    //Delete Operation
    @FXML
    protected void onDeleteButtonClickStudent() {
        CLMRepository repository = new CLMRepository();
        Student student = new Student();
        student.setId(Integer.parseInt(studentIdField.getText()));
        student.setName(studentNameField.getText());
        student.setDepartment(studentDepartmentField.getText());
        try {
            repository.deleteStudent(student);
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Sql Error");
            alert.setContentText(e.toString());
            alert.showAndWait();
        }
    }

    @FXML
    protected void onCloseButtonClickStudent() {
        System.exit(0);
    }

    @FXML
    protected void onButtonClickComputerView() throws Exception {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(CLMApp.class.getResource("computer-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Computer");
        stage.setScene(scene);
        stage.show();
    }
}
