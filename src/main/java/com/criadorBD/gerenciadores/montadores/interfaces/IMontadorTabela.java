package com.criadorBD.gerenciadores.montadores.interfaces;

import com.criadorBD.estruturas.BaseDados;
import com.criadorBD.estruturas.Tabela;

public interface IMontadorTabela {
    public String getSql(BaseDados baseDados, Tabela tabela);
}
