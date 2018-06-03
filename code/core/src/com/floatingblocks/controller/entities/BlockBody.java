package com.floatingblocks.controller.entities;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.floatingblocks.model.entities.BlockModel;
import com.floatingblocks.model.entities.EntityModel.ModelType;

import java.util.HashMap;

import static com.floatingblocks.view.game.GameView.PIXEL_TO_METER;

/**
 * Abstraction of the physics body of a block
 */
public class BlockBody extends EntityBody {

    /** Saves the pixel width of all blocks */
    private final HashMap < ModelType, Integer > width;

    /** Saves the pixel height of all blocks */
    private final HashMap < ModelType, Integer> height;

    /** Maximum width of all blocks */
    public static final int BLOCK_WIDTH = 280;

    /** Maximum height of all blocks */
    public static final int BLOCK_HEIGHT = 105;


    /**
     * Constructs a block body according to a block model.
     * @param world the physical world this block belongs to.
     * @param model the model representing this block.
     */
    public BlockBody(World world, BlockModel model) {
        super(world, model, BodyDef.BodyType.DynamicBody);

        width = new HashMap<ModelType, Integer>();

        width.put(ModelType.YELLOWB, 214);
        width.put(ModelType.PURPLEB, 280);
        width.put(ModelType.GREYB, 212);

        height = new HashMap<ModelType, Integer>();

        height.put(ModelType.YELLOWB, 105);
        height.put(ModelType.PURPLEB, 55);
        height.put(ModelType.GREYB, 237);

        float density = 0.2f, friction = 1f, restitution = 0f;
        float width =  this.width.get(model.getType()) * PIXEL_TO_METER / 2;
        float height = this.height.get(model.getType()) * PIXEL_TO_METER / 2;

        // Body
        createFixture(body, width, height, density, friction, restitution);

        body.setGravityScale(0);
    }
}