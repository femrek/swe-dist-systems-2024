package com.swedist.desktopapp.controller;

import com.swedist.desktopapp.model.Student;
import com.swedist.desktopapp.repository.CLMRepository;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class StudentController {
    @FXML
    private TextField student_t1, student_t2, student_t3;

    //FXML
    // Read Operation
    @FXML
    protected void onFetchButtonClickStudent() throws SQLException {
        CLMRepository repository = new CLMRepository();
        Student student;
        student = repository.getStudentById(Integer.parseInt(student_t1.getText()));
        student_t2.setText(student.getName());
        student_t3.setText(student.getDepartment());
    }

    // Create Operation
    @FXML
    protected void onSaveButtonClickStudent() throws Exception {
        CLMRepository repository = new CLMRepository();
        Student student = new Student();
//        student.setId(Integer.parseInt(t1.getText()));
        student.setName(student_t2.getText());
        student.setDepartment(student_t3.getText());
        repository.addStudent(student);
    }

    //Update Operation
    @FXML
    protected void onUpdateButtonClickStudent() throws SQLException {
        CLMRepository repository = new CLMRepository();
        Student student = new Student();
        student.setId(Integer.parseInt(student_t1.getText()));
        student.setName(student_t2.getText());
        student.setDepartment(student_t3.getText());
        repository.updateStudent(student);
    }

    //Delete Operation
    @FXML
    protected void onDeleteButtonClickStudent() throws SQLException {
        CLMRepository repository = new CLMRepository();
        Student student = new Student();
        student.setId(Integer.parseInt(student_t1.getText()));
        student.setName(student_t2.getText());
        student.setDepartment(student_t3.getText());
        repository.deleteStudent(student);
    }

    @FXML
    protected void onCloseButtonClickStudent() {
        System.exit(0);
    }
}