package br.com.padaria.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.padaria.model.Cliente;
import br.com.padaria.model.PadariaOpenHelper;
import br.com.padaria.model.Produto;

/**
 * Created by Gilberto on 30/04/2016.
 */
public class RepositorioProduto {

    private PadariaOpenHelper padariaOpenHelper;

    public RepositorioProduto(Context context) {
        this.padariaOpenHelper = new PadariaOpenHelper(context);
    }


    public void SalvarProduto(Produto produto) {

        SQLiteDatabase db = padariaOpenHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", produto.getNome());
        contentValues.put("descricao", produto.getDescricao());
        contentValues.put("quantidade", produto.getQuantidade());
        contentValues.put("valor", produto.getValor());

        db.insert(PadariaOpenHelper.NAME_TABELA_PRODUTO, null, contentValues);
        db.close();
    }


    public List<Produto> listarTodosProdutos() {

        SQLiteDatabase db = this.padariaOpenHelper.getWritableDatabase();

        Cursor cursor = db.query(PadariaOpenHelper.NAME_TABELA_PRODUTO, new String[]{"id", "nome", "descricao", "quantidade","valor"},
                null, null, null, null, null);

        List<Produto> listaDeProdutos = new ArrayList<>();

        while (cursor.moveToNext()) {
            Integer id = cursor.getInt(cursor.getColumnIndex("id"));
            String nome = cursor.getString(cursor.getColumnIndex("nome"));
            String descricao = cursor.getString(cursor.getColumnIndex("descricao"));
            Integer quantidade = cursor.getInt(cursor.getColumnIndex("quantidade"));
            Double valor = cursor.getDouble(cursor.getColumnIndex("valor"));

            Produto produto = new Produto(id, nome, descricao, quantidade, valor);
            listaDeProdutos.add(produto);
        }

        return listaDeProdutos;
    }


    public void deletarProduto(Produto produto) {

        SQLiteDatabase db = this.padariaOpenHelper.getWritableDatabase();

        db.delete(PadariaOpenHelper.NAME_TABELA_PRODUTO, "id = " + produto.getProdutoID(), null);
        db.close();
    }



    public void EditarProduto(Produto produto) {

        SQLiteDatabase db = this.padariaOpenHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", produto.getNome());
        contentValues.put("descricao", produto.getDescricao());
        contentValues.put("quantidade", produto.getQuantidade());
        contentValues.put("valor", produto.getValor());

        db.update(PadariaOpenHelper.NAME_TABELA_PRODUTO, contentValues, "id = " + produto.getProdutoID(), null);
        db.close();
    }


    public List<String> listaNomesProduto() {

        SQLiteDatabase db = this.padariaOpenHelper.getWritableDatabase();

        Cursor cursor = db.query(PadariaOpenHelper.NAME_TABELA_PRODUTO, new String[]{"id", "nome", "descricao", "quantidade","valor"},
                null, null, null, null, null);

        List<String> listaDeProdutos = new ArrayList<>();

        while (cursor.moveToNext()) {
            String nome = cursor.getString(cursor.getColumnIndex("nome"));

            listaDeProdutos.add(nome);
        }
        return listaDeProdutos;
    }

}
