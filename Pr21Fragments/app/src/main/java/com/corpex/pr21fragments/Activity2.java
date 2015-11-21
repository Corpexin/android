package com.corpex.pr21fragments;


import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Activity2 extends AppCompatActivity implements UnoFragment.Callback{
    private static final String EXTRA_MENSAJE = "E_mensaje";
    FragmentManager gestor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);
        gestor = getSupportFragmentManager();
        Intent intent= getIntent();
        String mensaje=intent.getStringExtra(EXTRA_MENSAJE);
        asignarFragmento(mensaje);
    }

    private void asignarFragmento(String mensaje) {
        FragmentTransaction transaction= gestor.beginTransaction();
        transaction.add(R.id.flHueco2, UnoFragment.newInstance(mensaje));
        transaction.commit();
    }

    public static void start(Context context, String cambiado) {
        Intent intent=new Intent(context,Activity2.class);
        intent.putExtra(EXTRA_MENSAJE,cambiado);
        context.startActivity(intent);
    }

    @Override
    public void pulsado(String mensaje) {

    }
}
