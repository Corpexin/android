package com.corpex.examenandroid2alejandromadrid.BaseDatos;

import android.provider.BaseColumns;

/**
 * Created by corpex, by the Grace of God on 04/03/2016.
 */
public class Contract {
    public static final int BDD_VERSION = 1;
    public static final String BDD_NAME = "compras";

    private Contract() {}


    public static abstract class Producto implements BaseColumns {
        public static final String TABLA = "productoS";
        public static final String NOMBRE = "nombre";
        public static final String NUMUNIDADES = "numUnidades";
        public static final String UNIDADC = "unidadC";
        public static final String[] TODOS = new String[]{_ID, NOMBRE, NUMUNIDADES,UNIDADC};
    }
}
