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
import com.jormandr.helpers.GameStateHandler;

/**
 * This is the class that deals with all rendering of sprites and any other
 * graphical aspects of the game
 *
 */
public class GameRenderer {

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

		AssetLoader shit = new AssetLoader(); // TODO remember why we called
												// this 'shit' then fix it
		
		//drawing background
		batcher.draw(AssetLoader.backgroundTexture, 0, 0);

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

		
		//drawing the UI
		batcher.draw(AssetLoader.uiBottom, 0, 720, 0, 0, 320, -51, 4, 4, 0);
		batcher.draw(AssetLoader.uiTopMid, 640-160, 81*4, 0, 0, 80, -81, 4, 4, 0);
		batcher.draw(AssetLoader.uiTV, 0, 57*4, 0, 0, 42, -57, 4, 4, 0);
		batcher.draw(AssetLoader.uiTV,1280, 57*4, 0, 0, -42, -57, 4, 4, 0);	
		
		for(int k = 0; k < 8; k++){
			if ( myWorld.getGameState() == k ){
				batcher.draw(AssetLoader.uiStateLightOn, 640-128+k*32, 81+28, 0, 0, 7, -7, 4, 4, 0);
			}
			else {batcher.draw(AssetLoader.uiStateLightOff, 640-128+k*32, 81+28, 0, 0, 7, -7, 4, 4, 0);
			}
		}
		
		

		batcher.disableBlending();

		// End SpriteBatch
		batcher.end();

	}
}
