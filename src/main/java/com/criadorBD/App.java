package com.criadorBD;

import java.sql.SQLException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class App {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, JsonMappingException, JsonProcessingException {

        String json = "{\"gerenciador\":\"mysql\",\"conexao\":{\"host\":\"localhost\",\"port\":\"3306\",\"user\":\"root\",\"pass\":\"123456\"},\"baseDados\":{\"nome\":\"testeHenricao\",\"tabelas\":[{\"nome\":\"usuario\",\"colunas\":[{\"nome\":\"id\",\"tipo\":\"INT\",\"chavePrimaria\":true,\"naoNula\":true,\"serial\":true},{\"nome\":\"nome\",\"tipo\":\"TEXT\",\"chavePrimaria\":false,\"naoNula\":false,\"serial\":false}]},{\"nome\":\"foto\",\"colunas\":[{\"nome\":\"id\",\"tipo\":\"INT\",\"chavePrimaria\":true,\"naoNula\":true,\"serial\":true},{\"nome\":\"usuario\",\"tipo\":\"INT\",\"chavePrimaria\":false,\"naoNula\":true,\"serial\":false},{\"nome\":\"arquivo\",\"tipo\":\"TEXT\",\"chavePrimaria\":false,\"naoNula\":true,\"serial\":false}]}]}}";
        Construtor construtor = new Construtor(json);
        //System.out.println(construtor.getSql());
        construtor.executar();
    }
}
