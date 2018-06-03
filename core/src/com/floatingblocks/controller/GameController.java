package com.floatingblocks.controller;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.floatingblocks.controller.entities.BlockBody;
import com.floatingblocks.controller.entities.BoatBody;
import com.floatingblocks.controller.entities.CoinBody;
import com.floatingblocks.controller.entities.WaterBody;
import com.floatingblocks.model.GameModel;
import com.floatingblocks.model.entities.BlockModel;
import com.floatingblocks.model.entities.BoatModel;
import com.floatingblocks.model.entities.CoinModel;
import com.floatingblocks.model.entities.EntityModel;
import com.floatingblocks.model.NextModel;

import java.util.ArrayList;
import java.util.List;

import static com.floatingblocks.view.game.GameView.PIXEL_TO_METER;
import static com.floatingblocks.view.game.GameView.VIEWPORT_WIDTH;

/**
 * Abstraction of the physics controller of the game
 */
public class GameController {

    /** The velocity of the boat */
    static final int BOAT_VELOCITY = 8;

    /** The physics world controlled by this controller */
    private final World world;

    /** The boat body */
    private final BoatBody boatBody;

    /** Entities that should be added in the next simulation step */
    private List<EntityModel> entities_toAdd = new ArrayList<EntityModel>();

    /** Auxiliar variable to help create the next body */
    private final NextBody nextBody;

    /** Auxiliar variable to help create the next model */
    private final NextModel nextModel;

    /** Accumulator used to calculate the simulation step */
    private float accumulator;

    /** The singleton instance of this controller */
    private static GameController instance;

    /** true if the boat is scaled, false otherwise */
    private boolean is_boat_scaled;

    /** To count for how long the boat is scaled */
    private float time_boat_scaled;

    /**
     * Creates a new GameController that controls the physics of a certain GameModel.
     */
    private GameController() {
         world = new World(new Vector2(0, -10), true);

        boatBody = new BoatBody(world, GameModel.getInstance().getBoat());

        List<BlockModel> blocks = GameModel.getInstance().getBlock();
        List<CoinModel> coins = GameModel.getInstance().getCoins();

        for (BlockModel block : blocks)
            new BlockBody(world, block);

        for(CoinModel coin : coins)
            new CoinBody(world, coin);

        nextBody = new NextBody(17f, 36f, 50, 15);
        nextModel = new NextModel(3, 0.2f, 15);

        new WaterBody(world, GameModel.getInstance().getWater());

        world.setContactListener(new CollisionsListener());

        time_boat_scaled = 5f;

    }

    /**
     * Returns a singleton instance of a game controller
     *
     * @return the singleton instance
     */
    public static GameController getInstance() {
        if(instance == null)
            instance = new GameController();
        return instance;
    }

    /**
     * Returns the world controlled by this controller. Needed for debugging purposes only.
     *
     * @return The world controlled by this controller.
     */
    public World getWorld() {
        return world;
    }

    /**
     * Calculates the next physics step of duration delta (in seconds).
     *
     * @param delta The size of this physics step in seconds.
     */
    public void update(float delta) {
         check_add_Entity(delta);

         update_boat(delta);

          float frameTime = Math.min(delta, 0.25f);
          accumulator += frameTime;

          while(accumulator >= 1/60f) {
            world.step(1/60f, 6, 2);
            accumulator -= 1/60f;
          }

          GameModel.getInstance().add_score(0.05f);


        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);

        for (Body body : bodies) {
            EntityModel model = (EntityModel) body.getUserData();
            verifyBounds(body);

            model.setPosition(body.getPosition().x, body.getPosition().y);
            model.setRotation(body.getAngle());
        }
    }

    /**
     * Verifies if the body is inside the bounds and if not
     *
     * @param body The body to be verified.
     */
    public void verifyBounds(Body body) {
        EntityModel entity_model = (EntityModel) body.getUserData();
        float half_width = 0;

        if (entity_model instanceof BoatModel) {
            half_width = BoatBody.BOAT_WIDTH * PIXEL_TO_METER/ 2;

        }

        else if (entity_model instanceof BlockModel) {
            half_width = BlockBody.BLOCK_WIDTH * PIXEL_TO_METER / 2;

            if (entity_model.getY() < 0) {
                entity_model.setFlaggedForRemoval(true);
                GameModel.getInstance().lose_block_score();
            }

        }

        if (entity_model.getY() < 0) {
            entity_model.setFlaggedForRemoval(true);
        }


        if (body.getPosition().x + half_width > VIEWPORT_WIDTH) {
            body.setTransform(VIEWPORT_WIDTH - half_width, body.getPosition().y, body.getAngle());
            body.setLinearVelocity(0, body.getLinearVelocity().y);
        }
        else if (body.getPosition().x - half_width < 0) {
            body.setTransform(half_width, body.getPosition().y, body.getAngle());
            body.setLinearVelocity(0, body.getLinearVelocity().y);
        }
    }


    /**
     * Sets the linear velocity of the boat and of the objects in it
     * Checks if the boat is near the left side of the screen and does not change the boats velocity in that case
     */
    public void move_left () {
        boatBody.setLinearVelocity(- BOAT_VELOCITY, 0);

        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);


        if (Math.floor(boatBody.getX() * 100) != Math.floor(BoatBody.BOAT_WIDTH * PIXEL_TO_METER / 2 * 100)) {

            for (Body body : bodies) {

                if (body.getUserData() instanceof BlockModel && ((BlockModel) body.getUserData()).isAboveBoat()) {
                    body.setLinearVelocity(- BOAT_VELOCITY, 0);
                }
            }
        }
    }
    /**
     * Sets the linear velocity of the boat and of the objects in it
     * Checks if the boat is near the right side of the screen and does not change the boats velocity in that case
     */
    public void move_right () {
        boatBody.setLinearVelocity(BOAT_VELOCITY, 0);

        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);


        if (Math.floor(boatBody.getX() * 100) != Math.floor( VIEWPORT_WIDTH * 100 - BoatBody.BOAT_WIDTH * PIXEL_TO_METER / 2 * 100)) {

            for (Body body : bodies) {

                if (body.getUserData() instanceof BlockModel && ((BlockModel) body.getUserData()).isAboveBoat()) {
                    body.setLinearVelocity(BOAT_VELOCITY, 0);
                }
            }
        }
    }


    /**
     * Updates the model and adds a new entity  if it's time to
     *
     * @param delta The nr of seconds passed since last updated
     */
    private void check_add_Entity (float delta) {

        if (nextModel.update(delta)) {

            entities_toAdd.add(nextModel.create_new_entity());
        }
    }

    /**
     * Creates the entities that have been added
     */
    public void create_NewEntities() {

        for (EntityModel model : entities_toAdd) {

            GameModel.getInstance().addEntity(model);
            nextBody.add_new_entity(world, model);
        }

        entities_toAdd.clear();
    }

    /**
     * Removes objects that have been flagged for removal on the previous step.
     */
    public void removeFlagged() {
        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);

        for (Body body : bodies) {
            if (((EntityModel)body.getUserData()).isFlaggedForRemoval()) {
                GameModel.getInstance().remove((EntityModel) body.getUserData());
                world.destroyBody(body);
            }
        }
    }

    /**
     * Scales the boat
     *
     * @param bigger true if the boat will be bigger, false otherwise
     */
    public void scale_boat (boolean bigger) {
        boatBody.scale_boat(bigger);
        is_boat_scaled = true;
    }


    /**
     * Returns true if the boat is scaled, flase otherwise
     *
     * @return the value of the variable is_boat_scaled
     */
    public boolean is_boat_scaled () {
        return is_boat_scaled;
    }

    /**
     * Updates the boat in order for it to be scaled only for 5 seconds
     *
     * @param delta The nr of seconds passed since last update
     */
    private void update_boat (float delta) {
        if (is_boat_scaled) {
            time_boat_scaled -= delta;

            if (time_boat_scaled <= 0){
                is_boat_scaled = false;
                time_boat_scaled = 5f;
            }
        }
    }
}

