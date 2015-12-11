package com.corpex.practicaandroid;


import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

//Activity que tiene una imagen colapsada de perfil y un fragmento con la informacion del perfil
public class Activity2 extends AppCompatActivity implements DosFragment.OnDetalleShownListener {
    ImageView ivCollapse;
    Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbarLayout;

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
        Alumno mAlumno = getIntent().getExtras().getParcelable(DosFragment.EXTRA_ALUMNO);
        assert mAlumno != null;
        //Ya que obtenemos el alumno hacemos 2x1 y cambiamos el nombre de la toolbar colapsable
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbar);
        collapsingToolbarLayout.setTitle(mAlumno.getNombre());
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Cargamos en la imagen colapsada la imagen de lorenpixel dependiendo de su numero de perfil
        Picasso.with(getApplicationContext()).load("http://lorempixel.com/320/320/people/"+mAlumno.getIdPerfil()).into(ivCollapse);

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
