package br.com.padaria.model;

/**
 * Created by Gilberto on 01/05/2016.
 */
public class ListaSalvar  {

    private Integer id;
    private Integer codigoPedido;
    private String nomeProduto;
    private Integer quantidade;
    private Double valor;
    private String data;

    public ListaSalvar() {
    }

    public ListaSalvar(Integer id, Integer codigoPedido, String nomeProduto, Integer quantidade, Double valor, String data) {
        this.id = id;
        this.codigoPedido = codigoPedido;
        this.nomeProduto = nomeProduto;
        this.quantidade = quantidade;
        this.valor = valor;
        this.data = data;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCodigoPedido() {
        return codigoPedido;
    }

    public void setCodigoPedido(Integer codigoPedido) {
        this.codigoPedido = codigoPedido;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
