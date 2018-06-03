package com.floatingblocks.controller.entities;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.floatingblocks.model.entities.CoinModel;

import static com.floatingblocks.view.game.GameView.PIXEL_TO_METER;

/**
 * Abstraction of the physics body of a coin
 */
public class CoinBody extends EntityBody {
    /** Pixel width of a coin */
    public static final int COIN_WIDTH = 64;

    /** Pixel height of a coin */
    public static final int COIN_HEIGHT = 64;

    /**
     * Constructs a block body according to a coin model.
     * @param world the physical world this coin belongs to.
     * @param model the model representing this coin.
     */
    public CoinBody(World world, CoinModel model) {
        super(world, model, BodyDef.BodyType.DynamicBody);

        float density = 0.1f, friction = 0f, restitution = 0f;
        float width = COIN_WIDTH * PIXEL_TO_METER / 2, height = COIN_HEIGHT * PIXEL_TO_METER / 2;
        // Body
        createFixture(body, width, height, density, friction, restitution);

        body.setGravityScale(0);
        body.setFixedRotation(true);
    }
}
