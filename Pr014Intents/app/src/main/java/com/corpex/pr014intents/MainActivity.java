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

public class MainActivity extends AppCompatActivity {

    static final int ID_ACT = 1;
    static final String DNI = "dni";
    Button btnEnviar;
    EditText etDNI;
    TextView tvResultNombre;
    TextView tvResultEdad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        btnEnviar = (Button) findViewById(R.id.btnEnviar);
        etDNI = (EditText) findViewById(R.id.etDNI);
        tvResultEdad = (TextView) findViewById(R.id.tvResultEdad);
        tvResultNombre = (TextView) findViewById(R.id.tvResultNombre);

        //listener para que se active el boton cuando se termine de editar el texto
        etDNI.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(etDNI.getText().toString())) {
                    btnEnviar.setEnabled(true);
                }
            }
        });

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviar();
            }
        });

    }


    private void enviar() {
        Intent i = new Intent(this, Activity2.class);
        i.putExtra(DNI, etDNI.getText().toString());
        startActivityForResult(i, ID_ACT);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (resultCode == Activity2.ID_RESULT) {
            tvResultNombre.setText(data.getStringExtra(Activity2.NOMBRE));
            tvResultEdad.setText(data.getStringExtra(Activity2.EDAD));
        }



    }
}

