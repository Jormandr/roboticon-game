package com.jormandr.gameobjects;

import com.jormandr.config.GameConfig;
import com.badlogic.gdx.math.Vector2;

public class UIElement {

	float[] buyPlot = new float[8];
	float[] orePlot = new float[8];
	float[] foodPlot = new float[8];
	float[] energyPlot = new float[8];
	float[] roboPlot = new float[8];
	float[] close = new float[8];
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
		
		
		//done
		float ww = (float)GameConfig.getWidth();
		float hh = (float)GameConfig.getHeight();
		close= new float[]{ww*36/48,hh*12/48,ww*36/48,hh*14/48,ww*38/48,hh*14/48,ww*38/48,hh*12/48};
		
		buyPlot=new float[]{ww*19/48,hh*32/48,ww*19/48,hh*34/48,ww*27/48,hh*34/48,ww*27/48,hh*32/48};
		orePlot= new float[]{ww*32/48,hh*17/48,ww*32/48,hh*19/48,ww*36/48,hh*19/48,ww*36/48,hh*17/48};
		foodPlot= new float[]{ww*32/48,hh*21/48,ww*32/48,hh*23/48,ww*36/48,hh*23/48,ww*36/48,hh*21/48};
		energyPlot= new float[]{ww*32/48,hh*25/48,ww*32/48,hh*27/48,ww*36/48,hh*27/48,ww*36/48,hh*25/48};
		roboPlot=new float[]{ww*32/48,hh*29/48,ww*32/48,hh*34/48,ww*36/48,hh*34/48,ww*36/48,hh*29/48}; 
		
		foodMarket = new float[]{ww*17/48,hh*17/48,ww*17/48,hh*21/48,ww*21/48,hh*21/48,ww*21/48,hh*17/48};
		oreMarket = new float[]{ww*21/48,hh*17/48,ww*21/48,hh*21/48,ww*25/48,hh*21/48,ww*25/48,hh*17/48};
		energyMarket = new float[]{ww*25/48,hh*17/48,ww*25/48,hh*21/48,ww*29/48,hh*21/48,ww*29/48,hh*17/48};
		roboMarket = new float[]{ww*29/48,hh*17/48,ww*29/48,hh*21/48,ww*33/48,hh*21/48,ww*33/48,hh*17/48};
		smallMarket = new float[]{ww*19/48,hh*23/48,ww*19/48,hh*27/48,ww*23/48,hh*27/48,ww*23/48,hh*23/48};
		mediumMarket = new float[]{ww*23/48,hh*23/48,ww*23/48,hh*27/48,ww*27/48,hh*27/48,ww*27/48,hh*23/48};
		largeMarket = new float[]{ww*27/48,hh*23/48,ww*27/48,hh*27/48,ww*31/48,hh*27/48,ww*31/48,hh*23/48};
		buyMarket = new float[]{ww*21/48,hh*29/48,ww*21/48,hh*33/48,ww*25/48,hh*33/48,ww*25/48,hh*29/48};
		sellMarket = new float[]{ww*25/48,hh*29/48,ww*25/48,hh*33/48,ww*29/48,hh*33/48,ww*29/48,hh*29/48};

	}
}
