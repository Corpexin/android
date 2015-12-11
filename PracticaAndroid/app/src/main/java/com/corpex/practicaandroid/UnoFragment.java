package com.corpex.practicaandroid;


import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

//Fragmento con un listview
public class UnoFragment extends Fragment implements AdapterView.OnItemClickListener{

    // Interfaz para notificación de eventos desde el fragmento.
    public interface OnAlumnoSelectedListener {
        // Cuando se selecciona un alumno
        void onAlumnoSelected(Alumno alumno, int position); //position?
    }

    AlumnoAdapter mAdaptador;
    private ListView listaAlumnos;
    private int mItemSeleccionado;
    private OnAlumnoSelectedListener mListener;

    //Notifico los cambios al adaptador
    @Override
    public void onResume() {
        ((ArrayAdapter)listaAlumnos.getAdapter()).notifyDataSetChanged();
        super.onResume();
    }

    //Cuando se crea el fragmento
    @Override
    public void onCreate(Bundle savedInstanceState) {
        setRetainInstance(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_uno, container, false);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnAlumnoSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " debe implementar OnAlumnoSelectedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initVistas();
        if(savedInstanceState ==null) //para evitar que vuelva a rellenar la lista y duplique los elementos
            mAdaptador = new AlumnoAdapter(this.getActivity(), Coleccion.rellenarLista());
        listaAlumnos.setAdapter(mAdaptador);
        FrameLayout flHueco2 = (FrameLayout) getActivity().findViewById(R.id.flHueco2);
        boolean mDosPaneles = flHueco2 != null && flHueco2.getVisibility() == View.VISIBLE;
        listaAlumnos.setOnItemClickListener(this);
        if (mDosPaneles) {
            pulsarItem(mItemSeleccionado);
        } else {
            marcarAlumno(mItemSeleccionado);
        }
        //eliminar alumno
        listaAlumnos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                showAlert(position);
                return true;
            }
        });

    }

    private void pulsarItem(int position) {
        marcarAlumno(position);
        if (mListener != null && !Coleccion.getListaAlumnos().isEmpty() && position<Coleccion.getListaAlumnos().size()) {
            mListener.onAlumnoSelected((Alumno) listaAlumnos.getItemAtPosition(position), position);
        }
    }

    public void marcarAlumno(int position) {
        mItemSeleccionado = position;
        listaAlumnos.setItemChecked(mItemSeleccionado, true);
        listaAlumnos.setSelection(mItemSeleccionado);
    }

    private void initVistas() {
        if (getView() != null) {
            // Se crea el adaptador y se asigna al ListView.
            listaAlumnos = (ListView) getView().findViewById(R.id.lvAlumnos);
        }
    }

    // Al pulsar sobre un elemento de la lista.
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // Se pulsa sobre el item.
        pulsarItem(position);
    }

    public void showAlert(final int position){
        //Al hacer long click muestra un dialog para elminar el item
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View alertDialogView = inflater.inflate(R.layout.test_dialog, null);
        alertDialog.setView(alertDialogView);

        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                mAdaptador.remove((Alumno) listaAlumnos.getItemAtPosition(position));
                dialog.cancel();
            }
        });

        alertDialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        alertDialog.show();

    }


}
