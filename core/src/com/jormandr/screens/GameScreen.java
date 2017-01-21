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

	// private int gameWidth = GameConfig.getWidth();
	// private int gameHeight = GameConfig.getHeight();

	private GameWorld world;
	private GameRenderer renderer;
	private int debug_0deltaCount = 0;

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.Screen#render(float)
	 */
	@Override
	public void render(float delta) {
		if (delta < 0.001f) {
			// Wait for 10 consecutive 0d, then warn *once*
			if (debug_0deltaCount < 10) {
				debug_0deltaCount += 1;
			} else if (debug_0deltaCount == 10) {
				Gdx.app.log("GameScreen", "Warning: 0 delta");
				debug_0deltaCount += 1;
			}
		} else {
			debug_0deltaCount = 0;
		}
		world.update(delta); // Delta is currently only needed to prevent flooding of debug statements, TODO remove
		renderer.render();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.Screen#resize(int, int)
	 */
	@Override
	public void resize(int width, int height) {
		Gdx.app.log("GameScreen", "resizing");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.Screen#show()
	 */
	@Override
	public void show() {
		Gdx.app.log("GameScreen", "show called");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.Screen#hide()
	 */
	@Override
	public void hide() {
		Gdx.app.log("GameScreen", "hide called");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.Screen#pause()
	 */
	@Override
	public void pause() {
		Gdx.app.log("GameScreen", "pause called");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.Screen#resume()
	 */
	@Override
	public void resume() {
		Gdx.app.log("GameScreen", "resume called");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.Screen#dispose()
	 */
	@Override
	public void dispose() {
		Gdx.app.log("GameScreen", "dispose called");

	}

}
