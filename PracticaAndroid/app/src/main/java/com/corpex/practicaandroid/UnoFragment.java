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

    // Interfaz para notificación de eventos desde el fragmento.
    public interface OnAlumnoSelectedListener {
        // Cuando se selecciona un alumno
        public void onAlumnoSelected(Alumno alumno, int position); //position?
    }

    private ListView listaAlumnos;
    private int mItemSeleccionado;
    private OnAlumnoSelectedListener mListener;

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
        Alumno alumno = (Alumno) listaAlumnos.getItemAtPosition(mItemSeleccionado);
        listaAlumnos.setItemChecked(mItemSeleccionado, true);
        listaAlumnos.setSelection(mItemSeleccionado);
        //getActivity().setTitle(alumno.getNombre());
    }

    // Obtiene e inicializa las vistas.
    private void initVistas() {
        if (getView() != null) {
            // Se crea el adaptador y se asigna al ListView.
            listaAlumnos = (ListView) getView().findViewById(R.id.lvAlumnos);
            AlumnoAdapter mAdaptador = new AlumnoAdapter(this.getActivity(), getDatos());
            listaAlumnos.setAdapter(mAdaptador);
        }
    }

    public ArrayList<Alumno> getDatos() {
        ArrayList<Alumno> alumnos = new ArrayList<>();
        alumnos.add(new Alumno("Pepe Gutierrez Gomez", "17", "Algeciras", "Avda Falsa 123", "tel:(+34)123456789", 1));
        alumnos.add(new Alumno("Maria Jimenez Semperez", "23", "La Linea", "Avda Verdadera 453", "tel:(+34)123356789", 2));
        alumnos.add(new Alumno("Gustavo Adolfo Benitez", "43", "Los Barrios", "Avda Flores 233", "tel:(+34)123556789", 3));
        alumnos.add(new Alumno("Gertrudio Benicio Solomero", "42", "Algeciras", "Avda Erquillo 654", "tel:(+34)126456789", 4));
        alumnos.add(new Alumno("Amparo Sindientes Gomez", "18", "Algeciras", "Avda Mugro 453", "tel:(+34)123454789", 5));
        alumnos.add(new Alumno("Braulio Ordoñez Tornero", "22", "Los barrios", "Avda Senda 167", "tel:(+34)123436789", 6));
        alumnos.add(new Alumno("Eurgencio Martinez Rondon", "23", "Malaga", "Avda Apache 543", "tel:(+34)123455389", 7));
        alumnos.add(new Alumno("Rotero Shurmano Valle", "17", "Cadiz", "Avda Canguro Paquillo 153", "tel:(+34)153456789", 8));
        alumnos.add(new Alumno("Siruelo Gonzalez Benevolente", "34", "Algeciras", "Avda Aquitepillo 363", "tel:(+34)115456789", 9));
        alumnos.add(new Alumno("Gertrudis Zalamera Fernandez", "24", "Tarifa", "Avda Falsa 453", "tel:(+34)123455489", 10));
        alumnos.add(new Alumno("Gonzalo Quilombo Guerrero", "45", "Los Barrios", "Avda Eltruco 563", "tel:(+34)164456789", 1));
        return alumnos;
    }

    // Al pulsar sobre un elemento de la lista.
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // Se pulsa sobre el item.
        pulsarItem(position);
    }
}
