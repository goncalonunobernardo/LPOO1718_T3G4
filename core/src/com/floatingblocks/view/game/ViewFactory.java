package com.floatingblocks.view.game;

import com.floatingblocks.FloatingBlocks;
import com.floatingblocks.model.entities.EntityModel;
import com.floatingblocks.view.game.entities.BlockView;
import com.floatingblocks.view.game.entities.BoatView;
import com.floatingblocks.view.game.entities.CoinView;
import com.floatingblocks.view.game.entities.EntityView;
import com.floatingblocks.view.game.entities.PowerUpView;
import com.floatingblocks.view.game.entities.SandyView;
import com.floatingblocks.view.game.entities.WaterView;

import java.util.HashMap;
import java.util.Map;

import static com.floatingblocks.model.entities.EntityModel.ModelType.BOAT;
import static com.floatingblocks.model.entities.EntityModel.ModelType.CHEMICAL;
import static com.floatingblocks.model.entities.EntityModel.ModelType.COIN;
import static com.floatingblocks.model.entities.EntityModel.ModelType.DESTRUCTION;
import static com.floatingblocks.model.entities.EntityModel.ModelType.GREYB;
import static com.floatingblocks.model.entities.EntityModel.ModelType.POWERUP;
import static com.floatingblocks.model.entities.EntityModel.ModelType.PURPLEB;
import static com.floatingblocks.model.entities.EntityModel.ModelType.SANDY;
import static com.floatingblocks.model.entities.EntityModel.ModelType.WATER;
import static com.floatingblocks.model.entities.EntityModel.ModelType.YELLOWB;

public class ViewFactory {
    private static Map<EntityModel.ModelType, EntityView> cache =
            new HashMap<EntityModel.ModelType, EntityView>();

    public static EntityView makeView(FloatingBlocks game, EntityModel model) {
        if (!cache.containsKey(model.getType())) {
            if (model.getType() == BOAT)
                cache.put(model.getType(), new BoatView(game));
            if (model.getType() == YELLOWB || model.getType() == GREYB || model.getType() == PURPLEB)
                cache.put(model.getType(), new BlockView(game, model.getType()));
            if (model.getType() == WATER)
                cache.put(model.getType(), new WaterView(game));
            if (model.getType() == COIN)
                cache.put(model.getType(), new CoinView(game));
            if (model.getType() == SANDY)
                cache.put(model.getType(), new SandyView(game));
            if (model.getType() == CHEMICAL || model.getType() == DESTRUCTION || model.getType() == POWERUP)
                cache.put(model.getType(), new PowerUpView(game, model.getType()));
        }

        return cache.get(model.getType());
    }
}
