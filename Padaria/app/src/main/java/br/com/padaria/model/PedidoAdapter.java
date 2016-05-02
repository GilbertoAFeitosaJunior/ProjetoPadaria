package br.com.padaria.model;

import android.content.Context;
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
public class PedidoAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private Context context;
    private List<Pedido> listaPedido;

    public PedidoAdapter(Context context, List<Pedido> listaPedido) {
        this.context = context;
        this.listaPedido = listaPedido;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return listaPedido.size();
    }

    @Override
    public Object getItem(int position) {
        return listaPedido.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = layoutInflater.inflate(R.layout.pedido_adapter, null);

        Pedido pedido = listaPedido.get(position);

        TextView numeroPedido = (TextView) view.findViewById(R.id.numeroPedidoAdapter);
        numeroPedido.setText(pedido.getPedidoID().toString());

        TextView cliente = (TextView) view.findViewById(R.id.nomeClienteAdapter);
        cliente.setText(pedido.getNomeCliente());

        TextView dataHora = (TextView) view.findViewById(R.id.dataHoraAdapter);
        dataHora.setText(pedido.getDataPedido());

      /* if(position % 2 == 0 ){
            view.setBackgroundColor(Color.rgb(36,123,36));
        }*/

        return view;
    }


}
