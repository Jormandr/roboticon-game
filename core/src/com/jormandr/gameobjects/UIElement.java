package com.jormandr.gameobjects;

import com.jormandr.config.GameConfig;
import com.badlogic.gdx.math.Vector2;

public class UIElement {
	// all buttons declared here
	static float[] buyPlot = new float[8];
	static float[] orePlot = new float[8];
	static float[] foodPlot = new float[8];
	static float[] energyPlot = new float[8];
	static float[] roboPlot = new float[8];
	static float[] close = new float[8];
	static float[] foodMarket = new float[8];
	static float[] roboMarket = new float[8];
	static float[] oreMarket = new float[8];
	static float[] energyMarket = new float[8];
	static float[] smallMarket = new float[8];
	static float[] mediumMarket = new float[8];
	static float[] largeMarket = new float[8];
	static float[] buyMarket = new float[8];
	static float[] sellMarket = new float[8];
	static float[] closeMarket = new float[8];

	public UIElement() { // initializes the button polygons using proportions of
							// screen width(see excel files in drive).
		// PLS DO NOT CHANGE. It's a pain to find errors in here
		float ww = (float) GameConfig.getWidth();
		float hh = (float) GameConfig.getHeight();
		close = new float[] { ww * 36 / 48, hh * 12 / 48, ww * 36 / 48, hh * 14 / 48, ww * 38 / 48, hh * 14 / 48,
				ww * 38 / 48, hh * 12 / 48 };

		buyPlot = new float[] { ww * 19 / 48, hh * 32 / 48, ww * 19 / 48, hh * 34 / 48, ww * 27 / 48, hh * 34 / 48,
				ww * 27 / 48, hh * 32 / 48 };
		orePlot = new float[] { ww * 32 / 48, hh * 17 / 48, ww * 32 / 48, hh * 19 / 48, ww * 36 / 48, hh * 19 / 48,
				ww * 36 / 48, hh * 17 / 48 };
		foodPlot = new float[] { ww * 32 / 48, hh * 21 / 48, ww * 32 / 48, hh * 23 / 48, ww * 36 / 48, hh * 23 / 48,
				ww * 36 / 48, hh * 21 / 48 };
		energyPlot = new float[] { ww * 32 / 48, hh * 25 / 48, ww * 32 / 48, hh * 27 / 48, ww * 36 / 48, hh * 27 / 48,
				ww * 36 / 48, hh * 25 / 48 };
		roboPlot = new float[] { ww * 32 / 48, hh * 29 / 48, ww * 32 / 48, hh * 34 / 48, ww * 36 / 48, hh * 34 / 48,
				ww * 36 / 48, hh * 29 / 48 };

		foodMarket = new float[] { ww * 17 / 48, hh * 17 / 48, ww * 17 / 48, hh * 21 / 48, ww * 21 / 48, hh * 21 / 48,
				ww * 21 / 48, hh * 17 / 48 };
		oreMarket = new float[] { ww * 21 / 48, hh * 17 / 48, ww * 21 / 48, hh * 21 / 48, ww * 25 / 48, hh * 21 / 48,
				ww * 25 / 48, hh * 17 / 48 };
		energyMarket = new float[] { ww * 25 / 48, hh * 17 / 48, ww * 25 / 48, hh * 21 / 48, ww * 29 / 48, hh * 21 / 48,
				ww * 29 / 48, hh * 17 / 48 };
		roboMarket = new float[] { ww * 29 / 48, hh * 17 / 48, ww * 29 / 48, hh * 21 / 48, ww * 33 / 48, hh * 21 / 48,
				ww * 33 / 48, hh * 17 / 48 };
		smallMarket = new float[] { ww * 19 / 48, hh * 23 / 48, ww * 19 / 48, hh * 27 / 48, ww * 23 / 48, hh * 27 / 48,
				ww * 23 / 48, hh * 23 / 48 };
		mediumMarket = new float[] { ww * 23 / 48, hh * 23 / 48, ww * 23 / 48, hh * 27 / 48, ww * 27 / 48, hh * 27 / 48,
				ww * 27 / 48, hh * 23 / 48 };
		largeMarket = new float[] { ww * 27 / 48, hh * 23 / 48, ww * 27 / 48, hh * 27 / 48, ww * 31 / 48, hh * 27 / 48,
				ww * 31 / 48, hh * 23 / 48 };
		buyMarket = new float[] { ww * 21 / 48, hh * 29 / 48, ww * 21 / 48, hh * 33 / 48, ww * 25 / 48, hh * 33 / 48,
				ww * 25 / 48, hh * 29 / 48 };
		sellMarket = new float[] { ww * 25 / 48, hh * 29 / 48, ww * 25 / 48, hh * 33 / 48, ww * 29 / 48, hh * 33 / 48,
				ww * 29 / 48, hh * 29 / 48 };

	}

	// gets x,y coordinates and window(menu type), returns button pressed, can
	// change int to enum if necessary.
	// The menu window size is common for all menus coz convenience&aestethics
	public static int isButtonOver(float x, float y, int window) {
		// default no button over press
		int rtn = 0;
		// common close button for all windows
		if ((x >= close[0] && x <= close[4]) && (y >= close[1] && y <= close[3])) {
			rtn = 1;
		}
		// plot menu
		if (window == 0) {
			if (x >= orePlot[0] && x <= orePlot[4]) {
				if (y >= orePlot[1] && y <= orePlot[3]) {
					rtn = 2;
				} else if (y >= foodPlot[1] && y <= foodPlot[3]) {
					rtn = 3;
				} else if (y >= energyPlot[1] && y <= energyPlot[3]) {
					rtn = 4;
				} else if (y >= roboPlot[1] && y <= roboPlot[3]) {
					rtn = 5;
				}
			}
			if ((x >= buyPlot[0] && x <= buyPlot[4]) && (y >= buyPlot[1] && y <= buyPlot[3])) {
				rtn = 6;
			}
		}
		// market menu
		if (window == 1) {
			if (y >= oreMarket[1] && y <= oreMarket[3]) {
				if (x >= oreMarket[0] && x <= oreMarket[4]) {
					rtn = 2;
				} else if (x >= foodMarket[0] && x <= foodMarket[4]) {
					rtn = 3;
				} else if (x >= energyMarket[0] && x <= energyMarket[4]) {
					rtn = 4;
				} else if (x >= roboMarket[0] && x <= roboMarket[4]) {
					rtn = 5;
				}
			} else if (y >= smallMarket[1] && y <= smallMarket[3]) {
				if (x >= smallMarket[0] && x <= smallMarket[4]) {
					rtn = 6;
				} else if (x >= mediumMarket[0] && x <= mediumMarket[4]) {
					rtn = 7;
				} else if (x >= largeMarket[0] && x <= largeMarket[4]) {
					rtn = 8;
				}
			}
			if ((x >= buyMarket[0] && x <= buyMarket[4]) && (y >= buyMarket[1] && y <= buyMarket[3])) {
				rtn = 9;
			} else if ((x >= sellMarket[0] && x <= sellMarket[4]) && (y >= sellMarket[1] && y <= sellMarket[3])) {
				rtn = 10;
			}
		}

		return rtn;
	}
}
