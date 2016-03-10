package com.corpex.ndtablayoutrecyclerviewsqlitepreferencesstetho;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by corpex, by the Grace of God on 03/03/2016.
 */
public class Visita implements Parcelable{
    private int idVisita;
    private int idAlumno;
    private String dia;
    private String resumen;
    private String horaInicio;
    private String horaFin;

    public Visita(String dia, String resumen, String horaInicio, String horaFin, int idAlumno){
        this.dia = dia;
        this.resumen = resumen;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.idAlumno = idAlumno;
    }

    public Visita() {

    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public int getIdVisita() {
        return idVisita;
    }

    public void setIdVisita(int idVisita) {
        this.idVisita = idVisita;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    //Implementacion para el parcel
    protected Visita(Parcel in) {
        idVisita = in.readInt();
        idAlumno = in.readInt();
        dia = in.readString();
        resumen = in.readString();
        horaInicio = in.readString();
        horaFin = in.readString();
    }

    public static final Creator<Visita> CREATOR = new Creator<Visita>() {
        @Override
        public Visita createFromParcel(Parcel in) {
            return new Visita(in);
        }

        @Override
        public Visita[] newArray(int size) {
            return new Visita[size];
        }
    };



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idVisita);
        dest.writeInt(idAlumno);
        dest.writeString(dia);
        dest.writeString(resumen);
        dest.writeString(horaInicio);
        dest.writeString(horaFin);
    }
}
