package com.corpex.pr21fragments;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements UnoFragment.Callback{

    Button btnComprobar;
    FragmentManager gestorFragmentos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnComprobar = (Button) findViewById(R.id.btnComprobar);
        gestorFragmentos = getSupportFragmentManager();
        asignarFrag(R.id.flHueco, "hola");
        btnComprobar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(findViewById(R.id.flHueco2) == null){
                   Activity2.start(MainActivity.this, "Cambiado");
                }
                else
                    asignarFrag(R.id.flHueco2, "EHHH");
            }
        });



    }

    private void asignarFrag(int flHueco, String mens) {
        FragmentTransaction transaccion = gestorFragmentos.beginTransaction();
        transaccion.replace(flHueco,UnoFragment.newInstance(mens));
        transaccion.commit();
    }

    @Override
    public void pulsado(String mensaje) {
        if(findViewById(R.id.flHueco2)==null)
            Activity2.start(MainActivity.this,mensaje);
        else
        asignarFrag(R.id.flHueco2,mensaje);
    }
}
