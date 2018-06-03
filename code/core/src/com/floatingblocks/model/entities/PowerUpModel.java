package com.floatingblocks.model.entities;

/**
 * Abstraction of a powerup
 */
public class PowerUpModel extends EntityModel {
    /** true if the powerup  just hit something, false otherwise */
    private boolean new_hit;

    /** The type of this power up: loseblocks, increase boat width temporarily*/
    private ModelType type;

    /**
     * Creates a new coin model in a certain position and having 0 rotation.
     *
     * @param x the x-coordinate in meters
     * @param y the y-coordinate in meters
     * @param rotation the rotation of the block in degrees
     */
    public PowerUpModel(float x, float y, float rotation, ModelType type) {
        super(x, y, rotation);
        if (type == ModelType.DESTRUCTION || type == ModelType.CHEMICAL)
            this.type = type;
        else
            this.type = ModelType.POWERUP;

        new_hit = false;
    }

    /**
     * Returns the model type of powerup
     *
     * @return the type of this powerup
     */
    public ModelType getType() {
        return type;
    }

    /**
     * Returns  true if the powerup just hit something, false otherwise
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
