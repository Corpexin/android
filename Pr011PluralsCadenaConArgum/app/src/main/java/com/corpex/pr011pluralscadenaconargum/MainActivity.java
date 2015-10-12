package com.corpex.pr011pluralscadenaconargum;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etSuspensos;
    Button btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        etSuspensos = (EditText) findViewById(R.id.etSuspensos);
        btnEnviar = (Button) findViewById(R.id.btnEnviar);


        btnEnviar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String mensaje="0";
                if(!TextUtils.isEmpty(etSuspensos.getText().toString())) {
                    mensaje = getResources().getQuantityString(
                            R.plurals.num_de_alumnos, Integer.parseInt(etSuspensos.getText().toString()));
                }
                Toast.makeText(getApplicationContext(), mensaje,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
