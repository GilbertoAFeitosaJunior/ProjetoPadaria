package br.com.padaria.view;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.LayoutInflater;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.List;

import br.com.padaria.R;
import br.com.padaria.controller.RepositorioCliente;
import br.com.padaria.model.Cliente;
import br.com.padaria.model.ClienteAdapter;


/**
 * Created by Gilberto on 28/04/2016.
 */
public class ClienteActivity extends Activity {

    private View viewCadastrarCliente;
    private LayoutInflater inflater;
    private RadioGroup mfRadioGroup;
    private EditText nome, idade;
    private String sexo = "Masculino";
    private RepositorioCliente repositorioCliente;
    private ListView clientListView;
    private Cliente cliente;
    List<Cliente> listaDeCliente;
    ClienteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.cliente_activity);
        ActionBar bar = getActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#cd950c")));

        repositorioCliente = new RepositorioCliente(this);

        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        clientListView = (ListView) findViewById(R.id.clientListView);
        clientListView.setEmptyView(findViewById(R.id.msg_lista_vazia));

        registerForContextMenu(clientListView);


        exibirTodosClientes();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        SearchView sv = new SearchView(this);
        sv.setOnQueryTextListener(new SearcFiltro());

        MenuItem pesquisa = menu.add(0, 0, 0, "Pesquisa");
        pesquisa.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        pesquisa.setActionView(sv);

        MenuItem menuVoltar = menu.add(0, 1, 1, "Voltar");
        menuVoltar.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        menuVoltar.setIcon(R.drawable.voltar);
        return true;
    }

    protected class SearcFiltro implements SearchView.OnQueryTextListener {
        @Override
        public boolean onQueryTextSubmit(String query) {
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            if (newText.toString().equals("")) {
                exibirTodosClientes();
            } else {
                searchItem(newText.toString());
            }
            return false;
        }
    }

    //localizar_pedido
    public void searchItem(String textToSearch) {
        for (Cliente item : listaDeCliente) {
            if (!item.getNome().toLowerCase().contains(textToSearch.toLowerCase())) {
                listaDeCliente.remove(item);
                adapter.notifyDataSetChanged();
                return;
            }
        }
    }

    //MENU PRINCIPAL
    public void MenuCliente(View view) {
        switch (view.getId()) {
            case R.id.addCliente:
                adicionarCliente();
                break;

        }
    }


    public void adicionarCliente() {
        viewCadastrarCliente = inflater.inflate(R.layout.add_cliente_activity, null);

        nome = (EditText) viewCadastrarCliente.findViewById(R.id.nome);
        idade = (EditText) viewCadastrarCliente.findViewById(R.id.idade);

        mfRadioGroup = (RadioGroup) viewCadastrarCliente.findViewById(R.id.mfRadioGroup);
        mfRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                boolean sim = R.id.radioMasculino == checkedId;
                boolean nao = R.id.radioFeminino == checkedId;
                if (sim) {
                    sexo = "Masculino";
                } else if (nao) {
                    sexo = "Feminino";
                }
            }
        });

        AlertDialog.Builder dlg = new AlertDialog.Builder(this);
        dlg.setTitle("CADASTRAR CLIENTE");
        dlg.setView(viewCadastrarCliente);

        dlg.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if (nome.getText().toString().equals("")) {
                            Toast.makeText(ClienteActivity.this, "Campo NOME obrigatório.", Toast.LENGTH_SHORT).show();
                            adicionarCliente();
                        } else if (idade.getText().toString().equals("")) {
                            Toast.makeText(ClienteActivity.this, "Campo IDADE é obrigatório.", Toast.LENGTH_SHORT).show();
                            adicionarCliente();
                        } else {
                            Salvar();
                            exibirTodosClientes();
                        }
                    }
                }
        );
        dlg.setNegativeButton("Cancelar", new DialogInterface.OnClickListener()

                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }
        );
        dlg.show();
    }


    public void editarCliente(final Cliente cliente) {
        viewCadastrarCliente = inflater.inflate(R.layout.add_cliente_activity, null);

        nome = (EditText) viewCadastrarCliente.findViewById(R.id.nome);
        idade = (EditText) viewCadastrarCliente.findViewById(R.id.idade);

        nome.setText(cliente.getNome());
        idade.setText(cliente.getIdade().toString());

        mfRadioGroup = (RadioGroup) viewCadastrarCliente.findViewById(R.id.mfRadioGroup);
        mfRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                boolean sim = R.id.radioMasculino == checkedId;
                boolean nao = R.id.radioFeminino == checkedId;
                if (sim) {
                    sexo = "Masculino";
                } else if (nao) {
                    sexo = "Feminino";
                }
            }
        });

        AlertDialog.Builder dlg = new AlertDialog.Builder(this);
        dlg.setTitle("CADASTRAR CLIENTE");
        dlg.setView(viewCadastrarCliente);

        dlg.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if (nome.getText().toString().equals("")) {
                            Toast.makeText(ClienteActivity.this, "Campo NOME obrigatório.", Toast.LENGTH_SHORT).show();

                        } else if (idade.getText().toString().equals("")) {
                            Toast.makeText(ClienteActivity.this, "Campo IDADE é obrigatório.", Toast.LENGTH_SHORT).show();

                        } else {
                            Editar(cliente);
                            exibirTodosClientes();
                        }
                    }
                }
        );
        dlg.setNegativeButton("Cancelar", new DialogInterface.OnClickListener()

                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }
        );
        dlg.show();


    }

    /*-------------------------------CRUD---------------------------------------------------------------------------*/
    //salvar cliente no banco
    public void Salvar() {
        Cliente cliente = new Cliente();

        cliente.setNome(nome.getText().toString());
        cliente.setIdade(Integer.parseInt(idade.getText().toString()));
        cliente.setSexo(sexo);

        repositorioCliente.SalvarCliente(cliente);
        Toast.makeText(ClienteActivity.this, "Cliente SALVO com Sucesso", Toast.LENGTH_SHORT).show();
    }

    //editar cliente no banco
    public void Editar(Cliente cliente) {

        cliente.setNome(nome.getText().toString());
        cliente.setIdade(Integer.parseInt(idade.getText().toString()));
        cliente.setSexo(sexo);

        repositorioCliente.editarCliente(cliente);
        Toast.makeText(ClienteActivity.this, "Cliente ALTERADO com Sucesso", Toast.LENGTH_SHORT).show();
    }

    //lista de clientes
    public void exibirTodosClientes() {
        listaDeCliente = repositorioCliente.listarTodosClientes();
        adapter = new ClienteAdapter(this, listaDeCliente);
        this.clientListView.setAdapter(adapter);
    }


    /*-------------------------------menu de contexto---------------------------------------------------------------------------*/
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.cliente_menu_contexto, menu);
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
       if(!item.getTitle().equals("Voltar")){
           AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
           cliente = (Cliente) this.clientListView.getItemAtPosition(info.position);
       }
        switch (item.getItemId()) {
            case 1:
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
            case R.id.menu_item_fecharr:
                return true;
            case R.id.menu_item_excluir:
                repositorioCliente.deletarCliente(cliente);
                exibirTodosClientes();
                Toast.makeText(ClienteActivity.this, "Cliente EXCLUIDO com sucesso.", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_item_editar:
                editarCliente(cliente);
            default:
                return false;

        }

        return true;
    }

}
