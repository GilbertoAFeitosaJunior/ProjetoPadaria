package br.com.padaria.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.padaria.model.Cliente;
import br.com.padaria.model.PadariaOpenHelper;
import br.com.padaria.model.Pedido;
import br.com.padaria.model.Produto;

/**
 * Created by Gilberto on 01/05/2016.
 */
public class RepositorioPedidos {

    private PadariaOpenHelper padariaOpenHelper;

    public RepositorioPedidos(Context context) {
        this.padariaOpenHelper = new PadariaOpenHelper(context);
    }

    public void SalvarPedido(Pedido pedido) {
        SQLiteDatabase db = padariaOpenHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nomeCliente", pedido.getNomeCliente());
        contentValues.put("dataPedido", pedido.getDataPedido());

        db.insert(PadariaOpenHelper.NAME_TABELA_PEDIDO, null, contentValues);
        db.close();
    }


    public List<Pedido> listarPedidos() {

        SQLiteDatabase db = this.padariaOpenHelper.getWritableDatabase();

        Cursor cursor = db.query(PadariaOpenHelper.NAME_TABELA_PEDIDO, new String[]{"id", "nomeCliente", "dataPedido"},
                null, null, null, null, null);

        List<Pedido> listaPedidos = new ArrayList<>();

        while (cursor.moveToNext()) {
            Integer id = cursor.getInt(cursor.getColumnIndex("id"));
            String nomeCliente = cursor.getString(cursor.getColumnIndex("nomeCliente"));
            String dataPedido = cursor.getString(cursor.getColumnIndex("dataPedido"));


            Pedido pedido = new Pedido(id, nomeCliente, dataPedido);
            listaPedidos.add(pedido);
        }

        return listaPedidos;
    }


    public List<Pedido> buscaPorID(Pedido pedido) {

        SQLiteDatabase db = this.padariaOpenHelper.getWritableDatabase();

        Cursor cursor = db.query(PadariaOpenHelper.NAME_TABELA_PEDIDO, new String[]{"id", "nomeCliente", "dataPedido"},
                null, null, null, null, null);

        List<Pedido> listaPedidos = new ArrayList<>();

        while (cursor.moveToNext()) {

            Integer id = cursor.getInt(cursor.getColumnIndex("id"));
            String nomeCliente = cursor.getString(cursor.getColumnIndex("nomeCliente"));
            String dataPedido = cursor.getString(cursor.getColumnIndex("dataPedido"));


            if (id == pedido.getPedidoID()) {
                Pedido novoPedido = new Pedido(id, nomeCliente, dataPedido);
                listaPedidos.add(novoPedido);
            }

        }

        return listaPedidos;
    }

    public List<Pedido> buscaPorNome(Pedido pedido) {
        SQLiteDatabase db = this.padariaOpenHelper.getWritableDatabase();

        Cursor cursor = db.query(PadariaOpenHelper.NAME_TABELA_PEDIDO, new String[]{"id", "nomeCliente", "dataPedido"},
                null, null, null, null, null);

        List<Pedido> listaPedidos = new ArrayList<>();

        while (cursor.moveToNext()) {

            Integer id = cursor.getInt(cursor.getColumnIndex("id"));
            String nomeCliente = cursor.getString(cursor.getColumnIndex("nomeCliente"));
            String dataPedido = cursor.getString(cursor.getColumnIndex("dataPedido"));


            if (nomeCliente.toLowerCase().equals(pedido.getNomeCliente().toLowerCase())) {
                Pedido novoPedido = new Pedido(id, nomeCliente, dataPedido);
                listaPedidos.add(novoPedido);
            }

        }

        return listaPedidos;
    }

}
