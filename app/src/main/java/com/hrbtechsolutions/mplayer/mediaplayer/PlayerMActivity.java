package com.hrbtechsolutions.mplayer.mediaplayer;


import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import java.util.concurrent.TimeUnit;
import android.os.Handler;

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
    SeekBar seekBar;
    Handler handler=new Handler();
    long totalTime, currentTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_m);

        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.aitebaar);

        Uri uri;
        uri = Uri.parse("android.resource://com.hrbtechsolutions.mplayer.mediaplayer/" + R.raw.aitebaar);

        buttonPlay = (Button) findViewById(R.id.PlayButton);
        buttonStop = (Button) findViewById(R.id.StopButton);
        buttonPause = (Button) findViewById(R.id.PauseButton);
        SongTitle = (TextView) findViewById(R.id.TitleOfSong);
        ArtistTitle = (TextView) findViewById(R.id.ArtistOfSong);
        AlbumTitle = (TextView) findViewById(R.id.AlbumOfSong);
        TotalTime = (TextView) findViewById(R.id.TotalTime);
        CurrentTime = (TextView) findViewById(R.id.CurrentTime);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        totalTime = mediaPlayer.getDuration();
        seekBar.setMax((int)totalTime);



        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                int i=seekBar.getProgress();
                mediaPlayer.seekTo(i);
                mediaPlayer.start();
            }
        });

        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        retriever.setDataSource(getApplicationContext(), uri);

        ArtistTitle.setText(retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST));
        SongTitle.setText(retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE));
        AlbumTitle.setText(retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM));





        TotalTime.setText(String.format("%d:%d",
                TimeUnit.MILLISECONDS.toMinutes(totalTime),
                TimeUnit.MILLISECONDS.toSeconds(totalTime))
        );


        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (!mediaPlayer.isPlaying()) {
             //       seekBar.setProgress(0);

                    mediaPlayer.start();
                    handler.postDelayed(UpdateSongTime,100);
                }
            }

        });

        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    mediaPlayer.seekTo(0);
                    mediaPlayer.pause();

                }
            }
        });

        buttonPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer.isPlaying())
                    mediaPlayer.pause();

            }
        });
    }


    private Runnable UpdateSongTime = new Runnable() {
        public void run() {
            currentTime = mediaPlayer.getCurrentPosition();
           if(TimeUnit.MILLISECONDS.toSeconds(currentTime)<10&&TimeUnit.MILLISECONDS.toMinutes(currentTime)<1){
            CurrentTime.setText(String.format("%d:0%d",
                    TimeUnit.MILLISECONDS.toMinutes( currentTime),
                    TimeUnit.MILLISECONDS.toSeconds( currentTime) ));
           }
            else
           {
               CurrentTime.setText(String.format("%d:%d",
                   TimeUnit.MILLISECONDS.toMinutes( currentTime),
                   TimeUnit.MILLISECONDS.toSeconds( currentTime) ));
           }
            seekBar.setProgress((int)currentTime);
            handler.postDelayed(this, 100);
        }
    };
}
