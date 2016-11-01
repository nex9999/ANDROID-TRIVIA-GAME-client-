package es.unavarra.tlm.dscr_10.listapartidas;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.AsyncHttpClient;
import java.io.UnsupportedEncodingException;
import cz.msebera.android.httpclient.entity.StringEntity;
import es.unavarra.tlm.dscr_10.Juego.ListenerJoin;
import es.unavarra.tlm.dscr_10.R;


public class SectionFragment extends Fragment {

    public static final String ARG_SECTION_NUMBER = "section_number";
    public static final int AVAILABLE_TAB_SECTION = 0;
    public static final int PLAYING_TAB_SECTION = 1;
    public static final int CREATED_TAB_SECTION = 2;

    String output;
    AsyncHttpClient client = null;
    ListView listview;
    String URLsection = "";
    int section;

    SharedPreferences settings;
    SharedPreferences.Editor editor;

    public SectionFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        settings = getActivity().getSharedPreferences("Config", 0);
        editor = settings.edit();

        View rootView = inflater.inflate(R.layout.fragment_main_listas, container, false);


        section = getArguments().getInt(ARG_SECTION_NUMBER);
        listview = (ListView)rootView.findViewById(R.id.listapartidas_tabs);

        switch (section){
            case AVAILABLE_TAB_SECTION:
                URLsection = "http://trivial.tatai.es:80/list/available.json";
                //listview.setOnItemClickListener(new ListenerJoin(getActivity()));
            break;

            case PLAYING_TAB_SECTION:
                URLsection = "http://trivial.tatai.es:80/list/playing.json";
            break;

            case CREATED_TAB_SECTION:
                URLsection = "http://trivial.tatai.es:80/list/created.json";
            break;
        }

        client = new AsyncHttpClient();

        crearLista();

        return rootView;


    }
    public void update(){
        crearLista();
        FragmentManager fmanager = getActivity().getSupportFragmentManager();
    }

    private void crearLista(){
        try {
            convertToJson();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

    private void convertToJson() throws JsonProcessingException {
        PlayerAuthRequest login = new PlayerAuthRequest();
        login.setToken(settings.getString("token",""));
        
        output = new ObjectMapper().writeValueAsString(login);
        enviar();
    }

    private void enviar(){
        ListaPartidasResponseHandler responseHandler = new ListaPartidasResponseHandler((ListaPartidasTabs)getActivity(),listview, section,this);
        //Toast.makeText(getActivity(),output,Toast.LENGTH_LONG).show();
        try {
            this.client.post(getActivity(), URLsection, new StringEntity(output), "application/json", responseHandler);
        } catch (UnsupportedEncodingException e) {

        }

    }

}