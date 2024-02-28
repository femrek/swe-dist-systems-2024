package com.swedist.desktopapp.repository;

import java.sql.*;

public class CLMRepository {
    // JDBC parameters
    private final String URL = "jdbc:mysql://127.0.0.1:3306/cr";
    private final String USER = "root";
    private final String PASSWORD = "mysql";
    private Connection conn = null;
    private Statement stm = null;
    private ResultSet rs = null;
}
