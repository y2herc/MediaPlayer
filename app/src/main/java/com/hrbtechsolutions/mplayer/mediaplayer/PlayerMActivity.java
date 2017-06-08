package com.hrbtechsolutions.mplayer.mediaplayer;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class PlayerMActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_m);

        Button buttonPlay;
        Button buttonStop;
        Button buttonPause;
        final MediaPlayer[] mediaPlayer = new MediaPlayer[1];


        buttonPlay=(Button)findViewById(R.id.PlayButton);
        buttonStop=(Button)findViewById(R.id.StopButton);
        buttonPause=(Button)findViewById(R.id.PauseButton);

        mediaPlayer[0] =MediaPlayer.create(getApplicationContext(),R.raw.aitebaar);

        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if(!mediaPlayer[0].isPlaying())
                mediaPlayer[0].start();

            }

        });

        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer[0] !=null && mediaPlayer[0].isPlaying()){
                    mediaPlayer[0].reset();
                }
            }
        });

        buttonPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer[0].isPlaying())
                    mediaPlayer[0].pause();

            }
        });
    }





}
