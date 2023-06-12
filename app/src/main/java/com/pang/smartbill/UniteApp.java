package com.pang.smartbill;

import android.app.Application;

import com.pang.smartbill.db.DBManager;

/* A class that represents a global application*/
public class UniteApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // initialized database
        DBManager.initDB(getApplicationContext());
    }
}
