package com.floatingblocks.model;

import com.floatingblocks.model.entities.BlockModel;
import com.floatingblocks.model.entities.BoatModel;
import com.floatingblocks.model.entities.CoinModel;
import com.floatingblocks.model.entities.PowerUpModel;

import org.junit.Test;

import static com.floatingblocks.model.GameModel.CATCH_BLOCK_SCORE;
import static com.floatingblocks.model.GameModel.COIN_SCORE;
import static com.floatingblocks.model.GameModel.LOSE_BLOCK_SCORE;
import static com.floatingblocks.model.entities.EntityModel.ModelType.BLOCK;
import static com.floatingblocks.model.entities.EntityModel.ModelType.POWERUP;
import static org.junit.Assert.*;

public class GameModelTest {

    @Test
    public void remove() {
        GameModel model = GameModel.getInstance();

        BlockModel block = new BlockModel(0, 0, 0, BLOCK);
        CoinModel coin = new CoinModel(0, 0, 0);
        PowerUpModel powerup = new PowerUpModel(0, 0, 0, POWERUP);

        model.addEntity(block);
        model.addEntity(coin);
        model.addEntity(powerup);

        int nr_blocks = model.getBlock().size();
        int nr_coins = model.getCoins().size();
        int nr_pu = model.getPowerups().size();

        model.remove(block);
        model.remove(coin);
        model.remove(powerup);

        assertEquals (nr_blocks - 1, model.getBlock().size());
        assertEquals (nr_coins - 1, model.getCoins().size());
        assertEquals (nr_pu - 1, model.getPowerups().size());
    }

    @Test
    public void add_score() {
        GameModel model = GameModel.getInstance();

        float prev_score = model.getScore();

        model.add_score(4);

        assertEquals(prev_score + 4, model.getScore(), 0);

        model.add_score(-4);

        assertEquals(prev_score, model.getScore(), 0);

    }

    @Test
    public void catch_coin_score() {
        GameModel model = GameModel.getInstance();

        float prev_score = model.getScore();

        model.catch_coin_score();

        assertEquals(prev_score + COIN_SCORE, model.getScore(), 0);

    }

    @Test
    public void catch_block_score() {
        GameModel model = GameModel.getInstance();

        float prev_score = model.getScore();

        model.catch_block_score();

        assertEquals(prev_score + CATCH_BLOCK_SCORE, model.getScore(), 0);
    }

    @Test
    public void lose_block_score() {
        GameModel model = GameModel.getInstance();

        float prev_score = model.getScore();

        model.lose_block_score();

        assertEquals(prev_score + LOSE_BLOCK_SCORE, model.getScore(), 0);
    }

    @Test
    public void addEntity() {
        GameModel model = GameModel.getInstance();

        BlockModel block = new BlockModel(0, 0, 0, BLOCK);
        CoinModel coin = new CoinModel(0, 0, 0);
        PowerUpModel powerup = new PowerUpModel(0, 0, 0, POWERUP);

        int nr_blocks = model.getBlock().size();
        int nr_coins = model.getCoins().size();
        int nr_pu = model.getPowerups().size();

        model.addEntity(block);
        model.addEntity(coin);
        model.addEntity(powerup);

        assertEquals (nr_blocks + 1, model.getBlock().size());
        assertEquals (nr_coins + 1, model.getCoins().size());
        assertEquals (nr_pu + 1, model.getPowerups().size());

    }

    @Test
    public void get_block_max_height() {
        GameModel model = GameModel.getInstance();

        BlockModel block_1 = new BlockModel(0, 10, 0, BLOCK);
        BlockModel block_2 = new BlockModel(0, 20, 0, BLOCK);
        BlockModel block_3 = new BlockModel(0, 5, 0, BLOCK);

        BoatModel boat = model.getBoat();

        model.addEntity(block_1);
        model.addEntity(block_2);
        model.addEntity(block_3);

        block_1.setAboveBoat(true);
        block_3.setAboveBoat(true);

        assertEquals(10 - 2 * boat.getY(), model.get_block_max_height(), 0f);

    }
}