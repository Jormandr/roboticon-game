package com.jormandr.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.jormandr.gameworld.GameRenderer;
import com.jormandr.gameworld.GameWorld;
import com.jormandr.helpers.InputHandler;

/**
 * This class is a Screen (see libgdx docs) It is the link between the game
 * renderer and the gameworld
 * <p>
 * The render method is called every frame and updates all game logic
 * 
 */

public class GameScreen implements Screen {

	private GameWorld world;
	private GameRenderer renderer;

	/**
	 * Constructor for GameScreen
	 * <p>
	 * Initialises a world and renderer
	 */
	public GameScreen() {
		world = new GameWorld();
		renderer = new GameRenderer(world);
		Gdx.input.setInputProcessor(new InputHandler(world));

		Gdx.graphics.getDeltaTime();

	}

	@Override
	public void render(float delta) {
		world.update();
		renderer.render();

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
		Gdx.app.log("GameScreen", "dispose called");

	}

}
