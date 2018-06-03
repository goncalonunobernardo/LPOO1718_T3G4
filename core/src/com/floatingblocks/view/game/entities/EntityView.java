package com.floatingblocks.view.game.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.floatingblocks.FloatingBlocks;
import com.floatingblocks.model.entities.EntityModel;

import static com.floatingblocks.view.game.GameView.PIXEL_TO_METER;

public abstract class EntityView {

    protected Sprite sprite;      /** The sprite representing this entity view. */

    /**
     * Creates a view belonging to a game.
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     */
    EntityView(FloatingBlocks game) {
        sprite = createSprite(game);
    }

    /**
     * Draws the sprite from this view using a sprite batch.
     * @param batch The sprite batch to be used for drawing.
     */
    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    /**
     * Abstract method that creates the view sprite. Concrete
     * implementation should extend this method to create their
     * own sprites.
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     * @return the sprite representing this view.
     */
    public abstract Sprite createSprite(FloatingBlocks game);

    /**
     * Updates this view based on a certain model.
     * @param model the model used to update this view
     */
    public void update(EntityModel model) {
        sprite.setCenter(model.getX() / PIXEL_TO_METER, model.getY() / PIXEL_TO_METER);
        sprite.setRotation((float) Math.toDegrees(model.getRotation()));
    }
}
