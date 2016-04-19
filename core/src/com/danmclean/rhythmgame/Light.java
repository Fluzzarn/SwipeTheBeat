package com.danmclean.rhythmgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Dan on 4/18/2016.
 */
public class Light  {

    Conductor conductor;
    int beatNumber = 1;
    float bpm = 200;
    float crotchet;

    float lastbeat;
    Sprite lightOn;
    Sprite lightOff;
    Boolean isOn;
    int noteSpeed = 200;
    private float posX,posY;


    private float earliest , latest;
    public Light() {
        crotchet = 60 / bpm;
        lastbeat = 0;


        Texture lightOnText = new Texture(Gdx.files.internal("lightOn.png"));
        Texture lightOffText = new Texture(Gdx.files.internal("lightOff.png"));
        lightOn = new Sprite(lightOnText);
        lightOff = new Sprite(lightOffText);
        isOn = false;
    }


    public Light setConductor(Conductor c)
    {
        conductor = c;
        return this;
    }

    public void Update(float deltaTime)
    {
        if(conductor.getSongPosition() > beatNumber * crotchet)
        {
            //Do things on beat
            isOn = true;
        }
        posX = 0;
        posY = (Gdx.graphics.getHeight() / 2) -  (conductor.getSongPosition() - beatNumber * crotchet) * noteSpeed;
    }


    public void Draw(SpriteBatch spriteBatch)
    {
        lightOff.setPosition(posX,posY);
        lightOn.setPosition(posX,posY);
        spriteBatch.begin();
        if(isOn)
            lightOn.draw(spriteBatch);
        else
            lightOff.draw(spriteBatch);
        spriteBatch.end();
    }

}
