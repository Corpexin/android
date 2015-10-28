package com.corpex.pr015parcelable;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Corpex, by the Grace of God on 23/10/2015.
 */
public class Alumno implements Parcelable {
    String DNI;
    String nombre;
    String edad;
    String sexo;


    public Alumno(String DNI){
        this.DNI = DNI;
    }


    public String getDNI() {
        return DNI;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    
    protected Alumno(Parcel in) {
        readFromParcel(in);
    }



    public static final Creator<Alumno> CREATOR = new Creator<Alumno>() {
        @Override
        public Alumno createFromParcel(Parcel in) {
            return new Alumno(in);
        }

        @Override
        public Alumno[] newArray(int size) {
            return new Alumno[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(edad);
        dest.writeString(DNI);
        dest.writeString(sexo);
    }

    private void readFromParcel(Parcel in) {
        nombre = in.readString();
        edad = in.readString();
        DNI = in.readString();
        sexo = in.readString();
    }
}
