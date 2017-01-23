package com.jormandr.gameworld;

import com.jormandr.config.GameConfig;
import com.jormandr.gameobjects.MapTile;
import com.jormandr.gameobjects.Market;
import com.jormandr.gameobjects.Plot;
import com.jormandr.gameworld.GameWorld.GameState;
import com.jormandr.gameworld.GameWorld.WorldState;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.jormandr.helpers.AssetLoader;
import com.jormandr.helpers.CollisionHandler;
import com.jormandr.helpers.InputHandler;

/**
 * This is the class that deals with all drawing of textures and any other
 * graphical aspects of the game
 *
 */
public class GameRenderer {

	// TODO get rid of magic numbers from the whole file

	private GameWorld myWorld; // are either of these objects necessary?
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
	}

	/**
	 * initialises game objects
	 */
	private void initGameObjects() {
		// Nothing to do here atm
	}

	/**
	 * where all assets (and shapes) are rendered
	 */
	public void render() {

		// Begin SpriteBatch
		batcher.begin();

		batcher.disableBlending();
		// drawing background, does not require transparency
		drawBackground();
		batcher.enableBlending();

		drawMainUI();

		// drawing the map grid
		drawMap(); // draw map after main user interface to allow for mouse-over
					// text related to map;

		// drawing the UI
		drawFrontUI();

		batcher.disableBlending();

		// End SpriteBatch
		batcher.end();

	}

	/**
	 * Front user interface assets that needs to be drawn to screen
	 */
	private void drawFrontUI() {

		// draw menu UI
		if (myWorld.isMenu()) {
			drawMenuUI();
		}

		// draw buttons if they exist, market and end phase always around
		for (int i = 0; i < InputHandler.getMenuButtons().size(); i += 1) {
			InputHandler.getMenuButtons().get(i).draw(batcher);
		}
	}

	/**
	 * All menu user interface assets that need to be drawn to screen
	 */
	private void drawMenuUI() {

		batcher.draw(AssetLoader.uiMenu, 320, 208, 0, 0, 160, 86, 4, 4, 0);

		if (InputHandler.menuIsPlot()) {
			drawPlotUI();
		}
		if (InputHandler.menuIsMarket()) {
			drawMarketUI();
		}
		if (InputHandler.menuIsEnd()) {
			drawEndUI();
		}

	}

	/**
	 * All plot menu user interface assets that need to be drawn to screen
	 */
	private void drawPlotUI() {
		AssetLoader.fontX.draw(batcher, "Plot Manager", 600, 232);

		MapTile tile = InputHandler.getTile();
		Plot plot = (Plot) tile;

		batcher.draw(AssetLoader.uiPlotScreen, 504, 260, 0, 0, 233 - 165, 100 - 52, 4, 4, 0);
		batcher.draw(AssetLoader.textureMap[tile.getType().ordinal()], 129 * 4, 76 * 4, 0, 0, 124, 68, 2, 2, 0);
		AssetLoader.fontX.draw(batcher, tile.getType().toString(), 380, 265);
		AssetLoader.fontX.draw(batcher, "Food: " + plot.getFoodValue(), 380, 280);
		AssetLoader.fontX.draw(batcher, "Ore: " + plot.getOreValue(), 380, 295);
		AssetLoader.fontX.draw(batcher, "Energy: " + plot.getEnergyValue(), 380, 310);
		AssetLoader.fontX.draw(batcher, "Cost: " + plot.getCost(), 380, 325);

		if (plot.hasRoboticon()) {
			batcher.draw(AssetLoader.roboticon, 129 * 4 + 60, 76 * 4, 0, 0, 191 - 164, 24, 4, 4, 0);
		}
	}

	/**
	 * All market user interface assets that need to be drawn to screen
	 */
	private void drawMarketUI() {
		// market has draw method
		Market.draw(batcher);
	}

	/**
	 * All end user interface assets that need to be drawn to screen
	 */
	private void drawEndUI() {
		int winner = 1337;
		if (myWorld.getWinner() != null) {
			winner = myWorld.getWinner().getPlayerNumber();
		}
		AssetLoader.fontX.draw(batcher, "Player " + winner + " wins!", 600, 232);
	}

	/**
	 * Most common user interface assets that need to be drawn to screen
	 */
	private void drawMainUI() {
		float j = 38 * GameWorld.getTimerPercentage();
		batcher.draw(AssetLoader.uiBottom, 0, 720, 0, 0, 320, -51, 4, 4, 0);
		batcher.draw(AssetLoader.uiTopMid, 640 - 160, (82 - 53) * 4, 0, 0, 80, -(81 - 52), 4, 4, 0);
		batcher.draw(AssetLoader.uiTV, 0, 57 * 4, 0, 0, 42, -57, 4, 4, 0);
		batcher.draw(AssetLoader.uiTV, 1280, 57 * 4, 0, 0, -42, -57, 4, 4, 0);
		batcher.draw(AssetLoader.uiTimerBase, 141 * 4, 6 * 4, 0, 0, 38, 8, 4, 4, 0);
		batcher.draw(AssetLoader.uiTimerJuice, 1280 - 141 * 4, 6 * 4, 0, 0, j, 8, -4, 4, 0);
		batcher.draw(AssetLoader.uiTimerFrame, 141 * 4, 6 * 4, 0, 0, 38, 8, 4, 4, 0);

		// draw state lights
		drawStateLightUI();
		// draw player info & scores
		drawScoreUI();
	}

	// not particularly elegant but have to check for each state
	/**
	 * draws lights to indicate game phase
	 */
	private void drawStateLightUI() {
		for (int k = 0; k < 8; k++) {
			batcher.draw(AssetLoader.uiStateLightOff, 640 - 128 + k * 32, 81 + 28, 0, 0, 7, -7, 4, 4, 0);
		}

		switch (myWorld.getGameState()) {
		case HANDLINGP1:
			switch (GameWorld.getPlayer(GameState.HANDLINGP1).getState()) {
			case PLOT:
				batcher.draw(AssetLoader.uiStateLightOn, 640 - 128 + 0 * 32, 81 + 28, 0, 0, 7, -7, 4, 4, 0);
				break;
			case BUY:
				batcher.draw(AssetLoader.uiStateLightOn, 640 - 128 + 1 * 32, 81 + 28, 0, 0, 7, -7, 4, 4, 0);
				break;
			case PLACE:
				batcher.draw(AssetLoader.uiStateLightOn, 640 - 128 + 2 * 32, 81 + 28, 0, 0, 7, -7, 4, 4, 0);
				break;
			case END:
			default:
				break;
			}
			break;
		case HANDLINGP2:
			switch (GameWorld.getPlayer(GameState.HANDLINGP2).getState()) {
			case PLOT:
				batcher.draw(AssetLoader.uiStateLightOn, 640 - 128 + 3 * 32, 81 + 28, 0, 0, 7, -7, 4, 4, 0);
				break;
			case BUY:
				batcher.draw(AssetLoader.uiStateLightOn, 640 - 128 + 4 * 32, 81 + 28, 0, 0, 7, -7, 4, 4, 0);
				break;
			case PLACE:
				batcher.draw(AssetLoader.uiStateLightOn, 640 - 128 + 5 * 32, 81 + 28, 0, 0, 7, -7, 4, 4, 0);
				break;
			case END:
			default:
				break;
			}
			break;
		case PRODUCE:
			batcher.draw(AssetLoader.uiStateLightOn, 640 - 128 + 6 * 32, 81 + 28, 0, 0, 7, -7, 4, 4, 0);
			break;
		case AUCTIONP1:
		case AUCTIONP2:
			batcher.draw(AssetLoader.uiStateLightOn, 640 - 128 + 7 * 32, 81 + 28, 0, 0, 7, -7, 4, 4, 0);
			break;
		case ENDCHECK:
		case END:
		case WAITINGFORP1:
		case WAITINGFORP2:
		case RANDOMEVENT:
		case START:
		default:
			break;
		}
	}

	/**
	 * draws background
	 */
	private void drawBackground() {
		batcher.draw(AssetLoader.backgroundTexture, 0, 0);
	}

	/**
	 * draws the grid of map tiles
	 */
	private void drawMap() {
		
		//checks through all map tiles to draw them in sequence

		MapTile[][] worldMap = GameWorld.getMap();
		int arrayX = GameConfig.getMapWidth();
		int arrayY = GameConfig.getMapHeight();

		for (int i = 0; i < arrayX; i++) {
			for (int j = 0; j < arrayY; j++) {

				MapTile tile = worldMap[i][j];
				float xx = tile.convertToX();
				float yy = tile.convertToY();

				if (CollisionHandler.tileMouseOver() == true && CollisionHandler.getNearestMapTile() == tile) {
					// tiles have their own draw method
					tile.draw(batcher, -6); // mouse-over raising it up slightly by the offsetY value
					
					if (myWorld.getWorldState() == WorldState.RUNNING) {

						// when we can see the grid of tiles draw info about the
						// mouse-over'd tile

						batcher.draw(AssetLoader.uiTileInfo, xx + 64, yy, 0, 0, 20, -28, 4, 4, 0);

						AssetLoader.fontX.draw(batcher, tile.getType().toString(), xx + 76, yy - 100);

						// making sure it's a plot in case it's a landmark etc.
						// (landmark MapTiles not yet implemented)

						if (tile instanceof Plot) {
							AssetLoader.fontX.draw(batcher, "Food: " + ((Plot) tile).getFoodValue(), xx + 76, yy - 80);
							AssetLoader.fontX.draw(batcher, "Ore: " + ((Plot) tile).getOreValue(), xx + 76, yy - 65);
							AssetLoader.fontX.draw(batcher, "Energy: " + ((Plot) tile).getEnergyValue(), xx + 76,
									yy - 50);
							AssetLoader.fontX.draw(batcher, "Cost: " + ((Plot) tile).getCost(), xx + 76, yy - 35);
						}
					}

				} else {
					tile.draw(batcher, 0);
				}
			}

		}
	}

	/**
	 * draws the players' info and scores
	 */
	private void drawScoreUI() {
		// drawing player1 score
		AssetLoader.fontX.draw(batcher, "Score: " + (GameWorld.getPlayer(GameState.HANDLINGP1).getScore()), 100, 108);
		AssetLoader.fontX.draw(batcher, "Food: " + (GameWorld.getPlayer(GameState.HANDLINGP1).getFood()), 100, 118);
		AssetLoader.fontX.draw(batcher, "Ore: " + (GameWorld.getPlayer(GameState.HANDLINGP1).getOre()), 100, 128);
		AssetLoader.fontX.draw(batcher, "Energy: " + (GameWorld.getPlayer(GameState.HANDLINGP1).getEnergy()), 100, 138);
		AssetLoader.fontX.draw(batcher, "Money: " + (GameWorld.getPlayer(GameState.HANDLINGP1).getMoney()), 100, 148);
		AssetLoader.fontX.draw(batcher,
				"Roboticons: " + (GameWorld.getPlayer(GameState.HANDLINGP1).getRoboticonsOwned()), 100, 158);

		// draw player2 score
		int ww = GameConfig.getWidth() - 150;

		AssetLoader.fontX.draw(batcher, "Score: " + (GameWorld.getPlayer(GameState.HANDLINGP2).getScore()), ww, 108);
		AssetLoader.fontX.draw(batcher, "Food: " + (GameWorld.getPlayer(GameState.HANDLINGP2).getFood()), ww, 118);
		AssetLoader.fontX.draw(batcher, "Ore: " + (GameWorld.getPlayer(GameState.HANDLINGP2).getOre()), ww, 128);
		AssetLoader.fontX.draw(batcher, "Energy: " + (GameWorld.getPlayer(GameState.HANDLINGP2).getEnergy()), ww, 138);
		AssetLoader.fontX.draw(batcher, "Money: " + (GameWorld.getPlayer(GameState.HANDLINGP2).getMoney()), ww, 148);
		AssetLoader.fontX.draw(batcher,
				"Roboticons: " + (GameWorld.getPlayer(GameState.HANDLINGP2).getRoboticonsOwned()), ww, 158);
	}
}
