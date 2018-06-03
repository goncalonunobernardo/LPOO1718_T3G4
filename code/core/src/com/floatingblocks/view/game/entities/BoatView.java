package com.floatingblocks.view.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.floatingblocks.FloatingBlocks;

public class BoatView extends EntityView {

    private boolean was_scaled;


    /**
     * Constructs a boat view.
     *
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     */
    public BoatView(FloatingBlocks game) {
        super(game);

        was_scaled = false;
    }

    /**
     * Scales the sprite if the parameter is true
     *
     * @param scaled True if the sprite should be scaled, false otherwise
     */
    public void set_scaled (boolean scaled) {


        if (!was_scaled && scaled) {
            sprite.setScale(1.5f, 1);
        }
        else if (was_scaled && !scaled) {


            sprite.setScale(1f, 1);
        }
        was_scaled = scaled;
    }

    /**
     * Creates a sprite representing this boat.
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     * @return the sprite representing this boat
     */
    @Override
    public Sprite createSprite(FloatingBlocks game) {
        Texture text = game.getAssetManager().get("boat.png");

        return new Sprite(text);
    }



}
