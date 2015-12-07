package com.corpex.practicaandroid;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class Activity2 extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener, DosFragment.OnDetalleShownListener {

    private FragmentManager gestor;
    Alumno alumno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        recibirIntent();
        gestor = getSupportFragmentManager();
        //La clase actuara como listener eb BackStack
        gestor.addOnBackStackChangedListener(this);
        //Se carga el fragmento si no esta cargado ya
        DosFragment dosFrg = (DosFragment) gestor.findFragmentById(R.id.flHueco2); //??????
        if (dosFrg == null) {
            // Se carga el fragmento de lista sin añadirlo a la BackStack
            //dosFrg = new DosFragment();
            //gestor.beginTransaction().replace(R.id.flHueco2, dosFrg, "unoFrg").commit();
            android.support.v4.app.FragmentTransaction transaccion= gestor.beginTransaction();
            DosFragment dosFrgt = DosFragment.newInstance((Alumno) getIntent().getExtras().getParcelable(DosFragment.EXTRA_ALUMNO), getIntent().getExtras().getInt(DosFragment.EXTRA_POSITION));
            transaccion.add(R.id.flHueco2, dosFrgt);
            transaccion.commit();

        }
    }

    private void recibirIntent() {
        Intent i = getIntent();
        Bundle b = i.getExtras();
        alumno = b.getParcelable(DosFragment.EXTRA_ALUMNO);
    }

    @Override
    public void onBackStackChanged() {

    }

    @Override
    public void onDetalleShown(int position) {
        //no se hace nada
    }
}
