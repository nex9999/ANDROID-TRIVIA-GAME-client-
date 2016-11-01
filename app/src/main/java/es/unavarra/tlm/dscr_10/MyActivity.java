package es.unavarra.tlm.dscr_10;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import es.unavarra.tlm.dscr_10.CarpetaLogin.entrar;
import es.unavarra.tlm.dscr_10.listapartidas.ListaPartidasTabs;
import es.unavarra.tlm.dscr_10.listapartidas.PlayerAuthRequest;
import info.hoang8f.widget.FButton;


public class MyActivity extends Activity {
    SharedPreferences settings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        final Context context = this.findViewById(android.R.id.content).getContext();
        final FButton botonentrar = (FButton) findViewById(R.id.entrar);
        settings = context.getSharedPreferences("Config", 0);

        String token = settings.getString("token","");
        if (token.equals("")){
            botonentrar.setOnClickListener (new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent entrar = new Intent(context, entrar.class);
                    startActivity(entrar);
                }
            });

            final FButton botonregistro = (FButton) findViewById(R.id.registro);
            final Context context2 = this.findViewById(android.R.id.content).getContext();
            botonregistro.setOnClickListener (new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent registro = new Intent(context2, es.unavarra.tlm.dscr_10.Registrar.registro.class);
                    startActivity(registro);
                }
            });
        }else{
            finish();
            Intent listaPartidas = new Intent(context,ListaPartidasTabs.class);
            context.startActivity(listaPartidas);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
