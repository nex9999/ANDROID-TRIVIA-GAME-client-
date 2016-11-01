package es.unavarra.tlm.dscr_10.CarpetaLogin;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import com.loopj.android.http.AsyncHttpClient;
import es.unavarra.tlm.dscr_10.R;




public class entrar extends Activity {

    SharedPreferences settings;
    SharedPreferences.Editor editor;
    EditText campoEmail;
    EditText campoPass ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrar);

        settings = getSharedPreferences("Config", 0);
        editor = settings.edit();
        campoEmail = (EditText)findViewById(R.id.email);
        campoPass = (EditText)findViewById(R.id.password);

        final Button botonaceptar = (Button) findViewById(R.id.aceptar);
        //final Context context = this.findViewById(android.R.id.content).getContext();


        //Vamos a iniciar la base de datos como salía en el guión de la práctica.

        //_________________________________________________________________________

        //Miramos a ver si el shared preferences tiene algo.

        //if(settings.getString("username","")!=""){

        //Si tiene algo dejamos invisible en editText y el botón de aceptar.
         //Además hacemos el query a la base de datos preguntando por el usuario de la sharedPreference.
            //Vemos que nos devuelve una lista de tipo Log. Como sólo va a haber una coincidencia con el nombre
            //de usuario, la lista solo tiene un elemento. Por eso ponemos el get(0) y luego el getNombre(), para
            //que coja el campo nombre. También cogemos el campo Acceso.
        //    List<Log> usuario = getList();

       // }

        //Si pulsa salir dejamos el sharedPreferences vacío.
        /*botonSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("username","");
                editor.commit();
                finish();

            }
        });*/

        AsyncHttpClient client = new AsyncHttpClient();
        botonaceptar.setOnClickListener(new ListenerLogin(campoEmail,campoPass,client,this));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.entrar, menu);
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

    public void getList(){

        //Vamos a hacer una querybuilder, que nos devuelve una lista de elementos Log. Preguntamos
        //Por el nombre de la sharedPrefernces.

        String nombre = settings.getString("username","");


        //List<Log> usuario = logDao.queryBuilder().where(LogDao.Properties.Nombre.eq(nombre)).list();
        //return usuario;

    }
}
