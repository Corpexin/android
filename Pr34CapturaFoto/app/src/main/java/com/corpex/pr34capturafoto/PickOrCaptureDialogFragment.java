package com.corpex.pr34capturafoto;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * Created by corpex, by the Grace of God on 12/02/2016.
 */
public class PickOrCaptureDialogFragment extends DialogFragment {

    private Listener mListener = null;

    public interface Listener { //interfaz usada por el main para recibir que opcion elige el usuario en el dialog
        public void onItemClick(DialogFragment dialog, int which);
    }

    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder b = new AlertDialog.Builder(this.getActivity());
        b.setTitle("Elige Opcion");
        b.setItems(R.array.pick_or_capture_opciones, new DialogInterface.OnClickListener() {
            // Cuando se selecciona el elemento.
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Se le notifica al listener, indicándole el índice del
                // elemento seleccionado.
                mListener.onItemClick(PickOrCaptureDialogFragment.this,
                        which);
            }
        });
        b.setIcon(R.mipmap.ic_launcher);
        return b.create();
    }

    // Cuando se enlaza el fragmento con la actividad.
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Establece la actividad como listener de los eventos del diálogo.
        try {
            mListener = (Listener) activity;
        } catch (ClassCastException e) {
            // La actividad no implementa la interfaz, se lanza excepción.
            throw new ClassCastException(activity.toString()
                    + " debe implementar PickOrCaptureDialogFragment.Listener");
        }
    }

}