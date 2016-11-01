package es.unavarra.tlm.dscr_10.listapartidas;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.AsyncHttpClient;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.entity.StringEntity;

/**
 * Created by root on 19/11/15.
 */
public class CreadorPartidas {
    AsyncHttpClient client = null;
    ListaPartidasTabs clase= null;
    SharedPreferences settings;
    String output;
    String token;

    public CreadorPartidas(ListaPartidasTabs clase){
        this.clase = clase;
        client = new AsyncHttpClient();
        settings = clase.getSharedPreferences("Config", 0);
        token = settings.getString("token", "");
    }

    public void creaNuevaPartida(){
        try {
            convertToJson();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }



    private void convertToJson() throws JsonProcessingException {
        PlayerAuthRequest login = new PlayerAuthRequest();
        login.setToken(token);
        output = new ObjectMapper().writeValueAsString(login);
        enviar();
    }

    private void enviar(){
        CrearPartidasResponseHandler responseHandler = new CrearPartidasResponseHandler(clase);

        //Toast.makeText(getActivity(),output,Toast.LENGTH_LONG).show();
        try {
            this.client.post(clase, "http://trivial.tatai.es:80/game/create.json", new StringEntity(output), "application/json", responseHandler);
        } catch (UnsupportedEncodingException e) {

        }
    }
}
