package com.floatingblocks.model.entities;

/**
 * Abstraction of a boat
 */
public class BoatModel extends EntityModel {

    /**
     * Creates a new boat model in a certain position and having 0 rotation.
     *
     * @param x the x-coordinate in meters
     * @param y the y-coordinate in meters
     */
    public BoatModel(float x, float y) {
        super(x, y, 0);
    }

    public ModelType getType() {
        return ModelType.BOAT;
    }
}
