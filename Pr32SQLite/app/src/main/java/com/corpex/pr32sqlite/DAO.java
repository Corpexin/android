package com.corpex.pr32sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by corpex, by the Grace of God on 01/02/2016.
 */
public class DAO {
    private final Helper mHelper;

    public DAO(Context contexto) {
        mHelper = new Helper(contexto); //pasarle las constantes de instituto
    }

    public SQLiteDatabase openWritableDatabase() {
        return mHelper.getWritableDatabase();
    }

    public void closeDatabase() {
        mHelper.close();
    }


    //Crear
    public long createAlumno(Alumno alumno) {
        SQLiteDatabase bd = mHelper.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(Constantes.Alumno.FOTO, alumno.getFoto());
        valores.put(Constantes.Alumno.NOMBRE, alumno.getNombre());
        valores.put(Constantes.Alumno.CURSO, alumno.getCurso());
        valores.put(Constantes.Alumno.TELEFONO, alumno.getTelefono());
        valores.put(Constantes.Alumno.DIRECCION, alumno.getDireccion());
        valores.put(Constantes.Alumno.EDAD, alumno.getEdad());

        long resultado = bd.insert(Constantes.Alumno.TABLA, null, valores);
        mHelper.close();

        return resultado;
    }

    //Borrar
    public boolean deleteAlumno(long id) {
        SQLiteDatabase bd = mHelper.getWritableDatabase();

        long resultado = bd.delete(Constantes.Alumno.TABLA, Constantes.Alumno._ID + " = " + id, null);

        mHelper.close();
        return resultado > 0;
    }

    //Actualizar
    public boolean updateAlumno(Alumno alumno) {
        SQLiteDatabase bd = mHelper.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(Constantes.Alumno.FOTO, alumno.getFoto());
        valores.put(Constantes.Alumno.NOMBRE, alumno.getNombre());
        valores.put(Constantes.Alumno.CURSO, alumno.getCurso());
        valores.put(Constantes.Alumno.TELEFONO, alumno.getTelefono());
        valores.put(Constantes.Alumno.DIRECCION, alumno.getDireccion());
        valores.put(Constantes.Alumno.EDAD, alumno.getEdad());

        long resultado = bd.update(Constantes.Alumno.TABLA, valores, Constantes.Alumno._ID + " = " + alumno.getId(), null);
        mHelper.close();
        return resultado > 0;
    }

    //Obtener
    public List<Alumno> getAllAlumnos() {
        SQLiteDatabase bd = mHelper.getWritableDatabase();
        List<Alumno> lista = new ArrayList<>();

        Cursor cursor = this.queryAllAlumnos(bd);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Alumno alumno = cursorToAlumno(cursor);
            lista.add(alumno);
            cursor.moveToNext();
        }

        cursor.close();
        mHelper.close();

        return lista;
    }

    public Cursor queryAllAlumnos(SQLiteDatabase bd) {
        return  bd.query(Constantes.Alumno.TABLA, Constantes.Alumno.TODOS, null,
                null, null, null, Constantes.Alumno.NOMBRE);
    }

    public Alumno cursorToAlumno(Cursor cursorAlumno) {
        Alumno alumno = new Alumno();
        alumno.setId(cursorAlumno.getInt(cursorAlumno.getColumnIndexOrThrow(Constantes.Alumno._ID)));
        alumno.setFoto(cursorAlumno.getString(cursorAlumno.getColumnIndexOrThrow(Constantes.Alumno.FOTO)));
        alumno.setNombre(cursorAlumno.getString(cursorAlumno.getColumnIndexOrThrow(Constantes.Alumno.NOMBRE)));
        alumno.setCurso(cursorAlumno.getString(cursorAlumno.getColumnIndexOrThrow(Constantes.Alumno.CURSO)));
        alumno.setTelefono(cursorAlumno.getString(cursorAlumno.getColumnIndexOrThrow(Constantes.Alumno.TELEFONO)));
        alumno.setDireccion(cursorAlumno.getString(cursorAlumno.getColumnIndexOrThrow(Constantes.Alumno.DIRECCION)));
        alumno.setEdad(cursorAlumno.getInt(cursorAlumno.getColumnIndexOrThrow(Constantes.Alumno.EDAD)));
        return alumno;
    }
}
