package com.criadorBD.gerenciadores;

import java.sql.SQLException;

import com.criadorBD.conexao.Conexao;
import com.criadorBD.estruturas.BaseDados;

public interface Gerenciador {
    public String getJdbcDriver();
    public String getJdbcUrl();
    public String getSql(BaseDados baseDados);
    public void executar(Conexao conexao, BaseDados baseDados) throws ClassNotFoundException, SQLException;
}
