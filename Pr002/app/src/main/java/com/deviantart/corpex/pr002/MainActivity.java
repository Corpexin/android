package com.deviantart.corpex.pr002;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    private TextView txtNombre;
    private TextView txtApellido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        txtNombre = (TextView) findViewById(R.id.txtNombre);
        txtApellido = (TextView) findViewById(R.id.txtApellido);
    }


    public void apellNombre(View view) {
        Toast.makeText(this, "Hola que ase tu", LENGTH);
    //Toast.nameText(this, cadena, Toast.LENGTH..).show
        //Toast myToast = ...
        //miToast.show
        //??txtnombre.setText....??
    }

    public void nomApell(View view) {

    }
}
