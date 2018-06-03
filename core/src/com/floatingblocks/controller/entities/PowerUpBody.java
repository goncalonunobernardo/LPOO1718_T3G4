package com.floatingblocks.controller.entities;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.floatingblocks.model.entities.PowerUpModel;

import static com.floatingblocks.view.game.GameView.PIXEL_TO_METER;

/**
 * Abstraction of the physics body of a powerup
 */
public class PowerUpBody extends EntityBody {
    /** Pixel width of a power up */
    public static final int PU_WIDTH = 103;

    /** Pixel height of a power up */
    public static final int PU_HEIGHT = 103;

    /**
     * Constructs a powerup body according to a p.u. model.
     * @param world the physical world this PU belongs to.
     * @param model the model representing this PU.
     */
    public PowerUpBody(World world, PowerUpModel model) {
        super(world, model, BodyDef.BodyType.DynamicBody);

        float density = 0.1f, friction = 0f, restitution = 0f;
        float width = PU_WIDTH * PIXEL_TO_METER / 2, height = PU_HEIGHT * PIXEL_TO_METER / 2;
        // Body
        createFixture(body, width, height, density, friction, restitution);

        body.setGravityScale(0);
        body.setFixedRotation(true);
    }


}
