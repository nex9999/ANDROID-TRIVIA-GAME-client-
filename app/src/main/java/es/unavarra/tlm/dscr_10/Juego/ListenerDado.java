package es.unavarra.tlm.dscr_10.Juego;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.SoundPool;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.AsyncHttpClient;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.entity.StringEntity;
import es.unavarra.tlm.dscr_10.R;
import es.unavarra.tlm.dscr_10.listapartidas.PlayerAuthRequest;

/**
 * Created by root on 26/11/15.
 */
public class ListenerDado implements View.OnClickListener{

    SharedPreferences settings;
    PlayerAuthRequest login;
    String gametoken, output;
    Context context;
    DiceResponseHandler responseHandler;
    AsyncHttpClient client = null;
    int sound_id;//Añadido nestor
    SoundPool dice_sound = new SoundPool(1, AudioManager.STREAM_MUSIC,0);//Añadido nestor
    private ImageView diceImage;//Añadido nestor
    Activity activity;//Añadido nestor
    public ListenerDado(Context context){

        this.context = context;
        client = new AsyncHttpClient();
        responseHandler = new DiceResponseHandler(this.context);
        sound_id=dice_sound.load(context,es.unavarra.tlm.dscr_10.R.raw.shake_dice,1);
        activity = (Activity) context;
        diceImage = (ImageView) activity.findViewById(R.id.botondado);

    }

    @Override
    public void onClick(View view) {

        settings = context.getSharedPreferences("Config", 0);
        login = new PlayerAuthRequest();
        login.setToken(settings.getString("token",""));
        gametoken = Jugar.infoMessage.getToken();
        try {
            output = convertToJson();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


        //Toast.makeText(getActivity(),output,Toast.LENGTH_LONG).show();
        String URL = "http://trivial.tatai.es:80/game/"+gametoken+"/dice.json";
        try {
            diceImage.setImageResource(es.unavarra.tlm.dscr_10.R.drawable.dice3droll);
            dice_sound.play(sound_id,1.0f,1.0f,0,0,1.0f);//Añadido nestor
            this.client.post(context, URL, new StringEntity(output), "application/json", responseHandler);

        } catch (UnsupportedEncodingException e) {

        }


    }
    private String convertToJson() throws JsonProcessingException {

        output = new ObjectMapper().writeValueAsString(login);
        return output;

    }


}
