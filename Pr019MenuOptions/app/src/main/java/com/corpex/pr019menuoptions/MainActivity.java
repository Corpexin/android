package com.corpex.pr019menuoptions;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        etNombre = (EditText) findViewById(R.id.etNombre);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflador = getMenuInflater();
        inflador.inflate(R.menu.mimenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Dependiendo del ítem seleccionado.
        switch (item.getItemId()) {
            case R.id.mnuAprobar:
                aprobar();
                return true;
            case R.id.mnuSuspender:
                suspender();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void suspender() {
        Toast.makeText(this, etNombre.getText()+" ha suspendido", Toast.LENGTH_SHORT).show();
    }

    private void aprobar() {
        Toast.makeText(this, etNombre.getText()+" ha aprobado", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        //MenuItem menuI  = menu.findItem(R.id.aprobar)  ejercicio despues de pr018
        //menuI.setTitle ...
        MenuItem aprobar = menu.findItem(R.id.mnuAprobar);
        aprobar.setTitle("Aprobar a "+etNombre.getText());
        MenuItem suspender = menu.findItem(R.id.mnuSuspender);
        suspender.setTitle("Suspender a "+etNombre.getText());
        return super.onPrepareOptionsMenu(menu);
    }
}
