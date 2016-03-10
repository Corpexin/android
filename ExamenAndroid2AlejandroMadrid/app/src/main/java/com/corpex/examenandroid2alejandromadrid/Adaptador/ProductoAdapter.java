package com.corpex.examenandroid2alejandromadrid.Adaptador;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.corpex.examenandroid2alejandromadrid.POJO.Producto;
import com.corpex.examenandroid2alejandromadrid.R;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by corpex, by the Grace of God on 03/03/2016.
 */
public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ViewHolder> {
    private View emptyView;
    private final ArrayList<Producto> mDatos;
    private OnItemClickListener onItemClickListener;

    // Constructor.
    public ProductoAdapter(ArrayList<Producto> datos) {
        mDatos = datos;
    }

    // Cuando se debe crear una nueva vista para el elemento.
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Se infla la especificación XML para obtener la vista-fila.
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_lista_item, parent, false);
        // Se crea el contenedor de vistas para la fila.
        final ViewHolder viewHolder = new ViewHolder(itemView);

        // Se establecen los listener de la vista correspondiente al ítem
        // y de las subvistas.

        // Cuando se hace click sobre el elemento.
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    // Se informa al listener.
                    onItemClickListener.onItemClick(v,
                            mDatos.get(viewHolder.getAdapterPosition()),
                            viewHolder.getAdapterPosition());
                }
            }
        });
        // Se retorna el contenedor.
        return viewHolder;
    }

    // Cuando se deben escribir los datos en las subvistas de la
    // vista correspondiente al ítem.
    @Override
    public void onBindViewHolder(ProductoAdapter.ViewHolder holder, int position) {
        // Se obtiene el alumno correspondiente.
        Producto visita = mDatos.get(position);
        // Se escriben los mDatos en la vista.
        holder.lblNombre.setText(visita.getNombre());
        holder.lblNumUnidades.setText("" + visita.getNumUnidades());
        holder.lblUnidadC.setText(visita.getUnidadC());
    }

    // Retorna el número de ítems gestionados.
    @Override
    public int getItemCount() {
        return mDatos.size();
    }

    // Elimina un elemento de la lista.
    public void removeItem(int position) {
        mDatos.remove(position);
        notifyItemRemoved(position);
    }

    // Añade un elemento a la lista.
    public void addItem(Producto visita) {
        // Se añade el elemento.
        mDatos.add(visita);

        // Se notifica que se ha insertado un elemento en la última posición.
        notifyItemInserted(mDatos.size() - 1);
        checkIfEmpty();
    }

    // Intercambia dos elementos de la lista.
    public void swapItems(int from, int to) {
        // Se realiza el intercambio.
        Collections.swap(mDatos, from, to);
        // Se notifica el movimiento.
        notifyItemMoved(from, to);
    }

    // Establece el listener a informar cuando se hace click sobre un elemento de la lista.
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    // Establece la empty view para la lista.
    public void setEmptyView(View emptyView) {
        this.emptyView = emptyView;
        // Muestra la empty view si  la lista está vacía.
        checkIfEmpty();
    }

    // Comprueba si la lista está vacía.
    private void checkIfEmpty() {
        if (emptyView != null) {
            // Muestra u oculta la empty view dependiendo de si la lista está vacía o no.
            emptyView.setVisibility(getItemCount() > 0 ? View.GONE : View.VISIBLE);
        }
    }

    // Contenedor de vistas para la vista-fila.
    static class ViewHolder extends RecyclerView.ViewHolder {

        // El contenedor de vistas para un elemento de la lista debe contener...
        private final TextView lblNombre;
        private final TextView lblNumUnidades;
        private final TextView lblUnidadC;


        // El constructor recibe la vista-fila.
        public ViewHolder(View itemView) {
            super(itemView);
            // Se obtienen las vistas de la vista-fila.
            lblNombre = (TextView) itemView.findViewById(R.id.lblNombre);
            lblNumUnidades = (TextView) itemView.findViewById(R.id.lblNumUnidades);
            lblUnidadC = (TextView) itemView.findViewById(R.id.lblUnidadC);
        }

    }

    // Interfaz que debe implementar el listener para cuando se haga click sobre un elemento.
    public interface OnItemClickListener {
        void onItemClick(View view, Producto visita, int position);
    }
}
