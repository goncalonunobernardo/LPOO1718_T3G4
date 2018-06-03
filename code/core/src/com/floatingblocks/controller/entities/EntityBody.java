package com.floatingblocks.controller.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.floatingblocks.model.entities.EntityModel;

/**
 * Abstraction of a physics body
 */
public class EntityBody {
    /** The physics body of this entity */
    final Body body;


    /**
     * Constructs a static body representing a model in a certain world.
     *
     * @param world The world this body lives on.
     * @param model The model representing the body.
     * @param type The type of body to be created: static, dynamic, kinematic
     */
    EntityBody(World world, EntityModel model, BodyDef.BodyType type) {

        BodyDef bodyDef = new BodyDef();

        bodyDef.type = type;

        bodyDef.position.set(model.getX(), model.getY());
        bodyDef.angle = 0;

        body = world.createBody(bodyDef);
        body.setUserData(model);
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
    protected void createFixture(Body body, float width, float height, float density, float friction, float restitution) {

        PolygonShape rectangle = new PolygonShape();
        rectangle.setAsBox(width, height);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = rectangle;
        fixtureDef.density = density;
        fixtureDef.friction = friction;
        fixtureDef.restitution = restitution;

        body.createFixture(fixtureDef);

        rectangle.dispose();
    }


    /**
     * Wraps the getX method from the Box2D body class.
     *
     * @return the x-coordinate of this body.
     */
    public float getX() {
        return body.getPosition().x;
    }

    /**
     * Wraps the getY method from the Box2D body class.
     *
     * @return the y-coordinate of this body.
     */
    public float getY() {
        return body.getPosition().y;
    }


    /**
     * Sets the linear velocity of this bdy
     *
     * @param vx the new x-velocity for this body
     * @param vy the new y-velocity for this body
     */
    public void setLinearVelocity (float vx, float vy) {
        body.setLinearVelocity(vx, vy);
    }

    /**
     * Wraps the setAngularVelocity method from the Box2D body class.
     *
     * @param omega the new angular velocity angle for this body
     */
    public void setAngularVelocity(float omega) {
        body.setAngularVelocity(omega);
    }


    /**
     * Wraps the getUserData method from the Box2D body class.
     *
     * @return the user data
     */
    public Object getUserData() {
        return body.getUserData();
    }

}
