package br.com.padaria.view;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import br.com.padaria.R;
import br.com.padaria.controller.RepositorioProduto;
;

/**
 * Created by Gilberto on 28/04/2016.
 */
public class MainActivity extends Activity {
    private View viewOpcaoPedido;
    private LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        ActionBar bar = getActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#cd950c")));
    }


    public void MenuPrincipal(View view) {
        switch (view.getId()) {
            case R.id.cliente:
                startActivity(new Intent(this, ClienteActivity.class));
                finish();
                break;
            case R.id.produto:
                startActivity(new Intent(this, ProdutoActivity.class));
                finish();
                break;
            case R.id.pedido:
                OpcaoPedito();
                break;
        }
    }


    public void OpcaoPedito() {
        viewOpcaoPedido = inflater.inflate(R.layout.opcao_pedido_contexto, null);
        ImageView novo_pedido = (ImageView) viewOpcaoPedido.findViewById(R.id.novo_pedido);

        AlertDialog.Builder dlg = new AlertDialog.Builder(this);
        dlg.setView(viewOpcaoPedido);

        MenuPedido(this.viewOpcaoPedido);

        dlg.show();
    }


    public void MenuPedido(View view) {
        switch (view.getId()) {
            case R.id.novo_pedido:
                startActivity(new Intent(this, NovoPedido.class));
                finish();
                break;
            case R.id.relatorios:
                startActivity(new Intent(this, RelatorioActivity.class));
                finish();
                break;
        }
    }


}
