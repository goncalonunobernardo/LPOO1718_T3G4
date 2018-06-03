package com.floatingblocks.model.entities;

/**
 * Abstraction of a coin
 */
public class CoinModel extends EntityModel {

    /** true if the coin just hit something, false otherwise */
    private boolean new_hit;

    /**
     * Creates a new coin model in a certain position and having 0 rotation.
     *
     * @param x the x-coordinate in meters
     * @param y the y-coordinate in meters
     * @param rotation the rotation of the block in degrees
     */
    public CoinModel(float x, float y, float rotation) {
        super(x, y, rotation);
        new_hit = false;
    }

    public ModelType getType() {
        return ModelType.COIN;
    }

    /**
     * Returns  true if the coin was just hit, false otherwise
     *
     * @return the value of the variable new_hit
     */
    public boolean was_hit () { return new_hit; }

    /**
     * Sets the variable new_hit to the parameter
     *
     * @param new_value Teh new value of that variable
     */
    public void set_hit (boolean new_value) {
        new_hit = new_value;
    }

}