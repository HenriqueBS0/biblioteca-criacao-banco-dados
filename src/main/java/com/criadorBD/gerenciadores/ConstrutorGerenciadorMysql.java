package com.criadorBD.gerenciadores;

import com.criadorBD.gerenciadores.montadores.mysql.FabricaMontadoresEstruturasMySql;

public class ConstrutorGerenciadorMysql extends ConstrutorGerenciador {

    public void setFabricaMontadoresEstruturas() {
        gerenciador.setFabricaMontadoresEstruturas(new FabricaMontadoresEstruturasMySql());
    }

    public void setJdbcDriver() {
        gerenciador.setJdbcDriver("com.mysql.cj.jdbc.Driver");
    }

    public void setJdbcUrl() {
        gerenciador.setJdbcUrl("jdbc:mysql");
    }

    public Gerenciador construir() {
        setFabricaMontadoresEstruturas();
        setJdbcDriver();
        setJdbcUrl();
        return gerenciador;
    }   
}
