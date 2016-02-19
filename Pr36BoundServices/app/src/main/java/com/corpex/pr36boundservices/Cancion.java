package com.corpex.pr36boundservices;

/**
 * Created by corpex, by the Grace of God on 15/02/2016.
 */
public class Cancion {
    String nombre;
    String duracion;
    String url;

    public Cancion(String nombre, String duracion, String url){
        this.nombre = nombre;
        this.duracion = duracion;
        this.url = url;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDuracion() {
        return duracion;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return nombre + " (" + duracion + ")";
    }
}
