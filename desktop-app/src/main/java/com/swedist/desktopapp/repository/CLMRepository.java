package com.swedist.desktopapp.repository;

import com.swedist.desktopapp.model.Student;

import java.sql.*;

public class CLMRepository {
    // JDBC parameters
    private final String URL = "jdbc:postgresql://127.0.0.1:3306/cr";
    private final String USER = "root";
    private final String PASSWORD = "1234";
    private Connection conn = null;
    private Statement stm = null;
    private ResultSet rs = null;

    public Student getStudentById(int studentId) throws SQLException
    {
        conn = DriverManager.getConnection(URL, USER, PASSWORD);
        String query = "SELECT * FROM student WHERE id =" + studentId + ";";
        stm = conn.createStatement();
        rs = stm.executeQuery(query);

        Student student = new Student();
        while(rs.next())
        {
            student.setId(rs.getInt("id"));
            student.setName(rs.getString("name"));
            student.setDepartment(rs.getString("department"));
        }
        conn.close();
        return student;
    }
}
