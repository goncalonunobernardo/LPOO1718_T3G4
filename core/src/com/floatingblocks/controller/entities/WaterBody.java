package com.floatingblocks.controller.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.floatingblocks.model.entities.WaterModel;


import static com.floatingblocks.view.game.GameView.PIXEL_TO_METER;
import static com.floatingblocks.view.game.GameView.VIEWPORT_WIDTH;

/**
 * Abstraction of the physics body of the water
 */
public class WaterBody extends EntityBody {

    /** Pixel height of the water */
    public static final int WATER_HEIGHT = 160;


    /**
     * Constructs a water body according to a water model.
     * @param world the physical world this water belongs to.
     * @param model the model representing this water.
     */
    public WaterBody(World world, WaterModel model) {
        super(world, model, BodyDef.BodyType.StaticBody);

        float density = 0.5f, friction = 0.4f, restitution = 0f;


        createFixture(body, VIEWPORT_WIDTH / 2f, WATER_HEIGHT * PIXEL_TO_METER / 2, density, friction, restitution);


        body.setGravityScale(0);

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

       body.createFixture(fixtureDef);

        rectangle.dispose();
    }


}
