package com.floatingblocks.view.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.floatingblocks.FloatingBlocks;

/**
 * Saves all the important attributes to draw Sandy in the screen
 * and to give the impression that it is blinking
 */
public class SandyView extends EntityView {
    public static final float SANDY_ANIMATION_SPEED = 0.08f;
    public static final int SANDY_WIDTH = 72;
    public static final int SANDY_HEIGHT = 73;

    private static float time = 0;
    private Animation<TextureRegion> rolls;

    /**
     * Constructs a coin view. Splits the texture into different TextureRegion
     * to create the animation And creates de sprite on the constructor
     *
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     */
    public SandyView(FloatingBlocks game) {
        super(game);
        Texture text = game.getAssetManager().get("sandy_animation.png");
        TextureRegion[][] frameSplitting = TextureRegion.split(text, SANDY_WIDTH, SANDY_HEIGHT);

        rolls = new Animation<TextureRegion> (SANDY_ANIMATION_SPEED, frameSplitting[0]);

        sprite = new Sprite (rolls.getKeyFrame(time, true));
        sprite.scale(2);

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

    /**
     * Creates a sprite representing Sandy
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     * @return the sprite representing Sandy
     */
    @Override
    public Sprite createSprite(FloatingBlocks game) {

        return null;
    }

    public Sprite getSprite() {
        return sprite;
    }

}
