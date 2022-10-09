package com.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    public static Connection get(String jdbcDriver, String jdbcUrl, String port, String database, String user, String password) throws ClassNotFoundException, SQLException {
        Class.forName(jdbcDriver);

        String urlConnetion = String.format("%s:%s/%s", jdbcUrl, port, database);

        return DriverManager.getConnection(urlConnetion, user, password);
    }
}
