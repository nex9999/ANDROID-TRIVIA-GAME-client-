package es.unavarra.tlm.dscr_10.Juego;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.AsyncHttpClient;

import java.io.UnsupportedEncodingException;
import java.util.List;

import cz.msebera.android.httpclient.entity.StringEntity;
import es.unavarra.tlm.dscr_10.R;
import es.unavarra.tlm.dscr_10.listapartidas.ListaPartidasResponseMessage;
import es.unavarra.tlm.dscr_10.listapartidas.PlayerAuthRequest;

/**
 * Created by root on 26/11/15.
 */
public class ListenerMove implements View.OnClickListener {

    Jugar context;
    int lado;
    String to;
    MoveResponseHandler responseHandler;
    AsyncHttpClient client = null;
    SharedPreferences settings;
    MoveRequestMessage peticion;
    String gametoken, output;

    public ListenerMove(Jugar context, int lado){
        this.context = context;

        this.lado = lado; //0 es iquierda y 1 es derecha

        client = new AsyncHttpClient();
    }
    @Override
    public void onClick(View view) {


        switch(lado){
            case 0:
                to = "right";
                break;
            case 1:
                to = "left";
                break;
        }

        if(to.equals("left") && DiceResponseHandler.message.getLeft().getType().equals("dice")){
            System.out.println("___ENTRA derecha");
            List<Status> status = InfoResponseHandler.message.getStatus();
            status.get(Jugar.turno).setPosition(DiceResponseHandler.message.getLeft().position);
            InfoResponseHandler.message.setStatus(status);
        }

        if(to.equals("right") && DiceResponseHandler.message.getRight().getType().equals("dice")){
            System.out.println("___ENTRA izquierda");
            List<Status> status = InfoResponseHandler.message.getStatus();
            status.get(Jugar.turno).setPosition(DiceResponseHandler.message.getRight().position);
            InfoResponseHandler.message.setStatus(status);
        }
        settings = context.getSharedPreferences("Config", 0);
        peticion = new MoveRequestMessage();
        peticion.setTo(to);
        peticion.setToken(settings.getString("token",""));

        gametoken = Jugar.infoMessage.getToken();
        try {
            output = convertToJson();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        responseHandler = new MoveResponseHandler(this.context);
        //Toast.makeText(getActivity(),output,Toast.LENGTH_LONG).show();
        String URL = "http://trivial.tatai.es:80/game/"+gametoken+"/move.json";
        try {
            //Toast.makeText(context, output, Toast.LENGTH_LONG).show();
            this.client.post(context, URL, new StringEntity(output), "application/json", responseHandler);

        } catch (UnsupportedEncodingException e) {

        }


    }

    private String convertToJson() throws JsonProcessingException {

        output = new ObjectMapper().writeValueAsString(peticion);
        return output;

    }


}
