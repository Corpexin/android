package com.corpex.practicaandroid;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Corpex, by the Grace of God on 19/11/2015.
 */
public class AlumnoAdapter extends ArrayAdapter<Alumno> {
    private ArrayList<Alumno> datos;

    public AlumnoAdapter(Context context, ArrayList<Alumno> datos) {
        super(context, R.layout.item_fragment_uno, datos);
        this.datos = datos;
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

    }
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_fragment_uno, parent, false);
            // Se crea un nuevo objeto contenedor de las vistas de la vista-fila.
            holder = new ViewHolder(convertView);
            // Se almacena el contenedor en la propiedad Tag de la vista-fila.
            convertView.setTag(holder);
        }
        // Si la vista-fila corresponde a un objeto reciclable.
        else {
            // Se obtienen el objeto contenedor desde la propiedad Tag de la vista-fila.
            holder = (ViewHolder) convertView.getTag();
        }
        // Se "escriben" los datos en dichas vistas. Para obtener el dato
        // concreto se utiliza el parámetro position que actúa como índice del
        // array de datos gestionados por el adaptador.
        onBindViewHolder(holder, position);
        // Se retorna la vista-fila.
        return convertView;
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.nombre.setText(datos.get(position).getNombre());
        holder.edad.setText(datos.get(position).getEdad());
        holder.ciudad.setText(datos.get(position).getCiudad());
       // holder.perfil.setImageResource(datos.get(position).getIdPerfil());//???????????
    }
}
