package com.corpex.examenandroid2alejandromadrid.POJO;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by corpex, by the Grace of God on 04/03/2016.
 */
public class Producto implements Parcelable{

    private long id;

    private String nombre;
    private float  numUnidades;
    private String unidadC;

    public Producto(String nombre, float numUnidades, String unidadC) {
        this.nombre = nombre;
        this.numUnidades = numUnidades;
        this.unidadC = unidadC;
    }

    public void setId(long id) {
        this.id = id;
    }
    public long getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getNumUnidades() {
        return numUnidades;
    }

    public void setNumUnidades(float numUnidades) {
        this.numUnidades = numUnidades;
    }

    public String getUnidadC() {
        return unidadC;
    }

    public void setUnidadC(String unidadC) {
        this.unidadC = unidadC;
    }


    //PARCELABLE
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeFloat(numUnidades);
        dest.writeString(unidadC);
    }

    protected Producto(Parcel in) {
        nombre = in.readString();
        numUnidades = in.readFloat();
        unidadC = in.readString();
    }

    public static final Creator<Producto> CREATOR = new Creator<Producto>() {
        @Override
        public Producto createFromParcel(Parcel in) {
            return new Producto(in);
        }

        @Override
        public Producto[] newArray(int size) {
            return new Producto[size];
        }
    };
}
