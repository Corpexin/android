package com.corpex.pr016listview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static final String NOMBRE = "nombre";
    private static final int ID_ACT = 1;
    ArrayList<String> lista;
    ListView lvLista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        añadirLista();
        ArrayAdapter<String> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lista);
        lvLista.setAdapter(adaptador);

        lvLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                enviar(position);
            }
        });
    }

    private void enviar(int position) {
        Intent i = new Intent(this, Main2Activity.class);
        i.putExtra(NOMBRE, ""+lvLista.getItemAtPosition(position).toString());
        startActivityForResult(i, ID_ACT);
    }

    private void añadirLista() {
        lvLista = (ListView) findViewById(R.id.lvLista);
        lista = new ArrayList<>();
        lista.add("primero");
        lista.add("segundo");
        lista.add("tercero");
        lista.add("cuarto");
    }


}
