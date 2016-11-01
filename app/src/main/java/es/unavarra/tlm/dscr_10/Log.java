package es.unavarra.tlm.dscr_10;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.app.Activity;
import android.widget.ListView;

import es.unavarra.tlm.dscr_10.dao.DaoMaster;
import es.unavarra.tlm.dscr_10.dao.DaoSession;
import es.unavarra.tlm.dscr_10.dao.LogDao;
import es.unavarra.tlm.dscr_10.dao.UpgradeManager;

public class Log extends Activity {

    ListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        UpgradeManager helper = new UpgradeManager(this, "app.db", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession daoSession = daoMaster.newSession();
        LogDao logDao = daoSession.getLogDao();


        adapter = new ListAdapter(this,logDao);
        ListView listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(adapter);

    }

}
