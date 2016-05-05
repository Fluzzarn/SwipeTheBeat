package com.danmclean.rhythmgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Dan on 5/5/2016.
 * A Bucket for dropping notes into
 */
public class Bucket {

    Sprite sprite;

    float posX, posY;

    private float left,right,up,down;
    public Bucket()
    {
        Texture tex = TextureHolder.LookupTexture("starBucket.png");

        sprite = new Sprite(tex);

        posX = Gdx.graphics.getWidth() / 2.0f - sprite.getWidth() / 2.0f;
        left = posX -sprite.getWidth() / 2.0f;
        right = posX +sprite.getWidth() / 2.0f;
        up = posX +sprite.getHeight() / 2.0f;
        down = posX -sprite.getHeight() / 2.0f;
    }


    public void Draw(SpriteBatch spriteBatch)
    {
        sprite.setPosition(posX,posY);
        spriteBatch.begin();
        sprite.draw(spriteBatch);
        spriteBatch.end();
    }

    public boolean CheckForIntersect(Light l)
    {

        boolean returnValue = false;

        float sup = l.GetLeftMost();
        float sdown = l.GetDownMost();
        float sleft = l.GetLeftMost();
        float sright = l.GetRightMost();


        if((sdown <= up && sup >= down) && (sright >= left && sleft <= right))
        {
            returnValue = true;
        }

        return returnValue;
    }
}
