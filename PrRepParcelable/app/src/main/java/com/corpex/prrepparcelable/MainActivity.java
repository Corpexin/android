package com.corpex.prrepparcelable;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int RC_ACT = 1;
    static final String ALUMNO = "alumno";
    EditText etNombre;
    EditText etEdad;
    Button btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        listeners();
    }

    private void listeners() {
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!etNombre.getText().toString().matches("") && !etEdad.getText().toString().matches("")){
                    Persona p = new Persona(etNombre.getText().toString(), Integer.parseInt(etEdad.getText().toString()));
                    Intent i = new Intent(MainActivity.this, Activity2.class);
                    i.putExtra(ALUMNO, p);
                    startActivity(i);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Rellena Nombre y Edad primero",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initViews() {
        etNombre = (EditText) findViewById(R.id.etNombre);
        etEdad = (EditText) findViewById(R.id.etEdad);
        btnEnviar = (Button) findViewById(R.id.btnEnviar);
    }
}
