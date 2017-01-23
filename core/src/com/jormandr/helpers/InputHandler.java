package com.jormandr.helpers;

import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.jormandr.gameobjects.MapTile;
import com.jormandr.gameobjects.Market.ResourceType;
import com.jormandr.gameobjects.Plot;
import com.jormandr.gameworld.GameWorld;
import com.jormandr.ui.ButtonType;
import com.jormandr.ui.UIButton;
import com.jormandr.ui.UIButtonBuyRobo;
import com.jormandr.ui.UIButtonClose;
import com.jormandr.ui.UIButtonEndPhase;
import com.jormandr.ui.UIButtonMarket;
import com.jormandr.ui.UIButtonUpgradeRobo;
import com.jormandr.ui.text.rbig.UIButtonAuctionEnd;
import com.jormandr.ui.text.rbig.UIButtonBuyPlot;
import com.jormandr.ui.text.rbig.UIButtonEndGame;
import com.jormandr.ui.text.rbig.UIButtonPlaceRobo;
import com.jormandr.ui.trade.UIButtonBuy;
import com.jormandr.ui.trade.UIButtonSell;

/**
 * InputHandler implements InputProcessor, and also loads and controls user
 * interface buttons
 *
 */
public class InputHandler implements InputProcessor {

	public enum MenuUI {
		PLOT, MARKET, END;
	}

	private static GameWorld myWorld;
	private static MenuUI currentMenu;
	private static MapTile selectedTile;

	private static UIButton closeButton, buyPlotButton, endTurnButton, placeRoboticonButton, upgradeFoodButton,
			upgradeOreButton, upgradeEnergyButton, marketButton, buyRoboticonButton, buyFoodButton, buyOreButton,
			buyEnergyButton, sellFoodButton, sellOreButton, sellEnergyButton, endAuctionButton, GameOverButton;

	private static ArrayList<UIButton> menuButtons;

	/**
	 * initialises the InputHandler with all the buttons and stuff
	 * 
	 * @param myWorld
	 */
	public InputHandler(GameWorld myWorld) {

		currentMenu = MenuUI.PLOT;
		InputHandler.myWorld = myWorld;

		menuButtons = new ArrayList<UIButton>();

		loadGameButtons();
		loadPlotButtons();
		loadMarketButtons();

		menuButtons.add(endTurnButton);
		menuButtons.add(marketButton);

		Gdx.app.log("InputHandler: ", "On");
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if (button == Input.Buttons.LEFT) {
			// log for testing report
			// Gdx.app.log("InputHandler: ", "Left Click");
			if (myWorld.isRunning()) {
				if (CollisionHandler.tileMouseOver()) {
					selectedTile = CollisionHandler.getNearestMapTile();
					// setup the plot menu
					currentMenu = MenuUI.PLOT;
					myWorld.toMenuPlot();
					return true;
				}
			}

			for (int i = 0; i < menuButtons.size(); i += 1) {
				if (menuButtons.get(i).isMouseOver(screenX, screenY)) {
					// log for testing report
					// Gdx.app.log("InputHandler: ", "Button Clicked");
					menuButtons.get(i).isTouchDown();
					return true;
				}
			}

		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if (button == Input.Buttons.LEFT) {
			// log for testing report
			// Gdx.app.log("InputHandler: ", "Left Up");
			for (int i = 0; i < menuButtons.size(); i += 1) {
				menuButtons.get(i).isTouchUp();
			}
		}
		return false;
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * returns the tile that has been left clicked on
	 * 
	 * @returns selectedTile
	 */
	public static MapTile getTile() {
		return selectedTile;
	}

	/**
	 * returns if the menu is PLOT
	 * 
	 * @return if the menu is PLOT
	 */
	public static boolean menuIsPlot() {
		if (currentMenu == MenuUI.PLOT) {
			return true;
		}
		return false;
	}

	/**
	 * returns if the menu is MARKET
	 * 
	 * @return if the menu is MARKET
	 */
	public static boolean menuIsMarket() {
		if (currentMenu == MenuUI.MARKET) {
			return true;
		}
		return false;

	}

	/**
	 * returns if the menu is END
	 * 
	 * @return if the menu is END
	 */
	public static boolean menuIsEnd() {
		if (currentMenu == MenuUI.END) {
			return true;
		}
		return false;
	}

	/**
	 * sets the currentMenu to menu
	 * 
	 * @param menu
	 */
	public static void setMenu(MenuUI menu) {
		currentMenu = menu;
	}

	/**
	 * Initialisation of upgrade buttons
	 */
	private void loadUpgradeButtons() {
		upgradeFoodButton = new UIButtonUpgradeRobo(796, 256, myWorld, ResourceType.FOOD);
		upgradeOreButton = new UIButtonUpgradeRobo(796, 312, myWorld, ResourceType.ORE);
		upgradeEnergyButton = new UIButtonUpgradeRobo(796, 368, myWorld, ResourceType.ENERGY);
	}

	/**
	 * Initialisation of sell buttons
	 */
	private void loadSellButtons() {
		sellFoodButton = new UIButtonSell(139 * 4, 88 * 4, myWorld, ResourceType.FOOD);
		sellOreButton = new UIButtonSell(156 * 4, 88 * 4, myWorld, ResourceType.ORE);
		sellEnergyButton = new UIButtonSell(173 * 4, 88 * 4, myWorld, ResourceType.ENERGY);
	}

	/**
	 * Initialisation of buy buttons
	 */
	private void loadBuyButtons() {
		buyFoodButton = new UIButtonBuy(139 * 4, 67 * 4, myWorld, ResourceType.FOOD);
		buyOreButton = new UIButtonBuy(156 * 4, 67 * 4, myWorld, ResourceType.ORE);
		buyEnergyButton = new UIButtonBuy(173 * 4, 67 * 4, myWorld, ResourceType.ENERGY);
	}

	/**
	 * Initialisation of market buttons
	 */
	private void loadMarketButtons() {
		buyRoboticonButton = new UIButtonBuyRobo(94 * 4, 102 * 4, myWorld);
		endAuctionButton = new UIButtonAuctionEnd(560, 464, myWorld);
		loadSellButtons();
		loadBuyButtons();
	}

	/**
	 * Initialisation of plot buttons
	 */
	private void loadPlotButtons() {
		buyPlotButton = new UIButtonBuyPlot(560, 464, myWorld);
		placeRoboticonButton = new UIButtonPlaceRobo(560, 464, myWorld);
		loadUpgradeButtons();
	}

	/**
	 * Initialisation of miscellaneous game buttons
	 */
	private void loadGameButtons() {
		endTurnButton = new UIButtonEndPhase(189 * 4, 24, myWorld);
		marketButton = new UIButtonMarket(118 * 4, 24, myWorld);
		closeButton = new UIButtonClose(904, 224, ButtonType.CLOSE, myWorld);
		GameOverButton = new UIButtonEndGame(560, 464, myWorld);
	}

	/**
	 * returns the array containing the menu buttons currently in use
	 * @return menuButtons
	 */
	public static ArrayList<UIButton> getMenuButtons() {
		return menuButtons;
	}

	/**
	 * clears the array containing the menu buttons currently in use
	 */
	public static void clearMenuButtons() {
		menuButtons.clear();
	}

	/**
	 * adds the menu buttons that are used in the main game screen
	 * to the array of currently in use buttons
	 */
	public static void LoadGameMenu() {
		menuButtons.add(endTurnButton);
		menuButtons.add(marketButton);
	}

	/**
	 * adds the menu buttons that are used in the plot game screen
	 * during the plot phase
	 * to the array of currently in use buttons
	 */
	public static void LoadPlotPlotMenu() {
		menuButtons.add(closeButton);
		menuButtons.add(marketButton);
		menuButtons.add(buyPlotButton);
		menuButtons.add(endTurnButton);
	}

	/**
	 * adds the menu buttons that are used in the plot game screen
	 * during the buy phase
	 * to the array of currently in use buttons
	 */
	public static void LoadPlotBuyMenu() {
		menuButtons.add(endTurnButton);
		menuButtons.add(marketButton);
		menuButtons.add(closeButton);
	}

	/**
	 * adds the menu buttons that are used in the plot game screen
	 * during the place phase
	 * to the array of currently in use buttons
	 */
	public static void LoadPlotPlaceMenu() {
		menuButtons.add(endTurnButton);
		menuButtons.add(marketButton);
		menuButtons.add(closeButton);
		menuButtons.add(placeRoboticonButton);
		if (((Plot) InputHandler.getTile()).getOwned() == GameWorld.getPlayer(myWorld.getGameState())) {
			menuButtons.add(upgradeFoodButton);
			menuButtons.add(upgradeOreButton);
			menuButtons.add(upgradeEnergyButton);
		}
	}

	/**
	 * adds the menu buttons that are used in the market game screen
	 * during the plot and place phase
	 * to the array of currently in use buttons
	 */
	public static void LoadMarketVoidMenu() {
		menuButtons.add(endTurnButton);
		menuButtons.add(marketButton);
		menuButtons.add(closeButton);
	}

	/**
	 * adds the menu buttons that are used in the market game screen
	 * during the buy phase
	 * to the array of currently in use buttons
	 */
	public static void LoadMarketRoboMenu() {
		menuButtons.add(endTurnButton);
		menuButtons.add(marketButton);
		menuButtons.add(closeButton);
		menuButtons.add(buyRoboticonButton);

	}

	/**
	 * adds the menu buttons that are used in the market game screen
	 * during the auction phase
	 * to the array of currently in use buttons
	 */
	public static void LoadMarketAuctionMenu() {
		menuButtons.add(buyFoodButton);
		menuButtons.add(buyOreButton);
		menuButtons.add(buyEnergyButton);
		menuButtons.add(sellFoodButton);
		menuButtons.add(sellOreButton);
		menuButtons.add(sellEnergyButton);
		menuButtons.add(endAuctionButton);
	}

	/**
	 * adds the menu buttons that are used in the end game screen
	 * to the array of currently in use buttons
	 */
	public static void LoadEndMenu() {
		menuButtons.add(GameOverButton);
	}

}
