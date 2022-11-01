package com.criadorBD;

import java.sql.SQLException;

import com.criadorBD.gerenciadores.ConstrutorGerenciadorMysql;
import com.criadorBD.gerenciadores.Gerenciador;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Construtor {

    ConstrutorConfiguracao configuracao;

    public Construtor(String json) throws JsonMappingException, JsonProcessingException {
        configuracao = getConfiguracaoFromJson(json);
    }

    private ConstrutorConfiguracao getConfiguracaoFromJson(String json) throws JsonMappingException, JsonProcessingException {
        ObjectMapper jsonMapper = new ObjectMapper();
        return jsonMapper.readValue(json, ConstrutorConfiguracao.class);
    }

    public String getSql() {
        return getGerenciador(configuracao.gerenciador).getSql(configuracao.baseDados);
    }

    public void executar() throws ClassNotFoundException, SQLException {
        getGerenciador(configuracao.gerenciador).executar(configuracao.conexao, configuracao.baseDados);
    }

    private Gerenciador getGerenciador(String nome) {
        switch (nome) {
            case "mysql":
                return (new ConstrutorGerenciadorMysql()).construir();
        }

        return null;
    } 
}
