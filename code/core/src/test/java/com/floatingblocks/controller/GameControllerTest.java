package com.floatingblocks.controller;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;
import com.floatingblocks.controller.entities.BlockBody;
import com.floatingblocks.controller.entities.BoatBody;
import com.floatingblocks.controller.entities.CoinBody;
import com.floatingblocks.model.GameModel;
import com.floatingblocks.model.entities.BlockModel;
import com.floatingblocks.model.entities.BoatModel;
import com.floatingblocks.model.entities.CoinModel;
import com.floatingblocks.model.entities.EntityModel;

import org.junit.Test;

import static com.floatingblocks.controller.GameController.BOAT_VELOCITY;
import static com.floatingblocks.view.game.GameView.PIXEL_TO_METER;
import static com.floatingblocks.view.game.GameView.VIEWPORT_WIDTH;
import static org.junit.Assert.*;

public class GameControllerTest {


    @Test
    public void verify_bounds (){
        GameController controller = GameController.getInstance();

        BoatBody boat = new BoatBody(controller.getWorld(), new BoatModel(VIEWPORT_WIDTH, 0));
        BlockBody block = new BlockBody(controller.getWorld(), new BlockModel(VIEWPORT_WIDTH, 0, 0, EntityModel.ModelType.YELLOWB));
        CoinBody coin = new CoinBody(controller.getWorld(), new CoinModel(VIEWPORT_WIDTH, 0, 0));

        Array<Body> bodies = new Array<Body>();
        controller.getWorld().getBodies(bodies);

        for (Body body : bodies) {
            EntityModel model = (EntityModel) body.getUserData();
            GameController.getInstance().verifyBounds(body);

            model.setPosition(body.getPosition().x, body.getPosition().y);
            model.setRotation(body.getAngle());
        }

        assertEquals(VIEWPORT_WIDTH - BoatBody.BOAT_WIDTH * PIXEL_TO_METER / 2, boat.getX(), 1f);
        assertEquals(0f,  boat.getY(), 0f);

        assertEquals(VIEWPORT_WIDTH - BlockBody.BLOCK_WIDTH * PIXEL_TO_METER / 2, block.getX(), 1f);
        assertEquals(0f,  boat.getY(), 0f);

        assertEquals(VIEWPORT_WIDTH - CoinBody.COIN_WIDTH * PIXEL_TO_METER / 2, coin.getX(), 1f);
        assertEquals(0f,  boat.getY(), 0f);


        BoatBody boat_2 = new BoatBody(controller.getWorld(), new BoatModel(0, -1f));
        BlockBody block_2 = new BlockBody(controller.getWorld(), new BlockModel(0, -1, 0, EntityModel.ModelType.YELLOWB));
        CoinBody coin_2 = new CoinBody(controller.getWorld(), new CoinModel(0, -1, 0));

        controller.getWorld().getBodies(bodies);

        for (Body body : bodies) {
            EntityModel model = (EntityModel) body.getUserData();
            GameController.getInstance().verifyBounds(body);

            model.setPosition(body.getPosition().x, body.getPosition().y);
            model.setRotation(body.getAngle());
        }

        BoatModel boat_model = (BoatModel) boat_2.getUserData();
        BlockModel block_model = (BlockModel) block_2.getUserData();
        CoinModel coin_model = (CoinModel) coin_2.getUserData();

        assertTrue (boat_model.isFlaggedForRemoval());
        assertTrue (block_model.isFlaggedForRemoval());
        assertTrue (coin_model.isFlaggedForRemoval());
    }

    @Test
    public void move_right() {
        GameController controller = GameController.getInstance();
        GameModel model = GameModel.getInstance();

        float prev_x = GameModel.getInstance().getBoat().getX();
        float prev_y = GameModel.getInstance().getBoat().getY();

        controller.move_right();
        controller.update(0.1f);


        assertEquals( (prev_x + BOAT_VELOCITY * 0.1f), model.getBoat().getX(), 1f);
        assertEquals( (prev_y), model.getBoat().getY(), 0.1f);
    }

    @Test
    public void move_left() {
        GameController controller = GameController.getInstance();
        GameModel model = GameModel.getInstance();

        float prev_x = GameModel.getInstance().getBoat().getX();
        float prev_y = GameModel.getInstance().getBoat().getY();

        controller.move_left();
        controller.update(0.1f);

        assertEquals( (prev_x - BOAT_VELOCITY * 0.1f), model.getBoat().getX(), 1f);
        assertEquals( (prev_y), model.getBoat().getY(), 0.1f);
    }




    @Test
    public void removeFlagged() {
        GameController controller = GameController.getInstance();

        Array<Body> bodies = new Array<Body>();
        controller.getWorld().getBodies(bodies);

        for (Body body : bodies) {
            EntityModel model = (EntityModel) body.getUserData();
            model.setFlaggedForRemoval(true);
        }

        controller.removeFlagged();
        controller.getWorld().getBodies(bodies);

        assertEquals(0, bodies.size, 0);
    }
}