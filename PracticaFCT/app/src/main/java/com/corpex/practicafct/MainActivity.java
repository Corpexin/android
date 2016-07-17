package com.corpex.practicafct;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, nuevo_alumno.OnFragmentInteractionListener, tab_layout_fragment.OnFragmentInteractionListener,Tab1Fragment.OnFragmentInteractionListener, Tab2Fragment.OnFragmentInteractionListener, ProximasVisitasFragment.OnFragmentInteractionListener {
    NavigationView navigationView;
    DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Selecciona la primera opcion si recienn entra (Cuidado con el cambio de orientacion)
        if (savedInstanceState == null) {
            onNavigationItemSelected(navigationView.getMenu().getItem(1));
        }


    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.confirmar) {
            return true;
        }else if(id == android.R.id.home){
            drawer.openDrawer(GravityCompat.START);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Fragment frg;

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nuevo_alumno) {
            frg = nuevo_alumno.newInstance("Nuevo Alumno");
            getSupportFragmentManager().beginTransaction().replace(R.id.content, frg, "Nuevo Alumno").commit();
        } else if (id == R.id.tutorias) {
            frg =  tab_layout_fragment.newInstance("Tutorias");
            getSupportFragmentManager().beginTransaction().replace(R.id.content, frg, "Tutorias").commit();
        } else if (id == R.id.prox_visitas) {
            frg = ProximasVisitasFragment.newInstance("Proximas Visitas");
            getSupportFragmentManager().beginTransaction().replace(R.id.content, frg, "Proximas Visitas").commit();
        } else if (id == R.id.configuracion) {
            Intent i = new Intent(this, PreferenciasActivity.class);
            this.startActivity(i);
        } else if (id == R.id.acerca_de) {
            Intent i = new Intent(this, AcercaDeActivity.class);
            this.startActivity(i);
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
