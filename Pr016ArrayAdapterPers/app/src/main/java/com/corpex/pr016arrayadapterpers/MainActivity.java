package com.corpex.pr016arrayadapterpers;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView lvAlumnos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initVistas();
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
