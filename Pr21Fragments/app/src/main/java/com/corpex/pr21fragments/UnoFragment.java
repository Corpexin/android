package com.corpex.pr21fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class UnoFragment extends Fragment {
    TextView texto;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_uno, container, false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // Se obtienen e inicializan las vistas.
        initVistas();
        // Se obtiene el texto y se muestra
        texto.setText(this.getArguments().getString(MainActivity.ARG_MENSAJE));
        super.onActivityCreated(savedInstanceState);
    }

    private void initVistas() {
        texto = (TextView) getActivity().findViewById(R.id.tvTexto);
    }

    public static UnoFragment newInstance(String mensaje) {
        UnoFragment unoFrg = new UnoFragment();
        Bundle argumentos = new Bundle();
        argumentos.putString(MainActivity.ARG_MENSAJE, mensaje);
        unoFrg.setArguments(argumentos);
        return unoFrg;
    }
}
