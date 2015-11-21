package com.corpex.pr21fragments;


import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UnoFragment extends Fragment{
    private static final String ARG_MENSAJE = "Mensaje";
    private Callback listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_uno, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Bundle argumentos =  getArguments();
        String mensaje = argumentos.getString(ARG_MENSAJE);
        TextView tvTexto = (TextView) getView().findViewById(R.id.tvTexto);
        // Se obtiene el texto
        tvTexto.setText(mensaje);
        final EditText etIntrod = (EditText) getView().findViewById(R.id.etIntroduccion);
        Button btnFrag = (Button) getView().findViewById(R.id.btnFrg);
        btnFrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.pulsado(etIntrod.getText().toString());
            }
        });
        setHasOptionsMenu(true);
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            menu.removeItem(R.menu.fragments);
        inflater.inflate(R.menu.fragments, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            listener = (Callback) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException("La actividad debe implementar la interfaz Callback");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener=null;
    }

    public static UnoFragment newInstance(String mensaje) {
        UnoFragment unoFrg = new UnoFragment();
        Bundle argumentos = new Bundle();
        argumentos.putString(ARG_MENSAJE, mensaje);
        unoFrg.setArguments(argumentos);
        return unoFrg;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.mnuLlamar) {
            Toast.makeText(getActivity(), "Llammando ....",
                    Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }


    public interface Callback {
        public void pulsado(String mensaje);
    }
}
