package com.corpex.practicafct.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



/**
 * Created by corpex, by the Grace of God on 28/02/2016.
 */
public class Helper extends SQLiteOpenHelper {

    private static final String SQL_CREATE_ALUMNO = "CREATE TABLE " + Contract.Alumno.TABLA + " (" +
            Contract.Alumno._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            Contract.Alumno.NOMBRE + " TEXT, " +
            Contract.Alumno.TELEFONO + " TEXT, " +
            Contract.Alumno.EMAIL + " TEXT," +
            Contract.Alumno.EMPRESA + " TEXT," +
            Contract.Alumno.TUTOR + " TEXT," +
            Contract.Alumno.HORARIO + " TEXT," +
            Contract.Alumno.DIRECCION + " TEXT," +
            Contract.Alumno.FOTO + " TEXT" +
            " );";
    private static final String SQL_CREATE_VISITA = "CREATE TABLE " + Contract.Visita.TABLA + " (" +
            Contract.Visita._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            Contract.Visita.IDALUMNO + " INTEGER," +
            Contract.Visita.DIA + " DATE, " +
            Contract.Visita.HORAINICIO + " TIME, " +
            Contract.Visita.HORAFIN + " TIME," +
            Contract.Visita.RESUMEN + " TEXT," +
            "FOREIGN KEY (" + Contract.Visita.IDALUMNO + ") References " + Contract.Alumno.TABLA + " (" + Contract.Alumno._ID + ")"+
            " );";

    public Helper(Context context, String BDName, SQLiteDatabase.CursorFactory factory, int DBVersion) {
        super(context, BDName, factory, DBVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ALUMNO);
        db.execSQL(SQL_CREATE_VISITA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Contract.Alumno.TABLA);
        db.execSQL(SQL_CREATE_ALUMNO);
        db.execSQL("DROP TABLE IF EXISTS " + Contract.Visita.TABLA);
        db.execSQL(SQL_CREATE_VISITA);
    }
}