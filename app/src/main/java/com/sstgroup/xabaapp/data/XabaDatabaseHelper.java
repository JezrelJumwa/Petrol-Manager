package com.sstgroup.xabaapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by julianlubenov on 5/10/17.
 */

public class XabaDatabaseHelper {
    private static XabaDatabaseHelper instance;

    private static DaoMaster.DevOpenHelper helper;
    private static SQLiteDatabase db;
    private static DaoMaster daoMaster;
    private static DaoSession daoSession;
    private static Context context;

    public static XabaDatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new XabaDatabaseHelper();

            helper = new DaoMaster.DevOpenHelper(context, "xaba-db", null);
            db = helper.getWritableDatabase();
            daoMaster = new DaoMaster(db);
            daoSession = daoMaster.newSession();
        }
        return  instance;
    }

    public void insertOrReplaceProfile(Profile profile) {

    }
}
