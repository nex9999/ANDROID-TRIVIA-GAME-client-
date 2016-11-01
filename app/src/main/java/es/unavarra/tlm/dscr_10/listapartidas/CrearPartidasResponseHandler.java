package es.unavarra.tlm.dscr_10.listapartidas;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;
import es.unavarra.tlm.dscr_10.R;

/**
 * Created by root on 19/11/15.
 */
public class CrearPartidasResponseHandler extends AsyncHttpResponseHandler {

    Context context = null;
    Activity activity;
    public CrearPartidasResponseHandler(ListaPartidasTabs context){
        this.context = context;
        activity = (Activity)context;
    }
    @Override
    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
        Toast.makeText(this.context,String.valueOf(statusCode),Toast.LENGTH_LONG);
        ViewPager mViewPager = (ViewPager) activity.findViewById(R.id.pager);
        mViewPager.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

    }
}
