package com.floatingblocks.model;

import com.floatingblocks.controller.entities.BlockBody;
import com.floatingblocks.controller.entities.CoinBody;
import com.floatingblocks.controller.entities.PowerUpBody;
import com.floatingblocks.model.entities.BlockModel;
import com.floatingblocks.model.entities.CoinModel;
import com.floatingblocks.model.entities.EntityModel;
import com.floatingblocks.model.entities.PowerUpModel;

import static com.badlogic.gdx.math.MathUtils.random;
import static com.floatingblocks.view.game.GameView.PIXEL_TO_METER;
import static com.floatingblocks.view.game.GameView.VIEWPORT_WIDTH;
import static com.floatingblocks.view.game.GameView.camera_height;

/**
 * Class that randomly decides which entity to create: coin or block
 * and the time that will pass until the next entity is created
 * (the minimum value of the time interval decrements according to a linear function)
 */
public class NextModel {

    /** The slope of the linear function used to compute the time until the next entity is created */
    private float c;

    /** The intercept of the linear function used to compute the time until the next entity is created */
    private float d;

    /** The nr of created entities in the function will be increasing - passing that number the time interval will remain the same */
    private float max_entities;

    /** The time in seconds until the next entity is created */
    private float next_entity;

    /** The nr of entities created so far. Variable used to compute the time until the next entity */
    private float entities_passed;

    /**
     * Initializes all the values according to the parameters:
     * the 2 points to compute the function are (0, initial_next_time) and (max_next_time, final_next_time)
     * The next entity will be created in 0 seconds and 0 entities have been created.
     *
     * @param initial_next_time The initial value until the next entity is created.
     *                          It is the minimal value of the interval in which the time is randomly chosen.
     * @param final_next_time The maximum value of that interval.
     * @param max_entities The nr of created entities in the function will be increasing -
     *                      passing that number the time interval will remain the same

     */
    public NextModel (float initial_next_time, float final_next_time, float max_entities) {

        this.c = (initial_next_time - final_next_time) / (0 - max_entities);
        this.d = initial_next_time;
        this.max_entities = max_entities;

        this.next_entity = 0;

        this.entities_passed = 0;
    }

    /**
     * Decrements the time counter until next entity is created.
     * If its less than 0, computes a new time value.
     *
     * @param delta The number of seconds since last update.
     * @return Yes if a new entity should be created, false otherwise
     */
    public boolean update (float delta) {
        next_entity -= delta;

        if (next_entity <= 0) {
            next_entity = get_time_until_next_entity();
            entities_passed++;
            return true;
        }

        return false;
    }

    /**
     * Creates a new entity by randomly picking between a block and a coin.
     * A random int is used to make sure that the blocks appear 70% of the times and the coins 30%
     *
     * @return a new entity (coin or block)
     */
    public EntityModel create_new_entity () {
        int random_nr = random.nextInt(11);

        if (random_nr > 4) {
            return create_block_model();
        }
        else if(random_nr < 4) {
            return create_coin_model();
        }
        else
            return create_powerup_model();
    }


    /**
     * Creates a new coin model in a random position
     *
     * @return the coin model just created
     */
    private CoinModel create_coin_model () {

        float half_width = CoinBody.COIN_WIDTH / 2 * PIXEL_TO_METER;
        float half_height = CoinBody.COIN_WIDTH * CoinBody.COIN_HEIGHT / 2 * PIXEL_TO_METER;

        return new CoinModel(random.nextFloat() * (VIEWPORT_WIDTH - half_width) + half_width, camera_height + half_height, 0);
    }

    /**
     * Creates a new block model in a random position
     *
     * @return the block model just created
     */
    private BlockModel create_block_model () {

        float half_width = BlockBody.BLOCK_WIDTH / 2 * PIXEL_TO_METER;
        float half_height = BlockBody.BLOCK_HEIGHT / 2 * PIXEL_TO_METER;

        int random_nr = random.nextInt(9);

        if (random_nr <= 1) {
            return new BlockModel(random.nextFloat() * (VIEWPORT_WIDTH - half_width) + half_width, camera_height + half_height, 0, EntityModel.ModelType.GREYB);

        }
        else if (random_nr <= 4) {
            return new BlockModel(random.nextFloat() * (VIEWPORT_WIDTH - half_width) + half_width, camera_height + half_height, 0, EntityModel.ModelType.YELLOWB);
        }

        else {
            return new BlockModel(random.nextFloat() * (VIEWPORT_WIDTH - half_width) + half_width, camera_height + half_height, 0, EntityModel.ModelType.PURPLEB);
        }
    }

    /**
     * Creates a new powerup model in a random position
     *
     * @return the powerup model just created
     */
    private PowerUpModel create_powerup_model () {

        float half_width = PowerUpBody.PU_WIDTH / 2 * PIXEL_TO_METER;
        float half_height = PowerUpBody.PU_WIDTH * PowerUpBody.PU_HEIGHT / 2 * PIXEL_TO_METER;

        int random_nr = random.nextInt(6);

        if (random_nr < 3)
            return new PowerUpModel(random.nextFloat() * (VIEWPORT_WIDTH - half_width) + half_width, camera_height + half_height, 0, EntityModel.ModelType.CHEMICAL);
        else
            return new PowerUpModel(random.nextFloat() * (VIEWPORT_WIDTH - half_width) + half_width, camera_height + half_height, 0, EntityModel.ModelType.DESTRUCTION);
    }

    /**
     * Computes a random time until the next entity is created.
     * Checks if the max nr of entities was passed or not.
     *
     * @return the time
     */
    private float get_time_until_next_entity () {
        if (entities_passed > max_entities) {
            return random.nextFloat() * c * max_entities + d;
        }
        else {
            return random.nextFloat() * c * entities_passed + d;
        }
    }

}
