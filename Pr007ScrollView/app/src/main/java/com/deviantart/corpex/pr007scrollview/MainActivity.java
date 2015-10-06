package com.deviantart.corpex.pr007scrollview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView tvTexto;
    EditText etMensaje;
    ImageView ivEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        tvTexto = (TextView) findViewById(R.id.tvTexto);
        etMensaje = (EditText) findViewById(R.id.etMensaje);
        ivEnviar = (ImageView) findViewById(R.id.ivEnviar);

        ivEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date d = new Date();
                SimpleDateFormat formato = new SimpleDateFormat("hh:mm:ss", Locale.getDefault());

                String mensaje = tvTexto.getText() + "\n[" + formato.format(d) + "] - "+  etMensaje.getText();
                tvTexto.setText(mensaje);
                etMensaje.setText("");
            }
        });
    }
}
