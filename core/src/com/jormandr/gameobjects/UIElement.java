package com.jormandr.gameobjects;

import com.jormandr.config.GameConfig;
import com.badlogic.gdx.math.Vector2;

public class UIElement {

	float[] buyPlot = new float[8];
	float[] orePlot = new float[8];
	float[] foodPlot = new float[8];
	float[] energyPlot = new float[8];
	float[] roboPlot = new float[8];
	float[] closePlot = new float[8];
	float[] foodMarket = new float[8];
	float[] roboMarket = new float[8];
	float[] oreMarket = new float[8];
	float[] energyMarket = new float[8];
	float[] smallMarket = new float[8];
	float[] mediumMarket = new float[8];
	float[] largeMarket = new float[8];
	float[] buyMarket = new float[8];
	float[] sellMarket = new float[8];
	float[] closeMarket = new float[8];

	public UIElement() {
		
		
		//needs to be redone
		//work in progress
		float ww = (float)GameConfig.getWidth();
		float hh = (float)GameConfig.getHeight();
		closePlot= new float[]{ww*32/48,hh*5/48,ww*32/48,hh*6/48,ww*33/48,hh*6/48,ww*33/48,hh*5/48};
		buyPlot=new float[]{ww*26/48,hh*14/48,ww*26/48,hh*15/48,ww*30/48,hh*15/48,ww*30/48,hh*14/48};
		float[] orePlot= new float[]{ww*32/48,hh*7/48,ww*32/48,hh*8/48,ww*34/48,hh*8/48,ww*34/48,hh*7/48};
		float[] foodPlot= new float[]{ww*32/48,hh*9/48,ww*32/48,hh*10/48,ww*34/48,hh*10/48,ww*34/48,hh*9/48};
		float[] energyPlot= new float[]{ww*32/48,hh*11/48,ww*32/48,hh*12/48,ww*34/48,hh*12/48,ww*34/48,hh*11/48};
		float[] roboPlot=new float[]{ww*32/48,hh*13/48,ww*32/48,hh*15/48,ww*34/48,hh*15/48,ww*34/48,hh*13/48}; 
		
		float[] foodMarket = new float[8];
		float[] roboMarket = new float[8];
		float[] oreMarket = new float[8];
		float[] energyMarket = new float[8];
		float[] smallMarket = new float[8];
		float[] mediumMarket = new float[8];
		float[] largeMarket = new float[8];
		float[] buyMarket = new float[8];
		float[] sellMarket = new float[8];
		float[] closeMarket= new float[8];

	}
}
