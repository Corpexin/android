package com.corpex.pr011ciclovida;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String STATE_CONTADOR = "estado";
    int mContador =0;
    TextView tvTexto;
    Button btnBoton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState != null){
           mContador = savedInstanceState.getInt(STATE_CONTADOR);
        }
        initViews();

    }

    private void initViews() {
        tvTexto = (TextView) findViewById(R.id.tvTexto);
        btnBoton = (Button) findViewById(R.id.btnBoton);
        tvTexto.setText(String.valueOf(mContador));

        btnBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContador++;
                tvTexto.setText(String.valueOf(mContador));

            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_CONTADOR, mContador);
    }
}
