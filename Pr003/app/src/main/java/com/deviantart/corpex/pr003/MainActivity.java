package com.deviantart.corpex.pr003;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CheckBox chkEducado;
    EditText etSaludar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        chkEducado.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String mens;
                if(chkEducado.isChecked()){
                    mens = "Modo Educado Activado";
                }
                else{
                    mens = "Modo Educado Desactivado";
                }
                Toast.makeText(MainActivity.this, mens, Toast.LENGTH_LONG).show();
            }
        });

    }

    private void initViews() {
        chkEducado = (CheckBox) findViewById(R.id.chkEducado);
        etSaludar = (EditText) findViewById(R.id.etNombre);
    }


    public void metBotonSaludar(View view) {
        String mensaje;

        if(!chkEducado.isChecked()) {
            mensaje = "Buenos dias " + etSaludar.getText();
        }else{
            mensaje = "Buenos dias tenga usted " + etSaludar.getText();
        }

        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
    }
}
