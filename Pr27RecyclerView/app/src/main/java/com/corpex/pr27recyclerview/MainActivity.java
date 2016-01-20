package com.corpex.pr27recyclerview;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AlumnosAdapter.OnItemClickListener {

    private static final String STATE_LISTA = "estadoLista";
    private static final String STATE_DATOS = "estadoDatos";
    ArrayList<ListItem> datos;
    private AlumnosAdapter mAdaptador;
    private ArrayList<ListItem> mDatos;
    private LinearLayoutManager mLayoutManager;
    private Parcelable mEstadoLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState == null){
            mDatos = getDatos();
        }
        else{
            mDatos = savedInstanceState.getParcelableArrayList(STATE_DATOS);
            mEstadoLista = savedInstanceState.getParcelable(STATE_LISTA);
        }
        initViews();
    }

    private void initViews() {
        configToolbar();
        configRecyclerView();
        //configFab();
    }
/**
    private void configFab() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabAccion);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agregarAlumno();
            }
        });
    }
**/
    private void agregarAlumno() {
        datos.add(new Alumno("Alumno Prueba"));
    }


    private void configRecyclerView() {
        RecyclerView lstAlumnos = (RecyclerView) findViewById(R.id.lstAlumnos);
        lstAlumnos.setHasFixedSize(true);
        mAdaptador = new AlumnosAdapter(mDatos);
        mAdaptador.setOnItemClickListener(this);
        lstAlumnos.setAdapter(mAdaptador);
        mLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        lstAlumnos.setLayoutManager(mLayoutManager);
        lstAlumnos.setItemAnimator(new DefaultItemAnimator());
    }

    private void configToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null){
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(View view, Alumno alumno, int position) {
        Toast.makeText(this, getString(R.string.datos_alumno,alumno.getNombre()),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Se salva el estado del RecyclerView.
        mEstadoLista = mLayoutManager.onSaveInstanceState();
        outState.putParcelable(STATE_LISTA, mEstadoLista);
        outState.putParcelableArrayList(STATE_DATOS, mAdaptador.getData());

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Se obtiene el estado anterior de la lista.
        mEstadoLista = savedInstanceState.getParcelable(STATE_LISTA);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Se retaura el estado de la lista.
        if (mEstadoLista != null) {
            mLayoutManager.onRestoreInstanceState(mEstadoLista);
        }
    }


    private ArrayList<ListItem> getDatos() {
        datos = new ArrayList<>();
        // Primer grupo.
        datos.add(new Grupo("CFGM Sistemas Microinformáticos y Redes"));
        datos.add(new Alumno("Baldomero"));
        datos.add(new Alumno("Sergio"));
        datos.add(new Alumno("Atanasio"));
        datos.add(new Alumno("Oswaldo"));
        datos.add(new Alumno("Rodrigo"));
        datos.add(new Alumno("Antonio"));
        // Segundo grupo.
        datos.add(new Grupo("CFGS Desarrollo de Aplicaciones Multiplataforma"));
        datos.add(new Alumno("Pedro"));
        datos.add(new Alumno("Pablo"));
        datos.add(new Alumno("Rodolfo"));
        datos.add(new Alumno("Gervasio"));
        datos.add(new Alumno("Prudencia"));
        datos.add(new Alumno("Gumersindo"));
        datos.add(new Alumno("Gerardo"));
        datos.add(new Alumno("Óscar"));
        return datos;
    }
}
