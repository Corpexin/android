package com.corpex.pr32sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by corpex, by the Grace of God on 01/02/2016.
 */
public class Helper extends SQLiteOpenHelper {

    private static final String SQL_CREATE_ALUMNOS =
            "CREATE TABLE " + Constantes.Alumno.TABLA + " (" +
                    Constantes.Alumno._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    Constantes.Alumno.FOTO + " TEXT, "+
                    Constantes.Alumno.NOMBRE + " TEXT, " +
                    Constantes.Alumno.CURSO + " TEXT, " +
                    Constantes.Alumno.TELEFONO+ " TEXT,"+
                    Constantes.Alumno.DIRECCION+ " TEXT"+
                    " )";

    public Helper(Context context) {
        super(context, Constantes.BD_NOMBRE, null, Constantes.BD_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ALUMNOS); //sentencia de creacion de la tabla
        //poner aqui el resto de sentencias de tablas
        ContentValues registro = new ContentValues();
        registro.put(Constantes.Alumno.FOTO, "http://lorempixel.com/400/200/");
        registro.put(Constantes.Alumno.NOMBRE, "Pepe Romero");
        registro.put(Constantes.Alumno.CURSO, "1CFGS");
        registro.put(Constantes.Alumno.TELEFONO, "666666666");
        registro.put(Constantes.Alumno.DIRECCION, "Avda Falsa 123");
        db.insert(Constantes.Alumno.TABLA, null, registro);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Constantes.Alumno.TABLA);
        db.execSQL(SQL_CREATE_ALUMNOS);
    }
}
