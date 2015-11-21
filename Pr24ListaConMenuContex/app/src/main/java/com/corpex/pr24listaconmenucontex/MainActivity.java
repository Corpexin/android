package com.corpex.pr24listaconmenucontex;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


import com.corpex.pr016arrayadapterpers.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView lvAlumnos;
    AlumnoAdapter adaptador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initVistas();
        configLstAlumnos();
    }

    private void configLstAlumnos() {
        lvAlumnos.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        adaptador = new AlumnoAdapter(this, getDatos());
        lvAlumnos.setAdapter(adaptador);
        lvAlumnos.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(android.view.ActionMode mode, int position, long id, boolean checked) {

            }

            @Override
            public boolean onCreateActionMode(android.view.ActionMode mode, Menu menu) {
                mode.getMenuInflater().inflate(R.menu.alumno_menu ,menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(android.view.ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(android.view.ActionMode mode, MenuItem item) {

                switch(item.getItemId()){
                    case R.id.mnuAprobar:
                        String mensaje="";
                        // Se obtienen los elementos seleccionados.
                        ArrayList<Alumno> elementos = getElementosSeleccionados(lvAlumnos, false);
                        // Se añade cada elemento al mensaje
                        for (Alumno elemento : elementos) {
                            mensaje = mensaje + elemento.getNombre() + " ";
                        }
                        // Se muestra el mensaje.
                        mensaje = getString(R.string.has_aprobado)+ mensaje;
                        Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_LONG).show();
                        break;
                    case R.id.mnuEliminar:
                        // Se obtienen los elementos seleccionados (y se quita la selección).
                        ArrayList<Alumno> elems = getElementosSeleccionados(lvAlumnos, true);
                        // Se eliminan del adaptador.
                        for (Alumno elemento : elems) {
                            adaptador.remove(elemento);
                        }
                        // Se notifica al adaptador que ha habido cambios.
                        adaptador.notifyDataSetChanged();
                        Toast.makeText(getApplicationContext(),elems.size() + getString(R.string.alumnos_eliminados), Toast.LENGTH_LONG).show();
                        break;
                }

                return true;
            }

            @Override
            public void onDestroyActionMode(android.view.ActionMode mode) {

            }
        });
    }


    // Retorna un ArrayList con los elementos seleccionados. Recibe la lista y
    // si debe quitarse la selección una vez obtenidos los elementos.
    private ArrayList<Alumno> getElementosSeleccionados(ListView lst,boolean uncheck) {
        // ArrayList resultado.
        ArrayList<Alumno> datos = new ArrayList<>();
        // Se obtienen los elementos seleccionados de la lista.
        SparseBooleanArray selec = lst.getCheckedItemPositions();
        for (int i = 0; i < selec.size(); i++) {
            // Si está seleccionado.
            if (selec.valueAt(i)) {
                int position = selec.keyAt(i);
                // Se quita de la selección (si hay que hacerlo).
                if (uncheck) {
                    lst.setItemChecked(position, false);
                }
                // Se añade al resultado.
                datos.add((Alumno) lst.getItemAtPosition(selec.keyAt(i)));
            }
        }
        // Se retorna el resultado.
        return datos;
    }

    private void initVistas() {
        lvAlumnos = (ListView) findViewById(R.id.lvAlumnos);
        lvAlumnos.setEmptyView(findViewById(R.id.lblEmpty)); //?
        //Adaptador
        lvAlumnos.setAdapter(new AlumnoAdapter(this, getDatos()));
        lvAlumnos.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Alumno al = (Alumno) parent.getItemAtPosition(position);//?
        //Aqui lo hago llamar con un intent.call
        Intent intent = new Intent(Intent.ACTION_CALL,
                Uri.parse(al.getTelefono()));

        //codigo chungo de internet para pedir permiso. Como no me lo da le pongo el startactivity en el else y tira millah
        PackageManager pm = this.getPackageManager();
        int hasPerm = pm.checkPermission(
                Manifest.permission.CALL_PHONE,
                this.getPackageName());
        if (hasPerm != PackageManager.PERMISSION_GRANTED) {
            startActivity(intent);
        }
        else{
            startActivity(intent);
        }
    }

    private ArrayList<Alumno> getDatos() {
        ArrayList<Alumno> alumnos = new ArrayList<>();
        alumnos.add(new Alumno("Pepe", "17", "tel:(+34)123456789"));
        alumnos.add(new Alumno("Maria", "24", "tel:(+34)123456789"));
        alumnos.add(new Alumno("Jose", "35", "tel:(+34)123456789"));
        alumnos.add(new Alumno("Inma", "16", "tel:(+34)123456789"));
        return alumnos;
    }
}
