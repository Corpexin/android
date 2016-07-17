package com.corpex.practicafct.DataBase;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.corpex.practicafct.POJO.Alumno;
import com.corpex.practicafct.POJO.Visita;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by corpex, by the Grace of God on 28/02/2016.
 */
public class DAO {
    private static DAO mInstance;
    private static Helper mHelper;


    private DAO(Context context){
        mHelper = new Helper(context, Contract.BDD_NAME, null, Contract.BDD_VERSION);
    }

    public static synchronized DAO getInstance(Context context){
        if( mInstance == null)
            mInstance = new DAO(context);

        return mInstance;
    }
    //Abre la base de datos con opción a escritura.
    public SQLiteDatabase openWritableDB(){
        return mHelper.getWritableDatabase();
    }
    //Cierra la base de datos.
    public void closeDB(){
        mHelper.close();
    }



    //      ALUMNO


    public long createAlumno(Alumno alumno){
        long idAlumnoInsertado;

        //Se abre la base de datos
        SQLiteDatabase db = mHelper.getWritableDatabase();
        //Se crea la lista de pares clave-valor para realizar la inserción.
        ContentValues valores = new ContentValues();
        valores.put(Contract.Alumno.NOMBRE, alumno.getNombre());
        valores.put(Contract.Alumno.TELEFONO, alumno.getTelefono());
        valores.put(Contract.Alumno.EMAIL, alumno.getEmail());
        valores.put(Contract.Alumno.EMPRESA, alumno.getEmpresa());
        valores.put(Contract.Alumno.TUTOR, alumno.getTutor());
        valores.put(Contract.Alumno.HORARIO, alumno.getHorario());
        valores.put(Contract.Alumno.DIRECCION, alumno.getDireccion());
        valores.put(Contract.Alumno.FOTO, alumno.getFoto());
        //Se realiza la Insert y se cierra la base de datos.
        idAlumnoInsertado = db.insert(Contract.Alumno.TABLA, null, valores);
        db.close();
        return idAlumnoInsertado;
    }

    public boolean deleteAlumno(long id){
        SQLiteDatabase db = mHelper.getWritableDatabase();
        long resultado = db.delete(Contract.Alumno.TABLA, Contract.Alumno._ID + " = " + id, null);
        db.close();
        return resultado > 0;
    }

    public Cursor queryAllAlumnos(SQLiteDatabase bd){
        //Devuelve todos los alumnos ordenados por el nombre.
        return bd.query(Contract.Alumno.TABLA, Contract.Alumno.TODOS, null, null, null, null, Contract.Alumno.NOMBRE);
    }
    public List<Alumno> getAllAlumnos(){
        SQLiteDatabase db = mHelper.getWritableDatabase();
        List<Alumno> lista = new ArrayList<>();
        //Se obtiene el cursor con todos los alumnos.
        Cursor cursor = queryAllAlumnos(db);
        cursor.moveToFirst();
        //Se traspasa todos los elementos del cursor hacia una lista.
        while (!cursor.isAfterLast()){
            lista.add(cursorToAlumno(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return lista;

    }
    //Crea un objeto alumno a partir del registro ACTUAL del cursor pasado por parámetro.
    public Alumno cursorToAlumno(Cursor cursorAlumno){
        Alumno alumno = new Alumno();
        alumno.setIdAlumno(cursorAlumno.getInt(cursorAlumno.getColumnIndexOrThrow(Contract.Alumno._ID)));
        alumno.setNombre(cursorAlumno.getString(cursorAlumno.getColumnIndexOrThrow(Contract.Alumno.NOMBRE)));
        alumno.setTelefono(cursorAlumno.getString(cursorAlumno.getColumnIndexOrThrow(Contract.Alumno.TELEFONO)));
        alumno.setEmail(cursorAlumno.getString(cursorAlumno.getColumnIndexOrThrow(Contract.Alumno.EMAIL)));
        alumno.setEmpresa(cursorAlumno.getString(cursorAlumno.getColumnIndexOrThrow(Contract.Alumno.EMPRESA)));
        alumno.setTutor(cursorAlumno.getString(cursorAlumno.getColumnIndexOrThrow(Contract.Alumno.TUTOR)));
        alumno.setHorario(cursorAlumno.getString(cursorAlumno.getColumnIndexOrThrow(Contract.Alumno.HORARIO)));
        alumno.setDireccion(cursorAlumno.getString(cursorAlumno.getColumnIndexOrThrow(Contract.Alumno.DIRECCION)));
        alumno.setFoto(cursorAlumno.getString(cursorAlumno.getColumnIndexOrThrow(Contract.Alumno.FOTO)));
        //Se retorna el alumno ya configurado.
        return alumno;
    }
    public Alumno getAlumno(long id){
        SQLiteDatabase bd = mHelper.getWritableDatabase();
        Cursor cursor = bd.query(Contract.Alumno.TABLA, Contract.Alumno.TODOS, Contract.Alumno._ID + "=" + id, null, null, null, null);
        cursor.moveToFirst();
        return cursorToAlumno(cursor);
    }

    //    VISITAS


    public long createVisita(Visita visita){
        long idVisitaInsertado;

        //Se abre la base de datos
        SQLiteDatabase db = mHelper.getWritableDatabase();
        //Se crea la lista de pares clave-valor para realizar la inserción.
        ContentValues valores = new ContentValues();
        valores.put(Contract.Visita.IDALUMNO, visita.getIdAlumno());
        valores.put(Contract.Visita.DIA, visita.getDia());
        valores.put(Contract.Visita.HORAINICIO, visita.getHoraInicio());
        valores.put(Contract.Visita.HORAFIN, visita.getHoraFin());
        valores.put(Contract.Visita.RESUMEN, visita.getResumen());

        //Se realiza la Insert y se cierra la base de datos.
        idVisitaInsertado = db.insert(Contract.Visita.TABLA, null, valores);
        db.close();
        return idVisitaInsertado;
    }
    public Cursor queryAlumnoVisitas(SQLiteDatabase bd, int idAlumno){
        return bd.query(Contract.Visita.TABLA, Contract.Visita.TODOS, Contract.Visita.IDALUMNO+ "=" + idAlumno, null, null, null, Contract.Visita.DIA);
    }
    public Cursor queryAllProxVisitas(SQLiteDatabase bd){
        String condicion = new Date().getTime() + "<" +Contract.Visita.DIA;

        //Devuelve las visitas posteriores al momento de ejecución de esta sentencia.
        return bd.query(Contract.Visita.TABLA, Contract.Visita.TODOS, null, null, null, null, Contract.Visita.DIA);
    }
    public List<Visita> getAllProxVisitas(){
        SQLiteDatabase db = mHelper.getWritableDatabase();
        List<Visita> lista = new ArrayList<>();
        //Se obtiene el cursor con todos los alumnos.
        Cursor cursor = queryAllProxVisitas(db);
        cursor.moveToFirst();
        //Se traspasa todos los elementos del cursor a una lista.
        while (!cursor.isAfterLast()){
            lista.add(cursorToVisita(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return lista;
    }
    //Devuelve una lista con las visitas, del alumno propiertario de idAlumno.
    public List<Visita> getAlumnoVisitas(int idAlumno){
        SQLiteDatabase db = mHelper.getWritableDatabase();
        List<Visita> lista = new ArrayList<>();
        //Se obtiene el cursor con todos los alumnos.
        Cursor cursor = queryAlumnoVisitas(db, idAlumno);
        cursor.moveToFirst();
        //Se traspasa todos los elementos del cursor hacia una lista.
        while (!cursor.isAfterLast()){
            lista.add(cursorToVisita(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return lista;

    }
    //Crea un objeto visita a partir del registro ACTUAL del cursor pasado por parámetro.
    public Visita cursorToVisita(Cursor cursorVisita){
        Visita visita = new Visita();
        visita.setIdVisita(cursorVisita.getInt(cursorVisita.getColumnIndexOrThrow(Contract.Visita._ID)));
        visita.setIdAlumno(cursorVisita.getInt(cursorVisita.getColumnIndexOrThrow(Contract.Visita.IDALUMNO)));
        visita.setDia(cursorVisita.getString(cursorVisita.getColumnIndexOrThrow(Contract.Visita.DIA)));
        visita.setHoraInicio(cursorVisita.getString(cursorVisita.getColumnIndexOrThrow(Contract.Visita.HORAINICIO)));
        visita.setHoraFin(cursorVisita.getString(cursorVisita.getColumnIndexOrThrow(Contract.Visita.HORAFIN)));
        visita.setResumen(cursorVisita.getString(cursorVisita.getColumnIndexOrThrow(Contract.Visita.RESUMEN)));
        //Se retorna el alumno ya configurado.
        return visita;
    }
}