package com.corpex.pr25dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * Created by Corpex, by the Grace of God on 26/11/2015.
 */
public class MiDialogFragment extends DialogFragment{
    MiDialogListener listener;

    public interface MiDialogListener{
        public void onPositiveButtonClick(DialogFragment dialog);
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder b = new AlertDialog.Builder(this.getActivity());
        b.setTitle("Pulsación");
        b.setMessage("Me han pulsado");
        b.setPositiveButton(R.string.aceptar, new DialogInterface.OnClickListener() {
            // Al pulsar el botón positivo.
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Se notifica el evento al listener.
                listener.onPositiveButtonClick(MiDialogFragment.this);
            }
        });

        return b.create();
    }

    // Al enlazar el fragmento con la actividad.
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Establece la actividad como listener de los eventos del diálogo.
        try {
            listener = (MiDialogListener) activity;
        } catch (ClassCastException e) {
            // La actividad no implementa la interfaz, se lanza excepción.
            throw new ClassCastException(activity.toString()
                    + " debe implementar Listener");
        }
    }
}