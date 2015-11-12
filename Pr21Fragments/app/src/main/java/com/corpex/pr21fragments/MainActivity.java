package com.corpex.pr21fragments;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    public static final String ARG_MENSAJE = "mensaje";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UnoFragment unoFrg = UnoFragment.newInstance("holi");

        FragmentManager gestorFragmentos = getFragmentManager();
        FragmentTransaction transaccion = gestorFragmentos.beginTransaction();

        transaccion.add(R.id.flHueco, unoFrg);
        transaccion.commit();
    }




}
