package com.hrbtechsolutions.mplayer.mediaplayer;

/**
 * Created by Fouzia on 6/12/2017.
 */

public class Song {

    public String songName;
    public String artistName;
    public String dataPath;

    public Song(String song, String artist,String datapath){

        this.songName=song;
        this.artistName=artist;
        this.dataPath=datapath;
    }

}
