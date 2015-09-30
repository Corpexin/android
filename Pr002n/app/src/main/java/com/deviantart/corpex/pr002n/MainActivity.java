package com.deviantart.corpex.pr002n;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView txtNombre;
    private TextView txtApellido;
    private TextView resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        txtNombre = (TextView) findViewById(R.id.txtNombre);
        txtApellido = (TextView) findViewById(R.id.txtApellido);
        Button btnNombreApellidos = (Button) this.findViewById(R.id.btnNombreApellidos);
        Button btnApellidosNombre = (Button) this.findViewById(R.id.btnApellidosNombre);
        btnNombreApellidos.setOnClickListener(this);
        btnApellidosNombre.setOnClickListener(this);
        resultado = (TextView) findViewById(R.id.tvResultado);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnNombreApellidos:
                btnNombreApellidosOnClick();
                break;
            case R.id.btnApellidosNombre:
                btnApellidosNombreOnClick();
                break;
        }
    }

    private void btnApellidosNombreOnClick() {
        String mensaje = txtApellido.getText()+" "+txtNombre.getText();
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
        resultado.setText(mensaje);
        resultado.setVisibility(View.VISIBLE);
    }

    private void btnNombreApellidosOnClick() {
        String mensaje = txtNombre.getText()+" "+txtApellido.getText();
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
        resultado.setText(mensaje);
        resultado.setVisibility(View.VISIBLE);
    }



}
