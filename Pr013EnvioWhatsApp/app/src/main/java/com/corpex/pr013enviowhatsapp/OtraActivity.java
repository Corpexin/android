package com.corpex.pr013enviowhatsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class OtraActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otra2);
        textView = (TextView) findViewById(R.id.textView);
        Intent intent = getIntent();
        String result =  intent.getStringExtra("Texto");
        textView.setText(result);
    }
}
