package com.corpex.pr014intents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {

    static final int ID_RESULT = 2;
    static final String NOMBRE = "nombre";
    static final String EDAD = "edad";
    TextView tvDNI;
    EditText etNombre;
    EditText etEdad;
    Button btnRecibir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        Intent i = getIntent();
        Bundle extras = i.getExtras();
        String dni = extras.getString(MainActivity.DNI);
        initViews(dni);


    }

    private void initViews(String dni) {
        etNombre = (EditText) findViewById(R.id.etNombre);
        etEdad = (EditText) findViewById(R.id.etEdad);
        tvDNI = (TextView) findViewById(R.id.tvResultDNI);
        btnRecibir = (Button) findViewById(R.id.btnRecibir);
        tvDNI.setText(dni);

        etNombre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(etNombre.getText().toString()) && !TextUtils.isEmpty(etEdad.getText().toString())) {
                    btnRecibir.setEnabled(true);
                }
            }
        });

        etEdad.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(etNombre.getText().toString()) && !TextUtils.isEmpty(etEdad.getText().toString())) {
                    btnRecibir.setEnabled(true);
                }
            }
        });

        btnRecibir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    public void finish(){
        Intent i = new Intent();
        i.putExtra(NOMBRE, etNombre.getText().toString());
        i.putExtra(EDAD, etEdad.getText().toString());
        setResult(ID_RESULT, i);

        super.finish();
    }
}
