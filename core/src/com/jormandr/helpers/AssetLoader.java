package com.jormandr.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jormandr.gameobjects.TileType;

/**
 * AssetLoader loads assets from assets file in game files
 *
 */
public class AssetLoader {

	// loads images from assets file
	public static Texture tilesTexture, uiTexture, backgroundTexture;
	public static TextureRegion grassRegion, badlandsRegion, desertRegion, waterRegion, uiTV, uiBottom, uiTopMid,
			uiStateLightOn, uiStateLightOff, uiTileInfo, uiMenu, uiPlotScreen, uiCloseOff, uiCloseOn, uiButtonRSmallIn,
			uiButtonRSmallOut, uiButtonSSmallIn, uiButtonSSmallOut, uiButtonRBigIn, uiButtonRBigOut, uiButtonSBigIn,
			uiButtonSBigOut, uiTimerBase, uiTimerJuice, uiTimerFrame, roboticon;
	public static TextureRegion[] textureMap = new TextureRegion[4];
	// loads font from assets file
	public static BitmapFont fontX = new BitmapFont(Gdx.files.internal("m5x7.fnt"), Gdx.files.internal("m5x7.png"),
			true);
	public static TextureRegion[] button_textures = new TextureRegion[10];
	public final static float[] button_size = new float[10];

	/**
	 * Loads files into textures and then splits textures into correct regions
	 * for rendering in-game
	 */
	public static void load() {

		// most assets are scaled by a factor of 4 (sometimes 2) when drawn to
		// give that "retro" pixel art style look

		loadTiles();
		loadUI();
		loadMisc();

		// Tile textures linked to enumerator
		textureMap[TileType.GRASS.ordinal()] = grassRegion;
		textureMap[TileType.DESERT.ordinal()] = desertRegion;
		textureMap[TileType.BADLANDS.ordinal()] = badlandsRegion;
		textureMap[TileType.WATER.ordinal()] = waterRegion;

		// UI button setup

		button_textures[0] = AssetLoader.uiCloseOff;
		button_textures[1] = AssetLoader.uiCloseOn;
		button_textures[2] = AssetLoader.uiButtonRSmallIn;
		button_textures[3] = AssetLoader.uiButtonRSmallOut;
		button_textures[4] = AssetLoader.uiButtonSSmallIn;
		button_textures[5] = AssetLoader.uiButtonSSmallOut;
		button_textures[6] = AssetLoader.uiButtonRBigIn;
		button_textures[7] = AssetLoader.uiButtonRBigOut;
		button_textures[8] = AssetLoader.uiButtonSBigIn;
		button_textures[9] = AssetLoader.uiButtonSBigOut;

		// Close button width and height
		button_size[0] = 10.0f;
		button_size[1] = 10.0f;
		// Small Rectangle button width and height
		button_size[2] = 30.0f;
		button_size[3] = 12.0f;
		// Small Square button width and height
		button_size[4] = 13.0f;
		button_size[5] = 12.0f;
		// Big Rectangle button width and height
		button_size[6] = 41.0f;
		button_size[7] = 18.0f;
		// Big Square button width and height
		button_size[8] = 18.0f;
		button_size[9] = 18.0f;

	}

	/**
	 * loads tile textures and sprites
	 */
	private static void loadTiles() {

		tilesTexture = new Texture(Gdx.files.internal("tilesPage.png"));
		tilesTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		grassRegion = new TextureRegion(tilesTexture, 63, 0, 62, 34);
		grassRegion.flip(false, true);
		badlandsRegion = new TextureRegion(tilesTexture, 0, 34, 62, 34);
		badlandsRegion.flip(false, true);
		desertRegion = new TextureRegion(tilesTexture, 0, 0, 62, 34);
		desertRegion.flip(false, true);
		waterRegion = new TextureRegion(tilesTexture, 63, 34, 62, 34);
		waterRegion.flip(false, true);
	}

	/**
	 * loads UI textures and sprites
	 */
	private static void loadUI() {

		// Main Game UI stuff
		
		//some assets not flipped in y-axis (didn't have time to fix this)
		uiTexture = new Texture(Gdx.files.internal("uiPage.png"));
		uiTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		uiBottom = new TextureRegion(uiTexture, 0, 0, 320, 51);
		uiTopMid = new TextureRegion(uiTexture, 0, 53, 83, 81 - 52);
		uiTV = new TextureRegion(uiTexture, 83, 53, 124 - 83, 110 - 53);
		uiStateLightOn = new TextureRegion(uiTexture, 126, 53, 133 - 126, 60 - 53);
		uiStateLightOff = new TextureRegion(uiTexture, 135, 53, 142 - 135, 60 - 53);
		uiTileInfo = new TextureRegion(uiTexture, 126, 61, 148 - 125, 89 - 60);
		uiTimerBase = new TextureRegion(uiTexture, 279, 81, 316 - 278, 88 - 80);
		uiTimerBase.flip(false, true);
		uiTimerJuice = new TextureRegion(uiTexture, 236, 91, 273 - 235, 98 - 90);
		uiTimerJuice.flip(false, true);
		uiTimerFrame = new TextureRegion(uiTexture, 279, 72, 316 - 278, 79 - 71);
		uiTimerFrame.flip(false, true);

		// Menu UI stuff
		uiMenu = new TextureRegion(uiTexture, 1, 111, 160, 196 - 110);
		uiMenu.flip(false, true);
		uiPlotScreen = new TextureRegion(uiTexture, 166, 53, 233 - 165, 100 - 52);
		uiPlotScreen.flip(false, true);
		uiCloseOff = new TextureRegion(uiTexture, 31, 96, 40 - 30, 105 - 95);
		uiCloseOff.flip(false, true);
		uiCloseOn = new TextureRegion(uiTexture, 1, 83, 10, 92 - 82);
		uiCloseOn.flip(false, true);
		uiButtonRSmallIn = new TextureRegion(uiTexture, 13, 83, 42 - 12, 94 - 82);
		uiButtonRSmallIn.flip(false, true);
		uiButtonRSmallOut = new TextureRegion(uiTexture, 45, 83, 74 - 44, 94 - 82);
		uiButtonRSmallOut.flip(false, true);
		uiButtonSSmallIn = new TextureRegion(uiTexture, 1, 96, 13, 107 - 95);
		uiButtonSSmallIn.flip(false, true);
		uiButtonSSmallOut = new TextureRegion(uiTexture, 16, 96, 28 - 15, 107 - 95);
		uiButtonSSmallOut.flip(false, true);
		uiButtonRBigIn = new TextureRegion(uiTexture, 236, 53, 276 - 235, 70 - 52);
		uiButtonRBigIn.flip(false, true);
		uiButtonRBigOut = new TextureRegion(uiTexture, 236, 72, 276 - 235, 89 - 71);
		uiButtonRBigOut.flip(false, true);
		uiButtonSBigIn = new TextureRegion(uiTexture, 299, 53, 316 - 298, 70 - 52);
		uiButtonSBigIn.flip(false, true);
		uiButtonSBigOut = new TextureRegion(uiTexture, 279, 53, 296 - 278, 70 - 52);
		uiButtonSBigOut.flip(false, true);
	}

	/**
	 * loads in miscellaneous textures and sprites
	 */
	private static void loadMisc() {
		// background

		backgroundTexture = new Texture(Gdx.files.internal("star background.png"));

		// roboticon

		roboticon = new TextureRegion(uiTexture, 165, 106, 191 - 164, 129 - 105);
		roboticon.flip(false, true);
	}

	/**
	 * Ends access to assets' files
	 */
	public static void dispose() {
		tilesTexture.dispose();
		uiTexture.dispose();
		backgroundTexture.dispose();
	}
}
