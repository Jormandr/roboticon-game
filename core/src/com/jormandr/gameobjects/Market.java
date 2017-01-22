package com.jormandr.gameobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jormandr.helpers.AssetLoader;

public class Market {
	
	private int[] value = new int[4];
	private float[] buyValue = new float [3];
	private float[] sellValue = new float [3];
	
	
	
	public Market(int food, int ore, int energy, int roboticons,float foodBuyValue,float oreBuyValue,float energyBuyValue, float roboticonsSellValue){
		
		value = new int[]{food,ore,energy,roboticons};
		buyValue = new float[]{foodBuyValue,oreBuyValue, energyBuyValue};
		sellValue = new float[]{foodBuyValue*1.5f,oreBuyValue*1.5f,energyBuyValue*1.5f, roboticonsSellValue};
		
	}
	
	public void update(){
		value[3] = value[1] % 4;
		value[1] = 0;
	}

	public void setFood(int value){
		this.value[0] = value;
	}
	
	public void setOre(int value){
		this.value[1] = value;
	}
	
	public void setEnergy(int value){
		this.value[2] = value;
	}
	
	public void setRoboticons(int value){
		this.value[3]	 = value;
	}
	
	public int getFood(){
		return value[0];
	}
	
	public int getOre(){
		return value[1];
	}
	
	public int getEnergy(){
		return value[2];
	}
	
	public int getRoboticons(){
		return value[3];
	}
	
	public int getFoodBuyValue(){
		return (int) buyValue[0];
	}
	
	public int getOreBuyValue(){
		return (int) buyValue[1];
	}
	
	public int getEnergyBuyValue(){
		return (int) buyValue[2];
	}
	
	public int getFoodSellValue(){
		return (int) sellValue[0];
	}
	
	public int getOreSellValue(){
		return (int) sellValue[1];
	}
	
	public int getEnergySellValue(){
		return (int) sellValue[2];
	}
	
	public int getRoboticonSellValue(){
		return (int) sellValue[3];
	}
	
	public void draw(SpriteBatch batcher){
		
		AssetLoader.fontX.draw(batcher, "BUY: RESOURCE: SELL", 380, 265);
		AssetLoader.fontX.draw(batcher, getFoodBuyValue() + "    :Food:     " + getFoodSellValue(), 380, 280);
		AssetLoader.fontX.draw(batcher, getOreBuyValue() + "    :Ore:      " + getOreSellValue(), 380, 295);
		AssetLoader.fontX.draw(batcher, getEnergyBuyValue() + "    :Energy:   " + getEnergySellValue(), 380, 310);
		AssetLoader.fontX.draw(batcher, "Roboticons in stock: " + getRoboticons(), 380, 340);		
		AssetLoader.fontX.draw(batcher, "Cost: " + getRoboticonSellValue(), 380, 350);			
	}

	public void changeRoboticons(int difference) {
		value[3] +=difference;
		
	}
	
	
	
	
	
	
	
}
