package com.corpex.pr29swiperefreshlayout;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by corpex, by the Grace of God on 21/01/2016.
 * Muchos programadores creen que depurar codigo va de arreglar bugs.
 * Tonterias. Depurar va de encontrar bugs y entender porque estaban ahí. Y saber que su
 * existencia no era un accidente. Llego a ti para entregar un mensaje
 * como una burbuja inconscientemente flota hacia la superficie y estalla,
 * revelandote el secreto que en realidad ya conocias...
 */
public class ListaAdapter extends RecyclerView.Adapter<ListaAdapter.ViewHolder>{

    private final ArrayList<String> mDatos;

    public ListaAdapter(ArrayList<String> datos){
        mDatos = datos;
    }

    @Override
    public ListaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ListaAdapter.ViewHolder holder, int position) {
        holder.onBind(mDatos.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatos.size();
    }

    public void addItem(String elemento){
        mDatos.add(elemento);
        notifyItemInserted(mDatos.size()-1);
    }

    public ArrayList<String> getData(){
        return mDatos;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView text1;

        public ViewHolder(View itemView) {
            super(itemView);
            text1 = (TextView) itemView.findViewById(android.R.id.text1);
        }

        public void onBind(String elemento) {
            text1.setText(elemento);
        }
    }
}
