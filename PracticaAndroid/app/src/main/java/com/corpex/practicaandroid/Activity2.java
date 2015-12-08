package com.corpex.practicaandroid;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class Activity2 extends AppCompatActivity implements DosFragment.OnDetalleShownListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        FragmentManager gestor = this.getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaccion= gestor.beginTransaction();
        DosFragment dosFrgt = DosFragment.newInstance((Alumno) getIntent().getExtras().getParcelable(DosFragment.EXTRA_ALUMNO), getIntent().getExtras().getInt(DosFragment.EXTRA_POSITION));
        transaccion.add(R.id.flHueco2, dosFrgt);
        transaccion.commit();
    }

    @Override
    public void onDetalleShown(int position) {
        //no se hace nada
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
