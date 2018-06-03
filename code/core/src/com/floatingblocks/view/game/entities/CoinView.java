package com.floatingblocks.view.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.floatingblocks.FloatingBlocks;

import static com.floatingblocks.controller.entities.CoinBody.COIN_HEIGHT;
import static com.floatingblocks.controller.entities.CoinBody.COIN_WIDTH;

/**
 * Saves all the important attributes to draw the coin in the screen
 * and to give the impression that the coin is spinning
 */
public class CoinView extends EntityView {

    public static final float COIN_ANIMATION_SPEED = 0.1f;

    private static float time = 0;
    private Animation<TextureRegion> rolls;
    private Music coin;

    /**
     * Constructs a coin view. Splits the texture into different TextureRegion
     * to create the animation And creates de sprite on the constructor
     *
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     */
    public CoinView(FloatingBlocks game) {
        super(game);

        Texture text = game.getAssetManager().get ("coin_animation.png");

        TextureRegion[][] frameSplitting = TextureRegion.split(text, COIN_WIDTH, COIN_HEIGHT);

        rolls = new Animation<TextureRegion> (COIN_ANIMATION_SPEED, frameSplitting[0]);

        sprite = new Sprite (rolls.getKeyFrame(time, true));
    }

    /**
     * Updates the static variable time to know which frame to display
     * according to the time passed
     */
    public static void update () {
        time += Gdx.graphics.getDeltaTime();
    }

    @Override
    public void draw(SpriteBatch batch) {

        sprite.setRegion(rolls.getKeyFrame(time, true));
        sprite.draw(batch);

    }

    public void handle_sound (boolean play_sound) {

        if (play_sound) {
            coin.play();
        }
    }

    /**
     * Creates a sprite representing this coin
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     * @return the sprite representing this coin
     */
    @Override
    public Sprite createSprite(FloatingBlocks game) {

        coin = game.getAssetManager().get("coin.mp3", Music.class);

        return null;
    }


}
