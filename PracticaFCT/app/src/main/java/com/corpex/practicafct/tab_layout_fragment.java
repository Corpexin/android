package com.corpex.practicafct;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link tab_layout_fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link tab_layout_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class tab_layout_fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private static final String ARG_TITULO = "titulo";
    private FloatingActionButton fabAccion;

    private String mTitulo;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public tab_layout_fragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static tab_layout_fragment newInstance(String title) {
        tab_layout_fragment fragment = new tab_layout_fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, title);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Se obtienen los argumentos.
        if (getArguments() != null) {
            mTitulo = getArguments().getString(ARG_TITULO);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getView() != null) {
            initVistas(getView());
        }
    }

    private void initVistas(final View view) { //cuidao que se ha modificado a FINAL
        fabAccion = (FloatingActionButton) view.findViewById(R.id.fabAccion);
        fabAccion.hide();
        fabAccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(view.getContext(), NuevaVisita.class));
            }
        });
        configToolbar(view);
        configViewPager(view);
    }

    private void configToolbar(View view) {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.setTitle("Tutorias");
        AppCompatActivity actividad = ((AppCompatActivity) getActivity());
        actividad.setSupportActionBar(toolbar);
        actividad.setTitle(mTitulo);
        if (actividad.getSupportActionBar() != null) {
            actividad.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            actividad.getSupportActionBar().setHomeButtonEnabled(true);
            actividad.getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab_layout, container, false);
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






    // Configura el ViewPager.
    private void configViewPager(View view) {
        final ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        final ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        viewPagerAdapter.addFragment(Tab1Fragment.newInstance(),"Alumno", 0);
        viewPagerAdapter.addFragment(Tab2Fragment.newInstance(),"Visitas",0);
        viewPager.setAdapter(viewPagerAdapter);
        final TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        // OJO se hace en post por bug en design support library 22.2.1
        tabLayout.post(new Runnable() {
            @Override
            public void run() {tabLayout.setupWithViewPager(viewPager);
            }
        });
        // Se añade un listener para poder mostrar / ocultar el FAB dependiendo
        // del fragmento que se muestre en el ViewPager.
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (position == 1) {
//                    ViewCompat.animate(fabAccion).scaleX(1).scaleY(1);
                    fabAccion.show();
                } else {
//                    ViewCompat.animate(fabAccion).scaleX(0).scaleY(0);
                    fabAccion.hide();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }








    // Adaptador para el ViewPager.
    static class ViewPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();
        private final List<Integer> mFragmentIcons = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        // Añade un fragmento al adaptador. Recibe el fragmento, el título
        // para la tab y el icono para la tab.
        public void addFragment(Fragment fragment, String title,@DrawableRes int resIdIcon) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
            mFragmentIcons.add(resIdIcon);
        }

        // Retorna el fragmento correspondiente a la posición recibida.
        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        // Retorna el número de fragmentos del adaptador.
        @Override
        public int getCount() {
            return mFragments.size();
        }

        // Retorna el título asociado a una determinada página.
        @Override
        public CharSequence getPageTitle(int position) {
            return "  " + mFragmentTitles.get(position);
        }

        // Retorna el resId del icono asociado a una determinada página.
        @DrawableRes
        public int getPageIcon(int position) throws Exception{
            try {
                return  mFragmentIcons.get(position);
            } catch (Exception e) {
                throw e;
            }
        }

    }

}
