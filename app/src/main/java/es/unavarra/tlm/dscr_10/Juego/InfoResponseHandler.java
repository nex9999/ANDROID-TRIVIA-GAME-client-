package es.unavarra.tlm.dscr_10.Juego;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.io.IOException;

import cz.msebera.android.httpclient.Header;
import es.unavarra.tlm.dscr_10.listapartidas.ListaPartidasTabs;

/**
 * Created by root on 24/11/15.
 */
public class InfoResponseHandler extends AsyncHttpResponseHandler {

    Context context;
    Activity activity;
    ProgressDialog progressDialog = null;
    static GameInfoResponse message;
    public InfoResponseHandler(Context context){
        this.context = context;
    }
    public InfoResponseHandler(Context context,ProgressDialog _progressDialog){
        this.context = context;
        //progressDialog = _progressDialog;

    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
        try {
            message = new ObjectMapper().readValues(new JsonFactory().createParser(responseBody), GameInfoResponse.class).next();
            //Toast.makeText(this.context, String.valueOf(message.getTurn()), Toast.LENGTH_LONG).show();
            if(progressDialog != null){
                //progressDialog.dismiss();
                activity = (Activity) context;
                activity.finish();
            }
            Intent jugar = new Intent(this.context,Jugar.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            this.context.startActivity(jugar);

        } catch (IOException e) {
            Toast.makeText(this.context, "Cannot read response", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

    }


}
