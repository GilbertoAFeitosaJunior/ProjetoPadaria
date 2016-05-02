package br.com.padaria.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.padaria.model.ListaProdutos;
import br.com.padaria.model.ListaSalvar;
import br.com.padaria.model.PadariaOpenHelper;
import br.com.padaria.model.Pedido;

/**
 * Created by Gilberto on 01/05/2016.
 */
public class RepositorioListaProdutos {

    private PadariaOpenHelper padariaOpenHelper;

    public RepositorioListaProdutos(Context context) {
        this.padariaOpenHelper = new PadariaOpenHelper(context);
    }

    public void SalvarPedido(ListaSalvar listaSalvar) {

        SQLiteDatabase db = padariaOpenHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("codigoPedido", listaSalvar.getCodigoPedido());
        contentValues.put("nomeProduto", listaSalvar.getNomeProduto());
        contentValues.put("quantidade", listaSalvar.getQuantidade());
        contentValues.put("valor", listaSalvar.getValor());
        contentValues.put("data", listaSalvar.getData());


        db.insert(PadariaOpenHelper.NAME_TABELA_LISTAPEDIDO, null, contentValues);
        db.close();
    }

    public List<ListaSalvar> exibirDetalhesPedido(Pedido pedido) {
        SQLiteDatabase db = this.padariaOpenHelper.getWritableDatabase();

        Cursor cursor = db.query(PadariaOpenHelper.NAME_TABELA_LISTAPEDIDO, new String[]{"id", "codigoPedido", "nomeProduto", "quantidade", "valor", "data"},
                null, null, null, null, null);

        List<ListaSalvar> listaSalvar = new ArrayList<>();

        while (cursor.moveToNext()) {

            Integer id = cursor.getInt(cursor.getColumnIndex("id"));
            Integer codigoPedido = cursor.getInt(cursor.getColumnIndex("codigoPedido"));
            String nomeProduto = cursor.getString(cursor.getColumnIndex("nomeProduto"));
            Integer quantidade = cursor.getInt(cursor.getColumnIndex("quantidade"));
            Double valor = cursor.getDouble(cursor.getColumnIndex("valor"));
            String data = cursor.getString(cursor.getColumnIndex("data"));


            if (pedido.getPedidoID() == codigoPedido && data.equals(pedido.getDataPedido())) {
                ListaSalvar lista = new ListaSalvar(id, codigoPedido, nomeProduto, quantidade, valor, data);
                listaSalvar.add(lista);
            }

        }

        return listaSalvar;
    }
}
