package com.danmclean.rhythmgame;

import java.util.Random;

/**
 * Created by Dan on 4/26/2016.
 *
 * Creates Lights which will eventually turn into notes
 */
public  class LightFactory {


    public static Conductor getConductor() {
        return conductor;
    }

    public static void setConductor(Conductor conductor) {
        LightFactory.conductor = conductor;
    }

    public static float getBPM() {
        return BPM;
    }

    public static void setBPM(float BPM) {
        LightFactory.BPM = BPM;
    }

    private static int MAX_COLS = 5;

    private static Random random = new Random();

    public static Light MakeLight(float beatNumber)
    {

        Light l = new Light(BPM);
        l.beatNumber = beatNumber;
        l.conductor = conductor;
        int column = random.nextInt() %MAX_COLS ;
        l.setColumn(column);
        return l;
    }

    public static Light MakeLight()
    {

        Light l = new Light(BPM);
        l.conductor = conductor;
        int column = random.nextInt() %MAX_COLS ;
        l.setColumn(column);
        return l;
    }

    public static Light MakeLight(float beatNumber, int col)
    {
        Light l = new Light(BPM);
        l.conductor = conductor;
        l.beatNumber = beatNumber;
        l.setColumn(col);
        return l;
    }

    private static Conductor conductor;



    private static float BPM;

}
