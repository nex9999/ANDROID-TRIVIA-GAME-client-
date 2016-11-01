package es.unavarra.tlm.dscr_10.Juego;

/**
 * Created by nex on 10/12/15.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import es.unavarra.tlm.dscr_10.R;
import info.hoang8f.widget.FButton;


public class PreguntaListAdapter extends BaseAdapter {


    Context context;
    LayoutInflater inflater;
    GameMoveResponse message;


    public PreguntaListAdapter(Context context,GameMoveResponse message){

        this.context = context;
        this.message = message;
    }


    @Override
    public int getCount() {
        return message.getResponses().length;
    }

    @Override
    public Object getItem(int position) {
        return message.getResponses()[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = inflater.from(parent.getContext()).inflate(R.layout.lista_respuestas_item, parent, false);

        } else {

        }

        FButton buttonRespuesta = (FButton)convertView.findViewById(R.id.button_respuesta);
        buttonRespuesta.setText(message.getResponses()[position]);
        buttonRespuesta.setOnClickListener(new ListenerRespuestas((Pregunta)context,position));


        return convertView;
    }
}
