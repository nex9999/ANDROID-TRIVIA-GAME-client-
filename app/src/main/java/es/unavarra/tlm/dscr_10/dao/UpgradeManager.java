package es.unavarra.tlm.dscr_10.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by edu on 22/10/15.
 */
public class UpgradeManager extends DaoMaster.OpenHelper{
    public UpgradeManager(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
