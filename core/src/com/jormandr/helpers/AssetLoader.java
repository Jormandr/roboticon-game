package com.jormandr.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jormandr.gameobjects.TileType;

/**
 * AssetLoader loads assets from assets file in game files
 *
 */
public class AssetLoader {

	public static Texture tilesTexture, roboticonsTexture, uiTexture, backgroundTexture;
	public static TextureRegion grassRegion, badlandsRegion, desertRegion, waterRegion, uiTV, uiBottom, uiTopMid,
			uiStateLightOn, uiStateLightOff;
	public static TextureRegion[] textureMap = new TextureRegion[4];

	/**
	 * Loads files into textures and then splits textures into correct regions
	 * for rendering in-game
	 */
	public static void load() {
		tilesTexture = new Texture(Gdx.files.internal("tilesPage.png"));
		tilesTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		grassRegion = new TextureRegion(tilesTexture, 63, 0, 62, 34);
		grassRegion.flip(false, false);
		badlandsRegion = new TextureRegion(tilesTexture, 0, 34, 62, 34);
		badlandsRegion.flip(false, false);
		desertRegion = new TextureRegion(tilesTexture, 0, 0, 62, 34);
		desertRegion.flip(false, false);
		waterRegion = new TextureRegion(tilesTexture, 63, 34, 62, 34);
		waterRegion.flip(false, false);

		uiTexture = new Texture(Gdx.files.internal("uiPage.png"));
		uiTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		uiBottom = new TextureRegion(uiTexture, 0, 0, 320, 51);
		uiTopMid = new TextureRegion(uiTexture, 1, 53, 80, 81);
		uiTV = new TextureRegion(uiTexture, 83, 53, 124-83, 110-53);
		uiStateLightOn = new TextureRegion(uiTexture, 126, 53, 133-126, 60-53);
		uiStateLightOff = new TextureRegion(uiTexture, 135, 53, 142-135, 60-53);
		
		backgroundTexture = new Texture(Gdx.files.internal("star background.png"));

	}

	/**
	 * Ends access to assets' files
	 */
	public static void dispose() {
		tilesTexture.dispose();
		// roboticonsTexture.dispose(); //not yet used
		uiTexture.dispose();
		backgroundTexture.dispose();
	}

	/**
	 * Constructor for Assetloader initialises textureMap
	 */
	public AssetLoader() {
		textureMap[TileType.GRASS.ordinal()] = grassRegion;
		textureMap[TileType.DESERT.ordinal()] = desertRegion;
		textureMap[TileType.BADLANDS.ordinal()] = badlandsRegion;
		textureMap[TileType.WATER.ordinal()] = waterRegion;
	}

}
