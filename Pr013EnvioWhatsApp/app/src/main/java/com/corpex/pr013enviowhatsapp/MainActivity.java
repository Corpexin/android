package com.corpex.pr013enviowhatsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static final String EXTRA_TEXTO = "Texto";
    EditText editText;
    Button button;
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        editText = (EditText)findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviar();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarOtra();
            }
        });
    }

    private void enviarOtra() {
        Intent intent = new Intent(this, OtraActivity.class);
        intent.putExtra(EXTRA_TEXTO, editText.getText().toString());
        startActivity(intent);
    }

    private void enviar() {
        Intent intent = new Intent();
        intent.setType("text/plain");
        intent.putExtra(android.content.Intent.EXTRA_TEXT, editText.getText().toString());
        startActivity(intent);
    }
}
