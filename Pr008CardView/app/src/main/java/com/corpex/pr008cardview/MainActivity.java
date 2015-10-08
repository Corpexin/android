package com.corpex.pr008cardview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {
    EditText etCuenta;
    EditText etProp;
    EditText etPropina;
    EditText etTotal;

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
    }

    private String calcProp() {
        float result=0;
        if (!TextUtils.isEmpty(etCuenta.getText().toString()) &&
                !TextUtils.isEmpty(etProp.getText().toString())) {
            float prop = Float.parseFloat(etProp.getText().toString());
            float cuent = Float.parseFloat(etCuenta.getText().toString());
            result = (prop * cuent) / 100;
            float total = result + cuent;
            etTotal.setText(""+total);
        }
        return Float.toString(result);
    }


}
