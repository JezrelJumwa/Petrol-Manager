package com.sstgroup.xabaapp;

import android.app.Application;
import android.content.Context;

import com.sstgroup.xabaapp.data.DaoMaster;
import com.sstgroup.xabaapp.data.DaoMaster.DevOpenHelper;
import com.sstgroup.xabaapp.data.DaoSession;

import org.greenrobot.greendao.database.Database;

import timber.log.Timber;


/**
 * Created by julianlubenov on 5/10/17.
 */

public class XabaApplication extends Application {
    private static XabaApplication instance;

    private DaoSession daoSession;


    public static Context getInstance() {

        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        DevOpenHelper helper = new DevOpenHelper(this, "xaba-db", null);
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
