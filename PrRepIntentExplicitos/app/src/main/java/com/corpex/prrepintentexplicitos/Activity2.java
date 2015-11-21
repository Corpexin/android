package com.corpex.prrepintentexplicitos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Activity2 extends AppCompatActivity {
    static final String NOMBRE = "nombre";
    static final String EDAD = "edad";
    EditText nombre;
    EditText edad;
    Button btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        initViews();
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void finish() {
        Intent i = new Intent();
        i.putExtra(NOMBRE, nombre.getText().toString());
        i.putExtra(EDAD, edad.getText().toString());
        setResult(RESULT_OK, i);
        super.finish();
    }

    private void initViews() {
        nombre = (EditText) findViewById(R.id.etNombre);
        edad = (EditText) findViewById(R.id.etEdad);
        btnEnviar = (Button) findViewById(R.id.btnEnviar);
    }


}
