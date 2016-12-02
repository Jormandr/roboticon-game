package com.jormandr.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.jormandr.config.GameConfig; // Will probably be needed at some point
import com.jormandr.gameworld.GameRenderer;
import com.jormandr.gameworld.GameWorld;

public class GameScreen implements Screen {

	// private int gameWidth = GameConfig.getWidth();
	// private int gameHeight = GameConfig.getHeight();

	private GameWorld world;
	private GameRenderer renderer;
	private float runTime;

	public GameScreen() {
		world = new GameWorld();
		renderer = new GameRenderer(world);

	}

	@Override
	public void render(float delta) {

		runTime += delta;
		world.update(delta);
		renderer.render(runTime);
	}

	@Override
	public void resize(int width, int height) {
		Gdx.app.log("GameScreen", "resizing");
	}

	@Override
	public void show() {
		Gdx.app.log("GameScreen", "show called");
	}

	@Override
	public void hide() {
		Gdx.app.log("GameScreen", "hide called");
	}

	@Override
	public void pause() {
		Gdx.app.log("GameScreen", "pause called");
	}

	@Override
	public void resume() {
		Gdx.app.log("GameScreen", "resume called");
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
