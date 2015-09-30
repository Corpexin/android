package com.deviantart.corpex.pr001;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    private TextView lblSaludo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        lblSaludo = (TextView) findViewById(R.id.lblSaludo);
    }


    public void btnInsultarOnClick(View v){
        lblSaludo.setText("que ase");
    }
}
