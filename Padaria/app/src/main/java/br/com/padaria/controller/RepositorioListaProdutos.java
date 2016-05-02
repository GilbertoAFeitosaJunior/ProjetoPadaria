package br.com.padaria.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

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

    public void SalvarPedido(ListaSalvar  listaSalvar) {

        SQLiteDatabase db = padariaOpenHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("codigoPedido", listaSalvar.getCodigoPedido());
        contentValues.put("nomeProduto", listaSalvar.getNomeProduto());
        contentValues.put("quantidade", listaSalvar.getQuantidade());
        contentValues.put("valor", listaSalvar.getValor());


        db.insert(PadariaOpenHelper.NAME_TABELA_LISTAPEDIDO, null, contentValues);
        db.close();
    }

}
