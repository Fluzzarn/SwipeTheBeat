package com.danmclean.rhythmgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

/**
 * Created by Dan on 4/26/2016.
 *
 *
 * Makes sure all Lights will be updated or moved
 */
public class LightManager {


    public ArrayList<Light> getLights() {
        return Lights;
    }

    private ArrayList<Light> Lights;

    public LightManager() {

        Lights = new ArrayList<Light>();
    }



    public void AddLight(Light l)
    {
        Lights.add(l);
    }


    public void Update(float dt, RhythmGame game)
    {
        for (Light l:Lights)
        {
            l.Update(Gdx.graphics.getDeltaTime());
            if(game.noteBucket.CheckForIntersect(l))
            {
                if(l.isOn)
                {
                    game.score += 500;
                    l.setActive(false);
                }
            }
        }

         CleanUp(game);
    }

    private void CleanUp(RhythmGame game)
    {
        Boolean removed = false;
        for(Light l:Lights)
        {
            if(!l.getActive())
            {

                removed= true;
                Lights.remove(l);
                break;
            }
        }
        if(removed)
            CleanUp(game);
    }

    public void Draw(SpriteBatch batch)
    {
        for(Light l:Lights)
        {
            l.Draw(batch);
        }
    }
}
