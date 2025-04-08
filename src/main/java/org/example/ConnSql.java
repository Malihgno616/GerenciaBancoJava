package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnSql {
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String DB_NAME = "gerencia_banco";
    private final String DB_USER = "root";
    private final String DB_PASS = "";

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName(DRIVER);
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/" + DB_NAME, DB_USER, DB_PASS);
    }

    public void closeConnection(Connection conn) throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }

    public int getIdCliente() {
        int idCliente = getIdCliente();
        return getIdCliente();
    }

    private int idCliente() {
        return idCliente();
    }
}
