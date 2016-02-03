package com.corpex.pr32sqlite;

import android.os.Parcel;
import android.os.Parcelable;

public class Alumno implements Parcelable {
    private Boolean repetidor;
    private Integer edad;
    private String direccion;
    private String telefono;
    private String curso;
    private String nombre;
    private String foto;
    private int id;

    public Alumno() {
    }

    public Alumno( String foto, String nombre, String curso, String telefono, String direccion, int edad, boolean repetidor) {
        this.repetidor = repetidor;
        this.edad = edad;
        this.direccion = direccion;
        this.telefono = telefono;
        this.curso = curso;
        this.nombre = nombre;
        this.foto = foto;
    }

    public Boolean getRepetidor() {
        return repetidor;
    }
    public void setRepetidor(Boolean repetidor) {
        this.repetidor = repetidor;
    }
    public Integer getEdad() {
        return edad;
    }
    public void setEdad(Integer edad) {
        this.edad = edad;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getCurso() {
        return curso;
    }
    public void setCurso(String curso) {
        this.curso = curso;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getFoto() {
        return foto;
    }
    public void setFoto(String foto) {
        this.foto = foto;
    }
    public int getId(){return id;}
    public void setId(int id){this.id = id;}


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(direccion);
        dest.writeString(telefono);
        dest.writeString(curso);
        dest.writeString(nombre);
        dest.writeString(foto);
    }

    protected Alumno(Parcel in) {
        direccion = in.readString();
        telefono = in.readString();
        curso = in.readString();
        nombre = in.readString();
        foto = in.readString();
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
}