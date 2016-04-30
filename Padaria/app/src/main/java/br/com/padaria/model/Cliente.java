package br.com.padaria.model;

/**
 * Created by Gilberto on 27/04/2016.
 */
public class Cliente {

    private Integer ClienteID;
    private String nome;
    private Integer idade;
    private String sexo;

    public Cliente() {
    }

    public Cliente(Integer clienteID, String nome, Integer idade, String sexo) {
        ClienteID = clienteID;
        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo;
    }

    public Integer getClienteID() {
        return ClienteID;
    }

    public void setClienteID(Integer clienteID) {
        ClienteID = clienteID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}
