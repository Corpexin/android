package com.corpex.practicafct;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProximasVisitasFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProximasVisitasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProximasVisitasFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ArrayAdapter<String> mAdaptador;
    private ArrayList<String> mListaContenido;
    private ListView lstProxV;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ProximasVisitasFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static ProximasVisitasFragment newInstance(String title) {
        ProximasVisitasFragment fragment = new ProximasVisitasFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews(getView());
    }

    private void initViews(View view) {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.setTitle("Proximas Visitas");
        AppCompatActivity actividad = ((AppCompatActivity) getActivity());
        actividad.setSupportActionBar(toolbar);

        if (actividad.getSupportActionBar() != null) {
            actividad.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            actividad.getSupportActionBar().setHomeButtonEnabled(true);
            actividad.getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        }

        mListaContenido = new ArrayList<>();
        mListaContenido.add("Yoksetioxdxd");
        mListaContenido.add("Yoksetiox123123dxd");
        mListaContenido.add("Yoksetioxd123123123xd");
        lstProxV = (ListView) view.findViewById(R.id.lstAlumnos);
        lstProxV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        mAdaptador = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_list_item_1, mListaContenido);
        lstProxV.setAdapter(mAdaptador);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_proximas_visitas, container, false);
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
