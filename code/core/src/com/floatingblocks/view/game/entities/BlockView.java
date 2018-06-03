package com.floatingblocks.view.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.floatingblocks.FloatingBlocks;
import com.floatingblocks.model.entities.EntityModel.ModelType;

import java.util.HashMap;

public class BlockView extends EntityView {

    private final HashMap <ModelType, String> images;
    private ModelType type;

    /**
     * Constructs a block view.
     *
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     */
    public BlockView(FloatingBlocks game, ModelType blockType) {

        super(game);

        type = blockType;

        images = new HashMap<ModelType, String>();
        images.put(ModelType.YELLOWB, "block.png");
        images.put(ModelType.PURPLEB, "block2.png");
        images.put(ModelType.GREYB, "block3.png");

        sprite = createSprite(game);
    }

    /**
     * Creates a sprite representing this block.
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     * @return the sprite representing this block
     */
    @Override
    public Sprite createSprite(FloatingBlocks game) {

        if (images == null)
            return null;

        Texture text = game.getAssetManager().get(images.get(type));
        return new Sprite (text);
    }


}
