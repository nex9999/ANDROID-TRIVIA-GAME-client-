package es.unavarra.tlm.dscr_10.Registrar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.io.IOException;

import cz.msebera.android.httpclient.Header;
import es.unavarra.tlm.dscr_10.listapartidas.ListaPartidasTabs;

/**
 * Created by nex on 5/11/15.
 */
public class RegistroResponseHandler extends AsyncHttpResponseHandler{

    private registro context = null;
    SharedPreferences settings;
    SharedPreferences.Editor editor;
    public RegistroResponseHandler(registro context) {
        this.context = context;
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

        settings = context.getSharedPreferences("Config", 0);
        editor = settings.edit();


        RegistroResponseMessage message = null;
        try {
            message = new ObjectMapper().readValues(new JsonFactory().createParser(responseBody), RegistroResponseMessage.class).next();
            editor.putString("token",message.getToken());
            editor.commit();
            Intent listaPartidas = new Intent(context,ListaPartidasTabs.class);
            context.startActivity(listaPartidas);

        } catch (IOException e) {
            Toast.makeText(this.context, "Cannot read response", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
        if (statusCode == 501) {
            Toast.makeText(this.context, "Player already exists", Toast.LENGTH_SHORT).show();
        }
    }


}
