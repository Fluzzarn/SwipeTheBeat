package com.danmclean.rhythmgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;

/**
 * Created by Dan on 4/26/2016.
 *
 * Manages all input for the game
 */
public class InputSystem extends InputAdapter {

    private LightManager lights;
    private Light currentL;
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        for (Light l:lights.getLights())
        {
            if(screenX > l.getPosX() && screenX < l.getRightX())

            {
                if(Gdx.graphics.getHeight() - screenY > l.getPosY() &&Gdx.graphics.getHeight() - screenY < l.getTopY())
                {
                    currentL = l;
                    l.setBeingDragged(true);
                    break;
                }
            }
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        if(currentL != null)
        {
            currentL.setBeingDragged(false);

            currentL = null;
        }

        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {

        if(currentL != null)
        {
            currentL.setTargetX(screenX - currentL.getWidth() / 2);
            currentL.setTargetY(Gdx.graphics.getHeight() -screenY  - currentL.getHeight() / 2);
        }
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }



    public LightManager getLights() {
        return lights;
    }

    public void setLights(LightManager lights) {
        this.lights = lights;
    }

}
