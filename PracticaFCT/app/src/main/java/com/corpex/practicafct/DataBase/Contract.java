package com.corpex.practicafct.DataBase;

/**
 * Created by corpex, by the Grace of God on 28/02/2016.
 */
import android.provider.BaseColumns;

public class Contract {
    public static final int BDD_VERSION = 1;
    public static final String BDD_NAME = "Tutorias";

    private Contract() {}

    public static abstract class Alumno implements BaseColumns {
        public static final String TABLA = "alumno";
        public static final String NOMBRE = "nombre";
        public static final String TELEFONO = "telefono";
        public static final String EMAIL = "email";
        public static final String EMPRESA = "empresa";
        public static final String TUTOR = "tutor";
        public static final String HORARIO = "horario";
        public static final String DIRECCION = "direccion";
        public static final String FOTO = "foto";
        public static final String[] TODOS = new String[]{_ID, NOMBRE, TELEFONO,
                EMAIL, EMPRESA, TUTOR, HORARIO, DIRECCION, FOTO};
    }

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