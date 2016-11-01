package es.unavarra.tlm.dscr_10.CarpetaLogin;


import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.AsyncHttpResponseHandler;
import java.io.IOException;
import cz.msebera.android.httpclient.Header;
import es.unavarra.tlm.dscr_10.listapartidas.ListaPartidasTabs;


public class LoginResponseHandler extends AsyncHttpResponseHandler{

    private entrar context = null;
    SharedPreferences settings;
    SharedPreferences.Editor editor;
    LoginResponseMessage message = null;

    public LoginResponseHandler(entrar context) {
        this.context = context;
        settings = context.getSharedPreferences("Config", 0);
        editor = settings.edit();
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
        try {
            message = new ObjectMapper().readValues(new JsonFactory().createParser(responseBody), LoginResponseMessage.class).next();
            saveUserToken();
            //Intent listaPartidas = new Intent(context,ListaPartidas.class);
            //context.startActivity(listaPartidas);
            this.context.finish();
            Intent listaPartidas = new Intent(context,ListaPartidasTabs.class);
            context.startActivity(listaPartidas);

        } catch (IOException e) {
            Toast.makeText(this.context, "Cannot read response", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
        if(statusCode == 501) {
            Toast.makeText(this.context, "Player does not exists", Toast.LENGTH_LONG).show();
        }
        if(statusCode == 502){
            Toast.makeText(this.context, "Password is not correct ", Toast.LENGTH_LONG).show();
        }

    }


    private void saveUserToken(){
        editor.putString("token",message.getToken());
        editor.putString("username",message.getName());
        editor.commit();

    }
}

