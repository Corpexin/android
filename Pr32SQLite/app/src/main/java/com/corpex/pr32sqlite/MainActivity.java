package com.corpex.pr32sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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

public class MainActivity extends AppCompatActivity implements AlumnosAdapter.OnItemClickListener, AlumnosAdapter.OnItemLongClickListener{
    public static final String EXTRA_MOD = "Agregar";
    private static final int RC_NUEVO = 1;
    ArrayList<Alumno> alumnos;
    RecyclerView lstAlumnos;
    AlumnosAdapter mAdaptador;
    LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initViews();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agregarAlumno();
            }
        });
        addAlumnos();
    }

    private void agregarAlumno() {
        Intent intent = new Intent(this, Main2Activity.class);
        intent.putExtra(EXTRA_MOD, false);
        startActivityForResult(intent, RC_NUEVO);
    }


    private void initViews() {
        // Se obtienen los datos de los alumnos a través del DAO.
        alumnos = (ArrayList<Alumno>) (new DAO(this)).getAllAlumnos();

        lstAlumnos = (RecyclerView) findViewById(R.id.lstAlumnos);
        lstAlumnos.setHasFixedSize(true);
        mAdaptador = new AlumnosAdapter(alumnos);
        //mAdaptador = new AlumnosAdapter(DB.getAlumnos());
        mAdaptador.setOnItemClickListener(this);
        mAdaptador.setOnItemLongClickListener(this);
        lstAlumnos.setAdapter(mAdaptador);
        mLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        lstAlumnos.setLayoutManager(mLayoutManager);
        lstAlumnos.setItemAnimator(new DefaultItemAnimator());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void addAlumnos() {
        alumnos.add(new Alumno());
        //new DAO(getActivity())).createAlumno(mAlumno)
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(View view, Alumno alumno, int position) {

    }

    @Override
    public void onItemLongClick(View view, Alumno alumno, int position) {

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Alumno alumno;
        if (resultCode == RESULT_OK) {
            if (requestCode == RC_NUEVO && data.hasExtra(Main2Activity.EXTRA_ALUMNO)) {
                alumno = data.getParcelableExtra(Main2Activity.EXTRA_ALUMNO);
                long id = (new DAO(this)).createAlumno(alumno);
                alumno.setId((int) id);
                mAdaptador.addItem(alumno);
                //mAdaptador.notifyDataSetChanged();
                Toast.makeText(this,"Insercion Correcta", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(this,"Insercion INCorrecta", Toast.LENGTH_SHORT).show();
            }

            /**} else if (requestCode == RC_EDIT_ALUMNO && data.hasExtra(Main2Activity.EXTRA_ALUMNO)){
                a = data.getParcelableExtra(AgregarActualizarActivity.EXTRA_ALUMNO);
                Instituto.getServicio().updateAlumno(a.getId(), a).enqueue(new Callback<Alumno>() {
                    @Override
                    public void onResponse(Response<Alumno> response) {

                    }

                    @Override
                    public void onFailure(Throwable t) {

                    }
                });**/

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
