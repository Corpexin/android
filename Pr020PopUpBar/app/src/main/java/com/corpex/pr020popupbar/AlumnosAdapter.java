package com.corpex.pr020popupbar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Corpex, by the Grace of God on 06/11/2015.
 */
public class AlumnosAdapter extends ArrayAdapter<Alumno> {
    private final List<Alumno> mAlumnos;
    private final LayoutInflater mInflador;

    public AlumnosAdapter(Context contexto, List<Alumno> alumnos) {
        super(contexto, R.layout.layout_item_main, alumnos);
        mAlumnos = alumnos;
        mInflador = LayoutInflater.from(contexto);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        // Si no se puede reciclar.
        if (convertView == null) {
            // Se obtiene la vista-fila inflando el layout.
            convertView = mInflador.inflate(R.layout.layout_item_main, parent, false);
            // Se crea el contenedor de vistas para la vista-fila.
            holder = new ViewHolder(convertView);
            // Se almacena el contenedor en la vista.
            convertView.setTag(holder);
        }
        // Si se puede reciclar.
        else {
            // Se obtiene el contenedor de vistas desde la vista reciclada.
            holder = (ViewHolder) convertView.getTag();
        }
        // Se escriben los datos en las vistas del contenedor de vistas.
        onBindViewHolder(holder, position);
        // Se retorna la vista que representa el elemento.
        return convertView;
    }

    // Cuando se deben escribir los datos en la vista del elemento.
    private void onBindViewHolder(ViewHolder holder, int position) {
        Alumno alumno = mAlumnos.get(position);
        holder.tvNombre.setText(alumno.getNombre());
        holder.tvEdad.setText(alumno.getEdad());
        // Se crea un nuevo objeto listener para cuando se pulse en la
        // imagen, a cuyo construtor se le pasa el mAlumno del que se trata.
        holder.imgPopupMenu.setOnClickListener(new imgPopupMenuOnClickListener(
                mAlumnos.get(position)));

    }

    // Contenedor de vistas para la vista-fila.
    static class ViewHolder {

        // El contenedor de vistas para un elemento de la lista debe contener...
        private final TextView tvNombre;
        private final TextView tvEdad;
        private final ImageView imgPopupMenu;

        // El constructor recibe la vista-fila.
        public ViewHolder(View itemView) {
            // Se obtienen las vistas de la vista-fila.
            tvNombre = (TextView) itemView
                    .findViewById(R.id.tvNombre);
            tvEdad = (TextView) itemView
                    .findViewById(R.id.tvEdad);
            imgPopupMenu = (ImageView) itemView
                    .findViewById(R.id.imagPopUpMenu);
        }

    }

    // Clase listener para pulsación sobre el icono del PopupMenu.
    private class imgPopupMenuOnClickListener implements View.OnClickListener {

        private final Alumno mAlumno;

        // Constructor. Recibe el alumno asociado.
        public imgPopupMenuOnClickListener(Alumno alumno) {
            mAlumno = alumno;
        }

        // Cuando se hace click sobre el icono.
        @Override
        public void onClick(View v) {
            // Se crea el menú.
            PopupMenu popup = new PopupMenu(getContext(), v);
            // Se infla la especificación de menú.
            MenuInflater inflador = popup.getMenuInflater();
            inflador.inflate(R.menu.menu_item_popup, popup.getMenu());
            // Se crea el listener para cuando se pulse un ítem del menú, a cuyo
            // constructor se le pasa el mAlumno asociado.
            popup.setOnMenuItemClickListener(new PopupMenuOnMenuItemClickListener(
                    mAlumno));
            // Se muestra el menú.
            popup.show();
        }
    }

    private class PopupMenuOnMenuItemClickListener implements
            PopupMenu.OnMenuItemClickListener {

        // Alumno asociado.
        final Alumno alumno;

        // Constructor. Recibe el mAlumno asociado.
        public PopupMenuOnMenuItemClickListener(Alumno alumno) {
            this.alumno = alumno;
        }

        // Cuando se selecciona un ítem del PopupMenu.
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.mnuLlamar:
                    Toast.makeText(getContext(), getContext().getString(R.string.llamar) + " " +
                            alumno.getNombre(), Toast.LENGTH_LONG).show();
                    break;
                case R.id.mnuEnviarMensaje:
                    Toast.makeText(getContext(), getContext().getString(R.string.enviar_mensaje) +
                            " " + alumno.getNombre(), Toast.LENGTH_LONG).show();
                    break;
            }
            return true;
        }

    }

}