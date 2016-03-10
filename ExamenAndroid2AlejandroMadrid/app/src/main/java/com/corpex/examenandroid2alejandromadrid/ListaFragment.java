package com.corpex.examenandroid2alejandromadrid;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.corpex.examenandroid2alejandromadrid.Adaptador.ProductoAdapter;
import com.corpex.examenandroid2alejandromadrid.BaseDatos.Contract;
import com.corpex.examenandroid2alejandromadrid.POJO.Producto;

import java.util.ArrayList;


public class ListaFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>,ProductoAdapter.OnItemClickListener {
    private RecyclerView lstVisitas;
    private ProductoAdapter mAdaptador;
    private LoaderManager gestor;
    String palabra;
    private SharedPreferences preferencias;
    SoundPool reproductor;


    // Constantes.
    private static final float VELOCIDAD_NORMAL = 1f;
    private static final int PRIORIDAD_MAXIMA = 1;
    private static final float VOLUMEN_MAX = 1f;
    private static final int SIN_BUCLE = 0;
    private static final int CALIDAD_NORMAL = 0;
    private static final int PRIORIDAD_NORMAL = 1;

    private int idDisparo;

    private  ArrayList<Producto> listaP;

    private OnFragmentInteractionListener mListener;

    public ListaFragment() {
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
        listaP = new ArrayList<>();

        configSonidos();
        gestor = getActivity().getSupportLoaderManager();
        gestor.initLoader(0, null, this);
        lstVisitas = (RecyclerView) view.findViewById(R.id.lstCompra);
        lstVisitas.setHasFixedSize(true);
        lstVisitas.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        lstVisitas.setItemAnimator(new DefaultItemAnimator());

        mAdaptador = new ProductoAdapter(listaP);
        mAdaptador.setOnItemClickListener(this);
        mAdaptador.setEmptyView(view.findViewById(R.id.lblNoHayAlumnos));
        lstVisitas.setAdapter(mAdaptador);

        if(palabra == null){
            palabra = "--";
        }
    }

    // Configura el reproductor de sonidos.
    private void configSonidos() {
        // Se crea el objeto SoundPool con un límite de 8 sonidos simultáneos y
        // calidad estándar.
        reproductor = new SoundPool(8, AudioManager.STREAM_MUSIC,CALIDAD_NORMAL);
        // Se cargan los ficheros de sonido (recibe el contexto, el recurso y la
        // prioridad estándar).
        idDisparo = reproductor.load(getContext(), R.raw.disparo, PRIORIDAD_NORMAL);

    }


    public static ListaFragment newInstance() {
        ListaFragment fragment = new ListaFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lista, container, false);
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

    @Override
    public void onResume() {
        preferencias = PreferenceManager.getDefaultSharedPreferences(getContext());
        if(preferencias == null)
            palabra = "--";
        else {
            String prefPestana =  preferencias.getString("prefEstado", "--");
            if(prefPestana.matches("--")) {
                palabra = "--";
            }else{
                palabra = prefPestana;
            }
        }
        super.onResume();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        // Se retorna el cargador del cursor. Se le pasa el contexto, la uri en
        // la que consultar los datos y las columnas a obtener.
        return new CursorLoader(getActivity(),Proveedor.CONTENT_URI_PRODUCTOS, Contract.Producto.TODOS,null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
    // Se cambia el cursor del adaptador por el que tiene datos.
        //mAdaptador.swapCursor(data);
        // Se vacía de datos el adaptador.
        //adaptador.changeCursor(null);
        // Se inicializa el cargador.


        if (data != null) {
            data.moveToFirst();
            // Es obligatorio mover el puntero al cursor a un registro antes de acceder a datos. Se recorre el cursor.
            do  {
                // Se obtiene el dato del registro correspondiente a la palabra.
                long id = data.getLong(data.getColumnIndexOrThrow(Contract.Producto._ID));
                String nombre = data.getString(data.getColumnIndexOrThrow(Contract.Producto.NOMBRE));
                float numUnidades = data.getFloat(data.getColumnIndexOrThrow(Contract.Producto.NUMUNIDADES));
                String unidadC = data.getString(data.getColumnIndexOrThrow(Contract.Producto.UNIDADC));
                Producto pr = new Producto(nombre, numUnidades, unidadC);
                pr.setId(id);
                mAdaptador.addItem(pr);
            }while(data.moveToNext());
        } else {

        }



    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }


    @Override
    public void onItemClick(View view, Producto visita, int position) {
        boolean activado = preferencias.getBoolean("prefSonido", false);

        if(activado)
            disparar();


        if(visita.getNombre().contains(palabra)){
            visita.setNombre(visita.getNombre().replaceFirst(palabra, ""));
            //Uri uri, ContentValues values, String selection,String[] selectionArgs
            ContentValues valores = new ContentValues();
            valores.put(Contract.Producto.NOMBRE, visita.getNombre());
            getContext().getContentResolver().update(Proveedor.CONTENT_URI_PRODUCTOS, valores, Contract.Producto._ID + " = " + visita.getId(), null);
        }else {
            visita.setNombre(palabra + visita.getNombre());
            //Uri uri, ContentValues values, String selection,String[] selectionArgs
            ContentValues valores = new ContentValues();
            valores.put(Contract.Producto.NOMBRE, visita.getNombre());
            getContext().getContentResolver().update(Proveedor.CONTENT_URI_PRODUCTOS, valores, Contract.Producto._ID + " = " + visita.getId(), null);
        }
    }

    private void disparar() {
        // Se reproduce el disparo.
        reproductor.play(idDisparo, VOLUMEN_MAX, VOLUMEN_MAX, PRIORIDAD_MAXIMA,
                SIN_BUCLE, VELOCIDAD_NORMAL);
    }

    public interface OnFragmentInteractionListener {

    }
}
