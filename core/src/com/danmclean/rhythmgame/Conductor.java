package com.danmclean.rhythmgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

/**
 * Created by Dan on 4/18/2016.
 */
public class Conductor {

    private int crochetsPerBar = 8;
    private float nextBeatTime = 0.0f;
    private float nextBarTime = 0.0f;
    private Music song;


    public float bpm = 200;
    public float crochet;
    public float deltaSongPos;
    public float lastHit; // the last (snapped to beat) time there was a hit
    public float actualLastHit;
    public float offset = 0.2f;
    public float addOffset;
    public static float offsetStatic = 0.4f;
    public static Boolean hasOffsetAdjusted;
    public int beatNumber = 0;
    public  int barNumber = 0;

    public Conductor()
    {
        crochet  = 60 / bpm;
    }


    public Music playSong(String internalPath)
    {
        song = Gdx.audio.newMusic(Gdx.files.internal(internalPath));

        return song;
    }

    public Music getSong()
    {
        if(song != null)
        {

            return song;

        }
        else
        {
            return null;
        }
    }

    public float getSongPosition()
    {
        if(song != null)
        {
            return song.getPosition();
        }
        return 0.0f;
    }


}
