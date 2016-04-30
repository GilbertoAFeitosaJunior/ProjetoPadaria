package br.com.padaria.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import br.com.padaria.R;

/**
 * Created by Gilberto on 28/04/2016.
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
    }



    public void MenuPrincipal(View view){
        switch (view.getId()){
            case R.id.cliente:
                startActivity(new Intent(this, ClienteActivity.class));
                finish();
                break;
            case R.id.produto:
                startActivity(new Intent(this, ProdutoActivity.class));
                finish();
                break;
            case R.id.pedido:
                Toast.makeText(MainActivity.this, "Clicou em Pedido", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
