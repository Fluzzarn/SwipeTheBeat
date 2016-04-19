package com.danmclean.rhythmgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class RhythmGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Conductor conductor;
	Light light1;
	Light light2;
	Light light3;
	Light light4;

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		conductor = new Conductor();
		Music song = conductor.playSong("dreamland.mp3");

		MakeLights();

		song.play();
	}

	private void MakeLights() {



		light1 = new Light();
		light1.setConductor(conductor);

		light2 = new Light();
		light2.setConductor(conductor);
		light2.beatNumber = 18;



		light3 = new Light();
		light3.setConductor(conductor);
		light3.beatNumber = 36;


		light4 = new Light();
		light4.setConductor(conductor);
		light4.beatNumber = 64;

	}

	@Override
	public void render () {
		Update();
		Draw();
	}

	private void Draw() {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		light1.Draw(batch);
		light2.Draw(batch);
		light3.Draw(batch);
		light4.Draw(batch);
	}

	private void Update()
	{
		light1.Update(0.16f);
		light2.Update(0.16f);
		light3.Update(0.16f);
		light4.Update(0.16f);
	}
}
