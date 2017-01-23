package com.jormandr.ui.trade;

import com.jormandr.gameobjects.Market;
import com.jormandr.gameobjects.Market.ResourceType;
import com.jormandr.gameworld.GameWorld;
import com.jormandr.players.Player;
import com.jormandr.ui.text.UIButtonTextSBig;

/**
 * Abstract class for trading between a player and the market
 *
 */
public abstract class UIButtonTrade extends UIButtonTextSBig {
	
	// abstract class holds the methods to required to do trading
	protected final int QUANTITY = 10;
	ResourceType resource;

	public UIButtonTrade(float x, float y, GameWorld world, ResourceType sauce) {
		super(x, y, world, "", "");

		resource = sauce;

	}


	/**
	 * returns the possible change in resource in the player's inventory
	 * 
	 * @param amount
	 * @return the possible change in resource in the player's inventory
	 */
	protected int getChangeResource(int amount) {
		Player player = GameWorld.getPlayer(myWorld.getGameState());
		int value;
		switch (resource) {
		case FOOD:
			value = player.getChangeFood(amount);
			break;
		case ORE:
			value = player.getChangeOre(amount);
			break;
		case ENERGY:
			value = player.getChangeEnergy(amount);
			break;
		default:
			value = 0;
		}
		return value;
	}

	/**
	 * returns value at which the market will sell the resource at
	 * 
	 * @return resourceSellValue
	 */
	protected int getResource() {
		int cost;
		switch (resource) {
		case FOOD:
			cost = Market.getFoodSellValue();
			break;
		case ORE:
			cost = Market.getOreSellValue();
			break;
		case ENERGY:
			cost = Market.getEnergySellValue();
			break;
		default:
			cost = 0;
			break;
		}
		return cost;
	}

	/**
	 * returns the amount of resource in the market inventory
	 * 
	 * @param amount
	 * @return the amount of resource in the market inventory
	 */
	protected int getResourceValue() {
		int cost;
		switch (resource) {
		case FOOD:
			cost = Market.getFood();
			break;
		case ORE:
			cost = Market.getOre();
			break;
		case ENERGY:
			cost = Market.getEnergy();
			break;
		default:
			cost = 0;
			break;
		}
		return cost;
	}

	/**
	 * changes the amount of resource in the market inventory by the value
	 * 
	 * @param value
	 */
	protected void changeResourceValue(int value) {
		switch (resource) {
		case FOOD:
			Market.changeFood(value);
			break;
		case ORE:
			Market.changeOre(value);
			break;
		case ENERGY:
			Market.changeEnergy(value);
			break;
		default:
			break;
		}

	}

	/**
	 * changes the amount of resource in the player inventory by the amount
	 * 
	 * @param amount
	 */
	protected void changeResource(int amount) {
		Player player = GameWorld.getPlayer(myWorld.getGameState());
		switch (resource) {
		case FOOD:
			player.changeFood(amount);
			break;
		case ORE:
			player.changeOre(amount);
			break;
		case ENERGY:
			player.changeEnergy(amount);
			break;
		default:
			break;
		}
	}

}
