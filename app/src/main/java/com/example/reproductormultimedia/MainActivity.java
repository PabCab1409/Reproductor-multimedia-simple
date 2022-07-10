package com.example.reproductormultimedia;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button play, pause, siguiente, anterior, ultima, primera, stop;
    TextView tv;
    MediaPlayer[] reproductor = new MediaPlayer[3];
    String[] canciones = new String[3];
    //para los actionListeners
    View v;
    int posicion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play = (Button) findViewById(R.id.play);
        pause = (Button) findViewById(R.id.pause);
        siguiente = (Button) findViewById(R.id.siguiente);
        anterior = (Button) findViewById(R.id.anterior);
        ultima = (Button) findViewById(R.id.ultima);
        primera = (Button) findViewById(R.id.primera);
        stop = (Button) findViewById(R.id.stop);
        tv = (TextView) findViewById(R.id.cancion);
        v = findViewById(android.R.id.content).getRootView();

        //dejo el reproductor listo para iniciar canciones
        prepararReproductor();

        //para poder relfejar el nombre de la cancion
        canciones[0] = String.valueOf(R.string.beatIt);
        canciones[1] = String.valueOf(R.string.goDancing);
        canciones[2] = String.valueOf(R.string.littleBeauty);


        //actionListeners a botones
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play();
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pause();
            }
        });

        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                siguiente();
            }
        });

        anterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anterior();
            }
        });

        ultima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ultima();
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stop();
            }
        });

        primera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                primera();
            }
        });



    }

    private void prepararReproductor() {
        reproductor[0] = MediaPlayer.create(this, R.raw.primera);
        reproductor[1] = MediaPlayer.create(this, R.raw.segunda);
        reproductor[2] = MediaPlayer.create(this, R.raw.tercera);
        posicion = 0;
    }

    public void play(){
        if(reproductor[posicion].isPlaying()){
            Toast.makeText(getApplicationContext(), R.string.playing, Toast.LENGTH_SHORT).show();
        }else{
            reproductor[posicion].start();
            Toast.makeText(getApplicationContext(), R.string.playOnce, Toast.LENGTH_SHORT).show();
        }
        tv.setText(canciones[posicion]);
    }

    public void pause(){
        if(!reproductor[posicion].isPlaying()){
            Toast.makeText(getApplicationContext(), R.string.pausing, Toast.LENGTH_SHORT).show();
        }else{
            reproductor[posicion].pause();
            Toast.makeText(getApplicationContext(), R.string.pauseOnce, Toast.LENGTH_SHORT).show();
        }
    }

    public void stop(){
        if(reproductor[posicion] != null){
            reproductor[posicion].stop();
            prepararReproductor();
            Toast.makeText(getApplicationContext(), R.string.stop, Toast.LENGTH_SHORT).show();
            reproductor[posicion].release();
        }
    }

    public void anterior(){
        pause();
        posicion -= 1;
        if(posicion <= 0){
            posicion = 0;
        }
        play();
    }

    public void siguiente(){
        pause();
        posicion += 1;
        if(posicion >= reproductor.length){
            posicion = reproductor.length;
        }
        play();
    }

    public void primera(){
        pause();
        posicion = 0;
        play();
    }

    public void ultima(){
        pause();
        posicion = reproductor.length - 1;
        play();
    }
}