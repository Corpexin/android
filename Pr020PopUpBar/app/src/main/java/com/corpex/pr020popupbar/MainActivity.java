package com.corpex.pr020popupbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity  extends AppCompatActivity  {
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
        lvAlumnos.setAdapter(new AlumnosAdapter(this, getDatos()));
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
