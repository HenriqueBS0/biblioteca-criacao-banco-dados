package com.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexao {
    public static void main(String[] args) throws SQLException {

        Connection conexao = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");   
            conexao = DriverManager.getConnection("jdbc:mysql://localhost/aviacao", "root", "123456");
            ResultSet resultSet = conexao.createStatement().executeQuery("SELECT * FROM crew");

            while (resultSet.next()) {
                System.out.println("Nome: " + resultSet.getString("crew_job"));
            }

        } catch (ClassNotFoundException exception) {
            System.out.println("Driver não localizado");
        } catch (SQLException exception) {            
            System.out.println("Erro ao acessar o banco: " + exception.getMessage());
        } finally {
            if(conexao != null) {
                conexao.close();
            }
        }
    }
}
