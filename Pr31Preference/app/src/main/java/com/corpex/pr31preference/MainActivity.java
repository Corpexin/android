package com.corpex.pr31preference;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewConfiguration;
import android.widget.TextView;

import java.lang.reflect.Field;


public class MainActivity extends AppCompatActivity {

    private TextView lblPreferencias;
    private SharedPreferences preferencias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        overflowEnDispositivoConTeclaMenu();
        // Obtengo las preferencias.
        preferencias = PreferenceManager.getDefaultSharedPreferences(this);
        // Obtengo las vistas.
        //lblPreferencias = (TextView) this.findViewById(R.id.lblPreferencias);
    }

    @Override
    protected void onResume() {
        if (preferencias != null) {
            preferencias = PreferenceManager.getDefaultSharedPreferences(this);
            String lema = preferencias.getString("prefLema", "");
            setTitle(lema);
        }
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnuPreferencias:
                // Lanzamos la actividad de preferencias.
                Intent i = new Intent(this, PreferenciasActivity.class);
                this.startActivity(i);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Activa el ítem de overflow en dispositivos con botón físico de menú.
    private void overflowEnDispositivoConTeclaMenu() {
        try {
            ViewConfiguration config = ViewConfiguration.get(this);
            Field menuKeyField = ViewConfiguration.class
                    .getDeclaredField("sHasPermanentMenuKey");
            if (menuKeyField != null) {
                menuKeyField.setAccessible(true);
                menuKeyField.setBoolean(config, false);
            }
        } catch (Exception ex) {
            // Ignorar
        }
    }
}