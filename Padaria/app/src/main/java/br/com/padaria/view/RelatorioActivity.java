package br.com.padaria.view;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.padaria.R;
import br.com.padaria.controller.RepositorioListaProdutos;
import br.com.padaria.controller.RepositorioPedidos;
import br.com.padaria.model.Cliente;
import br.com.padaria.model.ListaSalvar;
import br.com.padaria.model.Pedido;
import br.com.padaria.model.PedidoAdapter;
import br.com.padaria.model.Produto;
import br.com.padaria.model.RelatodioDetalhesAdapter;


/**
 * Created by Gilberto on 28/04/2016.
 */
public class RelatorioActivity extends Activity {


    private ListView pedidoListView, listDetalhes;
    private TextView numeroPedidoDetalhes, valorPedidoDetalhes, clientePedidoDetalhes, dataPedidoDetalhes;
    private RepositorioPedidos repositorioPedidos;
    private List<Pedido> listaPedidos;
    private PedidoAdapter pedidoAdapter;
    private LayoutInflater inflater, inflater2;
    private View viewLocalizar, viewDetalhesLista;
    private EditText localizarPedidoNumero;
    private int opc = 0;
    private Pedido pedido;
    private List<ListaSalvar> listaSalvar;
    private RepositorioListaProdutos repositorioListaProdutos;
    private RelatodioDetalhesAdapter relatodioDetalhesAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.relatorio_activity);

        ActionBar bar = getActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#cd950c")));

        repositorioPedidos = new RepositorioPedidos(this);

        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater2 = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        pedidoListView = (ListView) findViewById(R.id.pedidoListView);
        pedidoListView.setEmptyView(findViewById(R.id.msg_lista_vazia));
        registerForContextMenu(pedidoListView);

        ExibirTodosPedidos(opc);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuItem menuVoltar = menu.add(0, 1, 1, "Voltar");
        menuVoltar.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        menuVoltar.setIcon(R.drawable.voltar);

        MenuItem pesquisarID = menu.add(0, 2, 2, "Pesquisar por Nº Pedido");
        pesquisarID.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);

        MenuItem pesquisarNome = menu.add(0, 3, 3, "Pesquisar por Nome Cliente");
        pesquisarID.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);

        MenuItem mostarTodos = menu.add(0, 4, 4, "Exibir Todos Pedidos");
        mostarTodos.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);

        return true;
    }


    public void SearchPedido() {
        viewLocalizar = inflater.inflate(R.layout.pedido_localizar, null);
        localizarPedidoNumero = (EditText) viewLocalizar.findViewById(R.id.localizarPedidoNumero);
        AlertDialog.Builder dlg = new AlertDialog.Builder(this);
        dlg.setTitle("LOCALIZAR");
        dlg.setView(viewLocalizar);

        dlg.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                for (Pedido item : repositorioPedidos.listarPedidos()) {
                    if (item.getPedidoID() == Integer.parseInt(localizarPedidoNumero.getText().toString())) {
                        pedido = item;
                        opc = 1;
                        ExibirTodosPedidos(opc);
                        return;
                    } else {
                        opc = 3;
                        ExibirTodosPedidos(opc);
                    }
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

    public void SearchPedidoNome() {
        viewLocalizar = inflater.inflate(R.layout.pedido_localizar_nome, null);

        localizarPedidoNumero = (EditText) viewLocalizar.findViewById(R.id.localizarPedidoNumero);

        AlertDialog.Builder dlg = new AlertDialog.Builder(this);
        dlg.setTitle("LOCALIZAR");
        dlg.setView(viewLocalizar);

        dlg.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                for (Pedido item : repositorioPedidos.listarPedidos()) {
                    if (item.getNomeCliente().toLowerCase().contains(localizarPedidoNumero.getText().toString().toLowerCase())) {
                        pedido = item;
                        opc = 2;
                        ExibirTodosPedidos(opc);
                        return;
                    } else {
                        opc = 3;
                        ExibirTodosPedidos(opc);
                    }
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



    //lista de pedids

    public void ExibirTodosPedidos(int opc) {
        if (opc == 0) {
            listaPedidos = repositorioPedidos.listarPedidos();
            pedidoAdapter = new PedidoAdapter(this, listaPedidos);
            this.pedidoListView.setAdapter(pedidoAdapter);
        } else if (opc == 1) {
            listaPedidos = repositorioPedidos.buscaPorID(pedido);
            pedidoAdapter = new PedidoAdapter(this, listaPedidos);
            this.pedidoListView.setAdapter(pedidoAdapter);
        } else if (opc == 2) {
            listaPedidos = repositorioPedidos.buscaPorNome(pedido);
            pedidoAdapter = new PedidoAdapter(this, listaPedidos);
            this.pedidoListView.setAdapter(pedidoAdapter);
        } else if (opc == 3) {
            listaPedidos.clear();
            pedidoAdapter = new PedidoAdapter(this, listaPedidos);
            this.pedidoListView.setAdapter(pedidoAdapter);
        }
    }


    /*-------------------------------menu de contexto---------------------------------------------------------------------------*/
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.relatorio_menu_contexto, menu);
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        if (!(item.getTitle().equals("Voltar")|| item.getTitle().equals("Pesquisar por Nº Pedido")||item.getTitle().equals("Pesquisar por Nome Cliente")|| item.getTitle().equals("Exibir Todos Pedidos"))) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            pedido = (Pedido) this.pedidoListView.getItemAtPosition(info.position);
        }
        switch (item.getItemId()) {
            case 1:
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
            case 2:
                SearchPedido();
                break;
            case 3:
                SearchPedidoNome();
                break;
            case 4:
                opc = 0;
                ExibirTodosPedidos(opc);
                break;
            case R.id.exibirFechar:
                return true;
            case R.id.exibirDetalhes:
                exibirDetalhesPedidos(pedido);
                return true;
            default:
                return false;
        }

        return true;
    }


    public void exibirDetalhesPedidos(Pedido pedido) {

        viewDetalhesLista = inflater2.inflate(R.layout.detalhes_pedidos_lista, null);
        listDetalhes = (ListView) viewDetalhesLista.findViewById(R.id.listDetalhes);

        numeroPedidoDetalhes = (TextView) viewDetalhesLista.findViewById(R.id.numeroPedidoDetalhes);
        valorPedidoDetalhes = (TextView) viewDetalhesLista.findViewById(R.id.valorPedidoDetalhes);
        clientePedidoDetalhes = (TextView) viewDetalhesLista.findViewById(R.id.clientePedidoDetalhes);
        dataPedidoDetalhes = (TextView) viewDetalhesLista.findViewById(R.id.dataPedidoDetalhes);


        repositorioListaProdutos = new RepositorioListaProdutos(this);
        listaSalvar = repositorioListaProdutos.exibirDetalhesPedido(pedido);

        Double total = 0.0;
        for (ListaSalvar item : listaSalvar) {
            total += item.getValor();
        }

        Double teste = total;
        DecimalFormat df = new DecimalFormat("#.00");
        valorPedidoDetalhes.setText(df.format(teste).toString());

        numeroPedidoDetalhes.setText(pedido.getPedidoID().toString());
        clientePedidoDetalhes.setText(pedido.getNomeCliente());
        dataPedidoDetalhes.setText(pedido.getDataPedido());

        relatodioDetalhesAdapter = new RelatodioDetalhesAdapter(this, listaSalvar);
        listDetalhes.setAdapter(relatodioDetalhesAdapter);


        AlertDialog.Builder dlg = new AlertDialog.Builder(this);
        dlg.setTitle("DETALHES DO PEDIDO");
        dlg.setView(viewDetalhesLista);

        dlg.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }
        );
        dlg.show();
    }

}
