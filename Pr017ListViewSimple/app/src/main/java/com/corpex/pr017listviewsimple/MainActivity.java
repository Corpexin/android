package com.corpex.pr017listviewsimple;

import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String STATE_LISTA = "estado";
    private static final String STRING_LISTA = "lista";
    ListView lvAlumnos;
    EditText etNombre;
    ImageButton ibEnviar;
    ArrayAdapter<String> adaptador;
    Parcelable estadoLista;
    ArrayList<String> alumnos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        lvAlumnos = (ListView) findViewById(R.id.lvAlumnos);
        etNombre = (EditText) findViewById(R.id.etNombre);
        ibEnviar = (ImageButton) findViewById(R.id.ibEnviar);


        //Configuramos el adaptador (usamos uno por defecto) y se lo pasamos a la listview
        alumnos = new ArrayList<>();
        adaptador = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, alumnos);
        lvAlumnos.setAdapter(adaptador);

        //Si se hace click en enviar se añade al adaptador lo que haya en el etNombre
        ibEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etNombre.getText() != null) {
                    adaptador.add(etNombre.getText().toString());
                    etNombre.setText("");
                }
            }
        });

        //Si se hace click en uno de los elementos de la lista lo borra
        lvAlumnos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adaptador.remove((String) parent.getItemAtPosition(position));
            }
        });
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Se salva el estado del ListView.
        estadoLista = lvAlumnos.onSaveInstanceState();
        outState.putParcelable(STATE_LISTA, estadoLista);
        outState.putStringArrayList(STRING_LISTA, alumnos);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Se obtiene el estado anterior de la lista.
        estadoLista = savedInstanceState.getParcelable(STATE_LISTA);
        adaptador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, savedInstanceState.getStringArrayList(STATE_LISTA));
        lvAlumnos.setAdapter(adaptador);

    }

    @Override
    protected void onResume() {
        super.onResume();
        // Se retaura el estado de la lista.
        if (estadoLista != null) {
            lvAlumnos.onRestoreInstanceState(estadoLista);
        }
    }

}
