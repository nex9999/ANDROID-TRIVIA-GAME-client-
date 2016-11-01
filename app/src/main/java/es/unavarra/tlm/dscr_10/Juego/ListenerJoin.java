package es.unavarra.tlm.dscr_10.Juego;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.AsyncHttpClient;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.entity.StringEntity;
import es.unavarra.tlm.dscr_10.R;
import es.unavarra.tlm.dscr_10.listapartidas.Game;
import es.unavarra.tlm.dscr_10.listapartidas.ListaPartidasResponseHandler;
import es.unavarra.tlm.dscr_10.listapartidas.ListaPartidasResponseMessage;
import es.unavarra.tlm.dscr_10.listapartidas.ListaPartidasTabs;
import es.unavarra.tlm.dscr_10.listapartidas.PlayerAuthRequest;
import es.unavarra.tlm.dscr_10.listapartidas.SectionFragment;

/**
 * Created by root on 19/11/15.
 */
public class ListenerJoin implements AdapterView.OnItemClickListener {

    String gametoken, output, usertoken;
    ListaPartidasTabs context;
    ListaPartidasResponseMessage message;
    AsyncHttpClient client = null;
    SharedPreferences settings;
    PlayerAuthRequest login;
    SectionFragment fragment=null;

    public ListenerJoin(ListaPartidasTabs context, ListaPartidasResponseMessage message, SectionFragment _fragment) {
        gametoken = "";
        this.context = context;
        //Toast.makeText(context,"Ha entrado",Toast.LENGTH_LONG).show();
        this.message = message;
        client = new AsyncHttpClient();
        fragment = _fragment;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if(fragment != null){
            fragment.update();


        }
        settings = context.getSharedPreferences("Config", 0);
        login = new PlayerAuthRequest();
        login.setToken(settings.getString("token",""));
        gametoken = message.getGame(i).getToken();
        try {
            output = convertToJson();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        InfoResponseHandler responseHandler = new InfoResponseHandler(this.context);

        String URL = "http://trivial.tatai.es:80/game/"+gametoken+"/join.json";
        try {
            //Toast.makeText(context,output,Toast.LENGTH_LONG).show();
            this.client.post(context, URL, new StringEntity(output), "application/json", responseHandler);
        } catch (UnsupportedEncodingException e) {

        }


    }
    private String convertToJson() throws JsonProcessingException {

        output = new ObjectMapper().writeValueAsString(login);
        return output;

    }
}
