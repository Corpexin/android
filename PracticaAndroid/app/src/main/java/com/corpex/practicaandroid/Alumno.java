package com.corpex.practicaandroid;

/**
 * Created by Corpex, by the Grace of God on 19/11/2015.
 */
public class Alumno {
    private String nombre;
    private String edad;
    private String ciudad;
    private String calle;
    private String telefono;
    private int idPerfil;

    public Alumno(String nombre, String edad, String ciudad, String calle, String telefono, int idPerfil){
        this.nombre = nombre;
        this.edad = edad;
        this.ciudad = ciudad;
        this.calle = calle;
        this.telefono = telefono;
        this.idPerfil = idPerfil;
    }

    public String getEdad() {return edad;}
    public String getNombre() {return nombre;}
    public String getCiudad(){return ciudad;}
    public String getCalle(){return calle;}
    public String getTelefono() {
        return telefono;
    }
    public int getIdPerfil(){return idPerfil;}

}
