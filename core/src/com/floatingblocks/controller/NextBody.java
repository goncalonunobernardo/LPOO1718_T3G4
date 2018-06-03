package com.floatingblocks.controller;

import com.badlogic.gdx.physics.box2d.World;
import com.floatingblocks.controller.entities.BlockBody;
import com.floatingblocks.controller.entities.CoinBody;
import com.floatingblocks.controller.entities.EntityBody;
import com.floatingblocks.controller.entities.PowerUpBody;
import com.floatingblocks.model.entities.BlockModel;
import com.floatingblocks.model.entities.CoinModel;
import com.floatingblocks.model.entities.EntityModel;
import com.floatingblocks.model.entities.PowerUpModel;


/**
 * Class that creates the body of an entity (coin or block) and computes its velocity
 * The velocity increases according to a linear function
 */
public class NextBody {

    /** The slope of the linear function used to compute the velocity of the entity just created */
    private float a;

    /** The intercept of the linear function used to compute the velocity of the entity just created */
    private float b;

    /** The nr of created entities in the function will be increasing - passing that number the time interval will remain the same */
    private float max_entities;

    /** The nr of entities created so far. Variable used to compute the time until the next entity */
    private float entities_passed;

    /** The slope of the linear function used to compute the angular velocity of the entity just created */
    private float c;


    /**
     * Initializes all the values according to the parameters:
     * the 2 points to compute the function are (0, initial_velocity) and (max_velocity, max_entities)
     *
     * @param init_velocity The velocity of the first entity
     * @param final_velocity The maximum value of the velocity.
     * @param max_entities The nr of created entities in the function will be increasing -
     *                      passing that number the velocity will remain the same

     */
    NextBody(float init_velocity, float final_velocity, float max_entities, float ang_final_velocity) {

        this.a = (init_velocity - final_velocity) / (0 - max_entities);
        this.b = init_velocity;

        this.c = ang_final_velocity /  max_entities;

        this.max_entities = max_entities;


        this.entities_passed = 0;
    }

    /**
     * Computes the velocity for the entity just created.
     * Checks if the max nr of entities was passed
     * @return the computed velocity
     */
    private float get_velocity () {

        if (entities_passed > max_entities) {
            return a * max_entities + b;
        }
        else {
            return a * entities_passed + b;
        }
    }

    private float get_angular_velocity() {
        if (entities_passed > max_entities) {
            return c * max_entities;
        }
        else {
            return c * entities_passed;
        }
    }

    /**
     * Creates a new body and sets its velocity
     * @param world The world this body belongs to
     * @param model The model of this body
     */
    public void add_new_entity (World world, EntityModel model) {

        EntityBody body;

        if (model instanceof BlockModel) {

            body = new BlockBody(world, (BlockModel) model);
            body.setAngularVelocity(get_angular_velocity());
        }
        else if (model instanceof CoinModel) {

            body = new CoinBody(world, (CoinModel) model);
        }
        else if (model instanceof PowerUpModel) {
            body = new PowerUpBody(world, (PowerUpModel) model);
        }
        else
            return;

        body.setLinearVelocity(0, -get_velocity());

        entities_passed++;
    }
}
