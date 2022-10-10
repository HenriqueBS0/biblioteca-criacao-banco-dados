package com.criadorBD.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    public String host;
    public String port;
    public String user;
    public String pass;

    public static Connection get(Conexao conexao, String jdbcDriver, String jdbcUrl) throws ClassNotFoundException, SQLException {
        Class.forName(jdbcDriver);

        String urlConnetion = String.format("%s://%s:%s/", jdbcUrl, conexao.host, conexao.port);

        return DriverManager.getConnection(urlConnetion, conexao.user, conexao.pass);
    }
}
