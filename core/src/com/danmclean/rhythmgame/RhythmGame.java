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


	LightManager lightManager;

	@Override
	public void create () {


		InputSystem input = new InputSystem();


		Gdx.input.setInputProcessor(input);
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		conductor = new Conductor();
		Music song = conductor.playSong("dreamland.mp3");

		lightManager = new LightManager();
		MakeLights();

		input.setLights(lightManager);
		song.play();
	}

	private void MakeLights() {

		LightFactory.setBPM(93.39f);
		LightFactory.setConductor(conductor);
		lightManager.AddLight(LightFactory.MakeLight(9));
		lightManager.AddLight(LightFactory.MakeLight(18));
		lightManager.AddLight(LightFactory.MakeLight(27));
		lightManager.AddLight(LightFactory.MakeLight(36));




	}

	@Override
	public void render () {
		Update();
		Draw();
	}

	private void Draw() {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		lightManager.Draw(batch);
	}

	private void Update()
	{
		lightManager.Update(0.16f);
	}
}
