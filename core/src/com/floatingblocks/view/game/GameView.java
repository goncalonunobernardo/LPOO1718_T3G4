package com.floatingblocks.view.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.floatingblocks.FloatingBlocks;
import com.floatingblocks.controller.GameController;
import com.floatingblocks.model.GameModel;
import com.floatingblocks.model.entities.BlockModel;
import com.floatingblocks.model.entities.BoatModel;
import com.floatingblocks.model.entities.CoinModel;
import com.floatingblocks.model.entities.PowerUpModel;
import com.floatingblocks.model.entities.WaterModel;
import com.floatingblocks.view.game.entities.BoatView;
import com.floatingblocks.view.game.entities.CoinView;
import com.floatingblocks.view.game.entities.EntityView;
import com.floatingblocks.view.game.entities.PowerUpView;
import com.floatingblocks.view.game.entities.WaterView;

import java.util.List;


public class GameView extends ScreenAdapter {

    /** Used to debug the position of the physics fixtures */
    private static final boolean DEBUG_PHYSICS = false;

    /** Viewport width in meters */
    public static final int VIEWPORT_WIDTH = 30;

    /** Camera height in meters */
    public static float camera_height = 90;

    /** The height of the boat is 0.5 meter and the sprite has a height of 102 px */
    public static final float PIXEL_TO_METER = 2f / 102;

    /** The game this screen belongs to. */
    private final FloatingBlocks game;

    /** The camera used to show the viewport. */
    private final OrthographicCamera camera;

    /** A renderer used to debug the physical fixtures. */
    private Box2DDebugRenderer debugRenderer;

    /** The transformation matrix used to transform meters into pixels in order to show fixtures in their correct places.*/
    private Matrix4 debugCamera;

    /** The viewport of this screen*/
    private FitViewport viewport;

    /** True if the sound features are active, false otherwise */
    private boolean sound_features;

    /** The height of the blocks, for it to move slowly */
    private float height;

    /** The hud of the game */
    private HudView hud;

    /**
     * Creates this screen.
     * @param game The game this screen belongs to
     */
    public GameView(FloatingBlocks game) {
        this.game = game;

        loadAssets();
        camera = createCamera();

        float ratio = ((float) Gdx.graphics.getHeight() / (float)Gdx.graphics.getWidth());

        viewport = new FitViewport(VIEWPORT_WIDTH / PIXEL_TO_METER, VIEWPORT_WIDTH * ratio / PIXEL_TO_METER, camera);
        camera.update();
        camera.position.set(viewport.getWorldWidth() / 2f, viewport.getWorldHeight() / 2f, 0);

        camera_height = camera.position.y * PIXEL_TO_METER + VIEWPORT_WIDTH * ratio;

        sound_features = true;

        height = 0;

        hud = new HudView(game);

        viewport.apply();

    }

    /**
     * Creates the camera used to show the viewport.
     * @return the camera
     */
    private OrthographicCamera createCamera() {
        float ratio = ((float) Gdx.graphics.getHeight() / (float)Gdx.graphics.getWidth());
        OrthographicCamera camera = new OrthographicCamera(VIEWPORT_WIDTH / PIXEL_TO_METER, VIEWPORT_WIDTH / PIXEL_TO_METER * ratio);

        if (DEBUG_PHYSICS) {
            debugRenderer = new Box2DDebugRenderer();
            debugCamera = camera.combined.cpy();
            debugCamera.scl(1 / PIXEL_TO_METER);
        }

        return camera;
    }

    /**
     * Loads the assets needed by this screen.
     */
    private void loadAssets() {
        this.game.getAssetManager().load("background.png", Texture.class);
        this.game.getAssetManager().load( "boat.png" , Texture.class);
        this.game.getAssetManager().load( "block.png" , Texture.class);
        this.game.getAssetManager().load( "block2.png" , Texture.class);
        this.game.getAssetManager().load( "block3.png" , Texture.class);
        this.game.getAssetManager().load( "coin_animation.png" , Texture.class);
        this.game.getAssetManager().load( "water.png" , Texture.class);
        this.game.getAssetManager().load("coin_animation.png", Texture.class);
        this.game.getAssetManager().load("lose_allBlocks.png", Texture.class);
        this.game.getAssetManager().load("increase_boatSize.png", Texture.class);
        this.game.getAssetManager().load("chemical_sound.wav", Music.class);
        this.game.getAssetManager().load("water_splash.mp3", Music.class);
        this.game.getAssetManager().load("coin.mp3", Music.class);
        this.game.getAssetManager().finishLoading();
    }



    /**
     * Renders this screen.
     * @param delta time since last renders in seconds.
     */
    @Override
    public void render(float delta) {

        GameController.getInstance().removeFlagged();
        GameController.getInstance().create_NewEntities();
        handleInputs();

        // updates the physics controller for this game
        GameController.getInstance().update(delta);

        update_camera();
        camera.update();
        game.getBatch().setProjectionMatrix(camera.combined);

        CoinView.update();

        // Clears the screen
        Gdx.gl.glClearColor( 0/255f, 191/255f, 255/255f, 1 );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );

        game.getBatch().begin();

        drawBackground();
        drawEntities();

        game.getBatch().end();


        game.getBatch().setProjectionMatrix(hud.getCamera().combined);
        hud.update_score();
        hud.draw();


        if (DEBUG_PHYSICS) {
            debugCamera = camera.combined.cpy();
            debugCamera.scl(1 / PIXEL_TO_METER);
            debugRenderer.render(GameController.getInstance().getWorld(), debugCamera);
        }

    }

    /**
     * Handles any inputs and passes them to the controller.
     */
    private void handleInputs() {
        boolean gyroscopeAvail = Gdx.input.isPeripheralAvailable(Input.Peripheral.Gyroscope);

        if(gyroscopeAvail){
            float gyroY = Gdx.input.getGyroscopeY();                               /*gyroX and gyroZ are not intended for this game*/
            if(gyroY  > 5)   GameController.getInstance().move_right();
            else if (gyroY < -5)
                GameController.getInstance().move_left();
        }

        if (Gdx.input.justTouched()) {

            if (Gdx.input.getX() * PIXEL_TO_METER > VIEWPORT_WIDTH / 2) {
                GameController.getInstance().move_right();
            }
            else if (Gdx.input.getX() * PIXEL_TO_METER < VIEWPORT_WIDTH / 2) {
                GameController.getInstance().move_left();
            }
        }
    }

    /**
     * Draws the entities to the screen.
     */
    private void drawEntities() {

        BoatModel boat = GameModel.getInstance().getBoat();
        BoatView view = (BoatView) ViewFactory.makeView (game, boat);
        view.update(boat);
        view.set_scaled(GameController.getInstance().is_boat_scaled());
        view.draw(game.getBatch());

        List<BlockModel> blocks = GameModel.getInstance().getBlock();
        for(BlockModel block : blocks) {
            EntityView block_view = ViewFactory.makeView (game, block);
            block_view.update(block);
            block_view.draw(game.getBatch());
        }

        List<CoinModel> coins = GameModel.getInstance().getCoins();
        for(CoinModel coin : coins) {
            CoinView coin_view = (CoinView) ViewFactory.makeView (game, coin);
            coin_view.update(coin);
            coin_view.handle_sound(coin.was_hit() && sound_features);
            coin.set_hit(false);
            coin_view.draw(game.getBatch());
        }

        WaterModel water = GameModel.getInstance().getWater();
        WaterView water_view = (WaterView) ViewFactory.makeView (game, water);
        water_view.update(water);
        water_view.handle_sound (water.was_hit() && sound_features);
        water.set_hit(false);
        water_view.draw(game.getBatch());

        List<PowerUpModel> powerups = GameModel.getInstance().getPowerups();
        for(PowerUpModel powerup : powerups) {
            PowerUpView powerup_view = (PowerUpView) ViewFactory.makeView (game, powerup);
            powerup_view.update(powerup);
            powerup_view.handle_sound(powerup.was_hit() && sound_features);
            powerup.set_hit(false);
            powerup_view.draw(game.getBatch());
        }
    }

    /**
     * Changes the value of the boolean variable responsible for the sound features
     */
    public void change_sound_features (){
        sound_features = !sound_features;
    }

    /**
     * Draws the background
     */
    private void drawBackground() {
        Texture background = game.getAssetManager().get("background.png", Texture.class);
        game.getBatch().draw(background, 0,0,camera.viewportWidth,camera.viewportHeight * 4);
    }

    /**
     * Disposes of all assets
     */
    @Override
    public void dispose () {
        super.dispose();
        hud.dispose();
    }


    /**
     * Sets the input processor to the hub to able the player to press the back button
     */
    public void set_screen () {
        Gdx.input.setInputProcessor(hud.getStage());
    }

    /**
     * updates the camera position by making going up and down
     * according to the number of blocks stacked and their height
     */
    public void update_camera () {


        if (GameModel.getInstance().get_block_max_height() / PIXEL_TO_METER > height + 4) {
            camera.translate(0, 0.2f);
            height += 0.2f;
        }
        else if (GameModel.getInstance().get_block_max_height() / PIXEL_TO_METER < height - 4) {
            camera.translate(0, -0.2f);
            height -= 0.2f;
        }

        float ratio = ((float) Gdx.graphics.getHeight() / (float)Gdx.graphics.getWidth());

        camera_height = camera.position.y * PIXEL_TO_METER + VIEWPORT_WIDTH * ratio / 2;

    }
}
