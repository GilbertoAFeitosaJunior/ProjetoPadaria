package br.com.padaria.view;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.com.padaria.R;
import br.com.padaria.controller.RepositorioCliente;
import br.com.padaria.controller.RepositorioListaProdutos;
import br.com.padaria.controller.RepositorioPedidos;
import br.com.padaria.controller.RepositorioProduto;
import br.com.padaria.model.Cliente;
import br.com.padaria.model.ListaProdutos;
import br.com.padaria.model.ListaSalvar;
import br.com.padaria.model.Pedido;
import br.com.padaria.model.Produto;
import br.com.padaria.model.ProdutoPedidoAdapter;


/**
 * Created by Gilberto on 01/05/2016.
 */
public class NovoPedido extends Activity {


    private RepositorioProduto repositorioProduto;
    private EditText codCliente, addProduto, quantEditText;
    private TextView txtCliente, textNomeProduto, textDescProduto, valorCompra, textPrecoUn, totalItem;
    private RepositorioCliente repositorioCliente;
    private Produto produto1;
    private Cliente cliente;
    private List<ListaProdutos> produtoLista = new ArrayList<>();
    private ListaProdutos listaProdutos;
    private ListView listView;
    private RepositorioPedidos repositorioPedidos;
    private String data_completa;
    private Integer codigoPedido = 0;
    private RepositorioListaProdutos repositorioListaProdutos;
    private Boolean opc = true;
    private Integer valida = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.novo_pedido_activity);

        ActionBar bar = getActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#cd950c")));

        repositorioProduto = new RepositorioProduto(this);
        repositorioCliente = new RepositorioCliente(this);
        repositorioPedidos = new RepositorioPedidos(this);
        repositorioListaProdutos = new RepositorioListaProdutos(this);


        txtCliente = (TextView) findViewById(R.id.txtCliente);
        codCliente = (EditText) findViewById(R.id.codCliente);
        valorCompra = (TextView) findViewById(R.id.valorCompra);
        textPrecoUn = (TextView) findViewById(R.id.textPrecoUn);
        totalItem = (TextView) findViewById(R.id.totalItem);

        textNomeProduto = (TextView) findViewById(R.id.textNomeProduto);
        addProduto = (EditText) findViewById(R.id.addProduto);
        textDescProduto = (TextView) findViewById(R.id.textDescProduto);
        quantEditText = (EditText) findViewById(R.id.quantEditText);
        listView = (ListView) findViewById(R.id.listView);

        listView = (ListView) findViewById(R.id.listView);
        listView.setEmptyView(findViewById(R.id.msg_lista_vazia));
        registerForContextMenu(listView);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuItem menuVoltar = menu.add(0, 0, 0, "Voltar");
        menuVoltar.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        menuVoltar.setIcon(R.drawable.voltar);
        return true;
    }

    public void Buscar(View view) {
        switch (view.getId()) {
            case R.id.bntBuscarCliente:
                if (!codCliente.getText().toString().equals("")) {
                    cliente = BuscarCliente(codCliente.getText().toString());
                    if (cliente != null) {
                        txtCliente.setText(cliente.getNome().toString().toUpperCase());
                        txtCliente.setTextColor(Color.BLACK);
                    } else {
                        Toast.makeText(NovoPedido.this, "Cliente NÃO CADASTRADO", Toast.LENGTH_SHORT).show();
                        txtCliente.setText("CLIENTE NÃO CADASTRADO");
                        txtCliente.setTextColor(Color.RED);
                        codCliente.setText("");
                    }

                } else {
                    Toast.makeText(NovoPedido.this, "Cliente Padrão", Toast.LENGTH_SHORT).show();
                    txtCliente.setText("CLIENTE PADRÃO");
                }
                break;

            case R.id.bntBuscarProduto:
                if (!addProduto.getText().toString().equals("")) {
                    produto1 = BuscarProduto(addProduto.getText().toString());

                    if (produto1 != null) {
                        textNomeProduto.setText(produto1.getNome().toString().toUpperCase());
                        textDescProduto.setText(produto1.getDescricao().toString().toUpperCase());
                        Double teste = produto1.getValor();
                        DecimalFormat df = new DecimalFormat("0.00");

                        textPrecoUn.setText("R$ " + df.format(teste).toString());

                        textNomeProduto.setTextColor(Color.BLACK);
                        textDescProduto.setTextColor(Color.BLACK);
                        textPrecoUn.setTextColor(Color.BLACK);

                    } else {
                        Toast.makeText(NovoPedido.this, "Produto NÃO CADASTRADO", Toast.LENGTH_SHORT).show();
                        textNomeProduto.setText("PRODUTO NÃO CADASTRADO");
                        textNomeProduto.setTextColor(Color.RED);
                        addProduto.setText("");
                        textDescProduto.setText("");
                        textPrecoUn.setText("");
                    }
                } else {
                    Toast.makeText(NovoPedido.this, "O Campo Produto É OBRIGATORIO", Toast.LENGTH_SHORT).show();
                    textNomeProduto.setText("O CAMPO PRODUTO É OBRIGATORIO");
                    textNomeProduto.setText("");
                }
                break;

            case R.id.incluir:
                try {

                    if (!addProduto.getText().toString().equals("")) {
                        if (!quantEditText.getText().toString().equals("")) {
                            Integer validacao = Integer.parseInt(quantEditText.getText().toString());
                            if (!(validacao <= 0)) {
                                if (Integer.parseInt(quantEditText.getText().toString()) <= produto1.getQuantidade()) {

                                    for (ListaProdutos item : produtoLista) {
                                        if (item.getProduto().getProdutoID() == produto1.getProdutoID()) {

                                            for (ListaProdutos item1 : produtoLista) {
                                                if (item.getProduto().getProdutoID() == produto1.getProdutoID()) {
                                                    valida += item.getQuantidade();
                                                }
                                            }

                                            Integer n = valida + Integer.parseInt(quantEditText.getText().toString());
                                            Integer nn = produto1.getQuantidade();

                                            if (n > nn) {
                                                Toast.makeText(NovoPedido.this, "QUANTIDADE MAIOR QUE O ESTOQUE", Toast.LENGTH_SHORT).show();
                                                opc = false;
                                                valida = 0;
                                            }
                                            valida = 0;
                                        }
                                    }
                                    if (opc) {
                                        listaProdutos = new ListaProdutos();
                                        listaProdutos.setProduto(produto1);
                                        listaProdutos.setQuantidade(Integer.parseInt(quantEditText.getText().toString()));

                                        produtoLista.add(listaProdutos);


                                        exibirProdutos(produtoLista);

                                        addProduto.setText("");
                                        textNomeProduto.setText("");
                                        textNomeProduto.setText("");
                                        quantEditText.setText("");
                                        textDescProduto.setText("");
                                        textPrecoUn.setText("");
                                        opc = true;
                                    }


                                } else {
                                    Toast.makeText(NovoPedido.this, "QUANTIDADE MAIOR QUE O ESTOQUE", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(NovoPedido.this, "CAMPO QUANTIDADE DEVER SER > 0", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Toast.makeText(NovoPedido.this, "CAMPO QUANTIDADE É OBRIGATÓDIO", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(NovoPedido.this, "CAMPO QUANTIDADE É OBRIGATÓDIO", Toast.LENGTH_SHORT).show();
                    }

                    opc = true;

                } catch (Exception ex) {
                    Toast.makeText(NovoPedido.this, "CAMPO QUANTIDADE É OBRIGATÓDIO", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }


    //listar produtos
    public void exibirProdutos(List<ListaProdutos> listaProdutos) {

        Double totalCompra = 0.0;
        Integer itemTotal = 0;
        for (ListaProdutos item : listaProdutos) {
            item.setValor(item.getProduto().getValor() * item.getQuantidade());
            totalCompra += item.getValor();
            itemTotal += item.getQuantidade();
        }


        ProdutoPedidoAdapter adapter = new ProdutoPedidoAdapter(this, produtoLista);
        this.listView.setAdapter(adapter);
        Double teste = totalCompra;
        DecimalFormat df = new DecimalFormat("0.00");
        valorCompra.setText(df.format(teste).toString());
        totalItem.setText(itemTotal.toString());

    }

    public Cliente BuscarCliente(String codigo) {
        List<Cliente> listaCliente = repositorioCliente.listarTodosClientes();
        Integer cod = Integer.parseInt(codigo);
        for (Cliente cliente : listaCliente) {
            if (cliente.getClienteID() == cod) {
                return cliente;
            }
        }
        return null;
    }

    public Produto BuscarProduto(String codigo) {
        List<Produto> listaProduto = repositorioProduto.listarTodosProdutos();
        Integer cod = Integer.parseInt(codigo);
        for (Produto produto : listaProduto) {
            if (produto.getProdutoID() == cod) {
                return produto;
            }
        }
        return null;
    }


    public void SalvarPedido(View view) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-HH:mm:ss");
        Date data = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        Date data_atual = cal.getTime();
        data_completa = dateFormat.format(data_atual);

        if (!produtoLista.isEmpty()) {
            Pedido pedido = new Pedido();


            if (cliente == null) {
                cliente = new Cliente();
                cliente.setNome("Cliente Padrão");

            }
            pedido.setNomeCliente(cliente.getNome());

            pedido.setDataPedido(data_completa);
            repositorioPedidos.SalvarPedido(pedido);

            for (Pedido item : repositorioPedidos.listarPedidos()) {
                if (pedido.getDataPedido().equals(item.getDataPedido())) {
                    codigoPedido = item.getPedidoID();
                }
            }

            if (codigoPedido == null) {
                codigoPedido = 0;
            }

            for (ListaProdutos item : produtoLista) {

                ListaSalvar listaSalvar = new ListaSalvar();

                listaSalvar.setCodigoPedido(codigoPedido);
                listaSalvar.setNomeProduto(item.getProduto().getNome());
                listaSalvar.setQuantidade(item.getQuantidade());
                listaSalvar.setValor(item.getValor());
                listaSalvar.setData(data_completa);


                produto1.setQuantidade(produto1.getQuantidade() - item.getQuantidade());
                repositorioProduto.EditarProduto(produto1);
                repositorioListaProdutos.SalvarPedido(listaSalvar);
            }


            txtCliente.setText("");
            codCliente.setText("");
            addProduto.setText("");
            textNomeProduto.setText("");
            textNomeProduto.setText("");
            quantEditText.setText("");
            textDescProduto.setText("");
            textPrecoUn.setText("");
            produtoLista.clear();
            exibirProdutos(produtoLista);


            Toast.makeText(NovoPedido.this, "Pedido Salvo com sucesso.", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(NovoPedido.this, "Erro: Lista de Pedido Vazia", Toast.LENGTH_SHORT).show();
        }


    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.pedido_produto_menu_contexto, menu);
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = null;
        if (!item.getTitle().equals("Voltar")) {
            info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        }
        switch (item.getItemId()) {

            case 0:
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
            case R.id.menu_pedido_excluir:
                produtoLista.remove(info.position);
                exibirProdutos(produtoLista);
                Toast.makeText(NovoPedido.this, "Item REMOVIDO", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return false;
        }

        return true;

    }


}
