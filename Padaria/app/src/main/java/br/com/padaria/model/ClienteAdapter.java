package br.com.padaria.model;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.padaria.R;

/**
 * Created by Gilberto on 29/04/2016.
 */
public class ClienteAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private Context context;
    private List<Cliente> listaDeCliente;

    public ClienteAdapter(Context context, List<Cliente> listaDeCliente) {
        this.context = context;
        this.listaDeCliente = listaDeCliente;
        this.layoutInflater =  (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return listaDeCliente.size();
    }

    @Override
    public Object getItem(int position) {
        return listaDeCliente.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view =  layoutInflater.inflate(R.layout.cliente_adapter, null);

        Cliente cliente = listaDeCliente.get(position);

        TextView codigo = (TextView)  view.findViewById(R.id.codigoAdapter);
        codigo.setText(cliente.getClienteID().toString());

        TextView nome = (TextView)  view.findViewById(R.id.nomeAdapeter);
        nome.setText(cliente.getNome());

        TextView idade = (TextView)  view.findViewById(R.id.idadeAdapter);
        idade.setText(cliente.getIdade().toString());

        TextView sexo = (TextView)  view.findViewById(R.id.sexoAdapter);
        sexo.setText(cliente.getSexo());

      /* if(position % 2 == 0 ){
            view.setBackgroundColor(Color.rgb(36,123,36));
        }*/


        return view;
    }


}
