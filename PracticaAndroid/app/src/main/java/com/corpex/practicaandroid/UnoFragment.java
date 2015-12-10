package com.corpex.practicaandroid;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;

import java.util.ArrayList;

public class UnoFragment extends Fragment implements AdapterView.OnItemClickListener {

    static final String ALUMNO_ADD = "alumnoAdd";

    // Interfaz para notificación de eventos desde el fragmento.
    public interface OnAlumnoSelectedListener {
        // Cuando se selecciona un alumno
        void onAlumnoSelected(Alumno alumno, int position); //position?
    }

    private ListView listaAlumnos;
    private int mItemSeleccionado;
    private OnAlumnoSelectedListener mListener;
    ArrayList<Alumno> alumnos;

    //Cuando se crea el fragmento
    @Override
    public void onCreate(Bundle savedInstanceState) {
        // Se mantendrá la instancia del fragmento al cambiar la configuración.
        setRetainInstance(true);
        super.onCreate(savedInstanceState);
    }

    // Retorna la vista que mostrará el fragmento.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Se infla el layout del fragmento y se retorna la vista.
        return inflater.inflate(R.layout.fragment_uno, container, false);
    }

    // Cuando se vincula el fragmento a la actividad.
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            // La actividad actuará como listener cuando se seleccione un alumno.
            mListener = (OnAlumnoSelectedListener) activity;
        } catch (ClassCastException e) {
            // La actividad no implementa la interfaz necesaria.
            throw new ClassCastException(activity.toString() + " debe implementar OnAlumnoSelectedListener");
        }
    }

    // Cuando se desvincula el fragmento de la actividad.
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    // Cuando se ha terminado de crear la actividad al completo.
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Se obtienen e inicializan las vistas.
        initVistas();

        // Se comprueba si existe el fragmento de detalle y por tanto se usa dos paneles.
        FrameLayout flHueco2 = (FrameLayout) getActivity().findViewById(R.id.flHueco2);
        boolean mDosPaneles = flHueco2 != null && flHueco2.getVisibility() == View.VISIBLE;
        // El fragmento actuará como listener cuando se pulse sobre un elemento
        // de la lista.
        listaAlumnos.setOnItemClickListener(this);
        if (mDosPaneles) {
            pulsarItem(mItemSeleccionado);
        } else {
            marcarAlumno(mItemSeleccionado);
        }
    }

    // Cuando se "pulsa" sobre un elemento. Recibe la posición.
    private void pulsarItem(int position) {
        // Se marca el alumno seleccionado (por defecto la 0).
        marcarAlumno(position);
        // Se muestra el detalle del alumno.
        if (mListener != null) {
            // Se llama al método correspondiente del listener.
            mListener.onAlumnoSelected((Alumno) listaAlumnos.getItemAtPosition(position), position);
        }
    }

    // Marca el alumno seleccionado. Recibe el alumno.
    public void marcarAlumno(int position) {
        mItemSeleccionado = position;
        listaAlumnos.setItemChecked(mItemSeleccionado, true);
        listaAlumnos.setSelection(mItemSeleccionado);
    }

    // Obtiene e inicializa las vistas.
    private void initVistas() {

        if (getView() != null) {
            // Se crea el adaptador y se asigna al ListView.
            listaAlumnos = (ListView) getView().findViewById(R.id.lvAlumnos);
            alumnos = new ArrayList<>();
            if(((MainActivity) getActivity()).lista != null)
                alumnos.addAll(((MainActivity) getActivity()).lista);
            AlumnoAdapter mAdaptador = new AlumnoAdapter(this.getActivity(), getDatos());
            //if(((MainActivity) getActivity()).lista != null)
            //    mAdaptador.addNewRow(((MainActivity) getActivity()).lista);


            listaAlumnos.setAdapter(mAdaptador);
        }
    }

    public ArrayList<Alumno> getDatos() {

        alumnos.add(new Alumno("Pepe Gutierrez", "17", "Algeciras", "Avda Falsa 123", "(+34)123456789", 1));
        alumnos.add(new Alumno("Maria Jimenez", "23", "La Linea", "Avda Verdadera 453", "(+34)123356789", 2));
        alumnos.add(new Alumno("Gustavo Adolfo", "43", "Los Barrios", "Avda Flores 233", "(+34)123556789", 3));
        alumnos.add(new Alumno("Gertrudio Benicio", "42", "Algeciras", "Avda Erquillo 654", "(+34)126456789", 4));
        alumnos.add(new Alumno("Amparo Sindientes", "18", "Algeciras", "Avda Mugro 453", "(+34)123454789", 5));
        alumnos.add(new Alumno("Braulio Ordoñez", "22", "Los barrios", "Avda Senda 167", "(+34)123436789", 6));
        alumnos.add(new Alumno("Eurgencio Martinez", "23", "Malaga", "Avda Apache 543", "(+34)123455389", 7));
        alumnos.add(new Alumno("Rotero Shurmano", "17", "Cadiz", "Avda Canguro Paquillo 153", "(+34)153456789", 8));
        alumnos.add(new Alumno("Siruelo Gonzalez", "34", "Algeciras", "Avda Aquitepillo 363", "(+34)115456789", 9));
        alumnos.add(new Alumno("Gertrudis Zalamera", "24", "Tarifa", "Avda Falsa 453", "(+34)123455489", 10));
        alumnos.add(new Alumno("Gonzalo Quilombo", "45", "Los Barrios", "Avda Eltruco 563", "(+34)164456789", 1));
        ///////////////
        //alumnos.removeAll(((MainActivity) getActivity()).listaRemove);
        ///////////////

        Thread thread = new Thread(new Runnable() { //como el main no puede hacer labores de conexiones de red creo un nuevo thread
            @Override
            public void run() {
                for(Alumno a : alumnos){
                    a.cambiarImagen();
                }
            }
        });
        thread.start();
        try {
            thread.join(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return alumnos;


    }

    // Al pulsar sobre un elemento de la lista.
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // Se pulsa sobre el item.
        pulsarItem(position);
    }


    //Recibo el alumno añadido, si es que lo tiene, para insertarlo en la lista
    public static UnoFragment newInstance(ArrayList<Alumno> lista) {
        UnoFragment frgDetalle = new UnoFragment();
        Bundle argumentos = new Bundle();
        argumentos.putParcelableArrayList(ALUMNO_ADD, lista);
        frgDetalle.setArguments(argumentos);
        return frgDetalle;
    }

}
