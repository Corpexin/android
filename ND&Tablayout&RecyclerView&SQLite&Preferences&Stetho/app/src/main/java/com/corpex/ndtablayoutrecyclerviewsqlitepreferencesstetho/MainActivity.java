package com.corpex.ndtablayoutrecyclerviewsqlitepreferencesstetho;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, TabLayoutFragment.OnFragmentInteractionListener, Tab1Fragment.OnFragmentInteractionListener, Tab2Fragment.OnFragmentInteractionListener {
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
            onNavigationItemSelected(navigationView.getMenu().getItem(0));
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
        //No inflamos nada porque lo hara cada fraagmento
        return true;
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Fragment frg;
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            frg =  TabLayoutFragment.newInstance();
            getSupportFragmentManager().beginTransaction().replace(R.id.content, frg, "Tutorias").commit();
        } else if (id == R.id.nav_gallery) {

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
