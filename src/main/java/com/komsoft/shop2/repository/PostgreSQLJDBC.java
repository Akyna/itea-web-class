package com.komsoft.shop2.repository;

import com.komsoft.shop2.exception.DataBaseException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSQLJDBC {
    private Connection connection = null;

    public void establishConnection() throws DataBaseException {
        final String url = "jdbc:postgresql://localhost:5432/testDB";
        final String user = "postgres";
        final String password = "psql";
        if (this.connection == null) {
            try {
                Class.forName("org.postgresql.Driver");
                this.connection = DriverManager.getConnection(url, user, password);
//            return this.connection;
            } catch (SQLException | ClassNotFoundException e) {
                throw new DataBaseException("Error connecting to DataBase. No DB connected");
            }
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() throws DataBaseException {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                throw new DataBaseException("Error closing connection");
            }
        }
    }

}