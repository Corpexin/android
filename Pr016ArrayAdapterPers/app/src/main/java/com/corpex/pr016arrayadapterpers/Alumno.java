package com.corpex.pr016arrayadapterpers;

/**
 * Created by Corpex, by the Grace of God on 29/10/2015.
 */
public class Alumno {
    private String nombre;
    private String edad;
    private String telefono;

    public Alumno(String nombre, String edad, String telefono){
        this.nombre = nombre;
        this.edad = edad;
        this.telefono = telefono;


    }

    public String getEdad() {
        return edad;
    }

    public String getNombre() {
        return nombre;
    }
    public String getTelefono() {
        return telefono;
    }


}
