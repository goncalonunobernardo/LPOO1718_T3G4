package com.floatingblocks.controller.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.floatingblocks.model.entities.BoatModel;

import static com.floatingblocks.view.game.GameView.PIXEL_TO_METER;

/**
 * Abstraction of the physics body of a boat.
 * It saves an extra fixture for when the boat is scaled.
 */
public class BoatBody extends EntityBody {

    /** Pixel height of the boat */
    public static final int BOAT_HEIGHT = 180;

    /** Pixel width of the boat */
    public static float BOAT_WIDTH = 577;

    /** Boat fixture that is a sensor */
    private Fixture big_boat_fix;


    /**
     * Constructs a boat body according to a boat model.
     * @param world the physical world this boat belongs to.
     * @param model the model representing this boat.
     */
    public BoatBody(World world, BoatModel model) {
        super(world, model, BodyDef.BodyType.KinematicBody);

        float density = 0.1f, friction = 1f, restitution = 0f;

        // Body
        super.createFixture(body, BOAT_WIDTH * PIXEL_TO_METER / 2, BOAT_HEIGHT * PIXEL_TO_METER / 2, density, friction, restitution);
        createFixture(body, BOAT_WIDTH * PIXEL_TO_METER / 2 * 1.5f, BOAT_HEIGHT * PIXEL_TO_METER / 2, density, friction, restitution);


        body.setGravityScale(0);
        body.setFixedRotation(true);

    }

    /**
     * Helper method to create a polygon fixture represented by a set of vertexes.
     *
     * @param body The body the fixture is to be attached to.
     * @param width The width of the bitmap the vertexes where extracted from.
     * @param height The height of the bitmap the vertexes where extracted from.
     * @param density The density of the fixture. How heavy it is in relation to its area.
     * @param friction The friction of the fixture. How slippery it is.
     * @param restitution The restitution of the fixture. How much it bounces.
     */
    @Override
    protected void createFixture(Body body, float width, float height, float density, float friction, float restitution) {

        PolygonShape rectangle = new PolygonShape();
        rectangle.setAsBox(width, height);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = rectangle;
        fixtureDef.density = density;
        fixtureDef.friction = friction;
        fixtureDef.restitution = restitution;
        fixtureDef.isSensor = true;

        big_boat_fix = body.createFixture(fixtureDef);

        rectangle.dispose();
    }

    /**
     * Use to set the second fixture to a sensor or not, giving the impression of a resized boat
     *
     * @param bigger true if the boat has a bigger size now, false otherwise
     */
    public void scale_boat (boolean bigger) {
            big_boat_fix.setSensor(! bigger);

            if (bigger)
                BOAT_WIDTH = 865.5f;
            else
                BOAT_WIDTH = 577;
    }
}
