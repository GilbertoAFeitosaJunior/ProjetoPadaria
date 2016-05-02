package br.com.padaria.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Gilberto on 27/04/2016.
 */
public class PadariaOpenHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "db_padaria";
    private static final int VERSION_DATABASE = 1;

    public PadariaOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION_DATABASE);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SQL_TABELA_CLIENTE);
        db.execSQL(SQL_TABELA_PRODUTO);
        db.execSQL(SQL_TABELA_PEDIDO);
        db.execSQL(SQL_TABELA_LISTAPEDIDO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static final String NAME_TABELA_CLIENTE = "tbl_cliente";
    private static final String SQL_TABELA_CLIENTE =
            "CREATE TABLE tbl_cliente(" +
                    "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                    "nome TEXT," +
                    "idade INTEGER, "+
                    "sexo TEXT "+
                    ");";


    public static final String NAME_TABELA_PRODUTO = "tbl_produto";
    private static final String SQL_TABELA_PRODUTO =
            "CREATE TABLE tbl_produto(" +
                    "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                    "nome TEXT," +
                    "descricao TEXT," +
                    "quantidade INTEGER, "+
                    "valor REAL " +
                    ");";


    public static final String NAME_TABELA_PEDIDO = "tbl_pedido";
    private static final String SQL_TABELA_PEDIDO =
            "CREATE TABLE tbl_pedido(" +
                    "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                    "nomeCliente TEXT," +
                    "dataPedido TEXT" +
                    ");";

    public static final String NAME_TABELA_LISTAPEDIDO = "tbl_lista";
    private static final String SQL_TABELA_LISTAPEDIDO =
            "CREATE TABLE tbl_lista(" +
                    "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                    "codigoPedido, " +
                    "nomeProduto TEXT," +
                    "quantidade INTEGER," +
                    "valor REAL " +
                    ");";




}
