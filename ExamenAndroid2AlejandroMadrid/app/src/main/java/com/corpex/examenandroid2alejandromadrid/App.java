package com.corpex.examenandroid2alejandromadrid;

/**
 * Created by corpex, by the Grace of God on 03/03/2016.
 */
import android.app.Application;

import com.facebook.stetho.Stetho;


public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}