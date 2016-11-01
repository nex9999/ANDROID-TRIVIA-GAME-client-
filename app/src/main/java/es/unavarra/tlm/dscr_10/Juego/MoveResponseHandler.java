package es.unavarra.tlm.dscr_10.Juego;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.io.IOException;

import cz.msebera.android.httpclient.Header;

/**
 * Created by root on 26/11/15.
 */
public class MoveResponseHandler extends AsyncHttpResponseHandler {

    Jugar context;
    static GameMoveResponse message;
    static boolean tiraotravez = false;

    public MoveResponseHandler(Jugar context){
        this.context = context;

    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
        try {
            message = new ObjectMapper().readValues(new JsonFactory().createParser(responseBody), GameMoveResponse.class).next();
            //Toast.makeText(this.context, message.getResponses()[0], Toast.LENGTH_LONG).show();
            this.context.finish();
            if (message.getQuestion()!= null) {
                jugar();
            }else{
                tiraotravez = true;
                Intent jugar = new Intent(this.context,Jugar.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);;
                context.startActivity(jugar);

            }

        } catch (IOException e) {
            Toast.makeText(this.context, "Cannot read response", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
        if(statusCode==400){

            Toast.makeText(this.context, "Ya habías tirado. Contesta.", Toast.LENGTH_SHORT).show();

        }

    }
    public void jugar(){

        Intent pregunta = new Intent(this.context,Pregunta.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);;
        context.startActivity(pregunta);


    }

}
