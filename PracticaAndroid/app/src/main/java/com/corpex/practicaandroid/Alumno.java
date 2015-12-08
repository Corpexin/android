package com.corpex.practicaandroid;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import java.io.IOException;
import java.net.URL;

/**
 * Created by Corpex, by the Grace of God on 19/11/2015.
 */
public class Alumno implements Parcelable{
    private String nombre;
    private String edad;
    private String ciudad;
    private String calle;
    private String telefono;
    private int idPerfil;
    private Bitmap imagen;

    public Alumno(String nombre, String edad, String ciudad, String calle, String telefono, int idPerfil){
        this.nombre = nombre;
        this.edad = edad;
        this.ciudad = ciudad;
        this.calle = calle;
        this.telefono = telefono;
        this.idPerfil = idPerfil;
    }

    private void cambiarImagen() {
       if(imagen==null){ //solo carga las imagenes si no las ha cargado antes
           Thread thread = new Thread(new Runnable(){ //como el main no puede hacer labores de conexiones de red creo un nuevo thread
               @Override
               public void run() {
                   try {
                       URL url;
                       //El thread intenta coger la
                       try {
                           url = new URL("http://lorempixel.com/80/80/people/"+idPerfil+"/");
                           imagen = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                       } catch (IOException e) {
                           e.printStackTrace();
                       }
                   } catch (Exception e) {
                       e.printStackTrace();
                   }
               }
           });

           thread.start();
           //Me espero a que carguen las imagenes. Esto puede ralentizar el programa cuando se va
           //haciendo scroll una primera vez, pero si no lo hago, las imagenes suelen aparecer por
           //defecto sin cargar
           try {
               thread.join();
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }

    }
    public Bitmap getImagen(){
        cambiarImagen();
        return imagen;}
    public String getEdad() {return edad;}
    public String getNombre() {return nombre;}
    public String getCiudad(){return ciudad;}
    public String getCalle(){return calle;}
    public String getTelefono() {
        return telefono;
    }
    public int getIdPerfil(){return idPerfil;}


    // Desde aquí para que sea Parcelable.

    // Constructor.
    protected Alumno(Parcel in) {
        readFromParcel(in);
    }

    // Implementación por defecto.
    public int describeContents() {
        return 0;
    }

    // Escribir las propiedades del objeto en un Parcel de destino.
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(edad);
        dest.writeString(ciudad);
        dest.writeString(calle);
        dest.writeString(telefono);
        dest.writeInt(idPerfil);
    }

    // Leer desde un Parcel las propiedades del objeto.
    public void readFromParcel(Parcel in) {
        nombre = in.readString();
        edad = in.readString();
        ciudad = in.readString();
        calle = in.readString();
        telefono = in.readString();
        idPerfil = in.readInt();
    }

    // Creador del objeto Parcelable.
    public static final Parcelable.Creator<Alumno> CREATOR = new Parcelable.Creator<Alumno>() {
        // Crea un objeto Alumno a partir de un Parcel.
        public Alumno createFromParcel(Parcel in) {
            return new Alumno(in);
        }
        // Crea un array de alumnos del tamaño pasado como parámetro.
        public Alumno[] newArray(int size) {
            return new Alumno[size];
        }
    };

}
