package com.jormandr.gameworld;


// import java.util.List // See below

import com.badlogic.gdx.Gdx;
import com.jormandr.config.GameConfig;
import com.jormandr.gameobjects.MapTile;
import com.jormandr.gameobjects.Plot;
import com.jormandr.gameworld.GameWorld.GameState;
import com.jormandr.gameworld.GameWorld.WorldState;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;	
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.jormandr.helpers.AssetLoader;
import com.jormandr.helpers.CollisionHandler;
import com.jormandr.helpers.InputHandler;
import com.jormandr.players.Player.PlayerState;

/**
 * This is the class that deals with all rendering of sprites and any other
 * graphical aspects of the game
 *
 */
public class GameRenderer {

	// TODO get rid of magic numbers from the whole file

	private GameWorld myWorld;
	private OrthographicCamera cam;
	private ShapeRenderer shapeRenderer;
    // private List<UIButton> menuButtons; // Never used?

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
	 * where all assets and shapes are rendered
	 */
	public void render() {
		MapTile[][] worldMap = GameWorld.getMap();
		int arrayX = GameConfig.getMapWidth();
		int arrayY = GameConfig.getMapHeight();

		// We will move these outside of the loop for performance laters
		// Fill the entire screen with black, to prevent potential flickering.
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		shapeRenderer.begin(ShapeType.Line);
		
		shapeRenderer.rect(300, 300, 40, 40);

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
					
					tile.draw(batcher,xx,yy,60);
					if (myWorld.getWorldState() == WorldState.RUNNING) {
						
						batcher.draw(AssetLoader.uiTileInfo, xx + 64, yy, 0, 0, 20, -28, 4, 4, 0);

						AssetLoader.fontX.draw(batcher, tile.getType().toString(), xx + 76, yy - 100);

						if (tile instanceof Plot) {
							AssetLoader.fontX.draw(batcher, "Food: " + ((Plot) tile).getFoodValue(), xx + 76, yy - 80);
							AssetLoader.fontX.draw(batcher, "Ore: " + ((Plot) tile).getOreValue(), xx + 76, yy - 65);
							AssetLoader.fontX.draw(batcher, "Energy: " + ((Plot) tile).getEnergyValue(), xx + 76,
									yy - 50);
							AssetLoader.fontX.draw(batcher, "Cost: " + ((Plot) tile).getCost(),xx + 76,yy - 35);
							AssetLoader.fontX.draw(batcher, "Player: " + GameWorld.getPlayer(myWorld.getGameState()).getPlayerNumber(),xx + 76,yy - 20);
						}
					}

				} else {
					tile.draw(batcher,xx,yy,64);
				}
			}
		}

		// drawing the UI

		drawUI();
		
		if (myWorld.isMenu()) {
			drawMenuUI();
		}
		
		//test ui menu drawing
		//myWorld.getUIButton().draw(batcher);
		
		
		
		//drawing player scores
		
		AssetLoader.fontX.draw(batcher, "Score: " + (GameWorld.getPlayer(GameState.HANDLINGP1).getScore()), 50, 80);
		AssetLoader.fontX.draw(batcher, "Food: " + (GameWorld.getPlayer(GameState.HANDLINGP1).getFood()), 50, 90);
		AssetLoader.fontX.draw(batcher, "Ore: " + (GameWorld.getPlayer(GameState.HANDLINGP1).getOre()), 50, 100);
		AssetLoader.fontX.draw(batcher, "Energy: " + (GameWorld.getPlayer(GameState.HANDLINGP1).getEnergy()), 50, 110);
		AssetLoader.fontX.draw(batcher, "Money: " + (GameWorld.getPlayer(GameState.HANDLINGP1).getMoney()), 50, 120);
		AssetLoader.fontX.draw(batcher, "Roboticons: " + (GameWorld.getPlayer(GameState.HANDLINGP1).getRoboticonsOwned()), 50, 130);

		int ww = GameConfig.getWidth()-100;
		
		AssetLoader.fontX.draw(batcher, "Score: " + (GameWorld.getPlayer(GameState.HANDLINGP2).getScore()), ww, 80);
		AssetLoader.fontX.draw(batcher, "Food: " + (GameWorld.getPlayer(GameState.HANDLINGP2).getFood()), ww, 90);
		AssetLoader.fontX.draw(batcher, "Ore: " + (GameWorld.getPlayer(GameState.HANDLINGP2).getOre()), ww, 100);
		AssetLoader.fontX.draw(batcher, "Energy: " + (GameWorld.getPlayer(GameState.HANDLINGP2).getEnergy()), ww, 110);
		AssetLoader.fontX.draw(batcher, "Money: " + (GameWorld.getPlayer(GameState.HANDLINGP2).getMoney()), ww, 120);
		AssetLoader.fontX.draw(batcher, "Roboticons: " + (GameWorld.getPlayer(GameState.HANDLINGP2).getRoboticonsOwned()), ww, 130);
		
		batcher.disableBlending();

		// End SpriteBatch
		batcher.end();
	}
	
	
	private void drawMenuUI(){
		batcher.draw(AssetLoader.uiMenu, 320, 208, 0, 0, 160, 86, 4, 4, 0);
		//InputHandler.closeButton.draw(batcher);
		
        for (int i = 0; i < InputHandler.getMenuButtons().size(); i+=1) {
    		//Gdx.app.log("Drawing: ", "button");
        	InputHandler.getMenuButtons().get(i).draw(batcher);
        }
        
        MapTile tile = InputHandler.getTile();
        Plot plot = (Plot)tile;
        
        if (GameWorld.getPlayer(myWorld.getGameState()).getState() == PlayerState.PLOT);
		batcher.draw(AssetLoader.uiPlotScreen, 504, 260, 0, 0, 233 -165, 100 - 52, 4, 4, 0);  
		batcher.draw(AssetLoader.textureMap[tile.getType().ordinal()], 516, 304+(68*2), 0, 0,124, -68,2,2,0);
		AssetLoader.fontX.draw(batcher, tile.getType().toString(),380, 265);		
		AssetLoader.fontX.draw(batcher, "Food: " + plot.getFoodValue(),380, 280);
		AssetLoader.fontX.draw(batcher, "Ore: " + plot.getOreValue(),  380,295);
		AssetLoader.fontX.draw(batcher, "Energy: " + plot.getEnergyValue(),380, 310);
		AssetLoader.fontX.draw(batcher, "Cost: " + plot.getCost(),380,325);
	}
	
	private void drawUI() {
		batcher.draw(AssetLoader.uiBottom, 0, 720, 0, 0, 320, -51, 4, 4, 0);
		batcher.draw(AssetLoader.uiTopMid, 640 - 160, (82-53) * 4, 0, 0, 80, -(81-52), 4, 4, 0);
		batcher.draw(AssetLoader.uiTV, 0, 57 * 4, 0, 0, 42, -57, 4, 4, 0);
		batcher.draw(AssetLoader.uiTV, 1280, 57 * 4, 0, 0, -42, -57, 4, 4, 0);
		for (int k = 0; k < 8; k++) {
			if (myWorld.getGameState().ordinal() == k) {
				batcher.draw(AssetLoader.uiStateLightOn, 640 - 128 + k * 32, 81 + 28, 0, 0, 7, -7, 4, 4, 0);
			} else {
				batcher.draw(AssetLoader.uiStateLightOff, 640 - 128 + k * 32, 81 + 28, 0, 0, 7, -7, 4, 4, 0);
			}
		}

	}
}
