package com.deviantart.corpex.pr005;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etUsuario;
    EditText etContraseña;
    Button btnAceptar;
    Button btnCancelar;
    TextView tvUsuario;
    TextView tvContraseña;

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
        tvUsuario = (TextView) findViewById(R.id.tvUsuario);
        tvContraseña = (TextView) findViewById(R.id.tvContraseña);

        etUsuario.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                cambiarColor(tvUsuario, hasFocus);
            }
        });

        etContraseña.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                cambiarColor(tvContraseña, hasFocus);
            }
        });

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
                checkVisibility(etUsuario, tvUsuario);
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
                checkVisibility(etContraseña, tvContraseña);
            }
        });


        tieneDatos();
        checkVisibility(etContraseña, tvContraseña);
        checkVisibility(etUsuario, tvUsuario);
    }

    private void cambiarColor(TextView tv, boolean hasFocus) {
        if(hasFocus){
            tv.setTextColor(Color.GREEN);
        }
        else{
            tv.setTextColor(Color.LTGRAY);
        }
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
               //tvUsuario.setText("");
               //tvContraseña.setText("");
               etUsuario.setText("");
               etContraseña.setText("");
              // etUsuario.setHint("Usuario");
             //  etContraseña.setHint("Contraseña");
               break;
       }

    }

    private void checkVisibility(EditText txt, TextView lbl) {
        if (TextUtils.isEmpty(txt.getText().toString())) {
            lbl.setVisibility(View.INVISIBLE);
        } else {
            lbl.setVisibility(View.VISIBLE);
        }
    }
}
