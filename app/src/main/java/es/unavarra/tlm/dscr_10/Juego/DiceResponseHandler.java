package es.unavarra.tlm.dscr_10.Juego;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.io.IOException;
import java.util.Random;
import java.util.Timer;

import cz.msebera.android.httpclient.Header;
import es.unavarra.tlm.dscr_10.R;

/**
 * Created by root on 26/11/15.
 */
public class DiceResponseHandler extends AsyncHttpResponseHandler {
    Context context;
    static GameDiceResponse message;
    /*Añadido nestor*/
    private ImageView diceImage;
    Activity activity;
    /*Añadido nestor*/

    public DiceResponseHandler(Context context){
        this.context = context;
        /*Añadido nestor*/
        activity = (Activity) context;
        diceImage = (ImageView) activity.findViewById(R.id.botondado);
        /*Añadido nestor*/
    }

    /*
     * Método para asignar una imagen al dado dependiendo de lo devuelto por lal base de datos.
     */
    public void fireDice() {
    /*Añadido nestor*/
        switch(DiceResponseHandler.message.getShift()) {
            case 1:
                diceImage.setImageResource(es.unavarra.tlm.dscr_10.R.drawable.one);
                break;
            case 2:
                diceImage.setImageResource(es.unavarra.tlm.dscr_10.R.drawable.two);
                break;
            case 3:
                diceImage.setImageResource(es.unavarra.tlm.dscr_10.R.drawable.three);
                break;
            case 4:
                diceImage.setImageResource(es.unavarra.tlm.dscr_10.R.drawable.four);
                break;
            case 5:
                diceImage.setImageResource(es.unavarra.tlm.dscr_10.R.drawable.five);
                break;
            case 6:
                diceImage.setImageResource(es.unavarra.tlm.dscr_10.R.drawable.six);
                break;
            default:
        }


    }



    @Override
    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
        try {
            message = new ObjectMapper().readValues(new JsonFactory().createParser(responseBody), GameDiceResponse.class).next();
           // Toast.makeText(this.context, String.valueOf(message.getShift()), Toast.LENGTH_LONG).show();
            fireDice();
            Jugar.dadotirado();


        } catch (IOException e) {
            Toast.makeText(this.context, "Cannot read response", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
        Toast.makeText(this.context, String.valueOf(statusCode), Toast.LENGTH_LONG).show();
    }
}
