package com.corpex.pr33palette;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
    ImageView imagen;
    private Palette mPaleta;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imagen = (ImageView) findViewById(R.id.imageView);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        cargarImagen();
    }

    private void obtenerImagen() {
        Bitmap bitmap = ((BitmapDrawable) imagen.getDrawable()).getBitmap();
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                // La paleta de 16 colores ya ha sido generada asíncronamente.
                mPaleta = palette;
                toolbar.setBackgroundColor(palette.getVibrantSwatch().getRgb());
                toolbar.setTitleTextColor(palette.getMutedSwatch().getRgb());
            }
        });
    }



    private void cargarImagen() {
        Picasso.with(getApplicationContext()).load("http://lorempixel.com/400/200/").into(imagen);
        Picasso.with(this).load("http://lorempixel.com/400/200/")
                .into(imagen, new Callback() {
                    @Override
                    public void onSuccess() {
                        obtenerImagen();
                    }

                    @Override
                    public void onError() {
                    }
                });
    }
}
