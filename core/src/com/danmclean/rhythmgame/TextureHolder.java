package com.danmclean.rhythmgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;

/**
 * Created by Dan on 4/26/2016.
 *
 * Holds all Textures the game is going to use, Somewhat of a flyweight pattern going on here
 */
public class TextureHolder {



    private static HashMap<String, Texture> Textures = new HashMap<String, Texture>();



    public static Texture LookupTexture(String image)
    {

        if(Textures == null)
        {
            Textures = new HashMap<String, Texture>();
        }
        if(Textures.containsKey(image))
        {
            return Textures.get(image);
        }
        else
        {
            Texture t = new Texture(Gdx.files.internal(image));

            Textures.put(image,t);

            return t;
        }

    }
}
