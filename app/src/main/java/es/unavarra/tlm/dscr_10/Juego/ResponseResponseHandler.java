package es.unavarra.tlm.dscr_10.Juego;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Timer;
import java.util.TimerTask;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;
import es.unavarra.tlm.dscr_10.R;
import es.unavarra.tlm.dscr_10.listapartidas.ListaPartidasTabs;
import es.unavarra.tlm.dscr_10.listapartidas.PlayerAuthRequest;

/**
 * Created by root on 26/11/15.
 */
public class ResponseResponseHandler extends AsyncHttpResponseHandler {

    Pregunta context;
    private ProgressDialog progressDialog;//añadido nestor
    GameResponseResponse message;
    SharedPreferences settings;
    PlayerAuthRequest login;
    String gametoken, output;
    AsyncHttpClient client;
    Timer timer;

    public ResponseResponseHandler(Pregunta context){
        this.context = context;
        client = new AsyncHttpClient();

    }
    @Override
    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
        try {
            message = new ObjectMapper().readValues(new JsonFactory().createParser(responseBody), GameResponseResponse.class).next();
           // Toast.makeText(this.context, String.valueOf(statusCode), Toast.LENGTH_LONG).show();


        } catch (IOException e) {
            Toast.makeText(this.context, "Cannot read response", Toast.LENGTH_SHORT).show();
        }
        resultado();
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

    }

    public void resultado(){
        Pregunta.pregunta_this.finish();
        if(message.getCorrect()==true){

            if(message.getHq()==true){

                LayoutInflater inflater = this.context.getLayoutInflater();
                View layout = inflater.inflate(R.layout.custom_layout_toast,
                        (ViewGroup) this.context.findViewById(R.id.toast_layout_root));
                RelativeLayout imagen = (RelativeLayout)layout.findViewById(R.id.imagen);
                switch(message.getCategory()){
                    case "blue":
                       imagen.setBackgroundResource(R.drawable.blue_cheese);
                        break;
                    case "brown":
                        imagen.setBackgroundResource(R.drawable.brown_cheese);
                        break;
                    case "yellow":
                        imagen.setBackgroundResource(R.drawable.yellow_cheese);
                        break;
                    case "orange":
                        imagen.setBackgroundResource(R.drawable.orange_cheese);
                        break;
                    case "green":
                        imagen.setBackgroundResource(R.drawable.green_cheese);
                        break;
                    case "red":
                        imagen.setBackgroundResource(R.drawable.red_cheese);
                        break;

                }
                Toast toast = new Toast(this.context);
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.setView(layout);
                toast.show();


            }else{
                LayoutInflater inflater = this.context.getLayoutInflater();
                View layout = inflater.inflate(R.layout.custom_layout_toast,
                        (ViewGroup) this.context.findViewById(R.id.toast_layout_root));
                RelativeLayout imagen = (RelativeLayout)layout.findViewById(R.id.imagen);
                switch(message.getCategory()){
                    case "blue":
                        imagen.setBackgroundResource(R.drawable.blue_tick);
                        break;
                    case "brown":
                        imagen.setBackgroundResource(R.drawable.brown_tick);
                        break;
                    case "yellow":
                        imagen.setBackgroundResource(R.drawable.yellow_tick);
                        break;
                    case "orange":
                        imagen.setBackgroundResource(R.drawable.orange_tick);
                        break;
                    case "green":
                        imagen.setBackgroundResource(R.drawable.green_tick);
                        break;
                    case "red":
                        imagen.setBackgroundResource(R.drawable.red_tick);
                        break;

                }
                TextView texto = (TextView)layout.findViewById(R.id.texto);
                texto.setText("¡Correcto!");
                Toast toast = new Toast(this.context);
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.setView(layout);
                toast.show();
            }

            settings = context.getSharedPreferences("Config", 0);
            login = new PlayerAuthRequest();
            login.setToken(settings.getString("token",""));
            gametoken = settings.getString("gametoken","");
            try {
                output = convertToJson();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            //ProgressDialog progressDialog = ProgressDialog.show(context, "Trivia", "Loading...");

            InfoResponseHandler responseHandler = new InfoResponseHandler(this.context,progressDialog);
            //Toast.makeText(getActivity(),output,Toast.LENGTH_LONG).show();
            String URL = "http://trivial.tatai.es:80/game/"+gametoken+"/info.json";
            try {
                //Toast.makeText(context, output, Toast.LENGTH_LONG).show();
                this.client.post(context, URL, new StringEntity(output), "application/json", responseHandler);
                timer = new Timer();
                //this.context.finish();
                /*
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        context.acabar();
                        timer.cancel();

                    }
                }, 1000, 100);*/
            } catch (UnsupportedEncodingException e) {

            }

        }else{

            Toast.makeText(this.context, "Has fallado", Toast.LENGTH_SHORT).show();
        }
    }
    private String convertToJson() throws JsonProcessingException {

        output = new ObjectMapper().writeValueAsString(login);
        return output;

    }
}
