package com.floatingblocks.model.entities;

/**
 * Abstraction of a block
 */
public class BlockModel extends EntityModel {

    /** Says if the block has landed on the boat or not */
    private boolean above_boat;

    /** The type of this block: yellow, purple */
    private ModelType type;

    /**
     * Creates a new block model in a certain position and having 0 rotation.
     *
     * @param x the x-coordinate in meters
     * @param y the y-coordinate in meters
     * @param rotation the rotation of the block in degrees
     * @param type the type of this block: yellow, purple
     */
    public BlockModel(float x, float y, float rotation, ModelType type) {
        super(x, y, rotation);

        if (type == ModelType.YELLOWB || type == ModelType.PURPLEB || type == ModelType.GREYB)
            this.type = type;
        else
            this.type = ModelType.BLOCK;

        this.above_boat = false;
    }

    /**
     * Returns the model type this bloc kis
     *
     * @return the type of this block
     */
    public ModelType getType() {
        return type;
    }

    /**
     * Sets the value of the boolean variable
     *
     * @param value The new value of the variable
     */
    public void setAboveBoat (boolean value) {
        above_boat = value;
    }

    /**
     * Returns true if the block has landed on the boat, false otherwise
     *
     * @return the value of the boolean variable
     */
    public boolean isAboveBoat () {
        return above_boat;
    }

}
