package es.unavarra.tlm.dscr_10.CarpetaLogin;

import android.content.SharedPreferences;
import android.view.View;
import android.widget.EditText;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.AsyncHttpClient;
import java.io.UnsupportedEncodingException;
import cz.msebera.android.httpclient.entity.StringEntity;


public class ListenerLogin implements View.OnClickListener {

    final EditText email;
    final EditText password;
    final AsyncHttpClient client;
    final entrar activity;
    String output;
    SharedPreferences settings;
    SharedPreferences.Editor editor;

    public ListenerLogin (EditText email,EditText password,AsyncHttpClient client,entrar activity){

        this.email = email;
        this.password = password;
        this.client = client;
        this.activity = activity;

        settings = activity.getSharedPreferences("Config", 0);
        editor = settings.edit();
    }


    @Override
    public void onClick(View v) {

        try {
            convertToJson();

            //La actividad solo debería crearse cuando el usuario se registre correctamente!!!.

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private void convertToJson() throws JsonProcessingException {

        Login login = new Login();
        login.setEmail(email.getText().toString());
        login.setPassword(password.getText().toString());
        output = new ObjectMapper().writeValueAsString(login);

        enviar();
    }


    private void enviar(){
        LoginResponseHandler responseHandler = new LoginResponseHandler(this.activity);
        try {
            this.client.post(this.activity, "http://trivial.tatai.es:80/player/login.json", new StringEntity(output), "application/json", responseHandler);
        } catch (UnsupportedEncodingException e) {

        }

    }


}
