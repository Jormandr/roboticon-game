package com.jormandr.gameworld;

import com.badlogic.gdx.Gdx;
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
	
	private int convertToX(MapTile plot) {
		int convertedX = 0;
		int i = plot.getX();
		int j = plot.getY();
		
		convertedX = (i-j)*64;
		
		return convertedX;
	}
	
	private int convertToY(MapTile plot) {
		int convertedY = 0;
		int plotY = plot.getY();
		int i = plot.getX();
		int j = plot.getY();
		
		convertedY = (i+j)*32;
		
		return convertedY;
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
		
		//batcher.draw(AssetLoader.grassRegion, 5, 5, 10, 10);
		MapTile[][] worldMap = myWorld.getMap();
		int arrayX = myWorld.getMapWidth();
		int arrayY = myWorld.getMapHeight();
		
		batcher.enableBlending();
		
		AssetLoader shit = new AssetLoader();
		
		for (int i = 0; i < arrayX ; i++) {
			for (int j = 0; j < arrayY ; j++) {
				batcher.draw(shit.textureMap[worldMap[i][j].getType().ordinal()], 640 - 64 + convertToX(worldMap[i][j]), 190 + convertToY(worldMap[i][j]), 124, -68);
				
				
			}
		}
		
		
		
		
		
		// The bird needs transparency, so we enable that again.
		

		// End SpriteBatch
		batcher.end();

	}
}
