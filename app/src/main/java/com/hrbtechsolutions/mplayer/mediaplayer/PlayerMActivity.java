package com.hrbtechsolutions.mplayer.mediaplayer;


import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.concurrent.TimeUnit;

public class PlayerMActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;

    Button buttonPlay;
    Button buttonStop;
    Button buttonPause;
    TextView SongTitle;
    TextView ArtistTitle;
    TextView AlbumTitle;
    TextView TotalTime;
    TextView CurrentTime;

    long totalTime,currentTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_m);

        Uri uri;
        uri= Uri.parse("android.resource://com.hrbtechsolutions.mplayer.mediaplayer/"+R.raw.aitebaar);

        buttonPlay=(Button)findViewById(R.id.PlayButton);
        buttonStop=(Button)findViewById(R.id.StopButton);
        buttonPause=(Button)findViewById(R.id.PauseButton);
        SongTitle=(TextView)findViewById(R.id.TitleOfSong);
        ArtistTitle=(TextView)findViewById(R.id.ArtistOfSong);
        AlbumTitle=(TextView)findViewById(R.id.AlbumOfSong);
        TotalTime=(TextView)findViewById(R.id.TotalTime) ;
        CurrentTime=(TextView)findViewById(R.id.CurrentTime);

        MediaMetadataRetriever retriever=new MediaMetadataRetriever();
        retriever.setDataSource(getApplicationContext(),uri);
        String name=retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
        ArtistTitle.setText(name);
        name=retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
        SongTitle.setText(name);
        name=retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM);
        AlbumTitle.setText(name);
        mediaPlayer =MediaPlayer.create(getApplicationContext(),R.raw.aitebaar);


        totalTime=mediaPlayer.getDuration();
        TotalTime.setText(String.format("%d:%d",
                TimeUnit.MILLISECONDS.toMinutes( totalTime),
                TimeUnit.MILLISECONDS.toSeconds( totalTime))
        );



        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(!mediaPlayer.isPlaying())
                    mediaPlayer.start();
            }

        });

        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer !=null && mediaPlayer.isPlaying()){
                    mediaPlayer.seekTo(0);
                    mediaPlayer.pause();

            }
        }});

        buttonPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer.isPlaying())
                    mediaPlayer.pause();

            }
        });
    }





}
