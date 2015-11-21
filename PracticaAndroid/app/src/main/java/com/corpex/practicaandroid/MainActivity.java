package com.corpex.practicaandroid;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener {
    private FragmentManager gestor;
    private FrameLayout detalle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Obtenicon de Vista
        //ACTIVAR CUANDO SE CREE LA SEGUNDA ACTIVIDAD DETALLES detalle = (FrameLayout) this.findViewById(R.id.flHueco);
        //Obtencion del Gestor del Fragmento
        gestor = getSupportFragmentManager();
        //La clase actuara como listener eb BackStack
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