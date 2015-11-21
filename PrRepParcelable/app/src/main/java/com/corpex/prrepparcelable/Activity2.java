package com.corpex.prrepparcelable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {
    TextView nombre;
    TextView edad;
    Persona pers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        recibirIntent();
        initViews();
        nombre.setText(pers.nombre);
        String edadS = ""+pers.edad;
        edad.setText(edadS);
    }

    private void initViews() {
        nombre = (TextView) findViewById(R.id.tvNombre);
        edad = (TextView) findViewById(R.id.tvEdad);
    }

    private void recibirIntent() {
        Intent i = getIntent();
        Bundle b = i.getExtras();
        pers = b.getParcelable(MainActivity.ALUMNO);
    }
}
