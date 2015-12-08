package com.corpex.practicaandroid;




import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;



public class DosFragment extends Fragment {
    // Interfaz para notificación de eventos desde el fragmento.
    public interface OnDetalleShownListener {
        // Cuando se selecciona un alumno.
        public void onDetalleShown(int position);
    }

    public static final String EXTRA_ALUMNO = "alumno";
    public static final String EXTRA_POSITION = "position";
    ImageView ivFotoAlumno;
    TextView tvNombre;
    TextView tvEdad;
    TextView tvCiudad;
    TextView tvCalle;
    TextView tvTelefono;
    Alumno mAlumno;
    int mPosition;
    private OnDetalleShownListener mListener;

    public static DosFragment newInstance(Alumno alumno, int position) {
        DosFragment frgDetalle = new DosFragment();
        Bundle argumentos = new Bundle();
        argumentos.putParcelable(EXTRA_ALUMNO, alumno);
        argumentos.putInt(EXTRA_POSITION, position);
        frgDetalle.setArguments(argumentos);
        return frgDetalle;
    }

    // Retorna la vista que mostrará el fragmento.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Se infla el layout del fragmento y se retorna la vista.
        return inflater.inflate(R.layout.fragment_dos, container, false);
    }


    // Cuando se vincula el fragmento a la actividad.
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            // La actividad actuar� como listener cuando se seleccione una obra.
            mListener = (OnDetalleShownListener) activity;
        } catch (ClassCastException e) {
            // La actividad no implementa la interfaz necesaria.
            throw new ClassCastException(activity.toString()
                    + " debe implementar OnDetalleShownListener");
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
        // Se obtienen e inicializan las vistas.
        initVistas();

        // Se obtiene la obra desde el bundle de parámetros.
        mAlumno = this.getArguments().getParcelable(EXTRA_ALUMNO);
        mPosition = getArguments().getInt(EXTRA_POSITION);
        // Si hay obra, se muestra.
        if (mAlumno != null) {
            mostrarDetalle();
        }
        super.onActivityCreated(savedInstanceState);

    }

    // Obtiene e inicializa las vistas.
    private void initVistas() {
        if (getView() != null) {
            ivFotoAlumno = (ImageView) getView().findViewById(R.id.ivImagenAlumno);
            tvNombre = (TextView) getView().findViewById(R.id.tvNombre);
            tvEdad = (TextView) getView().findViewById(R.id.tvEdad);
            tvCiudad = (TextView) getView().findViewById(R.id.tvCiudad);
            tvCalle = (TextView) getView().findViewById(R.id.tvCalle);
            tvTelefono = (TextView) getView().findViewById(R.id.tvTelefono);
        }
    }

    // Muestra el detalle de un album en las vistas correspondientes.
    void mostrarDetalle() {
        getActivity().setTitle(mAlumno.getNombre());
        tvNombre.setText(mAlumno.getNombre());
        tvEdad.setText(mAlumno.getEdad());
        tvCiudad.setText(mAlumno.getCiudad());
        tvCalle.setText(mAlumno.getCalle());
        tvTelefono.setText(mAlumno.getTelefono());
        // Se notifica a la actividad.
        if (mListener != null) {
            mListener.onDetalleShown(mPosition);
        }
    }


    





}
