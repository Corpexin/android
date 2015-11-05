package com.corpex.pr018listviewseleccion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvLista;
    Button btnComprobar;
    TextView tvContador;
    TextView tvPuntuacion;
    ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    //Menu de opciones dado despues de esto
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflador = getMenuInflater();
        inflador.inflate(R.menu.menu_layout, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Dependiendo del ítem seleccionado.
        switch (item.getItemId()) {
            case R.id.mnuAgregar:
                // agregar();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //se ejcuta cada vez que quiera mostrar el menu. permite modificar los items de menu
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        //MenuItem menuI  = menu.findItem(R.id.aprobar)  ejercicio despues de pr018
        //menuI.setTitle ...
        return super.onPrepareOptionsMenu(menu);
    }

    private void initViews() {
        lvLista = (ListView) findViewById(R.id.lvListaRespuesta);
        btnComprobar = (Button) findViewById(R.id.btnComprobar);
        tvContador = (TextView) findViewById(R.id.tvContador);
        tvPuntuacion = (TextView) findViewById(R.id.tvPuntuacion);

        ArrayList<String> l = new ArrayList<>();
        l.add("Negro");
        l.add("Blanco");
        l.add("Verde");

        mAdapter = new ArrayAdapter<>(this, R.layout.layout_item_main, R.id.tvRespuesta, l);
        lvLista.setAdapter(mAdapter);
        lvLista.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        lvLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                btnComprobar.setEnabled(true);
            }
        });

        btnComprobar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mensaje;
                int posSeleccionado = lvLista.getCheckedItemPosition();
                String respuesta = (String) lvLista.getItemAtPosition(posSeleccionado);
                if(respuesta.matches("Blanco")){
                    mensaje = "Has acertado";
                }
                else{
                    mensaje = "Has fracasado";
                }
                Toast.makeText(getApplicationContext(), mensaje,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
