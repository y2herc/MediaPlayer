package com.hrbtechsolutions.mplayer.mediaplayer;

import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ArrayList<Song> arrayofSongs=new ArrayList<Song>();
    private final String track_name = MediaStore.Audio.Media.TITLE;
    private final String artist = MediaStore.Audio.Media.ARTIST;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

/*        arrayofSongs.add(new Song("Hasssan","New"));
        arrayofSongs.add(new Song("Hasssan","New"));
        arrayofSongs.add(new Song("Hasssan","New"));
        arrayofSongs.add(new Song("Hasssan","New"));
        arrayofSongs.add(new Song("Hasssan","New"));        arrayofSongs.add(new Song("Hasssan","New"));
        arrayofSongs.add(new Song("Hasssan","New"));
        arrayofSongs.add(new Song("Hasssan","New"));
        arrayofSongs.add(new Song("Hasssan","New"));
        arrayofSongs.add(new Song("Hasssan","New"));*/



        ContentResolver contentResolver = getContentResolver();
        Uri uri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor cursor = contentResolver.query(uri, null, null, null, null);

        if (cursor == null) {
            // query failed, handle error.
        } else if (!cursor.moveToFirst()) {
            // no media on the device
        } else {
            int titleColumn = cursor.getColumnIndex(android.provider.MediaStore.Audio.Media.TITLE);
            int artistColumn = cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
            do {
                String thisartist = cursor.getString(artistColumn);
                String thisTitle = cursor.getString(titleColumn);

                arrayofSongs.add(new Song(thisTitle,thisartist));

            } while (cursor.moveToNext());
        }

        SongAdapter adapter=new SongAdapter(this,arrayofSongs);

        ListView listView=(ListView)findViewById(R.id.listofsongs);
        listView.setAdapter(adapter);
    }




}
