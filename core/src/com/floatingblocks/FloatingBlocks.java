package com.floatingblocks;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.floatingblocks.view.game.GameView;
import com.floatingblocks.view.menu.MainMenu;
import com.floatingblocks.view.menu.OptionsMenu;

import sun.applet.Main;

public class FloatingBlocks extends Game {
	private SpriteBatch batch;
	private AssetManager assetManager;

	private GameView gameView;
	private MainMenu menuView;
	private OptionsMenu optionsView;
	
	@Override
	public void create () {
	    assetManager = new AssetManager();
		batch = new SpriteBatch();

		gameView = new GameView(this);
		menuView = new MainMenu(this);
        optionsView = new OptionsMenu(this);

		setMenuView();
	}

    /**
     * Starts the game
     */

    public void setMenuView() {
		this.setScreen(menuView);
        menuView.setScreen();
    }

    public void setGameView () {
    	super.setScreen(gameView);
    	gameView.set_screen();
	}

	public void setOptionsMenu () {
		super.setScreen(optionsView);
		optionsView.setScreen();
	}

	public void change_sound_features() {
    	gameView.change_sound_features();
	}

	public void exitGame () {

		Gdx.app.exit();
	}

    /**
     * Disposes of all assets.
     */
	@Override
	public void dispose () {
		gameView.dispose();
		menuView.dispose();
		optionsView.dispose();
		batch.dispose();
		assetManager.dispose();
	}

    /**
     * Returns the sprite batch used to improve drawing performance.
     * @return the sprite batch
     */
    public SpriteBatch getBatch() {
		return batch;
    }

    /**
     * Returns the asset manager used to load all textures and sounds.
     * @return the asset manager
     */
    public AssetManager getAssetManager() {
        return assetManager;
    }
}
