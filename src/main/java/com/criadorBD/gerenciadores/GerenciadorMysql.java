package com.criadorBD.gerenciadores;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.criadorBD.conexao.Conexao;
import com.criadorBD.estruturas.BaseDados;
import com.criadorBD.estruturas.Coluna;
import com.criadorBD.estruturas.Tabela;

public class GerenciadorMysql implements Gerenciador {
    public String getJdbcDriver() {
        return "com.mysql.cj.jdbc.Driver";
    }

    public String getJdbcUrl() {
        return "jdbc:mysql";
    }

    public String getSql(BaseDados baseDados) {
        return String.join("\n\n", getArrayComandos(baseDados));
    }

    public String getSqlCriacaoBaseDados(BaseDados baseDados) {
        String modelo = "CREATE DATABASE `{{base-dados-nome}}`;" ;
        return modelo.replace("{{base-dados-nome}}", baseDados.nome);
    }

    public String getSqlCriacaoTabela(String baseDadosNome, Tabela tabela) {
        ArrayList<String> colunas = new ArrayList<String>();

        for (Coluna coluna : tabela.colunas) {
            colunas.add(getSqlCriacaoColunaTabela(coluna));
        }

        String modelo = "CREATE TABLE `{{base-dados-nome}}`.`{{tabela-nome}}` (\n{{colunas}}\n);";

        modelo = modelo.replace("{{base-dados-nome}}", baseDadosNome);
        modelo = modelo.replace("{{tabela-nome}}", tabela.nome);
        modelo = modelo.replace("{{colunas}}", String.join(",\n", colunas));

        return modelo;
    }

    public String getSqlCriacaoColunaTabela(Coluna coluna) {
        String modelo = "{{tabulacao}}`{{coluna-nome}}` {{coluna-tipo}}";

        modelo = modelo.replace("{{tabulacao}}", "   ");
        modelo = modelo.replace("{{coluna-nome}}", coluna.nome);
        modelo = modelo.replace("{{coluna-tipo}}", coluna.tipo);

        return modelo;
    }

    public void executar(Conexao conexao, BaseDados baseDados) throws ClassNotFoundException, SQLException {
        Connection connection = Conexao.get(conexao, getJdbcDriver(), getJdbcUrl());
        Statement statement = connection.createStatement();

        connection.setAutoCommit(false);

        for (String comando : getArrayComandos(baseDados)) {
            statement.addBatch(comando);
        }

        statement.executeBatch();

        connection.commit();
        connection.close();
    }

    private ArrayList<String> getArrayComandos(BaseDados baseDados) {
        ArrayList<String> comandos = new ArrayList<String>();

        comandos.add(this.getSqlCriacaoBaseDados(baseDados));

        for (Tabela tabela : baseDados.tabelas) {
            comandos.add(getSqlCriacaoTabela(baseDados.nome, tabela));
        }

        return comandos;
    }
}
