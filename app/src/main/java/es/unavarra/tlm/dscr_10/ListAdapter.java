package es.unavarra.tlm.dscr_10;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import es.unavarra.tlm.dscr_10.dao.*;
import es.unavarra.tlm.dscr_10.dao.Log;


public class ListAdapter extends BaseAdapter {

    Context context;
    LogDao logDao;
    List<Log> usuario;
    LayoutInflater inflater;

    public ListAdapter(Context context,LogDao logDao){

        this.context = context;
        this.logDao = logDao;
    }


    @Override
    public int getCount() {
        usuario = logDao.queryBuilder().where(LogDao.Properties.Acceso.isNotNull()).orderDesc(LogDao.Properties.Acceso).list();

        return usuario.size();
    }

    @Override
    public Object getItem(int position) {
        return usuario.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log registro   = usuario.get(position);
        String nombre = registro.getNombre().toString();
        Date date = registro.getAcceso();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = sdf.format(date);
        //System.out.print("\n"+cal +"\n");



        if (convertView == null) {
            convertView = inflater.from(parent.getContext()).inflate(R.layout.layout_list_item, parent, false);
            // mViewHolder = new MyViewHolder(convertView);
           // convertView.setTag(mViewHolder);
        } else {
          //  mViewHolder = (MyViewHolder) convertView.getTag();
        }

        //TextView nombreUsuario = (TextView)convertView.findViewById(R.id.textView);
        //TextView accesoUsuario = (TextView)convertView.findViewById(R.id.textView2);
        //nombreUsuario.setText(nombre);
        //accesoUsuario.setText(dateString);

        return convertView;
    }
}
