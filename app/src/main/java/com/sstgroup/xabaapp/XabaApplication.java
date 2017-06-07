package com.sstgroup.xabaapp;

import android.app.Application;
import android.content.Context;

import com.sstgroup.xabaapp.db.DatabaseHelper;
import com.sstgroup.xabaapp.models.DaoMaster;
import com.sstgroup.xabaapp.models.DaoSession;
import com.sstgroup.xabaapp.models.Token;
import com.sstgroup.xabaapp.models.User;
import com.sstgroup.xabaapp.ui.activities.MainActivity;
import com.sstgroup.xabaapp.utils.NavigationUtils;
import com.sstgroup.xabaapp.utils.Preferences;

import org.greenrobot.greendao.database.Database;

import timber.log.Timber;


/**
 * Created by julianlubenov on 5/10/17.
 */

public class XabaApplication extends Application {
    private static XabaApplication instance;
    private Token mLoggedUserToken;


    public static XabaApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        User user = DatabaseHelper.getInstance(this).getLoggedUser(this);
        if (user != null)
            mLoggedUserToken = user.getToken();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    public boolean isAuthenticated() {
        return this.mLoggedUserToken != null;
    }

    public void setToken(Token token) {
        this.mLoggedUserToken = token;
    }

    public Token getToken() {
        return this.mLoggedUserToken;
    }

    public void logout(){
        String locationHash = Preferences.getLocationHash(this);
        String professionHash = Preferences.getProfessionHash(this);
        Preferences.clear(getApplicationContext());
        DatabaseHelper.getInstance(this).dropDb();
        setToken(null);
        Preferences.setLocationHash(this, locationHash);
        Preferences.setProfessionHash(this, professionHash);
        NavigationUtils.startSingleActivity(getApplicationContext(), MainActivity.class);
    }
}
