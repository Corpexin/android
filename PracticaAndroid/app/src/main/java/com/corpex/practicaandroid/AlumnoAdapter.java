package com.corpex.practicaandroid;

import android.content.Context;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Corpex, by the Grace of God on 19/11/2015.
 */
public class AlumnoAdapter extends ArrayAdapter<Alumno> {
    private ArrayList<Alumno> datos;
    private final LayoutInflater inflador;

    public AlumnoAdapter(Context context, ArrayList<Alumno> datos) {
        super(context, R.layout.item_fragment_uno, datos);
        this.datos = datos;
        inflador = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflador.inflate(R.layout.item_fragment_uno, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        onBindViewHolder(holder, position);
        return convertView;
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        //holder.nombre.setText(datos.get(position).getNombre());
        //holder.edad.setText(datos.get(position).getEdad());
        //holder.ciudad.setText(datos.get(position).getCiudad());
        // holder.perfil.setImageResource(datos.get(position).getIdPerfil());//???????????
        holder.bind(datos.get(position));
    }

    private static class ViewHolder {
        private final TextView nombre;
        private final TextView edad;
        private final TextView ciudad;
        private final ImageView perfil;

        public ViewHolder(View itemView) {
            nombre = (TextView) itemView.findViewById(R.id.lblNombre);
            edad = (TextView) itemView.findViewById(R.id.lblEdad);
            ciudad = (TextView) itemView.findViewById(R.id.lblCiudad);
            perfil = (ImageView) itemView.findViewById(R.id.ivPerfil);
        }

        public void bind(Alumno alumno) {
            nombre.setText(alumno.getNombre());
            edad.setText(alumno.getEdad());
            ciudad.setText(alumno.getCiudad());

            // holder.perfil.setImageResource(datos.get(position).getIdPerfil());//???????????


           if(perfil != null){
               Thread thread = new Thread(new Runnable(){
                   @Override
                   public void run() {
                       try {
                           URL url;
                           try {
                               url = new URL("http://lorempixel.com/80/80/people");
                               Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                               perfil.setImageBitmap(bmp);
                           } catch (IOException e) {
                               e.printStackTrace();
                           }
                       } catch (Exception e) {
                           e.printStackTrace();
                       }
                   }
               });

               thread.start();
           }



        }
    }



}
