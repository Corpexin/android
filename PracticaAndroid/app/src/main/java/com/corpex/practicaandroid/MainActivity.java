package com.corpex.practicaandroid;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener, UnoFragment.OnAlumnoSelectedListener, DosFragment.OnDetalleShownListener {
    private static final int RC_NUEVOAL = 1;
    private static final String STATE_LISTA = "estadoLista";
    public static final int RC_OTRA = 3;
    private FragmentManager gestor;
    private FrameLayout flHueco2;
    ArrayList<Alumno> lista;
    //ArrayList<Alumno> listaRemove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Conectamos la toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Obtencion de Vista
        flHueco2 = (FrameLayout) this.findViewById(R.id.flHueco2);
        //Obtencion del Gestor del Fragmento
        gestor = getSupportFragmentManager();
        //La clase actuara como listener en BackStack
        gestor.addOnBackStackChangedListener(this);
        if(savedInstanceState != null){
            lista = savedInstanceState.getParcelableArrayList(STATE_LISTA);
            actualizarFragmentoUno();
        }
        else{
            FragmentManager gestor = this.getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction transaccion= gestor.beginTransaction();
            UnoFragment unoFrgt = UnoFragment.newInstance(lista);
            transaccion.replace(R.id.flHueco, unoFrgt);
            transaccion.commitAllowingStateLoss();
        }

    }


    @Override
    public void onAlumnoSelected(Alumno alumno, int position) {
        //Si hay FrameLayout de detalle lo muestra
        if (flHueco2 != null) {
            mostrarFragmentoDos(alumno, position);
        } else {
            //Hay dos actividades. Creo la activity2 y le paso el alumno y la lista de añadidos
            Intent i = new Intent(this, Activity2.class);
            i.putExtra(DosFragment.EXTRA_ALUMNO, alumno);
            i.putExtra(Activity2.EXTRA_LISTA, lista);
            i.putExtra("BitmapImage", alumno.getImagen());
            startActivityForResult(i, RC_OTRA);
        }
    }

    //Recarga  el fragmento uno (para cuando insertemeos un nuevo alumno)
    private void actualizarFragmentoUno() {
        FragmentManager gestor = this.getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaccion= gestor.beginTransaction();
        UnoFragment unoFrgt = UnoFragment.newInstance(lista);
        transaccion.replace(R.id.flHueco, unoFrgt);
        transaccion.commitAllowingStateLoss();
    }

    //mostrar la secundaria
    private void mostrarFragmentoDos(Alumno alumno, int position) {
        DosFragment frgDetalle = DosFragment.newInstance(alumno, position);
        FragmentTransaction transaccion = gestor.beginTransaction();
        transaccion.replace(R.id.flHueco2, frgDetalle);
        transaccion.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
        transaccion.addToBackStack(alumno.getNombre());
        transaccion.commit();
    }

    @Override
    public void onDetalleShown(int position) {
        UnoFragment frg = (UnoFragment) gestor.findFragmentById(R.id.flHueco);
        if (frg != null) {
            frg.marcarAlumno(position);
        }
    }

    @Override
    public void onBackStackChanged() {
        //Backstack vacia + atras = salir app
        int numEntradas = gestor.getBackStackEntryCount();
        if (numEntradas == 0) {
            onBackPressed();
        } else {
            if (numEntradas > 1) {
                FragmentManager.BackStackEntry entrada = gestor.getBackStackEntryAt(gestor.getBackStackEntryCount() - 2);
                Toast toast = Toast.makeText(this,"Pulsa Back para volver a \n\"" + entrada.getName()+ "\"", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.BOTTOM, 0, 0);
                toast.show();
            }
        }
    }

    //Toolbar Botones
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    //Si se pulsa el añadir usuario inicia actividad 3
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.mnuNext) {
            startActivityForResult(new Intent(this, Activity3.class), RC_NUEVOAL);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //Recibir alumno creado en actividad 3
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (resultCode == Activity3.ID_RESULT) {
            if(lista == null){
                lista = new ArrayList<>();
            }
            lista.add((Alumno)data.getParcelableExtra(Activity3.ALUMNO));
            actualizarFragmentoUno();
            Toast.makeText(this,"Alumno Creado Correctamente", Toast.LENGTH_SHORT).show();

        }
        else if(resultCode == Activity2.RESULT_OK){
            lista = data.getParcelableArrayListExtra(Activity2.EXTRA_LISTA);
            actualizarFragmentoUno();
            Toast.makeText(this,"RECARGADO", Toast.LENGTH_SHORT).show();
        }
    }

    //Evitamos perder la lista al girar la pantalla
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(STATE_LISTA, lista);
    }

}