package com.deviantart.corpex.pr005;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etUsuario;
    EditText etContraseña;
    Button btnAceptar;
    Button btnCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        etUsuario = (EditText) findViewById(R.id.etUsuario);
        etContraseña = (EditText) findViewById(R.id.etContraseña);
        btnAceptar = (Button) findViewById(R.id.btnAceptar);
        btnAceptar.setOnClickListener(this);
        btnCancelar = (Button) findViewById(R.id.btnCancelar);
        btnCancelar.setOnClickListener(this);

        etUsuario.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                tieneDatos();
            }
        });

        etContraseña.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                tieneDatos();
            }
        });


        tieneDatos();
    }

    private void tieneDatos() {
        if(!TextUtils.isEmpty(etUsuario.getText()) && !TextUtils.isEmpty(etContraseña.getText()))
            btnAceptar.setEnabled(true);
        else
            btnAceptar.setEnabled(false);
    }


    @Override
    public void onClick(View v) {
       switch(v.getId()){
           case R.id.btnAceptar:
               Toast.makeText(MainActivity.this, "Estableciendo conexion con usuario "+etUsuario.getText(), Toast.LENGTH_LONG).show();
               break;
           case R.id.btnCancelar:
               break;
       }

    }
}
