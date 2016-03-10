package com.corpex.ndtablayoutrecyclerviewsqlitepreferencesstetho.BDDContentProviderYDemasBasura;

/**
 * Created by corpex, by the Grace of God on 03/03/2016.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



/**
 * Created by corpex, by the Grace of God on 28/02/2016.
 */
public class Helper extends SQLiteOpenHelper {


    private static final String SQL_CREATE_VISITA = "CREATE TABLE " + Contract.Visita.TABLA + " (" +
            Contract.Visita._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            Contract.Visita.DIA + " DATE, " +
            Contract.Visita.HORAINICIO + " TIME, " +
            Contract.Visita.HORAFIN + " TIME," +
            Contract.Visita.RESUMEN + " TEXT"+ " );";

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
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Contract.Visita.TABLA);
        db.execSQL(SQL_CREATE_VISITA);
    }
}
