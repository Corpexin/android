package com.corpex.practicaandroid;

import android.content.Context;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;


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
        holder.bind(datos.get(position));
    }


    private static class ViewHolder {
        private final TextView nombre;
        private final TextView edad;
        private final TextView ciudad;
        private final ImageView imagen;

        public ViewHolder(View itemView) {
            nombre = (TextView) itemView.findViewById(R.id.lblNombre);
            edad = (TextView) itemView.findViewById(R.id.lblEdad);
            ciudad = (TextView) itemView.findViewById(R.id.lblCiudad);
            imagen = (ImageView) itemView.findViewById(R.id.ivPerfil);
        }

        public void bind( Alumno alumno) {
            nombre.setText(alumno.getNombre());
            edad.setText(alumno.getEdad());
            ciudad.setText(alumno.getCiudad());
            imagen.setImageBitmap(alumno.getImagen());
        }
    }



}
