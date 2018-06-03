package com.floatingblocks.model.entities;

/**
 * Abstraction of the water
 */
public class WaterModel extends EntityModel {
    /** true if the water was just hit, false otherwise */
    private boolean new_hit;

    /**
     * Creates a new watermodel in a given position
     *
     * @param x The x position of the model in meters
     * @param y The y position of the model in meters
     */
    public WaterModel(float x, float y) {
        super(x, y, 0);
        new_hit = false;
    }

    public ModelType getType() {
        return ModelType.WATER;
    }

    /**
     * Returns  true if the water was just hit, false otherwise
     *
     * @return the value of the variable new_hit
     */
    public boolean was_hit () { return new_hit; }

    /**
     * Sets the variable new_hit to the parameter
     *
     * @param new_value The new value of that variable
     */
    public void set_hit (boolean new_value) {
        new_hit = new_value;
    }
}
