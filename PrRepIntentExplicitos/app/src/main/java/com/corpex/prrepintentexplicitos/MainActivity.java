package com.corpex.prrepintentexplicitos;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final int RC_DATOS = 1;
    Button btnSolicitar;
    TextView datos;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        btnSolicitar = (Button) findViewById(R.id.btnSolicitar);
        datos = (TextView) findViewById(R.id.datosAlumno);

        btnSolicitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Activity2.class);
                startActivityForResult(i, RC_DATOS);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle b = data.getExtras();
        String nombre = b.getString(Activity2.NOMBRE);
        String edad = b.getString(Activity2.EDAD);
        datos.setText("Nombre: "+nombre+"  Edad: "+edad);
    }
}
