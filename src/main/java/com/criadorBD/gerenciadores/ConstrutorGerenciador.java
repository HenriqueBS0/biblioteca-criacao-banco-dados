package com.criadorBD.gerenciadores;

abstract public class ConstrutorGerenciador {

    public Gerenciador gerenciador = new Gerenciador();

    abstract void setFabricaMontadoresEstruturas();
    abstract void setJdbcDriver();
    abstract void setJdbcUrl();
    abstract Gerenciador construir();
}
