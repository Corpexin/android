
package com.corpex.pr32sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity {

    public static final String EXTRA_ALUMNO = "alumno";
    EditText txtNombre;
    EditText txtCurso;
    EditText txtTelefono;
    EditText txtDireccion;
    EditText txtEdad;
    Button enviar;

    Intent intent;
    Intent intent2;
    Alumno alumno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initViews();

        intent = getIntent();
        if (intent != null ) {
            if (intent.hasExtra(EXTRA_ALUMNO)) {
                alumno = intent.getParcelableExtra(EXTRA_ALUMNO);
                cargarAlumno();
            }
        }
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
    }

    private void cargarAlumno() {
        txtNombre.setText(alumno.getNombre());
        txtCurso.setText(alumno.getCurso());
        txtTelefono.setText(alumno.getTelefono());
        txtDireccion.setText(alumno.getDireccion());
        txtEdad.setText(String.valueOf(alumno.getEdad()));
    }

    private void initViews() {
        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtCurso = (EditText) findViewById(R.id.txtCurso);
        txtTelefono = (EditText) findViewById(R.id.txtTelefono);
        txtDireccion = (EditText) findViewById(R.id.txtdireccion);
        txtEdad = (EditText) findViewById(R.id.txtEdad);
        enviar = (Button) findViewById(R.id.btnEnviar);
    }


    public void finish(){
        if(alumno==null)
            alumno = new Alumno("http://lorempixel.com/400/200/", txtNombre.getText().toString(), txtCurso.getText().toString(), txtTelefono.getText().toString(), txtDireccion.getText().toString(), Integer.parseInt(txtEdad.getText().toString()), false);
        else{
            alumno.setNombre(txtNombre.getText().toString());
            alumno.setCurso(txtCurso.getText().toString());
            alumno.setTelefono(txtTelefono.getText().toString());
            alumno.setDireccion(txtDireccion.getText().toString());
            alumno.setEdad(Integer.parseInt(txtEdad.getText().toString()));
        }
        intent2 = new Intent();
        intent2.putExtra(EXTRA_ALUMNO, alumno);
        setResult(RESULT_OK, intent2);
        super.finish();
    }
}
