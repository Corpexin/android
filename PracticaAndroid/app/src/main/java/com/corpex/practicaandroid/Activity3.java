package com.corpex.practicaandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Random;

public class Activity3 extends AppCompatActivity {
    static final String ALUMNO = "alumno";
    static final int ID_RESULT = 2;
    EditText etNombre;
    EditText etEdad;
    EditText etCiudad;
    EditText etCalle;
    EditText etTelefono;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
        //Conectamos la toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initViews();
    }

    private void initViews() {
        etNombre = (EditText) findViewById(R.id.txtNombre);
        etEdad = (EditText) findViewById(R.id.txtEdad);
        etCiudad = (EditText) findViewById(R.id.txtCiudad);
        etCalle = (EditText) findViewById(R.id.txtCalle);
        etTelefono = (EditText) findViewById(R.id.txtTelefono);
        btnAdd = (Button) findViewById(R.id.btnAdd);

        //Controlamos para activar el boton que ninguno de los campos es nulo
        etNombre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(etNombre.getText().toString()) && !TextUtils.isEmpty(etEdad.getText().toString()) &&
                        !TextUtils.isEmpty(etCiudad.getText().toString()) && !TextUtils.isEmpty(etCalle.getText().toString()) &&
                        !TextUtils.isEmpty(etTelefono.getText().toString())) {
                    btnAdd.setEnabled(true);
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
                if (!TextUtils.isEmpty(etNombre.getText().toString()) && !TextUtils.isEmpty(etEdad.getText().toString()) &&
                        !TextUtils.isEmpty(etCiudad.getText().toString()) && !TextUtils.isEmpty(etCalle.getText().toString()) &&
                        !TextUtils.isEmpty(etTelefono.getText().toString())) {
                    btnAdd.setEnabled(true);
                }
            }
        });

        etCalle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(etNombre.getText().toString()) && !TextUtils.isEmpty(etEdad.getText().toString()) &&
                        !TextUtils.isEmpty(etCiudad.getText().toString()) && !TextUtils.isEmpty(etCalle.getText().toString()) &&
                        !TextUtils.isEmpty(etTelefono.getText().toString())) {
                    btnAdd.setEnabled(true);
                }
            }
        });

        etCiudad.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(etNombre.getText().toString()) && !TextUtils.isEmpty(etEdad.getText().toString()) &&
                        !TextUtils.isEmpty(etCiudad.getText().toString()) && !TextUtils.isEmpty(etCalle.getText().toString()) &&
                        !TextUtils.isEmpty(etTelefono.getText().toString())) {
                    btnAdd.setEnabled(true);
                }
            }
        });

        etTelefono.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(etNombre.getText().toString()) && !TextUtils.isEmpty(etEdad.getText().toString()) &&
                        !TextUtils.isEmpty(etCiudad.getText().toString()) && !TextUtils.isEmpty(etCalle.getText().toString()) &&
                        !TextUtils.isEmpty(etTelefono.getText().toString())) {
                    btnAdd.setEnabled(true);
                }
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    //Devuelvo el alumno agregado
    public void finish(){
        Random rnd = new Random();
        Intent i = new Intent();
        if(!etNombre.getText().toString().matches("") && !etEdad.getText().toString().matches("") && !etCiudad.getText().toString().matches("")) {
            Alumno mAlumno = new Alumno(etNombre.getText().toString(), etEdad.getText().toString(), etCiudad.getText().toString(),
                    etCalle.getText().toString(), etTelefono.getText().toString(), rnd.nextInt(10));
            i.putExtra(ALUMNO, mAlumno);
            setResult(ID_RESULT, i);
        }else{
            setResult(RESULT_CANCELED);
        }
        super.finish();
    }

}
