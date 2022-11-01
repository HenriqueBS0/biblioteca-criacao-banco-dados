package com.criadorBD;

import java.sql.SQLException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class App {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, JsonMappingException, JsonProcessingException {

        String json = "{\"gerenciador\":\"mysql\",\"conexao\":{\"host\":\"localhost\",\"port\":\"3306\",\"user\":\"root\",\"pass\":\"123456\"},\"baseDados\":{\"nome\":\"TesteHenrique\",\"tabelas\":[{\"nome\":\"professor\",\"colunas\":[{\"nome\":\"id\",\"tipo\":\"int\"},{\"nome\":\"nome\",\"tipo\":\"text\"}]},{\"nome\":\"aluno\",\"colunas\":[{\"nome\":\"id\",\"tipo\":\"int\"},{\"nome\":\"nome\",\"tipo\":\"text\"}]}]}}";
        Construtor construtor = new Construtor(json);
        System.out.println(construtor.getSql());
        construtor.executar();
    }
}
