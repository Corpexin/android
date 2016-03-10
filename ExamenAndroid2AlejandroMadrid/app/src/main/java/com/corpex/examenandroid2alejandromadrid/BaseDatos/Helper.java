package com.corpex.examenandroid2alejandromadrid.BaseDatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by corpex, by the Grace of God on 04/03/2016.
 */
public class Helper extends SQLiteOpenHelper {


    private static final String SQL_CREATE_VISITA = "CREATE TABLE " + Contract.Producto.TABLA + " (" +
            Contract.Producto._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            Contract.Producto.NOMBRE + " TEXT, " +
            Contract.Producto.NUMUNIDADES + " REAL, " +
            Contract.Producto.UNIDADC + " TEXT" + " );";



    public Helper(Context context, String BDName, SQLiteDatabase.CursorFactory factory, int DBVersion) {
        super(context, BDName, factory, DBVersion);
    }

    // Constructor. Recibe el contexto.
    public Helper(Context ctx) {
        // Se llama al constructor del padre, que es quien realmente crea o
        // actualiza la versión de BD si es necesario.
        super(ctx, Contract.BDD_NAME, null, Contract.BDD_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_VISITA);
        db.execSQL(
                "INSERT INTO " + Contract.Producto.TABLA + "(" +
                        Contract.Producto.NOMBRE + ", " + Contract.Producto.NUMUNIDADES + ", " + Contract.Producto.UNIDADC +
                ") VALUES ('Manzana', 3, 'Kilos') "
        );
        db.execSQL(
                "INSERT INTO " + Contract.Producto.TABLA + "(" +
                        Contract.Producto.NOMBRE + ", " + Contract.Producto.NUMUNIDADES + ", " + Contract.Producto.UNIDADC +
                        ") VALUES ('Peras', 4, 'Kilos') "
        );

        db.execSQL(
                "INSERT INTO " + Contract.Producto.TABLA + "(" +
                        Contract.Producto.NOMBRE + ", " + Contract.Producto.NUMUNIDADES + ", " + Contract.Producto.UNIDADC +
                        ") VALUES ('Agua', 3, 'Litros') "
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Contract.Producto.TABLA);
        db.execSQL(SQL_CREATE_VISITA);
    }
}
