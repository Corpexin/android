package com.corpex.practicafct.POJO;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by corpex, by the Grace of God on 28/02/2016.
 */
public class Alumno implements Parcelable {
    private int idAlumno;
    private String nombre;
    private String telefono;
    private String email;
    private String empresa;
    private String tutor;
    private String horario;
    private String direccion;
    private String foto;

    public Alumno(String nombre, String telefono, String email, String empresa, String tutor, String horario, String direccion, String foto) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.empresa = empresa;
        this.tutor = tutor;
        this.horario = horario;
        this.direccion = direccion;
        this.foto = foto;
    }

    public Alumno() {

    }


    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getTutor() {
        return tutor;
    }

    public void setTutor(String tutor) {
        this.tutor = tutor;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }


    protected Alumno(Parcel in) {
        idAlumno = in.readInt();
        nombre = in.readString();
        telefono = in.readString();
        email = in.readString();
        empresa = in.readString();
        tutor = in.readString();
        horario = in.readString();
        direccion = in.readString();
        foto = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idAlumno);
        dest.writeString(nombre);
        dest.writeString(telefono);
        dest.writeString(email);
        dest.writeString(empresa);
        dest.writeString(tutor);
        dest.writeString(horario);
        dest.writeString(direccion);
        dest.writeString(foto);
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
