package com.criadorBD.gerenciadores.montadores.mysql;

import com.criadorBD.estruturas.BaseDados;
import com.criadorBD.gerenciadores.montadores.interfaces.IMontadorBaseDados;

public class MontadorBaseDadosMySql implements IMontadorBaseDados {
    public String getSql(BaseDados baseDados) {        
        String modelo = "CREATE DATABASE `{{base-dados-nome}}`;" ;
        return modelo.replace("{{base-dados-nome}}", baseDados.nome);
    }
}
