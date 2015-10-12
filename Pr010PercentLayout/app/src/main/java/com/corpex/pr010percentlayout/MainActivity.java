package com.corpex.pr010percentlayout;

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tvRojo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        tvRojo = (TextView) findViewById(R.id.tvRojo);
        tvRojo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvRojo.setBackgroundColor(Color.MAGENTA);
                mostrarSnackBar();
            }
        });
    }

    private void mostrarSnackBar() {
        Snackbar.make(findViewById(R.id.plRaiz), "Se ha cambiado el color", Snackbar.LENGTH_LONG)
                .setAction(getString(R.string.deshacer), new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        tvRojo.setBackgroundColor(Color.RED);
                    }
                })
                .show();
    }
}
