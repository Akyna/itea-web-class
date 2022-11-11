package com.itea.servlets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LoginDB {
    private String message = "";

    public void getConnection() {
/*

package com.vertex.academy.databases;

//STEP 1. Import required packages
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCPostgreSQLExample {

   //  Database credentials
      static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/vertex"
      static final String USER = "username";
      static final String PASS = "password";

  public static void main(String[] argv) {

	System.out.println("Testing connection to PostgreSQL JDBC");

	try {
		Class.forName("org.postgresql.Driver");
	} catch (ClassNotFoundException e) {
		System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
		e.printStackTrace();
		return;
	}

	System.out.println("PostgreSQL JDBC Driver successfully connected");
	Connection connection = null;

	try {
		connection = DriverManager
		.getConnection(DB_URL, USER, PASS);

	} catch (SQLException e) {
		System.out.println("Connection Failed");
		e.printStackTrace();
		return;
	}

	if (connection != null) {
		System.out.println("You successfully connected to database now");
	} else {
		System.out.println("Failed to make connection to database");
	}
  }
}
*/
    }

    public boolean isAuthorized(String login, String password) {
        boolean result = false;
        this.message = "";
        if (login != null) {
            if ("admin".equals(login) && "123".equals(password)) {
                this.message = "Access granted";
                result = true;
            } else {
                this.message = "Access denied";
                result = false;
            }
        }
        return result;
    }

    public String getMessage() {
        return this.message;
    };

}

