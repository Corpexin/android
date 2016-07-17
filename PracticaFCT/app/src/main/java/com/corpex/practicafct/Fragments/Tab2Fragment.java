package com.corpex.practicafct.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.corpex.practicafct.Adapters.VisitasAdapter;
import com.corpex.practicafct.POJO.Visita;
import com.corpex.practicafct.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Tab2Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Tab2Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tab2Fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView lstVisitas;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Tab2Fragment() {
        // Required empty public constructor
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
        final VisitasAdapter mAdaptador = new VisitasAdapter(getVisitas());
        mAdaptador.setOnItemClickListener(new VisitasAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Visita visita, int position) {

            }
        });
        lstVisitas.setAdapter(mAdaptador);
        lstVisitas.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        lstVisitas.setItemAnimator(new DefaultItemAnimator());
    }

    private static ArrayList<Visita> getVisitas() {
        ArrayList<Visita> lista = new ArrayList<>();
        lista.add(new Visita("11/12/2003", "yoksetioxd", "14:00", "15:00", 2));
        lista.add(new Visita("11/12/2003", "yoksetioxd", "14:00", "15:00", 2));
        return lista;
    }

    // TODO: Rename and change types and number of parameters
    public static Tab2Fragment newInstance() {
        return new Tab2Fragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);//para poder inflar el menu con sus propios botones
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            mListener.abrirDrawer();
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab2, container, false);
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


    public interface OnFragmentInteractionListener {
        void abrirDrawer();
    }
}
