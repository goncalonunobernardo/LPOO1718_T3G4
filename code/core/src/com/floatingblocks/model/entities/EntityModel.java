package com.floatingblocks.model.entities;

/**
 * Abstraction of an entity of the game
 */
public abstract class EntityModel {

    /** The types of entities this game has */
    public enum ModelType { BLOCK, PURPLEB, YELLOWB, GREYB, BOAT, WATER, COIN, SANDY, CHEMICAL, DESTRUCTION, POWERUP};

    /** The current x-position of this model in meters */
    private float x;

    /** The current y-position of this model in meters */
    private float y;

    /** The current rotation of this model in radians */
    private float rotation;

    /** Has it been flagged for removal */
    private boolean flaggedForRemoval;

    /**
     * Constructs a model with a position
     *
     * @param x The x-coordinate of this entity in meters.
     * @param y The y-coordinate of this entity in meters.
     * @param rotation The current rotation of this entity in radians.
     */
    EntityModel(float x, float y, float rotation) {
        this.x = x;
        this.y = y;
        this.rotation = rotation;
        this.flaggedForRemoval = false;
    }

    /**
     * Returns the x-coordinate of this entity.
     *
     * @return The x-coordinate of this entity in meters.
     */
    public float getX() {

        return x;
    }

    /**
     * Returns the y-coordinate of this entity.
     *
     * @return The y-coordinate of this entity in meters.
     */
    public float getY() {
        return y;
    }

    /**
     * Returns the rotation of this entity.
     *
     * @return The rotation of this entity in radians.
     */
    public float getRotation() {
        return rotation;
    }

    /**
     * Sets the position of this entity.
     *
     * @param x The x-coordinate of this entity in meters.
     * @param y The y-coordinate of this entity in meters.
     */
    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Sets the rotation of this entity.
     * @param rotation The current rotation of this entity in radians.
     */
    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    /**
     * Returns if this entity has been flagged for removal
     *
     * @return
     */
    public boolean isFlaggedForRemoval() {
        return flaggedForRemoval;
    }

    /**
     * Makes this model flagged for removal on next step
     */
    public void setFlaggedForRemoval(boolean flaggedForRemoval) {
        this.flaggedForRemoval = flaggedForRemoval;
    }

    /**
     * Returns the type of entity represented by this instance
     *
     * @return the type of entity
     */
    public abstract ModelType getType();
}
