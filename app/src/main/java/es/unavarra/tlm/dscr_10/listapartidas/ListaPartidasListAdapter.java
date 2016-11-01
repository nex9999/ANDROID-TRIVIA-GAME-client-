package es.unavarra.tlm.dscr_10.listapartidas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import es.unavarra.tlm.dscr_10.R;

public class ListaPartidasListAdapter  extends  BaseAdapter{


    Context context;
    LayoutInflater inflater;
    ListaPartidasResponseMessage message;


    public ListaPartidasListAdapter(Context context,ListaPartidasResponseMessage message){

        this.context = context;
        this.message = message;
    }


    @Override
    public int getCount() {
        return message.getSize();
    }

    @Override
    public Object getItem(int position) {
        return message.getGame(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String nombrePartida   = message.getGame(position).getCreator().getName();
        String gameAvailable;
        if(message.getGame(position).getAvailable()==1){
            gameAvailable="disponible";}
        else{
            gameAvailable="no disponible";
        };
        if (convertView == null) {
            convertView = inflater.from(parent.getContext()).inflate(R.layout.lista_partidas_item, parent, false);

        } else {

        }

        TextView nombrePartidaTView = (TextView)convertView.findViewById(R.id.nombrepartida);
        TextView gameAvailableTView = (TextView)convertView.findViewById(R.id.gameavailable);

        nombrePartidaTView.setText(nombrePartida);
        gameAvailableTView.setText(gameAvailable);

        return convertView;
    }
}