package com.jormandr.ui.trade;

import com.jormandr.gameobjects.Market;
import com.jormandr.gameobjects.Market.ResourceType;
import com.jormandr.gameworld.GameWorld;
import com.jormandr.players.Player;
import com.jormandr.ui.text.UIButtonTextSBig;

public abstract class UIButtonTrade extends UIButtonTextSBig {

	ResourceType resource;

	public UIButtonTrade(float x, float y, GameWorld world, ResourceType sauce) {
		super(x, y, world, "", "");

		resource = sauce;

	}

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
