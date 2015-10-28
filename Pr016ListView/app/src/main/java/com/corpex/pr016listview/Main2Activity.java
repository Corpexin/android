package com.corpex.pr016listview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    TextView tvNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent i = getIntent();
        Bundle extras = i.getExtras();
        String nombre = extras.getParcelable(MainActivity.NOMBRE);
        initViews(nombre);
    }

    private void initViews(String nombre) {
        tvNombre = (TextView) findViewById(R.id.tvNombre);
        tvNombre.setText(nombre);
    }
}
