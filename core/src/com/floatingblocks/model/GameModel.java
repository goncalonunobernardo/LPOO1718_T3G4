package com.floatingblocks.model;

import com.floatingblocks.model.entities.BlockModel;
import com.floatingblocks.model.entities.BoatModel;
import com.floatingblocks.model.entities.CoinModel;
import com.floatingblocks.model.entities.EntityModel;
import com.floatingblocks.model.entities.PowerUpModel;
import com.floatingblocks.model.entities.SandyModel;
import com.floatingblocks.model.entities.WaterModel;

import java.util.ArrayList;
import java.util.List;

import static com.floatingblocks.controller.entities.WaterBody.WATER_HEIGHT;
import static com.floatingblocks.view.game.GameView.PIXEL_TO_METER;
import static com.floatingblocks.view.game.GameView.VIEWPORT_WIDTH;

/**
 * A model representing the game
 */
public class GameModel {

    /** The score won when the player catches a coin */
    public static final int COIN_SCORE = 50;

    /** The score whon when the player catches a block */
    public static final int CATCH_BLOCK_SCORE = 10;

    /** The score lost when the player loses a block */
    public static final int LOSE_BLOCK_SCORE = -100;

    /** The singleton instance of the game model */
    private static GameModel instance;

    /** The boat controlled by the user in this game */
    private BoatModel boat;

    /** The blocks currently in the game */
    private List<BlockModel> blocks;

    /** The coins currently in the game */
    private List<CoinModel> coins;

    /** The water of this game */
    private WaterModel water;

    /** Sandy's Model */
    private SandyModel sandy;

    /** The PowerUps available ingame*/
    private List<PowerUpModel> powerups;

    /** The score of the game */
    private float score;


    /**
     * Creates a game model for this game
     */
    private GameModel() {

        boat = new BoatModel(VIEWPORT_WIDTH / 2, 3.7f);
        blocks = new ArrayList<BlockModel>();
        coins = new ArrayList<CoinModel>();
        water = new WaterModel (VIEWPORT_WIDTH / 2,WATER_HEIGHT * PIXEL_TO_METER / 2);
        sandy = new SandyModel(7,5);
        powerups = new ArrayList<PowerUpModel>();

        score = 0;
    }

    /**
     * Returns a singleton instance of the game model
     *
     * @return the singleton instance
     */
    public static GameModel getInstance() {
        if(instance == null)
            instance = new GameModel();
        return instance;
    }

    /**
     * Returns the boat controlled by the user
     *
     * @return the boat of this game
     */
    public BoatModel getBoat () {
        return boat;
    }

    /**
     * Returns the blocks currently on the game
     *
     * @return the blocks list
     */
    public List<BlockModel> getBlock () {
        return blocks;
    }

    /**
     * Returns the coins currently on the game
     *
     * @return the coins list
     */
    public List<CoinModel> getCoins () { return coins; }

    /**
     * Returns the powerups currently on the game
     *
     * @return the powerups list
     */
    public List<PowerUpModel> getPowerups () { return powerups; }

    /**
     * Return the water of this game
     *
     * @return the water
     */
    public WaterModel getWater() {
        return water;
    }

    /**
     * Returns Sandy model
     *
     * @return the boat of this game
     */
    public SandyModel getSandy () {
        return sandy;
    }

    /**
     * Removes a model from this game.
     *
     * @param model the model to be removed
     */
    public void remove(EntityModel model) {

        if (model instanceof BlockModel) {
            blocks.remove(model);
        }
        if (model instanceof CoinModel) {
            coins.remove(model);
        }
        if (model instanceof PowerUpModel) {
            powerups.remove(model);
        }
    }

    /**
     * Returns the current score of the player
     *
     * @return the player's score
     */
    public int getScore () {
        return (int) score;
    }

    /**
     * Updates the score value by adding the new value to the current score
     *
     * @param adding The value to be added to the score
     */
    public void add_score (float adding) {
        score += adding;
    }

    /**
     * Adds to the current score the value of catching a coin
     */
    public void catch_coin_score () {
        add_score(COIN_SCORE);
    }

    /**
     * Adds to the current score the value of catching a block
     */
    public void catch_block_score () {
        add_score(CATCH_BLOCK_SCORE);
    }

    /**
     * Adds to the current score the value of losing a block
     */
    public void lose_block_score () {
        add_score(LOSE_BLOCK_SCORE);
    }

    /**
     * Adds a model from this game.
     *
     * @param model the model to be added
     */
    public void addEntity(EntityModel model) {
        if (model instanceof BlockModel) {
            blocks.add((BlockModel)model);
        }
        if (model instanceof CoinModel) {
            coins.add((CoinModel)model);
        }
        if (model instanceof PowerUpModel) {
            powerups.add((PowerUpModel)model);
        }
    }

    /**
     * Computes and returns the height of the first block of the ones that are above the boat
     *
     * @return the height of that block
     */
    public float get_block_max_height () {

        float max_height = 0;

        for (int i = 0; i < blocks.size(); i++) {
            if (blocks.get(i).isAboveBoat() && blocks.get(i).getY() > max_height)
                max_height = blocks.get(i).getY();
        }

        if (max_height >  2 *  boat.getY())
            max_height -= 2 * boat.getY();

        return max_height;
    }

    public void destroy_blocks() {
        for(BlockModel block : blocks) {
            if(block.isAboveBoat())
                block.setFlaggedForRemoval(true);
        }
    }
}
