package com.criadorBD.gerenciadores;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.criadorBD.conexao.Conexao;
import com.criadorBD.estruturas.BaseDados;
import com.criadorBD.estruturas.Tabela;
import com.criadorBD.gerenciadores.montadores.interfaces.IFabricaMontadoresEstruturas;
import com.criadorBD.gerenciadores.montadores.interfaces.IMontadorBaseDados;
import com.criadorBD.gerenciadores.montadores.interfaces.IMontadorTabela;
public class Gerenciador {

    private IFabricaMontadoresEstruturas fabricaMontadoresEstruturas;
    private String jdbcDriver;
    private String jdbcUrl;


    public IFabricaMontadoresEstruturas getFabricaMontadoresEstruturas() {
        return this.fabricaMontadoresEstruturas;
    }

    public void setFabricaMontadoresEstruturas(IFabricaMontadoresEstruturas fabricaMontadoresEstruturas) {
        this.fabricaMontadoresEstruturas = fabricaMontadoresEstruturas;
    }

    public String getJdbcDriver() {
        return this.jdbcDriver;
    }

    public void setJdbcDriver(String jdbcDriver) {
        this.jdbcDriver = jdbcDriver;
    }

    public String getJdbcUrl() {
        return this.jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }


    public String getSql(BaseDados baseDados) {
        return String.join("\n\n", getArrayComandos(baseDados));
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

        IMontadorBaseDados montadorBaseDados = getFabricaMontadoresEstruturas().montadorBaseDados();
        IMontadorTabela montadorTabela = getFabricaMontadoresEstruturas().montadorTabela();

        comandos.add(montadorBaseDados.getSql(baseDados));

        for (Tabela tabela : baseDados.tabelas) {
            comandos.add(montadorTabela.getSql(baseDados, tabela));
        }

        return comandos;
    }

}
