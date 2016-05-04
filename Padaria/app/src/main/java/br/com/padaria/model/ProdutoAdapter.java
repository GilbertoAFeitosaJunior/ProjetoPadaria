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
public class ProdutoAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private Context context;
    private List<Produto> listaDeProduto;

    public ProdutoAdapter(Context context, List<Produto> listaDeProduto) {
        this.context = context;
        this.listaDeProduto = listaDeProduto;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return listaDeProduto.size();
    }

    @Override
    public Object getItem(int position) {
        return listaDeProduto.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = layoutInflater.inflate(R.layout.produto_adapter, null);

        Produto produto = listaDeProduto.get(position);

        if(produto.getQuantidade()!=0){
            TextView nome = (TextView) view.findViewById(R.id.nomeProdutoAdapater);
            nome.setText(produto.getNome());

            TextView qunt = (TextView) view.findViewById(R.id.quantidadeProdutoAdapater);
            qunt.setText(produto.getQuantidade().toString());
        }else{
            TextView nome = (TextView) view.findViewById(R.id.nomeProdutoAdapater);
            nome.setText(produto.getNome());
            nome.setTextColor(Color.RED);

            TextView qunt = (TextView) view.findViewById(R.id.quantidadeProdutoAdapater);
            qunt.setText(produto.getQuantidade().toString());
            qunt.setTextColor(Color.RED);

            TextView descricao = (TextView) view.findViewById(R.id.descricao);
            descricao.setTextColor(Color.RED);


        }


      /* if(position % 2 == 0 ){
            view.setBackgroundColor(Color.rgb(36,123,36));
        }*/


        return view;
    }


}
