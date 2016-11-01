package es.unavarra.tlm.dscr_10.Juego;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.io.IOException;

import cz.msebera.android.httpclient.Header;

/**
 * Created by root on 19/11/15.
 */
public class JoinResponseHandler extends AsyncHttpResponseHandler {

    Context context;
    GameInfoResponse message;

    public JoinResponseHandler(Context context){
        this.context = context;
    }
    @Override
    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

        //System.out.println("______________________"+statusCode);
        try {
            message = new ObjectMapper().readValues(new JsonFactory().createParser(responseBody), GameInfoResponse.class).next();
            //Toast.makeText(this.context, String.valueOf(message.getTurn()), Toast.LENGTH_LONG).show();

            Intent jugar = new Intent(this.context, Jugar.class);
            this.context.startActivity(jugar);
        } catch (IOException e) {
            Toast.makeText(this.context, "Cannot read response", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
        System.out.println("______________________"+statusCode);
    }

}
