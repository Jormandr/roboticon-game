package com.jormandr.gameworld;

import com.badlogic.gdx.Gdx;
import com.jormandr.config.GameConfig;
import com.jormandr.gameobjects.MapTile;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.jormandr.helpers.AssetLoader;
import com.jormandr.helpers.CollisionHandler;

public class GameRenderer {

	private GameWorld myWorld; // TODO check if we need this
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

	private float[] getMouseVerts(float[] mousePos) {
		float[] mouseVerts = new float[8];
		mouseVerts[0] = mousePos[0];
		mouseVerts[1] = mousePos[1];
		mouseVerts[2] = mousePos[0] + 50;
		mouseVerts[3] = mousePos[1];
		mouseVerts[4] = mousePos[0] + 50;
		mouseVerts[5] = mousePos[1] - 50;
		mouseVerts[6] = mousePos[0];
		mouseVerts[7] = mousePos[1] - 50;

		return mouseVerts;
	}

	public void render(float runTime) {
		MapTile[][] worldMap = GameWorld.getMap();
		int arrayX = GameConfig.getMapWidth();
		int arrayY = GameConfig.getMapHeight();

		// We will move these outside of the loop for performance laters
		// Fill the entire screen with black, to prevent potential flickering.
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// float[] test = new float[8];
		shapeRenderer.begin(ShapeType.Line);
		shapeRenderer.setColor(Color.RED);
		// for (int i = 0; i < 5; i++) {
		// for (int j = 0; j < 5; j++) {
		//// Gdx.app.log("vertices check", worldMap[0][0].getVerts()[0]));
		// Polygon dave = new Polygon();
		// dave.equals(test);
		// test = worldMap[i][j].getVerts();
		// shapeRenderer.polygon(test);
		// }
		// }

		shapeRenderer.polygon(getMouseVerts(CollisionHandler.getMousePos()));

		shapeRenderer.end();

		// Begin SpriteBatch
		batcher.begin();

		// Disable transparency
		// This is good for performance when drawing images that do not require
		// transparency.
		batcher.disableBlending();

		// batcher.draw(AssetLoader.grassRegion, 5, 5, 10, 10);

		batcher.enableBlending();
		// Gdx.app.log("vertices check", "before loop");
		// Gdx.app.log("vertices check",
		// Float.toString(worldMap[0][0].getVerts()));

		AssetLoader shit = new AssetLoader(); // TODO remember why we called
												// this 'shit' then fix it

		for (int i = 0; i < arrayX; i++) {
			for (int j = 0; j < arrayY; j++) {
				// TODO work out what the warning on the line below means
				if (worldMap[i][j] == CollisionHandler.getNearestMapTile()
						&& CollisionHandler.tileMouseOver() == true) {
					batcher.draw(shit.textureMap[worldMap[i][j].getType().ordinal()], +worldMap[i][j].convertToX(),
							worldMap[i][j].convertToY() + 60, 124, -68);
				} else {
					batcher.draw(shit.textureMap[worldMap[i][j].getType().ordinal()], +worldMap[i][j].convertToX(),
							worldMap[i][j].convertToY() + 64, 124, -68);
				}
			}
		}

		batcher.draw(AssetLoader.uiBottom, 0, 720, 0, 0, 320, -51, 4, 4, 0);
		batcher.draw(AssetLoader.uiTopMid, 640 - 40, 28, 0, 0, 79, -28, 4, 4, 0);
		// currently not working; needed to go home but we need to change how we
		// draw the UI anyway

		batcher.disableBlending();
		// The bird needs transparency, so we enable that again.

		// End SpriteBatch
		batcher.end();

	}
}
