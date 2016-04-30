package br.com.padaria.model;

/**
 * Created by Gilberto on 27/04/2016.
 */
public class Produto {

    private Integer produtoID;
    private String nome;
    private String descricao;
    private Integer quantidade;
    private Double valor;

    public Produto() {
    }

    public Produto(Integer produtoID, String nome, String descricao, Integer quantidade, Double valor) {
        this.produtoID = produtoID;
        this.nome = nome;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public Integer getProdutoID() {
        return produtoID;
    }

    public void setProdutoID(Integer produtoID) {
        this.produtoID = produtoID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
