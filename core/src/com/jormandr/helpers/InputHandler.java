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
import com.jormandr.ui.text.rbig.UIButtonAuctionEnd;
import com.jormandr.ui.text.rbig.UIButtonBuyPlot;
import com.jormandr.ui.text.rbig.UIButtonEndGame;
import com.jormandr.ui.text.rbig.UIButtonPlaceRobo;
import com.jormandr.ui.trade.UIButtonBuy;
import com.jormandr.ui.trade.UIButtonSell;
import com.jormandr.ui.upgrade.UIButtonUpgradeRoboEnergy;
import com.jormandr.ui.upgrade.UIButtonUpgradeRoboFood;
import com.jormandr.ui.upgrade.UIButtonUpgradeRoboOre;

public class InputHandler implements InputProcessor {

	public enum MenuUI {
		PLOT, MARKET, END;
	}

	private static ArrayList<UIButton> menuButtons;
	private static GameWorld myWorld;
	private static MenuUI currentMenu;

	private static UIButton closeButton, buyPlotButton, endTurnButton, placeRoboticonButton, upgradeFoodButton,
			upgradeOreButton, upgradeEnergyButton, marketButton, buyRoboticonButton, buyFoodButton, buyOreButton,
			buyEnergyButton, sellFoodButton, sellOreButton, sellEnergyButton, endAuctionButton, GameOverButton;
	private static MapTile selectedTile;

	public InputHandler(GameWorld myWorld) {

		currentMenu = MenuUI.PLOT;
		InputHandler.myWorld = myWorld;

		menuButtons = new ArrayList<UIButton>();
		closeButton = new UIButtonClose(904, 224, ButtonType.CLOSE, myWorld);

		buyPlotButton = new UIButtonBuyPlot(560, 464, myWorld);
		endTurnButton = new UIButtonEndPhase(189 * 4, 24, myWorld);

		placeRoboticonButton = new UIButtonPlaceRobo(560, 464, myWorld);

		upgradeFoodButton = new UIButtonUpgradeRoboFood(796, 256, ButtonType.RSMALL, myWorld);
		upgradeOreButton = new UIButtonUpgradeRoboOre(796, 312, ButtonType.RSMALL, myWorld);
		upgradeEnergyButton = new UIButtonUpgradeRoboEnergy(796, 368, ButtonType.RSMALL, myWorld);

		marketButton = new UIButtonMarket(118 * 4, 24, myWorld);

		buyRoboticonButton = new UIButtonBuyRobo(94 * 4, 102 * 4, myWorld);

		buyFoodButton = new UIButtonBuy(139 * 4, 67 * 4, myWorld, ResourceType.FOOD);
		buyOreButton = new UIButtonBuy(156 * 4, 67 * 4, myWorld, ResourceType.ORE);
		buyEnergyButton = new UIButtonBuy(173 * 4, 67 * 4, myWorld, ResourceType.ENERGY);

		sellFoodButton = new UIButtonSell(139 * 4, 88 * 4, myWorld, ResourceType.FOOD);
		sellOreButton = new UIButtonSell(156 * 4, 88 * 4, myWorld, ResourceType.ORE);
		sellEnergyButton = new UIButtonSell(173 * 4, 88 * 4, myWorld, ResourceType.ENERGY);

		endAuctionButton = new UIButtonAuctionEnd(560, 464, myWorld);

		GameOverButton = new UIButtonEndGame(560, 464, myWorld);

		menuButtons.add(endTurnButton);
		menuButtons.add(marketButton);

		Gdx.app.log("InputHandler: ", "On");
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if (button == Input.Buttons.LEFT) {
			Gdx.app.log("InputHandler: ", "Left Click");
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
					Gdx.app.log("InputHandler: ", "Button Clicked");
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
			Gdx.app.log("InputHandler: ", "Left Up");
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

	public static ArrayList<UIButton> getMenuButtons() {
		return menuButtons;
	}

	public static void clearMenuButtons() {
		menuButtons.clear();
	}

	public static void LoadGameMenu() {
		menuButtons.add(endTurnButton);
		menuButtons.add(marketButton);
	}

	public static void LoadPlotPlotMenu() {
		menuButtons.add(closeButton);
		menuButtons.add(marketButton);
		menuButtons.add(buyPlotButton);
		menuButtons.add(endTurnButton);
	}

	public static void LoadPlotBuyMenu() {
		menuButtons.add(endTurnButton);
		menuButtons.add(marketButton);
		menuButtons.add(closeButton);
	}

	public static void LoadPlotPlaceMenu() {
		menuButtons.add(endTurnButton);
		menuButtons.add(marketButton);
		menuButtons.add(closeButton);
		menuButtons.add(placeRoboticonButton);
		if (((Plot) getTile()).getOwned() == GameWorld.getPlayer(myWorld.getGameState())) {
			menuButtons.add(upgradeFoodButton);
			menuButtons.add(upgradeOreButton);
			menuButtons.add(upgradeEnergyButton);
		}
	}

	public static void LoadMarketVoidMenu() {
		menuButtons.add(endTurnButton);
		menuButtons.add(marketButton);
		menuButtons.add(closeButton);
	}

	public static void LoadMarketRoboMenu() {
		menuButtons.add(endTurnButton);
		menuButtons.add(marketButton);
		menuButtons.add(closeButton);
		menuButtons.add(buyRoboticonButton);

	}

	public static void LoadMarketAuctionMenu() {
		menuButtons.add(buyFoodButton);
		menuButtons.add(buyOreButton);
		menuButtons.add(buyEnergyButton);
		menuButtons.add(sellFoodButton);
		menuButtons.add(sellOreButton);
		menuButtons.add(sellEnergyButton);
		menuButtons.add(endAuctionButton);
	}

	public static void LoadEndMenu() {
		menuButtons.add(GameOverButton);
	}

	public static MapTile getTile() {
		return selectedTile;
	}

	public static boolean menuIsPlot() {
		if (currentMenu == MenuUI.PLOT) {
			return true;
		}
		return false;
	}

	public static boolean menuIsMarket() {
		if (currentMenu == MenuUI.MARKET) {
			return true;
		}
		return false;

	}

	public static boolean menuIsEnd() {
		if (currentMenu == MenuUI.END) {
			return true;
		}
		return false;
	}

	public static void setMenu(MenuUI menu) {
		currentMenu = menu;
	}

}
