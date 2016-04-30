package br.com.padaria.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import br.com.padaria.R;
import br.com.padaria.controller.RepositorioProduto;
import br.com.padaria.model.Produto;
import br.com.padaria.model.ProdutoAdapter;

/**
 * Created by Gilberto on 30/04/2016.
 */
public class ProdutoActivity extends Activity {

    private ListView produtoListView;
    private LayoutInflater inflater;
    private View viewCadastrarProduto, viewDetalhesProduto, viewNovaQuantProduto, viewEditarProduto;
    private EditText nomeProduto, descricaoProduto, quantidadeProduto, valorProduto, novaQuantidadeProduto, localizarProduto;
    private EditText nomeProdutoEditar, descricaoProdutoEditar, valorProdutoEditar;
    private TextView quantidadeProdutoEditar;
    private RepositorioProduto repositorioProduto;
    private List<Produto> listaDeProdutos;
    private ProdutoAdapter produtoAdapter;
    private Produto produto;
    private TextView codigoProdutoContext, nomeProdutoContext, descriacaoProdutoContext, quatidadeProdutoContext, valorProdutoContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.protudo_activity);
        repositorioProduto = new RepositorioProduto(this);

        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        produtoListView = (ListView) findViewById(R.id.produtoListView);
        produtoListView.setEmptyView(findViewById(R.id.msg_lista_vazia));

        exibirTodosProdutos();

        registerForContextMenu(produtoListView);

        localizarProduto = (EditText) findViewById(R.id.localizarProduto);
        localizarProduto.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals("")) {
                    exibirTodosProdutos();

                } else {
                    searchItem(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }


    //localizar
    public void searchItem(String textToSearch) {
        for (Produto item : listaDeProdutos) {
            if (!item.getNome().toLowerCase().contains(textToSearch.toLowerCase())) {
                listaDeProdutos.remove(item);
                produtoAdapter.notifyDataSetChanged();
                return;
            }
        }
    }

    //MENU PRINCIPAL
    public void menuProduto(View view) {
        switch (view.getId()) {
            case R.id.voltar:
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
            case R.id.addProduto:
                adicionarProduto();
                break;

        }
    }


    public void adicionarProduto() {
        viewCadastrarProduto = inflater.inflate(R.layout.add_produto_activity, null);

        nomeProduto = (EditText) viewCadastrarProduto.findViewById(R.id.nomeProduto);
        descricaoProduto = (EditText) viewCadastrarProduto.findViewById(R.id.descricaoProduto);
        quantidadeProduto = (EditText) viewCadastrarProduto.findViewById(R.id.quantidadeProduto);
        valorProduto = (EditText) viewCadastrarProduto.findViewById(R.id.valorProduto);


        AlertDialog.Builder dlg = new AlertDialog.Builder(this);
        dlg.setTitle("CADASTRAR PRODUTO");
        dlg.setView(viewCadastrarProduto);

        dlg.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                if (nomeProduto.getText().toString().equals("")) {
                    Toast.makeText(ProdutoActivity.this, "Campo NOME obrigatório.", Toast.LENGTH_SHORT).show();
                    adicionarProduto();
                } else if (descricaoProduto.getText().toString().equals("")) {
                    Toast.makeText(ProdutoActivity.this, "Campo DESCRIÇÃO é obrigatório.", Toast.LENGTH_SHORT).show();
                    adicionarProduto();
                } else if (quantidadeProduto.getText().toString().equals("")) {
                    Toast.makeText(ProdutoActivity.this, "Campo QUANTIDADE é obrigatório.", Toast.LENGTH_SHORT).show();
                    adicionarProduto();
                } else if (valorProduto.getText().toString().equals("")) {
                    Toast.makeText(ProdutoActivity.this, "Campo VALOR é obrigatório.", Toast.LENGTH_SHORT).show();
                    adicionarProduto();
                } else {
                    Salvar();
                    exibirTodosProdutos();
                }

            }

        });
        dlg.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        dlg.show();
    }

    /*-------------------------------CRUD---------------------------------------------------------------------------*/
    //salvar produto no banco
    public void Salvar() {
        Produto produto = new Produto();

        produto.setNome(nomeProduto.getText().toString());
        produto.setDescricao(descricaoProduto.getText().toString());
        produto.setQuantidade(Integer.parseInt(quantidadeProduto.getText().toString()));
        produto.setValor(Double.parseDouble(valorProduto.getText().toString()));

        repositorioProduto.SalvarProduto(produto);
        Toast.makeText(ProdutoActivity.this, "Produto SALVO com Sucesso", Toast.LENGTH_SHORT).show();
    }

    //lista de clientes
    public void exibirTodosProdutos() {
        listaDeProdutos = repositorioProduto.listarTodosProdutos();
        produtoAdapter = new ProdutoAdapter(this, listaDeProdutos);
        this.produtoListView.setAdapter(produtoAdapter);
    }

    /*-------------------------------menu de contexto---------------------------------------------------------------------------*/
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.produto_menu_contexto, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        produto = (Produto) this.produtoListView.getItemAtPosition(info.position);

        switch (item.getItemId()) {

            case R.id.menu_item_produto_fechar:

                return true;
            case R.id.menu_item_produto_exibirDetalhes:
                exibirDetalhesProduto(produto);
                return true;
            case R.id.menu_item_produto_excluir:
                repositorioProduto.deletarProduto(produto);
                exibirTodosProdutos();
                Toast.makeText(ProdutoActivity.this, "Produto EXCLUIDO com sucesso.", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_item_produto_acrescentar:
                AcrescentarSaldo(produto);
                return true;
            case R.id.menu_item_produto_editar:
                EditarProduto(produto);
                return true;

            default:
                return false;
        }
    }


    public void AcrescentarSaldo(final Produto produto) {
        viewNovaQuantProduto = inflater.inflate(R.layout.acrescentar_produto_activity, null);


        novaQuantidadeProduto = (EditText) viewNovaQuantProduto.findViewById(R.id.novaQuantidadeProduto);


        AlertDialog.Builder dlg = new AlertDialog.Builder(this);
        dlg.setTitle("ACRESCENTAR SALDO");
        dlg.setView(viewNovaQuantProduto);

        dlg.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (novaQuantidadeProduto.getText().toString().equals("")) {
                    Toast.makeText(ProdutoActivity.this, "Campo Quantidade é obrigatório.", Toast.LENGTH_SHORT).show();
                    AcrescentarSaldo(produto);
                } else {
                    Integer saldo = Integer.parseInt(novaQuantidadeProduto.getText().toString());
                    produto.setQuantidade(saldo + produto.getQuantidade());
                    repositorioProduto.EditarProduto(produto);
                    Toast.makeText(ProdutoActivity.this, "Estoque Alimentoado com sucesso", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dlg.setNegativeButton("Cancelar", new DialogInterface.OnClickListener()

        {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        dlg.show();
    }


    public void exibirDetalhesProduto(Produto produto) {
        viewDetalhesProduto = inflater.inflate(R.layout.detalhes_produto_activity, null);

        codigoProdutoContext = (TextView) viewDetalhesProduto.findViewById(R.id.codigoProdutoContext);
        nomeProdutoContext = (TextView) viewDetalhesProduto.findViewById(R.id.nomeProdutoContext);
        descriacaoProdutoContext = (TextView) viewDetalhesProduto.findViewById(R.id.descriacaoProdutoContext);
        quatidadeProdutoContext = (TextView) viewDetalhesProduto.findViewById(R.id.quatidadeProdutoContext);
        valorProdutoContext = (TextView) viewDetalhesProduto.findViewById(R.id.valorProdutoContext);

        codigoProdutoContext.setText(produto.getProdutoID().toString());
        nomeProdutoContext.setText(produto.getNome());
        descriacaoProdutoContext.setText(produto.getDescricao());
        quatidadeProdutoContext.setText(produto.getQuantidade().toString());
        valorProdutoContext.setText(produto.getValor().toString());

        AlertDialog.Builder dlg = new AlertDialog.Builder(this);
        dlg.setTitle("DETALHES DO PRODUTO");
        dlg.setView(viewDetalhesProduto);

        dlg.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }
        );
        dlg.show();
    }


    public void EditarProduto(final Produto produto) {
        viewEditarProduto = inflater.inflate(R.layout.editar_produto_activity, null);

        quantidadeProdutoEditar = (TextView) viewEditarProduto.findViewById(R.id.quantidadeProdutoEditar);
        nomeProdutoEditar = (EditText) viewEditarProduto.findViewById(R.id.nomeProdutoEditar);
        descricaoProdutoEditar = (EditText) viewEditarProduto.findViewById(R.id.descricaoProdutoEditar);
        valorProdutoEditar = (EditText) viewEditarProduto.findViewById(R.id.valorProdutoEditar);

        quantidadeProdutoEditar.setText(produto.getQuantidade().toString());
        nomeProdutoEditar.setText(produto.getNome());
        descricaoProdutoEditar.setText(produto.getDescricao());
        valorProdutoEditar.setText(produto.getValor().toString());

        AlertDialog.Builder dlg = new AlertDialog.Builder(this);
        dlg.setTitle("EDITAR PRODUTO");
        dlg.setView(viewEditarProduto);

        dlg.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                if (nomeProdutoEditar.getText().toString().equals("")) {
                    Toast.makeText(ProdutoActivity.this, "Campo NOME obrigatório.", Toast.LENGTH_SHORT).show();
                    EditarProduto(produto);
                } else if (descricaoProdutoEditar.getText().toString().equals("")) {
                    Toast.makeText(ProdutoActivity.this, "Campo DESCRIÇÃO é obrigatório.", Toast.LENGTH_SHORT).show();
                    EditarProduto(produto);
                } else if (valorProdutoEditar.getText().toString().equals("")) {
                    Toast.makeText(ProdutoActivity.this, "Campo VALOR é obrigatório.", Toast.LENGTH_SHORT).show();
                    EditarProduto(produto);
                } else {
                    produto.setNome(nomeProdutoEditar.getText().toString());
                    produto.setDescricao(descricaoProdutoEditar.getText().toString());
                    produto.setValor(Double.parseDouble(valorProdutoEditar.getText().toString()));

                    repositorioProduto.EditarProduto(produto);
                    Toast.makeText(ProdutoActivity.this, "Produto ALTERADO com sucesso", Toast.LENGTH_SHORT).show();
                    exibirTodosProdutos();
                }


            }

        });
        dlg.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        dlg.show();
    }

}


