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
import es.unavarra.tlm.dscr_10.listapartidas.ListaPartidasResponseMessage;
import es.unavarra.tlm.dscr_10.listapartidas.ListaPartidasTabs;
import es.unavarra.tlm.dscr_10.listapartidas.PlayerAuthRequest;

/**
 * Created by root on 24/11/15.
 */
public class ListenerInfo implements AdapterView.OnItemClickListener{
    String gametoken, output, usertoken;
    ListaPartidasTabs context;
    ListaPartidasResponseMessage message;
    AsyncHttpClient client = null;
    SharedPreferences settings;
    SharedPreferences.Editor editor_settings;
    PlayerAuthRequest login;

    public ListenerInfo(ListaPartidasTabs context, ListaPartidasResponseMessage message) {
        gametoken = "";
        this.context = context;
        //Toast.makeText(context,"Ha entrado",Toast.LENGTH_LONG).show();
        this.message = message;
        client = new AsyncHttpClient();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        settings = context.getSharedPreferences("Config", 0);
        editor_settings = settings.edit().putString("gametoken",message.getGame(i).getToken());
        editor_settings.commit();
        login = new PlayerAuthRequest();
        login.setToken(settings.getString("token",""));
        gametoken = message.getGame(i).getToken();
        try {
            output = convertToJson();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        InfoResponseHandler responseHandler = new InfoResponseHandler(this.context);
        //Toast.makeText(getActivity(),output,Toast.LENGTH_LONG).show();
        String URL = "http://trivial.tatai.es:80/game/"+gametoken+"/info.json";
        try {
            //Toast.makeText(context, output, Toast.LENGTH_LONG).show();
            this.client.post(context, URL, new StringEntity(output), "application/json", responseHandler);
        } catch (UnsupportedEncodingException e) {

        }


    }
    private String convertToJson() throws JsonProcessingException {

        output = new ObjectMapper().writeValueAsString(login);
        return output;

    }
}
