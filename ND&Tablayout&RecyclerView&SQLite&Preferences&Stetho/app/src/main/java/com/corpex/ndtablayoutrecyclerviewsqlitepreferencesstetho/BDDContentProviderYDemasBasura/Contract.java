package com.corpex.ndtablayoutrecyclerviewsqlitepreferencesstetho.BDDContentProviderYDemasBasura;

/**
 * Created by corpex, by the Grace of God on 03/03/2016.
 */
import android.provider.BaseColumns;

public class Contract {
    public static final int BDD_VERSION = 1;
    public static final String BDD_NAME = "Tutorias";

    private Contract() {}


    public static abstract class Visita implements BaseColumns {
        public static final String TABLA = "visita";
        public static final String IDALUMNO = "idAlumno";
        public static final String DIA = "dia";
        public static final String HORAINICIO = "horaInicio";
        public static final String HORAFIN = "horaFin";
        public static final String RESUMEN = "resumen";
        public static final String[] TODOS = new String[]{_ID, IDALUMNO, DIA,
                HORAINICIO, HORAFIN, RESUMEN};
    }
}