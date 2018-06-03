package com.floatingblocks.model.entities;

/**
 * Abstraction of sandy
 */
public class SandyModel extends EntityModel {

    /**
     * Creates a Sandy model in a certain position and having 0 rotation.
     *
     * @param x the x-coordinate in meters
     * @param y the y-coordinate in meters
     */
    public SandyModel(float x, float y) {
        super(x, y, 0);
    }

    public ModelType getType() {
        return ModelType.SANDY;
    }
}
