package com.danmclean.rhythmgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer;
import com.badlogic.gdx.math.RandomXS128;

import java.util.Random;

/**
 * Created by Dan on 4/18/2016.
 *
 *
 * This should be a note
 */
public class Light  {

    Conductor conductor;
    float beatNumber = 1;
    float bpm = 200;
    float crotchet;

    float lastbeat;
    Sprite lightOn;
    Sprite lightOff;
    Boolean isOn;
    int noteSpeed = 200;




    private float posX;
    private float posY;


    private float targetX;
    private float targetY;

    public static final int MAX_COLS = 5;

    private int column;

    private Boolean isBeingDragged = false;

    private float earliest , latest;
    private float latestX,latestY,earliestX,earliestY;

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    private Boolean isActive;


    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public Light() {
        init();
    }

    public Light(float BPM)
    {
        isActive = true;
        bpm = BPM;
        latest = 1;
        earliest = 1;

        init();
    }


    private void init() {
        crotchet = 60 / bpm;
        lastbeat = 0;




        Texture lightOnText = TextureHolder.LookupTexture("starNoteOn.png");
        Texture lightOffText = TextureHolder.LookupTexture(("starNote.png"));
        lightOn = new Sprite(lightOnText);
        lightOff = new Sprite(lightOffText);
        isOn = false;
        isActive = true;

        //Latest is relative
        latestY = (Gdx.graphics.getHeight() / 2) - (beatNumber * crotchet * noteSpeed + latest * crotchet * noteSpeed);
        earliestY = (Gdx.graphics.getHeight() / 2) - (beatNumber * crotchet * noteSpeed - earliest * crotchet * noteSpeed);

        posY = (Gdx.graphics.getHeight() / 2) -  beatNumber * crotchet * noteSpeed;
    }





    public Light setConductor(Conductor c)
    {
        conductor = c;
        return this;
    }

    public void Update(float deltaTime)
    {


        if(!isBeingDragged)
        {
            targetX = column * Gdx.graphics.getWidth() / MAX_COLS;
            targetY = (Gdx.graphics.getHeight() / 2) -  (conductor.getSongPosition() - beatNumber * crotchet) * noteSpeed;
            if(conductor.getSongPosition() > beatNumber * crotchet)
            {
                //Do things on beat
                isOn = true;
            }
            if(posY < latestY)
            {
                Die();

            }
        }
            lerpToPos(targetX,targetY,posX,posY,deltaTime);

    }

    private void Die()
    {
        isActive = false;

    }


    public void lerpToPos(float targetX, float targetY, float startX, float startY, float delta)
    {
        delta *= 10;
        posX = (1-delta)*startX + delta*targetX ;
        posY = (1-delta)*startY + delta*targetY ;


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


    public float getPosX() {
        return posX;
    }

    public float getPosY() {
        return posY;
    }

    public float getRightX()
    {
        return posX + lightOff.getWidth();
    }

    public float getTopY()
    {
        return posY + lightOff.getHeight();
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }


    public float getTargetX() {
        return targetX;
    }

    public void setTargetX(float targetX) {
        this.targetX = targetX;
    }

    public float getTargetY() {
        return targetY;
    }

    public void setTargetY(float targetY) {
        this.targetY = targetY;
    }


    public Boolean getBeingDragged() {
        return isBeingDragged;
    }

    public void setBeingDragged(Boolean beingDragged) {
        isBeingDragged = beingDragged;
    }


    public float getWidth()
    {
        return lightOff.getWidth();
    }

    public float getHeight()
    {
        return lightOff.getHeight();
    }

    public float GetLeftMost() {

        return posX - lightOff.getWidth() / 2.0f;
    }
    public float GetRightMost() {

        return posX + lightOff.getWidth() / 2.0f;
    }
    public float GetUpMost() {

        return posY + lightOff.getHeight() / 2.0f;
    }
    public float GetDownMost() {

        return posY - lightOff.getHeight() / 2.0f;
    }
}
