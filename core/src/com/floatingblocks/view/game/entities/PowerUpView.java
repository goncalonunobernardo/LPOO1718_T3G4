package com.floatingblocks.view.game.entities;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.floatingblocks.FloatingBlocks;
import com.floatingblocks.model.entities.EntityModel;

import java.util.HashMap;

public class PowerUpView extends EntityView {

    private final HashMap<EntityModel.ModelType, String> images;
    private EntityModel.ModelType type;

    private Music catched;

    /**
     * Constructs a powerup view.
     *
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     */
    public PowerUpView(FloatingBlocks game, EntityModel.ModelType powerupType) {

        super(game);

        type = powerupType;

        images = new HashMap<EntityModel.ModelType, String>();
        images.put(EntityModel.ModelType.CHEMICAL, "increase_boatSize.png");
        images.put(EntityModel.ModelType.DESTRUCTION, "lose_allBlocks.png");

        sprite = createSprite(game);
    }

    /**
     * Plays the sound if the parameter is true
     *
     * @param play_sound true if the sound is to played, false otherwise
     */
    public void handle_sound (boolean play_sound) {

        if (play_sound) {
            catched.play();
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
        catched = game.getAssetManager().get("chemical_sound.wav");
        if (images == null)
            return null;

        Texture text = game.getAssetManager().get(images.get(type));
        return new Sprite (text);

    }




}
