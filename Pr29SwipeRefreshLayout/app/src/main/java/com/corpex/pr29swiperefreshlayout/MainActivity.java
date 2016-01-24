package com.corpex.pr29swiperefreshlayout;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    RecyclerView lstLista;
    ListaAdapter mAdaptador;
    LinearLayoutManager mLayoutManager;
    ArrayList<String> mDatos;
    SwipeRefreshLayout swPanel;
    SimpleDateFormat mFormateador = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configToolbar();
        getDatosIniciales();
        setupPanel();
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        lstLista = (RecyclerView) findViewById(R.id.lstLista);
        lstLista.setHasFixedSize(true);
        mAdaptador = new ListaAdapter(mDatos);
        lstLista.setAdapter(mAdaptador);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        lstLista.setLayoutManager(mLayoutManager);
        lstLista.setItemAnimator(new DefaultItemAnimator());
    }

    private void setupPanel() {
        swPanel = (SwipeRefreshLayout) findViewById(R.id.swlPanel);
        swPanel.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refrescar();
            }
        });
        swPanel.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    private void refrescar() {
        swPanel.setRefreshing(true);
        new Handler().postDelayed(new Runnable(){

            @Override
            public void run() {
                mAdaptador.addItem(mFormateador.format(new Date()));
                swPanel.setRefreshing(false);
            }
        }, 2000);
    }

    private void configToolbar() {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
    }

    public void getDatosIniciales() {
        mDatos = new ArrayList<>();
        mDatos.add(mFormateador.format(new Date()));
    }
}
