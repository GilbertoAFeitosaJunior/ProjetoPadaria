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
public class ProdutoPedidoAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private Context context;
    private List<ListaProdutos> listaDeProdutos;

    public ProdutoPedidoAdapter(Context context, List<ListaProdutos> listaDeProdutos) {
        this.context = context;
        this.listaDeProdutos = listaDeProdutos;
        this.layoutInflater =  (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return listaDeProdutos.size();
    }

    @Override
    public Object getItem(int position) {
        return listaDeProdutos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view =  layoutInflater.inflate(R.layout.pedido_produto_adapter, null);

        ListaProdutos lista = listaDeProdutos.get(position);
        Produto produto = lista.getProduto();
        Integer quantidade = lista.getQuantidade();

        TextView nome = (TextView)  view.findViewById(R.id.nomePedidoAdapter);
        nome.setText(produto.getNome());



        TextView valor = (TextView)  view.findViewById(R.id.valorPedidoAdapter);
        Double teste = lista.getValor();
        DecimalFormat df = new DecimalFormat("0.00");
        valor.setText("R$ "+df.format(teste).toString());

        TextView quant = (TextView)  view.findViewById(R.id.quantPedidoAdapter);
        quant.setText(quantidade.toString());



      /* if(position % 2 == 0 ){
            view.setBackgroundColor(Color.rgb(36,123,36));
        }*/


        return view;
    }


}
