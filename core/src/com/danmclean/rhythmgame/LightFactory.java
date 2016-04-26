package com.danmclean.rhythmgame;

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


    public static Light MakeLight(float beatNumber)
    {

        Light l = new Light(BPM);
        l.beatNumber = beatNumber;
        l.conductor = conductor;
        return l;
    }

    public static Light MakeLight()
    {

        Light l = new Light(BPM);
        l.conductor = conductor;
        return l;
    }

    private static Conductor conductor;



    private static float BPM;

}
