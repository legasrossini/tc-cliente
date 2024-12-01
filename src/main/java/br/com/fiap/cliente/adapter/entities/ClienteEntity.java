package br.com.fiap.cliente.adapter.entities;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@DynamoDBTable(tableName = "Cliente")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteEntity {

    @DynamoDBHashKey(attributeName = "cpf")
    private String cpf;
    private String nome;
    private String email;
}