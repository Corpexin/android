package com.deviantart.corpex.pr004;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    Button btnMegusta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews(); //Inicializamos las vistas
    }

    private void initViews() {
        radioGroup = (RadioGroup) findViewById(R.id.rdGroup);
        btnMegusta = (Button) findViewById(R.id.btnMeGusta);
        btnMegusta.setOnClickListener(new View.OnClickListener() { //creamos el listener para el boton me gusta
            @Override
            public void onClick(View v) {
                String mensaje = "Me gusta ";
                Button b = (Button) findViewById(radioGroup.getCheckedRadioButtonId());

                if(((String)b.getText()).matches("Primavera")) //condicion que regula que articulo poner (el o la) para cada estacion
                    mensaje = mensaje + "la";
                else
                    mensaje = mensaje + "el";

                mensaje = mensaje +" "+ b.getText();
                Toast.makeText(MainActivity.this, mensaje, Toast.LENGTH_LONG).show(); //mostramos un toast con la estacion
            }
        });
    }


}
