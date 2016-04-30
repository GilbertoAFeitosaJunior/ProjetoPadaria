package br.com.padaria.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.padaria.model.Cliente;
import br.com.padaria.model.PadariaOpenHelper;

/**
 * Created by Gilberto on 29/04/2016.
 */
public class RepositorioCliente {

    private PadariaOpenHelper padariaOpenHelper;

    public RepositorioCliente(Context context) {
        this.padariaOpenHelper = new PadariaOpenHelper(context);
    }


    public void SalvarCliente(Cliente cliente) {

        SQLiteDatabase db = padariaOpenHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", cliente.getNome());
        contentValues.put("idade", cliente.getIdade());
        contentValues.put("sexo", cliente.getSexo());

        db.insert(PadariaOpenHelper.NAME_TABELA_CLIENTE, null, contentValues);
        db.close();
    }


    public List<Cliente> listarTodosClientes() {

        SQLiteDatabase db = this.padariaOpenHelper.getWritableDatabase();

        Cursor cursor = db.query(PadariaOpenHelper.NAME_TABELA_CLIENTE, new String[]{"id", "nome", "idade", "sexo"},
                null, null, null, null, null);

        List<Cliente> listaDeClientes = new ArrayList<>();

        while (cursor.moveToNext()) {
            Integer id = cursor.getInt(cursor.getColumnIndex("id"));
            String nome = cursor.getString(cursor.getColumnIndex("nome"));
            Integer idade = cursor.getInt(cursor.getColumnIndex("idade"));
            String sexo = cursor.getString(cursor.getColumnIndex("sexo"));

            Cliente cliente = new Cliente(id, nome, idade, sexo);
            listaDeClientes.add(cliente);
        }

        return listaDeClientes;
    }


    public void deletarCliente(Cliente cliente) {

        SQLiteDatabase db = this.padariaOpenHelper.getWritableDatabase();

        db.delete(PadariaOpenHelper.NAME_TABELA_CLIENTE, "id = " + cliente.getClienteID(), null);
        db.close();
    }


    public void editarCliente(Cliente cliente) {

        SQLiteDatabase db = this.padariaOpenHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", cliente.getNome());
        contentValues.put("idade", cliente.getIdade());
        contentValues.put("sexo", cliente.getSexo());

        db.update(PadariaOpenHelper.NAME_TABELA_CLIENTE, contentValues, "id = " + cliente.getClienteID(), null);
        db.close();
    }
}
