package com.criadorBD.gerenciadores.montadores.mysql;

import java.util.ArrayList;

import com.criadorBD.estruturas.BaseDados;
import com.criadorBD.estruturas.Coluna;
import com.criadorBD.estruturas.Tabela;
import com.criadorBD.gerenciadores.montadores.interfaces.IMontadorTabela;

public class MontadorTabelaMySql implements IMontadorTabela {

    public String getSql(BaseDados baseDados, Tabela tabela) {
        ArrayList<String> colunas = new ArrayList<String>();

        for (Coluna coluna : tabela.colunas) {
            colunas.add(getSqlCriacaoColunaTabela(coluna));
        }

        String modelo = "CREATE TABLE `{{base-dados-nome}}`.`{{tabela-nome}}` (\n{{colunas}}\n);";

        modelo = modelo.replace("{{base-dados-nome}}", baseDados.nome);
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
}
