package com.corpex.practicafct;

/**
 * Created by corpex, by the Grace of God on 27/02/2016.
 */
public class Visita {
    private String dia;
    private String resumen;

    public Visita(String dia, String resumen){
        this.dia = dia;
        this.resumen = resumen;
    }
    public String getDia() {
        return dia;
    }

    public String getResumen() {
        return resumen;
    }
}
