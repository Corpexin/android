package com.corpex.practicaandroid;




import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


//Fragmento con los detalle de un alumno concreto que se mostrar en la main y en la act2 segun orientacion
public class DosFragment extends Fragment {


    public interface OnDetalleShownListener {
        void onDetalleShown(int position);
    }

    public static final String EXTRA_ALUMNO = "alumno";
    public static final String EXTRA_POSITION = "position";
    TextView tvEdad;
    TextView tvCiudad;
    TextView tvCalle;
    TextView tvTelefono;
    Alumno mAlumno;
    int mPosition;
    private OnDetalleShownListener mListener;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dos, container, false);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnDetalleShownListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " debe implementar OnDetalleShownListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        initVistas();
        mAlumno = this.getArguments().getParcelable(EXTRA_ALUMNO);
        mPosition = getArguments().getInt(EXTRA_POSITION);
        //Si hay alumno lo muestra
        if (mAlumno != null) {
            mostrarDetalle();
        }
        super.onActivityCreated(savedInstanceState);

    }

    private void initVistas() {
        if (getView() != null) {
            tvEdad = (TextView) getView().findViewById(R.id.tvEdad);
            tvCiudad = (TextView) getView().findViewById(R.id.tvCiudad);
            tvCalle = (TextView) getView().findViewById(R.id.tvCalle);
            tvTelefono = (TextView) getView().findViewById(R.id.tvTelefono);
        }
    }

    void mostrarDetalle() {
        getActivity().setTitle(mAlumno.getNombre());
        tvEdad.setText(mAlumno.getEdad());
        tvCiudad.setText(mAlumno.getCiudad());
        tvCalle.setText(mAlumno.getCalle());
        tvTelefono.setText(mAlumno.getTelefono());
        if (mListener != null) {
            mListener.onDetalleShown(mPosition);
        }
    }


    public static DosFragment newInstance(Alumno alumno, int position) {
        DosFragment frgDetalle = new DosFragment();
        Bundle argumentos = new Bundle();
        argumentos.putParcelable(EXTRA_ALUMNO, alumno);
        argumentos.putInt(EXTRA_POSITION, position);
        frgDetalle.setArguments(argumentos);
        return frgDetalle;
    }


}
