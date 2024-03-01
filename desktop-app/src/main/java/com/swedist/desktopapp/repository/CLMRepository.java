package com.swedist.desktopapp.repository;

import com.swedist.desktopapp.model.Computer;
import com.swedist.desktopapp.model.Student;
import java.sql.*;

public class CLMRepository {
    // JDBC parameters
    private final String URL = "jdbc:postgresql://127.0.0.1:5432/ComputerLab";
    private final String USER = "postgres";
    private final String PASSWORD = "1234";
    private Connection conn = null;
    private Statement stm = null;
    private ResultSet rs = null;

    public Student getStudentById(int studentId) throws SQLException
    {
        conn = DriverManager.getConnection(URL, USER, PASSWORD);
        String query = "SELECT * FROM student WHERE student_id =" + studentId + ";";
        stm = conn.createStatement();
        rs = stm.executeQuery(query);

        Student student = new Student();
        while(rs.next())
        {
            student.setId(rs.getInt("student_id"));
            student.setName(rs.getString("student_name"));
            student.setDepartment(rs.getString("department"));
        }
        conn.close();
        return student;
    }

    public Computer getComputerById(int computerId) throws SQLException
    {
        conn = DriverManager.getConnection(URL, USER, PASSWORD);
        String query = "SELECT * FROM computer WHERE computer_id=" + computerId + ";";
        stm = conn.createStatement();
        rs = stm.executeQuery(query);

        Computer computer = new Computer();
        while (rs.next())
        {
            computer.setId(rs.getInt("computer_id"));
            computer.setBrand(rs.getString("brand"));
            computer.setModel(rs.getString("model"));
        }
        conn.close();
        return computer;
    }

    // Add a new Student
    public void addStudent(Student student1) throws SQLException
    {
        conn = DriverManager.getConnection(URL, USER, PASSWORD);
        stm = conn.createStatement();
        String query = "INSERT INTO student (student_name,department) VALUES ('" + student1.getName() + "','" + student1.getDepartment() +"');";
        stm.execute(query);
        conn.close();
    }

    public void updateStudent(Student student) throws SQLException
    {
        Student std = getStudentById(student.getId());
        if (std != null){
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            String query = "UPDATE student SET student_name ='"+ student.getName() + "', department = '"+student.getDepartment() + "'WHERE student_id ="+ student.getId()+";";
            stm = conn.createStatement();
            stm.execute(query);
            conn.close();
        }
    }

    public void deleteStudent(Student student) throws SQLException
    {
        Student std = getStudentById(student.getId());
        if (std != null){
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            String query = "DELETE FROM student WHERE student_id="+ student.getId() + ";";
            stm = conn.createStatement();
            stm.execute(query);
            conn.close();
        }
    }
}
