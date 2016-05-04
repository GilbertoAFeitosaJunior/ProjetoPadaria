package br.com.padaria.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

import br.com.padaria.R;

/**
 * Created by Gilberto on 29/04/2016.
 */
public class RelatodioDetalhesAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private Context context;
    private  List<ListaSalvar> listaSalvar;

    public RelatodioDetalhesAdapter(Context context,  List<ListaSalvar> listaSalvar) {
        this.context = context;
        this.listaSalvar = listaSalvar;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return listaSalvar.size();
    }

    @Override
    public Object getItem(int position) {
        return listaSalvar.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = layoutInflater.inflate(R.layout.detalhes_pedidos_activity, null);

        ListaSalvar lista = listaSalvar.get(position);


        TextView nomeProdutoDetalhes = (TextView) view.findViewById(R.id.nomeProdutoDetalhes);
        nomeProdutoDetalhes.setText(lista.getNomeProduto());

        TextView quantProdutoDetalhes = (TextView) view.findViewById(R.id.quantProdutoDetalhes);
        quantProdutoDetalhes.setText(lista.getQuantidade().toString());

      /* if(position % 2 == 0 ){
            view.setBackgroundColor(Color.rgb(36,123,36));
        }*/

        return view;
    }


}
