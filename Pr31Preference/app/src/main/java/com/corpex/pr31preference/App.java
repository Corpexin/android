package com.corpex.pr31preference;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by corpex, by the Grace of God on 29/01/2016.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
