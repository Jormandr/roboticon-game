package com.jormandr.gameworld;

import com.badlogic.gdx.Gdx;
import com.jormandr.config.GameConfig;
import com.jormandr.gameobjects.MapTile;
import com.jormandr.gameobjects.Plot;
import com.jormandr.gameobjects.TileType;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.jormandr.helpers.AssetLoader;

public class GameRenderer {

	private GameWorld myWorld;
	private OrthographicCamera cam;
	private ShapeRenderer shapeRenderer;

	private SpriteBatch batcher;

	public GameRenderer(GameWorld world) {
		myWorld = world;
		cam = new OrthographicCamera();
		cam.setToOrtho(true, 1280, 720);
		batcher = new SpriteBatch();
		// Attach batcher to camera
		batcher.setProjectionMatrix(cam.combined);

		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(cam.combined);

		// Call helper methods to initialise instance variables
		initGameObjects();
		initAssets();
	}

	private void initGameObjects() {
		// yet to have game objects to initialise
	}

	private void initAssets() {
		// yet to have any game assets to initialise
	}

	public void render(float runTime) {

		// We will move these outside of the loop for performance laters
		// Fill the entire screen with black, to prevent potential flickering.
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// Begin SpriteBatch
		batcher.begin();

		// Disable transparency
		// This is good for performance when drawing images that do not require
		// transparency.
		batcher.disableBlending();

		// batcher.draw(AssetLoader.grassRegion, 5, 5, 10, 10);
		MapTile[][] worldMap = myWorld.getMap();
		int arrayX = GameConfig.getMapWidth();
		int arrayY = GameConfig.getMapHeight();

		batcher.enableBlending();

		AssetLoader shit = new AssetLoader();

		for (int i = 0; i < arrayX; i++) {
			for (int j = 0; j < arrayY; j++) {
				batcher.draw(shit.textureMap[worldMap[i][j].getType().ordinal()],
						640 - 64 + worldMap[i][j].convertToX(), 288 + worldMap[i][j].convertToY(), 124, -68);
			}
		}

		batcher.draw(AssetLoader.uiBottom, 0, 720, 0, 0, 320, -51, 4, 4, 0);
		batcher.draw(AssetLoader.uiTopMid, 640 - 40, 28, 0, 0, 79, -28, 4, 4, 0); // currently
																					// not
																					// working,
																					// needed
																					// to
																					// go
																					// home
																					// but
																					// we
																					// need
																					// to
																					// change
																					// how
																					// we
																					// draw
																					// the
																					// ui
																					// anyway

		batcher.disableBlending();
		// The bird needs transparency, so we enable that again.

		// End SpriteBatch
		batcher.end();

	}
}
