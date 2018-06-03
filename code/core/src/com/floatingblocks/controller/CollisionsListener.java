package com.floatingblocks.controller;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.floatingblocks.model.GameModel;
import com.floatingblocks.model.entities.BlockModel;
import com.floatingblocks.model.entities.BoatModel;
import com.floatingblocks.model.entities.CoinModel;
import com.floatingblocks.model.entities.EntityModel;
import com.floatingblocks.model.entities.PowerUpModel;
import com.floatingblocks.model.entities.WaterModel;


/**
 * Handles all of the collisions in the world
 */
public class CollisionsListener implements ContactListener {

    public CollisionsListener() {}

    /**
     * A contact between two objects was detected
     *
     * @param contact the detected contact
     */
    @Override
    public void beginContact(Contact contact) {
        Body bodyA = contact.getFixtureA().getBody();
        Body bodyB = contact.getFixtureB().getBody();

        if ((bodyA.getUserData() instanceof BlockModel || bodyA.getUserData() instanceof CoinModel) && bodyB.getUserData() instanceof WaterModel)
            coin_block_water_collision(bodyB);
        if (bodyA.getUserData() instanceof WaterModel && (bodyB.getUserData() instanceof BlockModel || bodyB.getUserData() instanceof CoinModel))
            coin_block_water_collision(bodyA);

        if (bodyA.getUserData() instanceof BlockModel && bodyB.getUserData() instanceof BoatModel)
            blockBoatCollision(bodyA);
        if (bodyA.getUserData() instanceof BoatModel && bodyB.getUserData() instanceof BlockModel)
            blockBoatCollision(bodyB);

        if (bodyA.getUserData() instanceof BlockModel && bodyB.getUserData() instanceof BlockModel)
            blockCollision(bodyA, bodyB);

        if (bodyA.getUserData() instanceof  CoinModel && bodyB.getUserData() instanceof BoatModel && !contact.getFixtureB().isSensor())
            coinBoatCollision (bodyA);
        if (bodyA.getUserData() instanceof  BoatModel && bodyB.getUserData() instanceof CoinModel && !contact.getFixtureA().isSensor())
            coinBoatCollision (bodyB);

        if (bodyA.getUserData() instanceof PowerUpModel && bodyB.getUserData() instanceof BoatModel)
            powerupBoatCollision (bodyA);
        if (bodyA.getUserData() instanceof  BoatModel && bodyB.getUserData() instanceof PowerUpModel)
            powerupBoatCollision (bodyB);

        if (bodyA.getUserData() instanceof  CoinModel && bodyB.getUserData() instanceof BlockModel)
            coinBlockCollision (bodyA, bodyB);
        if (bodyA.getUserData() instanceof  BlockModel && bodyB.getUserData() instanceof CoinModel)
            coinBlockCollision (bodyB, bodyA);

        if (bodyA.getUserData() instanceof  PowerUpModel && bodyB.getUserData() instanceof BlockModel)
            powerupBlockCollision (bodyA, bodyB);
        if (bodyA.getUserData() instanceof  BlockModel && bodyB.getUserData() instanceof PowerUpModel)
            powerupBlockCollision (bodyB, bodyA);
    }

    /**
     * Handles a collision between a powerup and the boat
     *
     * @param bodyB The powerup body
     */
    private void powerupBoatCollision(Body bodyB) {
        PowerUpModel powerupModel = (PowerUpModel) bodyB.getUserData();
        powerupModel.set_hit(true);
        powerupModel.setFlaggedForRemoval(true);

        catch_powerup(powerupModel.getType());
    }

    /**
     * Handles a collision between a powerup and a block
     *
     * @param powerupBody The powerup body
     * @param blockBody The block body
     */
    private void powerupBlockCollision(Body powerupBody, Body blockBody) {
        PowerUpModel powerupModel = (PowerUpModel) powerupBody.getUserData();
        BlockModel blockModel = (BlockModel) blockBody.getUserData();

        if (blockModel.isAboveBoat()) {
            powerupModel.set_hit(true);
            powerupModel.setFlaggedForRemoval(true);
            catch_powerup(powerupModel.getType());
        }
    }

    /**
     * Chooses which functions to call accodring to the powerup just caught
     *
     * @param type The type of powerup caught
     */
    private void catch_powerup (EntityModel.ModelType type) {
        if (type == EntityModel.ModelType.DESTRUCTION ) {
            GameModel.getInstance().destroy_blocks();
        } else if(type == EntityModel.ModelType.CHEMICAL) {
            GameController.getInstance().scale_boat(true);
        }

    }

    @Override
    public void endContact(Contact contact) {
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

    /**
     * Handles a collision between a coin or a block and the water
     *
     * @param body The water body
     */
    private void coin_block_water_collision (Body body) {

        WaterModel waterModel = (WaterModel) body.getUserData();
        waterModel.set_hit(true);
    }

    /**
     * Handles a collision between a block and the boat
     *
     * @param blockBody The block body
     */
    private void blockBoatCollision (Body blockBody) {
        BlockModel blockModel = (BlockModel) blockBody.getUserData();
        blockModel.setAboveBoat(true);

        blockBody.setGravityScale(1);

        GameModel.getInstance().catch_block_score();

    }

    /**
     * Handles a collision between 2 blocks
     *
     * @param blockBodyA One of the blocks' bodies
     * @param blockBodyB The other block's body
     */
    private void blockCollision (Body blockBodyA, Body blockBodyB) {
        BlockModel blockModelA = (BlockModel) blockBodyA.getUserData();
        BlockModel blockModelB = (BlockModel) blockBodyB.getUserData();

        if (blockModelA.isAboveBoat() && ! blockModelB.isAboveBoat()) {
            blockModelB.setAboveBoat(true);
            blockBodyB.setGravityScale(1);
            GameModel.getInstance().catch_block_score();
        }

        if (blockModelB.isAboveBoat() && ! blockModelA.isAboveBoat()) {
            blockModelA.setAboveBoat(true);
            blockBodyA.setGravityScale(1);
            GameModel.getInstance().catch_block_score();
        }
    }

    /**
     * Handles a collision between a coin and the boat
     *
     * @param coinBody The coin body
     */
    public void coinBoatCollision (Body coinBody) {
        CoinModel coinModel = (CoinModel) coinBody.getUserData();
        coinModel.set_hit(true);
        coinModel.setFlaggedForRemoval(true);

        GameModel.getInstance().catch_coin_score();
    }

    /**
     * Handles a collision between a coin and a block
     *
     * @param coinBody The coin body
     * @param blockBody The block body
     */
    public void coinBlockCollision (Body coinBody, Body blockBody) {
        CoinModel coinModel = (CoinModel) coinBody.getUserData();
        BlockModel blockModel = (BlockModel) blockBody.getUserData();

        if (blockModel.isAboveBoat()) {
            coinModel.set_hit(true);
            coinModel.setFlaggedForRemoval(true);
            GameModel.getInstance().catch_coin_score();
        }
    }

}
