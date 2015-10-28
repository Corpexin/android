package com.corpex.pr015parcelable;

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
    static final String ALUMNO2 = "alumno2";
    TextView tvDNI;
    EditText etNombre;
    EditText etEdad;
    Button btnRecibir;
    TextView tvSexo;
    Alumno mAlumno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        Intent i = getIntent();
        Bundle extras = i.getExtras();
        mAlumno = extras.getParcelable(MainActivity.ALUMNO);
        initViews(mAlumno);
    }

    private void initViews(Alumno mAlumno) {
        etNombre = (EditText) findViewById(R.id.etNombre);
        etEdad = (EditText) findViewById(R.id.etEdad);
        tvDNI = (TextView) findViewById(R.id.tvResultDNI);
        btnRecibir = (Button) findViewById(R.id.btnRecibir);
        tvSexo = (TextView) findViewById(R.id.tvSexoResult);
        tvDNI.setText(mAlumno.getDNI());
        tvSexo.setText(mAlumno.getSexo());
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
        mAlumno.setNombre(etNombre.getText().toString());
        mAlumno.setEdad(etEdad.getText().toString());
        i.putExtra(ALUMNO2, mAlumno);
        setResult(ID_RESULT, i);

        super.finish();
    }
}
