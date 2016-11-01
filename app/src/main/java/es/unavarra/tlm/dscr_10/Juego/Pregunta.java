package es.unavarra.tlm.dscr_10.Juego;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import es.unavarra.tlm.dscr_10.ListAdapter;
import es.unavarra.tlm.dscr_10.R;
import es.unavarra.tlm.dscr_10.listapartidas.ListaPartidasListAdapter;
import info.hoang8f.widget.FButton;

/**
 * Created by root on 26/11/15.
 */
public class Pregunta extends Activity {

    ListView respuestas;
    FButton pregunta;
    ArrayAdapter<String> Adapter_respuestas;
    private PreguntaListAdapter adapter;
    private ListView listView;
    public static Pregunta pregunta_this;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregunta);

        pregunta_this = this;
        respuestas = (ListView)findViewById(R.id.respuestas);
        pregunta = (FButton)findViewById(R.id.pregunta);
       // Toast.makeText(context,MoveResponseHandler.message.getResponses()[1],Toast.LENGTH_LONG);
        /*Adapter_respuestas= new ArrayAdapter(context, android.R.layout.simple_list_item_1,MoveResponseHandler.message.getResponses());
        respuestas.setAdapter(Adapter_respuestas);
        Adapter_respuestas.notifyDataSetChanged();
        pregunta.setText(MoveResponseHandler.message.getQuestion());
        */

        pregunta.setText(MoveResponseHandler.message.getQuestion());
        adapter = new PreguntaListAdapter(this,MoveResponseHandler.message);
        respuestas.setAdapter(adapter);
        adapter.notifyDataSetChanged();



    }
    public void acabar(){
        this.finish();

    }
}
