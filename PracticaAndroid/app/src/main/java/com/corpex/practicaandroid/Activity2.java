package com.corpex.practicaandroid;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import java.util.ArrayList;

public class Activity2 extends AppCompatActivity implements DosFragment.OnDetalleShownListener {
    ImageView ivCollapse;
    Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbarLayout;
    ArrayList<Alumno> lista;
    public static final String EXTRA_LISTA = "listaI";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        iniciar();

        //Llamo al fragmento 2
        FragmentManager gestor = this.getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaccion= gestor.beginTransaction();
        DosFragment dosFrgt = DosFragment.newInstance((Alumno) getIntent().getExtras().getParcelable(DosFragment.EXTRA_ALUMNO), getIntent().getExtras().getInt(DosFragment.EXTRA_POSITION));
        transaccion.add(R.id.flHueco2, dosFrgt);
        transaccion.commit();
    }


    private void iniciar() {
        ivCollapse = (ImageView) findViewById(R.id.ivCollapse);
        if(getIntent().getExtras().getParcelableArrayList(EXTRA_LISTA) != null){
            lista = getIntent().getExtras().getParcelableArrayList(EXTRA_LISTA);
        }
        Alumno mAlumno = getIntent().getExtras().getParcelable(DosFragment.EXTRA_ALUMNO);
        assert mAlumno != null;
        ivCollapse.setImageBitmap((Bitmap) getIntent().getParcelableExtra("BitmapImage"));
        //Ya que obtenemos el alumno hacemos 2x1 y cambiamos el nombre de la toolbar colapsable
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbar);
        collapsingToolbarLayout.setTitle(mAlumno.getNombre());

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

    //al hacer el finish devuelvo la lista.
    @Override
    public void finish() {
        Intent resultado = new Intent();
        resultado.putExtra(EXTRA_LISTA, lista);
        setResult(RESULT_OK, resultado);
        super.finish();
    }


}
