package com.jormandr.helpers;

import java.util.ArrayList;

import com.jormandr.gameobjects.MapTile;
import com.jormandr.gameobjects.Plot;
import com.jormandr.gameobjects.Market.ResourceType;
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

public class UIHandler {
	
	private static GameWorld myWorld;
	
	
	private static UIButton closeButton, buyPlotButton, endTurnButton, placeRoboticonButton, upgradeFoodButton,
	upgradeOreButton, upgradeEnergyButton, marketButton, buyRoboticonButton, buyFoodButton, buyOreButton,
	buyEnergyButton, sellFoodButton, sellOreButton, sellEnergyButton, endAuctionButton, GameOverButton;

	private static ArrayList<UIButton> menuButtons;
	
	public UIHandler(){
		
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
		if (((Plot) InputHandler.getTile()).getOwned() == GameWorld.getPlayer(myWorld.getGameState())) {
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

}
