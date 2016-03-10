package com.corpex.ndtablayoutrecyclerviewsqlitepreferencesstetho;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class Tab1Fragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final long MILISEGUNDOS_ESPERA = 2000;
    private RecyclerView lstVisitas;
    private VisitasAdapter mAdaptador;
    SwipeRefreshLayout swlPanel;
    private OnFragmentInteractionListener mListener;
    private ArrayList<Visita> visitas;

    public Tab1Fragment() {
        // Required empty public constructor
    }


    public static Tab1Fragment newInstance() {
        Tab1Fragment fragment = new Tab1Fragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab1, container, false);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getView() != null) {
            initVistas(getView());
        }
    }

    private void initVistas(View view) {
        lstVisitas = (RecyclerView) view.findViewById(R.id.lstAlumnos);
        lstVisitas.setHasFixedSize(true);
        mAdaptador = new VisitasAdapter(getVisitas());
        mAdaptador.setOnItemClickListener(new VisitasAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Visita visita, int position) {

            }
        });
        lstVisitas.setAdapter(mAdaptador);
        lstVisitas.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        lstVisitas.setItemAnimator(new DefaultItemAnimator());

        //Configuracion del swiperefresh
        swlPanel = (SwipeRefreshLayout) getView().findViewById(R.id.swlPanel);
        // El fragmento actuará como listener del gesto de swipe.
        swlPanel.setOnRefreshListener(this);
        // Se establecen los colores que debe usar la animación.
        swlPanel.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public ArrayList<Visita> getVisitas() {

        //////////////////////AQUI IMPLEMENTAMOS SQL
        return new ArrayList<>();
    }

    @Override
    public void onRefresh() {
        refrescar();
    }

    private void refrescar() {
        // Se activa la animación.
        swlPanel.setRefreshing(true);
        // Se simula que la tarea de carga tarda unos segundos.
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Se añaden los datos al adaptador.
                //mAdaptador.addItem(mFormateador.format(new Date()));
                // Se cancela la animación del panel.
                swlPanel.setRefreshing(false);
            }
        }, MILISEGUNDOS_ESPERA);
    }


    public interface OnFragmentInteractionListener {
    }
}
