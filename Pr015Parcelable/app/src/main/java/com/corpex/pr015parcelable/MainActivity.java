package com.corpex.pr015parcelable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    static final int ID_ACT = 1;
    static final String ALUMNO = "alumno";
    private static final String STATE_NOMBRE = "nombre";
    private static final String STATE_EDAD = "edad";
    Button btnEnviar;
    EditText etDNI;
    TextView tvResultNombre;
    TextView tvResultEdad;
    Spinner spSexo;
    Alumno mAlumno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews(savedInstanceState);
    }

    private void initViews(Bundle savedInstanceState) {
        btnEnviar = (Button) findViewById(R.id.btnEnviar);
        etDNI = (EditText) findViewById(R.id.etDNI);
        tvResultEdad = (TextView) findViewById(R.id.tvResultEdad);
        tvResultNombre = (TextView) findViewById(R.id.tvResultNombre);
        spSexo = (Spinner) findViewById(R.id.spSexo);

        if(savedInstanceState != null){
            tvResultNombre.setText(savedInstanceState.getString(STATE_NOMBRE));
            tvResultEdad.setText(savedInstanceState.getString(STATE_EDAD));
        }

        //listener para que se active el boton cuando se termine de editar el texto
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviar();
            }
        });

        spSexo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                btnEnviar.setEnabled(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


    private void enviar() {
        mAlumno = new Alumno(etDNI.getText().toString());
        mAlumno.setSexo(""+spSexo.getSelectedItem());
        Intent i = new Intent(this, Activity2.class);
        i.putExtra(ALUMNO, mAlumno);
        startActivityForResult(i, ID_ACT);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (resultCode == Activity2.ID_RESULT) {
            mAlumno = data.getParcelableExtra(Activity2.ALUMNO2);
            tvResultNombre.setText(mAlumno.getNombre());
            tvResultEdad.setText(mAlumno.getEdad());
        }
    }

    //Guardar variables cuando se destruye la actividad
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_NOMBRE, ""+tvResultNombre.getText());
        outState.putString(STATE_EDAD, ""+tvResultEdad.getText());
    }


}


