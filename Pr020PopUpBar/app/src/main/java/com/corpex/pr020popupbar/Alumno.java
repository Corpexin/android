package com.corpex.pr020popupbar;

/**
 * Created by Corpex, by the Grace of God on 06/11/2015.
 */
public class Alumno {
    private String nombre;
    private String edad;

    public Alumno(String nombre, String edad, String telefono){
        this.nombre = nombre;
        this.edad = edad;


    }

    public String getEdad() {
        return edad;
    }

    public String getNombre() {
        return nombre;
    }


}
