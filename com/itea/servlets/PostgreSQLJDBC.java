package com.itea.servlets;

import java.sql.*;

public class PostgreSQLJDBC {
    static final String GET_LOGIN_PASSWORD = "SELECT full_name FROM users WHERE login = ? AND password = ?";
    private Connection connection = null;
    private StringBuilder error = new StringBuilder();

    public Connection establishConnection() {
        final String url = "jdbc:postgresql://localhost:5432/testDB";
        final String user = "postgres";
        final String password = "psql";
        if (this.connection == null) {
            try {
                Class.forName("org.postgresql.Driver");
                this.connection = DriverManager.getConnection(url, user, password);
                System.out.println("Opened database successfully");
            } catch (SQLException | ClassNotFoundException e) {
                error = new StringBuilder(e.getMessage());
            }
        }
        return this.connection;
    }

    public void closeConnection() {
        if (this.connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                error = new StringBuilder(e.getMessage());
            }
        }
    }

    public String getError() {
        return error.toString();
    }

    public String getFullNameByLoginAndPassword(String login, String password) {
        String fullName = null;
        if (this.connection != null && login != null && password != null) {
            try {
                PreparedStatement statement = this.connection.prepareStatement(GET_LOGIN_PASSWORD);
//                Statement statement = this.connection.createStatement();
                statement.setString(1, login);
                statement.setString(2, password);
                ResultSet resultSet = statement.executeQuery();
//                ResultSet resultSet = statement.executeQuery( "SELECT * FROM users WHERE login = '" + login + "';");
                if (resultSet.next()) {
                    fullName = resultSet.getString("full_name");
                    System.out.println( "login = " + login + ",     password = " + password);
                }
                resultSet.close();
                statement.close();
            } catch (SQLException e) {
                error = new StringBuilder(e.getMessage());
            }
        }
        return fullName;
    }

}
