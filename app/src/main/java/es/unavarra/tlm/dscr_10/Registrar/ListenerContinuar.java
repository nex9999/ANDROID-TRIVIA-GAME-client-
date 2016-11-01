package es.unavarra.tlm.dscr_10.Registrar;

import android.view.View;
import android.widget.EditText;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.AsyncHttpClient;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.entity.StringEntity;

/**
 * Created by dscr03 on 10/8/15.
 */
public class ListenerContinuar implements View.OnClickListener{

    final EditText email;
    final EditText nombre;
    final EditText password;
    String output;
    final AsyncHttpClient client;
    final registro activity;

    public ListenerContinuar(EditText email,EditText password,EditText nombre,AsyncHttpClient client,registro activity){
         this.email = email;
         this.nombre = nombre;
         this.password = password;
         this.client = client;
         this.activity = activity;

        System.out.print("\n"+"entra");
    }

    @Override
    public void onClick(View v) {

        try {
            convertToJson();

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


    }

    private void convertToJson() throws JsonProcessingException {

        DatosRegistro datosRegistro = new DatosRegistro();
        datosRegistro.setEmail(email.getText().toString());
        datosRegistro.setName(nombre.getText().toString());
        datosRegistro.setPassword(password.getText().toString());

        output = new ObjectMapper().writeValueAsString(datosRegistro);
        enviar();
        System.out.print("\n"+output);
    }


    private void enviar(){
        RegistroResponseHandler responseHandler = new RegistroResponseHandler(this.activity);
        try {
            this.client.post(this.activity, "http://trivial.tatai.es:80/player/register.json", new StringEntity(output), "application/json", responseHandler);
        } catch (UnsupportedEncodingException e) {

        }

    }
}
