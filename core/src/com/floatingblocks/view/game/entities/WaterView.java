package com.floatingblocks.view.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.floatingblocks.FloatingBlocks;

import static com.floatingblocks.controller.entities.WaterBody.WATER_HEIGHT;
import static com.floatingblocks.view.game.GameView.PIXEL_TO_METER;
import static com.floatingblocks.view.game.GameView.VIEWPORT_WIDTH;

public class WaterView extends EntityView {

    Music water_splash;

    /**
     * Constructs a water view.
     *
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     */
    public WaterView(FloatingBlocks game) {
        super(game);


        water_splash = game.getAssetManager().get("water_splash.mp3", Music.class);

        sprite.setAlpha(0.5f);
    }

    public void draw(SpriteBatch batch) {
        sprite.draw(batch);

    }

    public void handle_sound (boolean play_sound) {

        if (play_sound) {
            water_splash.play();
        }
    }

    /**
     * Creates a sprite representing this water
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     * @return the sprite representing this water
     */
    @Override
    public Sprite createSprite(FloatingBlocks game) {
        Texture texture = game.getAssetManager().get("water.png");
        texture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        return new Sprite (texture, 0, 0, (int)(VIEWPORT_WIDTH /  PIXEL_TO_METER), WATER_HEIGHT);
    }
}
