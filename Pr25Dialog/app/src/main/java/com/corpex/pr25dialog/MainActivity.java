package com.corpex.pr25dialog;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MiDialogFragment.MiDialogListener, MiDialogFragment2.MiDialogListener, MiDialogFragmentListaSeleccion.MiDialogListener,  MiDialogFragmentListaSimple.SeleccionSimpleDialogListener {

    @Bind(R.id.button)
    Button button;
    @Bind(R.id.button2)
    Button button2;
    @Bind(R.id.button3)
    Button button3;
    @Bind(R.id.button4)
    Button button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment frgMiDialogo = new MiDialogFragment();
                frgMiDialogo.show(getSupportFragmentManager(), "MiDialogFragment");
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment frgMiDialogo = new MiDialogFragment2();
                frgMiDialogo.show(getSupportFragmentManager(), "MiDialogFragment2");
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment frgMiDialogo = new MiDialogFragmentListaSeleccion();
                frgMiDialogo.show(getSupportFragmentManager(), "MiDialogFragment2");
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment frgMiDialogo = new MiDialogFragmentListaSimple();
                frgMiDialogo.show(getSupportFragmentManager(), "MiDialogFragment2");
            }
        });
    }

    @Override
    public void onPositiveButtonClick(DialogFragment dialog) {
        Toast.makeText(this, "Boton pulsado", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNegativeButtonClick(DialogFragment dialog) {
        Toast.makeText(this, "No", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(DialogFragment dialog, int which) {
        String[] turnos = getResources().getStringArray(R.array.turnos);
        Toast.makeText(this, turnos[which], Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPositiveButtonClick(DialogFragment dialog, int which) {
        String[] turnos = getResources().getStringArray(R.array.turnos);
        Toast.makeText(this, turnos[which], Toast.LENGTH_SHORT).show();
    }
}
