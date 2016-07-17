package com.corpex.practicafct.Fragments;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.corpex.practicafct.DataBase.DAO;
import com.corpex.practicafct.POJO.Alumno;
import com.corpex.practicafct.R;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class nuevo_alumno extends Fragment {
    private OnFragmentInteractionListener mListener;
    private TextView txtNombre;
    private TextView txtEmail;
    private TextView txtMovil;
    private TextView txtEmpresa;
    private TextView txtTutor;
    private TextView txtHorario;
    private TextView txtDireccion;
    private ImageView ivFoto;
    private TextInputLayout tilNombre;
    private TextInputLayout tilEmail;
    private TextInputLayout tilMovil;
    private TextInputLayout tilEmpresa;
    private TextInputLayout tilTutor;
    private TextInputLayout tilHorario;
    private TextInputLayout tilDireccion;
    private Alumno alumno;
    private FloatingActionButton fab;
    String pathFoto;

    private static final int RC_CAPTURAR_FOTO = 0;
    private static final int RC_SELECCIONAR_FOTO = 1;
    private static final String PREF_PATH_FOTO = "prefPathFoto";
    private static final int OPTION_PICK = 0;
    private String sPathFotoOriginal; // path en el que se guarda la foto capturada.
    private String sNombreArchivo; // Nombre para guardar en privado la foto escalada.

    public nuevo_alumno() {}

    public static nuevo_alumno newInstance() {
        nuevo_alumno fragment = new nuevo_alumno();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);


    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews(getView());

        SharedPreferences preferencias = getActivity().getSharedPreferences(getString(R.string.app_name), getActivity().MODE_PRIVATE);
        pathFoto = preferencias.getString(PREF_PATH_FOTO, "");

    }

    private void initViews(View view) {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.setTitle("Nuevo  Alumno");
        AppCompatActivity actividad = ((AppCompatActivity) getActivity());
        actividad.setSupportActionBar(toolbar);

        if (actividad.getSupportActionBar() != null) {
            actividad.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            actividad.getSupportActionBar().setHomeButtonEnabled(true);
            actividad.getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        }


        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               mostrarDialog();
            }
        });

        tilNombre = (TextInputLayout) getActivity().findViewById(R.id.tilNombre);
        tilMovil = (TextInputLayout) getActivity().findViewById(R.id.tilMovil);
        tilEmail = (TextInputLayout) getActivity().findViewById(R.id.tilEmail);
        tilEmpresa = (TextInputLayout) getActivity().findViewById(R.id.tilEmpresa);
        tilTutor = (TextInputLayout) getActivity().findViewById(R.id.tilTutor);
        tilHorario = (TextInputLayout) getActivity().findViewById(R.id.tilHorario);
        tilDireccion = (TextInputLayout) getActivity().findViewById(R.id.tilDireccion);
        txtNombre = (TextView) getActivity().findViewById(R.id.txtNombre);
        txtMovil = (TextView) getActivity().findViewById(R.id.txtMovil);
        txtEmail = (TextView) getActivity().findViewById(R.id.txtEmail);
        txtEmpresa = (TextView) getActivity().findViewById(R.id.txtEmpresa);
        txtTutor = (TextView) getActivity().findViewById(R.id.txtTutor);
        txtHorario = (TextView) getActivity().findViewById(R.id.txtHorario);
        txtDireccion = (TextView) getActivity().findViewById(R.id.txtDireccion);
        ivFoto = (ImageView) getActivity().findViewById(R.id.imageView);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.confirmar) {
            if(comprobarCamposVacios()){
                alumno = new Alumno(txtNombre.getText().toString(), txtMovil.getText().toString(), txtEmail.getText().toString(), txtEmpresa.getText().toString(), txtTutor.getText().toString(), txtHorario.getText().toString(), txtDireccion.getText().toString(), pathFoto);
                DAO.getInstance(getContext()).createAlumno(alumno);
                Toast toast = Toast.makeText(getContext(), "Alumno añadido", Toast.LENGTH_LONG);
                toast.show();
            }
            return true;
        }else if(id == android.R.id.home){
            mListener.abrirDrawer();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nuevo_alumno, container, false);
    }

    // Cuando se vincula el fragmento a la actividad.
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            // La actividad actuará como listener cuando se seleccione una obra.
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            // La actividad no implementa la interfaz necesaria.
            throw new ClassCastException(activity.toString()
                    + " debe implementar OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void abrirDrawer();
    }

    private boolean comprobarCamposVacios(){
        boolean rellenos = false;
        if(!comprobarTil(tilNombre))
            rellenos = true;
        if(!comprobarTil(tilMovil))
            rellenos = true;
        if(!comprobarTil(tilEmail))
            rellenos = true;
        if(!comprobarTil(tilEmpresa))
            rellenos = true;
        if(!comprobarTil(tilTutor))
            rellenos = true;
        if(!comprobarTil(tilHorario))
            rellenos = true;
        if(!comprobarTil(tilDireccion))
            rellenos = true;
        if(pathFoto!=null)
            rellenos = true;
        return rellenos;
    }

    private boolean comprobarTil(TextInputLayout til) {
        if(til.getEditText().getText().toString().isEmpty()){
            til.setError("Campo Obligatorio");
            til.setErrorEnabled(true);
        }
        else
            til.setErrorEnabled(false);

        return til.isErrorEnabled();
    }










    //METODOS PARA OBTENER FOTO
    private void mostrarDialog() {

        AlertDialog.Builder b = new AlertDialog.Builder(this.getActivity());
        b.setTitle("Elige Opcion");
        b.setItems(R.array.pick_or_capture_opciones, new DialogInterface.OnClickListener() {
            // Cuando se selecciona el elemento.
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Date d = new Date();
                SimpleDateFormat formato = new SimpleDateFormat("ssmmddMMyy");
                if (which == 0) {
                    seleccionarFoto(formato.format(d)+"mifoto.jpg"); // Si BD, por ejemplo ID_alumno.jpg.
                } else {
                    capturarFoto(formato.format(d)+"mifoto.jpg"); // Si BD, por ejemplo ID_alumno.jpg.
                }

            }
        });
        b.setIcon(R.mipmap.icono);
        b.create();
        b.show();
    }

    private void seleccionarFoto(String nombreArchivoPrivado) {
        // Se guarda el nombre para uso posterior.
        sNombreArchivo = nombreArchivoPrivado;
        // Se seleccionará un imagen de la galería.
        // (el segundo parámetro es el Data, que corresponde a la Uri de la galería.)
        Intent i = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        i.setType("image/*");
        startActivityForResult(i, RC_SELECCIONAR_FOTO);
    }

    private void capturarFoto(String nombreArchivoPrivado) {
        // Se guarda el nombre para uso posterior.
        sNombreArchivo = nombreArchivoPrivado;
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Si hay alguna actividad que sepa realizar la acción.
        if (i.resolveActivity(getActivity().getPackageManager()) != null) {
            // Se crea el archivo para la foto en el directorio público (true).
            // Se obtiene la fecha y hora actual.
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                    Locale.getDefault()).format(new Date());
            String nombre = "IMG_" + timestamp + "_" + ".jpg";
            File fotoFile = crearArchivoFoto(nombre, true);
            if (fotoFile != null) {
                // Se guarda el path del archivo para cuando se haya hecho la captura.
                sPathFotoOriginal = fotoFile.getAbsolutePath();
                // Se añade como extra del intent la uri donde debe guardarse.
                i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(fotoFile));
                startActivityForResult(i, RC_CAPTURAR_FOTO);
            }
        }
    }


    public void onActivityResult(int requestCode, int resultCode,Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case RC_CAPTURAR_FOTO:
                    // Se agrega la foto a la Galería
                    agregarFotoAGaleria(sPathFotoOriginal);
                    // Se escala la foto, se almacena en archivo propio y se muestra en ImageView.
                    cargarImagenEscalada(sPathFotoOriginal);
                    break;
                case RC_SELECCIONAR_FOTO:
                    // Se obtiene el path real a partir de la uri retornada por la galería.
                    Uri uriGaleria = intent.getData();
                    sPathFotoOriginal = getRealPath(uriGaleria);
                    // Se escala la foto, se almacena en archivo propio y se muestra en ImageView.
                    cargarImagenEscalada(sPathFotoOriginal);
                    break;
            }
        }
    }


    private void agregarFotoAGaleria(String pathFoto) {
        // Se crea un intent implícito con la acción de
        // escaneo de un fichero multimedia.
        Intent i = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        // Se obtiene la uri del archivo a partir de su path.
        File archivo = new File(pathFoto);
        Uri uri = Uri.fromFile(archivo);
        // Se establece la uri con datos del intent.
        i.setData(uri);
        // Se envía un broadcast con el intent.
        this.getActivity().sendBroadcast(i);
    }

    private void cargarImagenEscalada(String pathFoto) {
        // Se utiliza una tarea asíncrona, para escalar, guardar en archivo propio y mostrar
        // la foto en el ImageView.
        MostrarFotoAsyncTask tarea = new MostrarFotoAsyncTask();
        tarea.execute(pathFoto);
    }


    private File crearArchivoFoto(String nombre, boolean publico) {
        // Se obtiene el directorio en el que almacenarlo.
        File directorio;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            if (publico) {
                // En el directorio público para imágenes del almacenamiento externo.
                directorio = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            } else {
                directorio = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            }
        } else {
            // En almacenamiento interno.
            directorio = getActivity().getFilesDir();
        }
        // Su no existe el directorio, se crea.
        if (directorio != null && !directorio.exists()) {
            if (!directorio.mkdirs()) {
                Log.d(getString(R.string.app_name), "error al crear el directorio");
                return null;
            }
        }
        // Se crea un archivo con ese nombre y la extensión jpg en ese
        // directorio.
        File archivo = null;
        if (directorio != null) {
            archivo = new File(directorio.getPath() + File.separator +
                    nombre);
            Log.d(getString(R.string.app_name), archivo.getAbsolutePath());
        }
        // Se retorna el archivo creado.
        return archivo;
    }

    private String getRealPath(Uri uriGaleria) {
        // Se consulta en el content provider de la galería el path real del archivo de la foto.
        String[] filePath = {MediaStore.Images.Media.DATA};
        Cursor c = getActivity().getContentResolver().query(uriGaleria, filePath, null, null, null);
        c.moveToFirst();
        int columnIndex = c.getColumnIndex(filePath[0]);
        String path = c.getString(columnIndex);
        c.close();
        return path;
    }



    private class MostrarFotoAsyncTask extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... params) {
            // Se escala la foto, cuyo path corresponde al primer parámetro,
            // retornado el Bitmap correspondiente.
            return escalarFoto(params[0], getResources().getDimensionPixelSize(R.dimen.ancho_visor), getResources().getDimensionPixelSize(R.dimen.alto_visor));
        }

        // Una vez finalizado el hilo de trabajo. Se ejecuta en el hilo
        // principal. Recibe el Bitmap de la foto escalada (o null si error).
        @Override
        protected void onPostExecute(Bitmap bitmapFoto) {
            if (bitmapFoto != null) {
                // Se guarda la copia propia de la imagen.
                File archivo = crearArchivoFoto(sNombreArchivo, false);
                if (archivo != null) {
                    if (guardarBitmapEnArchivo(bitmapFoto, archivo)) {
                        // Se almacena el path de la foto a mostrar en el ImageView.
                        guardarEnPreferencias(archivo.getAbsolutePath());
                        // Se muestra la foto en el ImageView.
                        ivFoto.setImageBitmap(bitmapFoto);
                    }
                }
            }
        }

        private void guardarEnPreferencias(String path) {
            // Se almacena en las preferencias el path del archivo con la foto escalada y privada
            SharedPreferences preferencias = getActivity().getSharedPreferences(getString(R.string.app_name),getActivity().MODE_PRIVATE);
            SharedPreferences.Editor editor = preferencias.edit();
            editor.putString(PREF_PATH_FOTO, path);
            pathFoto = path;
            editor.apply();
        }

        // Escala la foto indicada, para ser mostarda en un visor determinado.
        // Retorna el bitmap correspondiente a la imagen escalada o null si
        // se ha producido un error.
        private Bitmap escalarFoto(String pathFoto, int anchoVisor,
                                   int altoVisor) {
            try {
                // Se obtiene el tamaño de la imagen.
                BitmapFactory.Options opciones = new BitmapFactory.Options();
                opciones.inJustDecodeBounds = true; // Solo para cálculo.
                BitmapFactory.decodeFile(pathFoto, opciones);
                int anchoFoto = opciones.outWidth;
                int altoFoto = opciones.outHeight;
                // Se obtiene el factor de escalado para la imagen.
                int factorEscalado = Math.min(anchoFoto / anchoVisor, altoFoto
                        / altoVisor);
                // Se escala la imagen con dicho factor de escalado.
                opciones.inJustDecodeBounds = false; // Se escalará.
                opciones.inSampleSize = factorEscalado;
                return BitmapFactory.decodeFile(pathFoto, opciones);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        // Guarda el bitamp de la foto en un archivo. Retorna si ha ido bien.
        private boolean guardarBitmapEnArchivo(Bitmap bitmapFoto, File archivo) {
            try {
                FileOutputStream flujoSalida = new FileOutputStream(archivo);
                bitmapFoto.compress(Bitmap.CompressFormat.JPEG, 100, flujoSalida);
                flujoSalida.flush();
                flujoSalida.close();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
    }
}
