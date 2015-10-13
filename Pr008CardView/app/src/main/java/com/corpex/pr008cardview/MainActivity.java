package com.corpex.pr008cardview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {
    EditText etCuenta;
    EditText etProp;
    EditText etPropina;
    EditText etTotal;
    EditText etComensal;
    EditText etPorComens;
    Button limpiar1;
    Button limpiar2;
    Button redondear1;
    Button redondear2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        etCuenta = (EditText) findViewById(R.id.etCuenta);
        etProp = (EditText) findViewById(R.id.etProp);
        etPropina = (EditText) findViewById(R.id.etPropina);
        etTotal = (EditText) findViewById(R.id.etTotal);
        etComensal = (EditText) findViewById(R.id.etComensales);
        etPorComens = (EditText) findViewById(R.id.etPorComens);
        limpiar1 = (Button) findViewById(R.id.btnLimpiar1);
        limpiar2 = (Button) findViewById(R.id.btnLimpiar2);
        redondear1 = (Button) findViewById(R.id.btnRedondear1);
        redondear2= (Button) findViewById(R.id.btnRedondear2);

        limpiar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etCuenta.setText("0.00");
                etProp.setText("2");
                etPropina.setText("0.0");
            }
        });

        limpiar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etComensal.setText("5");
                etPorComens.setText("0.00");
            }
        });

        redondear1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float prop = Float.parseFloat(etProp.getText().toString());
                float cuent = Float.parseFloat(etCuenta.getText().toString());
                float result = (prop * cuent) / 100;
                float total = result + cuent;
                float nuevoTotal = (float) Math.floor(total);
                etTotal.setText(""+nuevoTotal);
            }
        });

        redondear2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float prop = Float.parseFloat(etProp.getText().toString());
                float cuent = Float.parseFloat(etCuenta.getText().toString());
                float result = (prop * cuent) / 100;
                float total = result + cuent;
                etTotal.setText(""+total);
                float pcom = total/Integer.parseInt(etComensal.getText().toString());
                float nuevoPcom = (float) Math.floor(pcom);
                etPorComens.setText(""+nuevoPcom);
            }
        });

        etCuenta.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                 etCuenta.setText("");
            }
        });
        etProp.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                    etProp.setText("");
            }
        });

        etComensal.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                    etComensal.setText("");
            }
        });
        etCuenta.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                etPropina.setText(calcProp());
            }
        });

        etProp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                etPropina.setText(calcProp());
            }
        });

        etComensal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                etPropina.setText(calcProp());
            }
        });


    }

    private String calcProp() {
        float result=0;
        if (!TextUtils.isEmpty(etCuenta.getText().toString()) &&
                !TextUtils.isEmpty(etProp.getText().toString()) &&  !TextUtils.isEmpty(etComensal.getText().toString())) {
            float prop = Float.parseFloat(etProp.getText().toString());
            float cuent = Float.parseFloat(etCuenta.getText().toString());
            result = (prop * cuent) / 100;
            float total = result + cuent;
            etTotal.setText(""+total);
            float pcom = total/Integer.parseInt(etComensal.getText().toString());
            etPorComens.setText(""+pcom);
        }
        return Float.toString(result);
    }


}
