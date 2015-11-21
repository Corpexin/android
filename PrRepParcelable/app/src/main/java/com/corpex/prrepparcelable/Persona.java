package com.corpex.prrepparcelable;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Corpex, by the Grace of God on 21/11/2015.
 */
public class Persona implements Parcelable{
    String nombre;
    int edad;

    public Persona(String nombre, int edad){
        this.nombre = nombre;
        this.edad = edad;
    }


    public Persona(Parcel in){
        readFromParcel(in);
    }


    @Override
    public int describeContents() {
        return 0;
    }

    private void readFromParcel(Parcel in) {
        nombre = in.readString();
        edad = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeInt(edad);
    }

    public static final Creator<Persona> CREATOR = new Creator<Persona>() {
        @Override
        public Persona createFromParcel(Parcel in) {
            return new Persona(in);
        }

        @Override
        public Persona[] newArray(int size) {
            return new Persona[size];
        }
    };
}
