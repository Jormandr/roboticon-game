package com.jormandr.gameworld;

import com.badlogic.gdx.Gdx;
import com.jormandr.config.GameConfig;
import com.jormandr.gameobjects.MapTile;
import com.jormandr.gameobjects.Plot;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.jormandr.helpers.AssetLoader;
import com.jormandr.helpers.CollisionHandler;

/**
 * This is the class that deals with all rendering of sprites and any other
 * graphical aspects of the game
 *
 */
public class GameRenderer {

	// TODO get rid of magic numbers from the whole file

	private GameWorld myWorld; // TODO check if we need this
	private OrthographicCamera cam;
	private ShapeRenderer shapeRenderer;

	private SpriteBatch batcher;

	/**
	 * sets up camera, batcher and shape renderer for further rendering
	 * procedures
	 * 
	 * @param world
	 */
	public GameRenderer(GameWorld world) {
		myWorld = world;
		cam = new OrthographicCamera();
		cam.setToOrtho(true, GameConfig.getWidth(), GameConfig.getHeight());
		batcher = new SpriteBatch();
		// Attach batcher to camera
		batcher.setProjectionMatrix(cam.combined);

		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(cam.combined);

		// Call helper methods to initialise instance variables
		initGameObjects();
		initAssets();
	}

	/**
	 * initialises game objects
	 */
	private void initGameObjects() {
		// yet to have game objects to initialise
	}

	/**
	 * initialises assets
	 */
	private void initAssets() {
		// yet to have any game assets to initialise
	}

	/**
	 * gets the position of the mouse and creates a polygon at that position
	 * 
	 * @param mousePos
	 * @return polygon at mouse position
	 */
	private float[] getMouseVerts(float[] mousePos) {
		// TODO work out the significance of 25
		float[] mouseVerts = new float[8];
		mouseVerts[0] = mousePos[0];
		mouseVerts[1] = mousePos[1];
		mouseVerts[2] = mousePos[0] + 25;
		mouseVerts[3] = mousePos[1];
		mouseVerts[4] = mousePos[0] + 25;
		mouseVerts[5] = mousePos[1] - 25;
		mouseVerts[6] = mousePos[0];
		mouseVerts[7] = mousePos[1] - 25;

		return mouseVerts;
	}

	/**
	 * where all assets and shapes are rendered
	 * 
	 * @param runTime
	 */
	public void render(float runTime) {
		MapTile[][] worldMap = GameWorld.getMap();
		int arrayX = GameConfig.getMapWidth();
		int arrayY = GameConfig.getMapHeight();

		// We will move these outside of the loop for performance laters
		// Fill the entire screen with black, to prevent potential flickering.
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		shapeRenderer.begin(ShapeType.Line);

		// no shapes currently being rendered this is being kept in for
		// completeness of pipeline
		shapeRenderer.end();

		// Begin SpriteBatch
		batcher.begin();

		// Disable transparency
		// This is good for performance when drawing images that do not require
		// transparency.
		batcher.disableBlending();
		// again nothing not transparent but good to have in for later
		batcher.enableBlending();

		// Due to bad programming, this supposedly useless AssetLoader must be
		// initialised
		AssetLoader voodoo = new AssetLoader(); // TODO fix this

		// doesn't work 100% of the time

		// drawing background
		batcher.draw(AssetLoader.backgroundTexture, 0, 0);

		// drawing the map grid
		for (int i = 0; i < arrayX; i++) {
			for (int j = 0; j < arrayY; j++) {

				MapTile tile = worldMap[i][j];
				float xx = tile.convertToX();
				float yy = tile.convertToY();

				if (tile == CollisionHandler.getNearestMapTile() && CollisionHandler.tileMouseOver() == true) {
					batcher.draw(AssetLoader.textureMap[tile.getType().ordinal()], xx, yy + 60, 124, -68);

					if (myWorld.getGameState() == 0) {

						batcher.setColor(1.0f, 0.5f, 0.5f, 1.0f);
						batcher.draw(AssetLoader.uiTileInfo, xx + 64, yy, 0, 0, 20, -28, 4, 4, 0);
						batcher.setColor(1.0f, 1.0f, 1.0f, 1.0f);
						AssetLoader.fontX.draw(batcher, tile.getType().toString(), xx + 76, yy - 100);

						if (tile instanceof Plot) {
							AssetLoader.fontX.draw(batcher, "Food: " + ((Plot) tile).getFoodValue(), xx + 76, yy - 80);
							AssetLoader.fontX.draw(batcher, "Ore: " + ((Plot) tile).getOreValue(), xx + 76, yy - 65);
							AssetLoader.fontX.draw(batcher, "Energy: " + ((Plot) tile).getEnergyValue(), xx + 76,
									yy - 50);
						}
					}

				} else {
					batcher.draw(AssetLoader.textureMap[tile.getType().ordinal()], xx, yy + 64, 124, -68);
				}
			}
		}

		// drawing the UI

		drawUI();

		batcher.disableBlending();

		// End SpriteBatch
		batcher.end();
	}

	private void drawUI() {
		batcher.draw(AssetLoader.uiBottom, 0, 720, 0, 0, 320, -51, 4, 4, 0);
		batcher.draw(AssetLoader.uiTopMid, 640 - 160, 81 * 4, 0, 0, 80, -81, 4, 4, 0);
		batcher.draw(AssetLoader.uiTV, 0, 57 * 4, 0, 0, 42, -57, 4, 4, 0);
		batcher.draw(AssetLoader.uiTV, 1280, 57 * 4, 0, 0, -42, -57, 4, 4, 0);

		for (int k = 0; k < 8; k++) {
			if (myWorld.getGameState() == k) {
				batcher.draw(AssetLoader.uiStateLightOn, 640 - 128 + k * 32, 81 + 28, 0, 0, 7, -7, 4, 4, 0);
			} else {
				batcher.draw(AssetLoader.uiStateLightOff, 640 - 128 + k * 32, 81 + 28, 0, 0, 7, -7, 4, 4, 0);
			}
		}

	}
}
