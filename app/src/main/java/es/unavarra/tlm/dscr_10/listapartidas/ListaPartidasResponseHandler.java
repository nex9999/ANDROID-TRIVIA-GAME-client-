package es.unavarra.tlm.dscr_10.listapartidas;

import android.support.v4.view.ViewPager;
import android.widget.ListView;
import android.widget.Toast;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.AsyncHttpResponseHandler;
import java.io.IOException;
import cz.msebera.android.httpclient.Header;
import es.unavarra.tlm.dscr_10.Juego.ListenerInfo;
import es.unavarra.tlm.dscr_10.Juego.ListenerJoin;
import es.unavarra.tlm.dscr_10.R;


public class ListaPartidasResponseHandler extends AsyncHttpResponseHandler {

    private ListaPartidasTabs context = null;
    private ListaPartidasListAdapter adapter;
    private ListView listView;
    public ListaPartidasResponseMessage message = null;
    int section;
    private SectionFragment fragment = null;

    public ListaPartidasResponseHandler(ListaPartidasTabs context,ListView listView, int section) {
        this.context = context;
        this.listView = listView;
        this.section = section;
    }
    public ListaPartidasResponseHandler(ListaPartidasTabs context,ListView listView, int section,SectionFragment _fragment) {
        this.context = context;
        this.listView = listView;
        this.section = section;
        fragment = _fragment;
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

        try {
            message = new ObjectMapper().readValues(new JsonFactory().createParser(responseBody), ListaPartidasResponseMessage.class).next();
           // Toast.makeText(this.context, message.getGame(0).getToken(), Toast.LENGTH_SHORT).show();

            setListaAdapter();

            //Toast.makeText(this.context, message.getGame(0).getCreator().getName().toString(), Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            Toast.makeText(this.context, "Cannot read response", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

    }

    public void setListaAdapter(){
        adapter = new ListaPartidasListAdapter(context,message);

        listView.setAdapter(adapter);

        switch (section){
            case 0:

                listView.setOnItemClickListener(new ListenerJoin(context,message,fragment));

                break;

            case 1:
                //Toast.makeText(context,"Sección "+section,Toast.LENGTH_LONG);

                listView.setOnItemClickListener(new ListenerInfo(context,message));
                break;

            case 2:
                //Toast.makeText(context,"Sección "+section,Toast.LENGTH_LONG);
                //URLsection = "http://trivial.tatai.es:80/list/created.json";
                break;
        }

       //ViewPager mViewPager = (ViewPager) context.findViewById(R.id.pager);
       // mViewPager.getAdapter().notifyDataSetChanged();


    }

}
