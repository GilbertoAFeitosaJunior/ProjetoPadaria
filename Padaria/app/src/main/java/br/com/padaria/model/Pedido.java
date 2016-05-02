package br.com.padaria.model;

/**
 * Created by Gilberto on 01/05/2016.
 */
public class Pedido {

    private Integer pedidoID;
    private String nomeCliente;
    private String dataPedido;

    public Pedido() {
    }

    public Pedido(Integer pedidoID, String nomeCliente, String dataPedido) {
        this.pedidoID = pedidoID;
        this.nomeCliente = nomeCliente;
        this.dataPedido = dataPedido;
    }

    public Integer getPedidoID() {
        return pedidoID;
    }

    public void setPedidoID(Integer pedidoID) {
        this.pedidoID = pedidoID;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(String dataPedido) {
        this.dataPedido = dataPedido;
    }
}
