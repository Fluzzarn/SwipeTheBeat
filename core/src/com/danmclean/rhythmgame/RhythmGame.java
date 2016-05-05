package com.danmclean.rhythmgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;

import java.lang.annotation.ElementType;

public class RhythmGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Conductor conductor;
	Bucket noteBucket;
	int score;
	LightManager lightManager;
	BitmapFont font;
	@Override
	public void create () {


		InputSystem input = new InputSystem();

		font = new BitmapFont();
		Gdx.input.setInputProcessor(input);
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		conductor = new Conductor();

		noteBucket = new Bucket();
		Music song = conductor.playSong("dreamland.mp3");

		lightManager = new LightManager();
		MakeLights();

		input.setLights(lightManager);
		song.play();
	}

	private void MakeLights()
	{

		XmlReader xmlReader = new XmlReader();
		FileHandle xmlPath = Gdx.files.internal("dreamland.xml");
		String xml = xmlPath.readString();

		XmlReader.Element root = xmlReader.parse(xml);

		float bpm = Float.parseFloat(root.getChildByName("BPM").getAttribute("BPM"));
		LightFactory.setBPM(bpm);
		LightFactory.setConductor(conductor);

		Array<XmlReader.Element> notes = root.getChildByName("notes").getChildrenByName("note");
		for (XmlReader.Element note: notes)
		{
			int col = Integer.parseInt(note.getChildByName("column").getAttribute("col"));
			float beat = Float.parseFloat(note.getChildByName("beat").getAttribute("beat"));

			lightManager.AddLight(LightFactory.MakeLight(beat,col));
		}




	}

	@Override
	public void render () {
		Update();
		Draw();
	}

	private void Draw() {
		Gdx.gl.glClearColor(100/255.0f,149/255.0f,237/255.0f,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		lightManager.Draw(batch);
		noteBucket.Draw(batch);

		batch.begin();
		font.getData().setScale(5.0f,5.0f);
		font.draw(batch,"Score: " + score,50,50);
		batch.end();
	}

	private void Update()
	{

		lightManager.Update(0.16f,this);
	}
}
