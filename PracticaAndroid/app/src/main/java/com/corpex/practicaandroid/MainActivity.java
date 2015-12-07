package com.corpex.practicaandroid;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener, UnoFragment.OnAlumnoSelectedListener {
    private FragmentManager gestor;
    private FrameLayout detalle;
    //fllista = fragmento 1 fldetalles = fragmento2

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Obtenicon de Vista
        //ACTIVAR CUANDO SE CREE LA SEGUNDA ACTIVIDAD DETALLES detalle = (FrameLayout) this.findViewById(R.id.flHueco);
        //Obtencion del Gestor del Fragmento
        gestor = getSupportFragmentManager();
        //La clase actuara como listener en BackStack
        gestor.addOnBackStackChangedListener(this);
        //Se carga el fragmento si no esta cargado ya
        UnoFragment unoFrg = (UnoFragment) gestor.findFragmentById(R.id.flHueco); //??????
        if (unoFrg == null) {
            // Se carga el fragmento de lista sin añadirlo a la BackStack
            unoFrg = new UnoFragment();
            gestor.beginTransaction().replace(R.id.flHueco, unoFrg, "unoFrg").commit();
        }
    }

    //Faltaria algo asi como onAlumnoSelected
    @Override
    public void onAlumnoSelected(Alumno alumno, int position) {
        // Si hay FrameLayout de detalle (puede que no haya porque por el tamaño
        // del dispositivo tengamos dos actividades distintas).
        if (detalle != null) {
            // Se muestra el detalle de la obra.
            mostrarFragmentoDetalle(alumno, position);
        } else {
            // Hay dos actividades. Se llama a la otra actividad pasándole la
            // obra a mostrar (cuya clase debe implementar Parcelable).
            Intent i = new Intent(this, Activity2.class);
            ////////HACER UN PARCELABLE Y ENVIARLO A LA ACTIVIDAD 2
            ////////HACER QUE LA ACTIVIDAD 2 LO RECIBA Y CAMBIE LOS DATOS
            i.putExtra(DosFragment.EXTRA_ALUMNO, alumno);
            this.startActivity(i);
        }
    }

    //mostrar la secundaria
    private void mostrarFragmentoDetalle(Alumno alumno, int position) {

    }
    //Faltaria un metodo para cargar el detalle en el framelayout correspondiente
    // Faltaria Cuando se muestra un determinado detalle (necesario para la actualización de la interfaz con la BackStack.

    @Override
    public void onBackStackChanged() {
        //Backstack vacia + atras = salir app
        int numEntradas = gestor.getBackStackEntryCount();
        if (numEntradas == 0) {
            onBackPressed();
        } else {
            // Si hay más de una entrada, se informa sobre la obra que
            // se mostrará si se pulsa el botón Back.
            if (numEntradas > 1) {
                FragmentManager.BackStackEntry entrada = gestor.getBackStackEntryAt(gestor.getBackStackEntryCount() - 2);
                Toast toast = Toast.makeText(this,"Pulsa Back para volver a \n\"" + entrada.getName()+ "\"", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.BOTTOM, 0, 0);
                toast.show();
            }
        }
    }


}