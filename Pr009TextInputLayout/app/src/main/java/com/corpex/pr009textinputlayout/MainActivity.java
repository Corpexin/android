package com.corpex.pr009textinputlayout;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        setValidacionTelefono();
        setValidacionEmail();

    }

    private void setValidacionTelefono() {
        final EditText etTelefono = (EditText) findViewById(R.id.etTelefono);
        final TextInputLayout tilTelefono = (TextInputLayout) findViewById(R.id.tilTelefono);
        etTelefono.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(etTelefono.getText().toString())) {
                    if (etTelefono.getText().toString().length() < 9 || etTelefono.getText().toString().length() > 9) {
                        tilTelefono.setError("Error. El numero debe tener 9 cifras");
                    } else {
                        tilTelefono.setErrorEnabled(false);
                    }
                } else {
                    tilTelefono.setErrorEnabled(false);
                }
            }
        });
    }

    private void setValidacionEmail() {
        final EditText etEmail = (EditText) findViewById(R.id.etEmail);
        final TextInputLayout tilEmail = (TextInputLayout) findViewById(R.id.tilEmail);

        etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!TextUtils.isEmpty(etEmail.getText().toString())){
                    if(!Patterns.EMAIL_ADDRESS.matcher(etEmail.getText().toString()).matches()){
                        tilEmail.setError("Error. Formato de Email incorrecto");
                    }
                    else{
                        tilEmail.setErrorEnabled(false);
                    }
                }
                else{
                    tilEmail.setErrorEnabled(false);
                }
            }
        });
    }

}
