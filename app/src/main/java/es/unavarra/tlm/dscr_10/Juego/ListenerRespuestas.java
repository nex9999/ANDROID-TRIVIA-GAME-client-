package es.unavarra.tlm.dscr_10.Juego;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.AsyncHttpClient;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.entity.StringEntity;
import es.unavarra.tlm.dscr_10.listapartidas.PlayerAuthRequest;

/**
 * Created by root on 26/11/15.
 */
public class ListenerRespuestas implements View.OnClickListener {

    Pregunta context;
    SharedPreferences settings;
    ResponseRequestMessage respuesta_enviar;
    String gametoken, output;
    AsyncHttpClient client;
    private int i;

    public ListenerRespuestas(Pregunta context,int _i){
        this.context = context;
        client = new AsyncHttpClient();
        i = _i;
    }



    @Override
    public void onClick(View v) {
        settings = context.getSharedPreferences("Config", 0);
        respuesta_enviar = new ResponseRequestMessage();
        respuesta_enviar.setToken(settings.getString("token",""));
        respuesta_enviar.setResponse(i);
        gametoken = Jugar.infoMessage.getToken();
        try {
            output = convertToJson();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        ResponseResponseHandler responseHandler = new ResponseResponseHandler(this.context);
        //Toast.makeText(getActivity(),output,Toast.LENGTH_LONG).show();
        String URL = "http://trivial.tatai.es:80/game/"+gametoken+"/response.json";
        try {
            //Toast.makeText(context,output,Toast.LENGTH_LONG).show();
            this.client.post(context, URL, new StringEntity(output), "application/json", responseHandler);


        } catch (UnsupportedEncodingException e) {

        }
    }
    private String convertToJson() throws JsonProcessingException {

        output = new ObjectMapper().writeValueAsString(respuesta_enviar);
        return output;

    }
}
