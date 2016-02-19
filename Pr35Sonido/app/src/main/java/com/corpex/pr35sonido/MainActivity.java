package com.corpex.pr35sonido;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lista;
    Button play;
    Button stop;
    ArrayList<String> canciones;
    MediaPlayer reproductor;
    int posicionAct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lista = (ListView) findViewById(R.id.listView);
        play = (Button) findViewById(R.id.Play);
        stop = (Button) findViewById(R.id.Stop);
        reproductor = new MediaPlayer();
        canciones = new ArrayList<>();
        getCanciones();
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_activated_1, canciones);
        lista.setAdapter(adaptador);
        lista.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                posicionAct = position;
                reproducirCancion(canciones.get(position));
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (play.getText().toString().matches("Pause")) {
                    reproductor.pause();
                    play.setText("Play");
                } else {
                    reproductor.start();
                    play.setText("Pause");
                }

            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lista.setItemChecked(posicionAct, false);
                reproductor.stop();
            }
        });
    }

    private void reproducirCancion(String url) {
        reproductor.reset();
        reproductor.setLooping(false);
        reproductor.setAudioStreamType(AudioManager.STREAM_MUSIC);
        reproductor.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                reproductor.start();
                play.setText("Pause");
            }
        });
        reproductor.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if (canciones != null) {
                    lista.setItemChecked(posicionAct, false);
                    if(posicionAct<canciones.size())
                        posicionAct++;
                    else
                        posicionAct=0;
                    reproducirCancion(canciones.get((posicionAct) % canciones.size()));
                    lista.setItemChecked(posicionAct, true);
                }
            }
        });
        try {
            reproductor.setDataSource(url);
            reproductor.prepareAsync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getCanciones() {
        canciones.add("http://p4.music.126.net/HSWArRVrxF7vdTKBzfiYPw==/1364493933168399.mp3");
        canciones.add("http://p4.music.126.net/akOyb6Hba9sbxUJzxZCB_w==/6037418348295773.mp3");
        canciones.add("http://p4.music.126.net/e-KPRebMj_DCbY77vm5Psg==/3231464674991313.mp3");
        canciones.add("http://p4.music.126.net/wcq_BZvH03Lg8vGUwg5miA==/3368903627722497.mp3");
    }
}
