package com.floatingblocks.view.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.floatingblocks.FloatingBlocks;
import com.floatingblocks.model.GameModel;
import com.floatingblocks.model.entities.SandyModel;
import com.floatingblocks.view.game.ViewFactory;
import com.floatingblocks.view.game.entities.SandyView;

/**
 * Abstraction of a menu where the user can mute or turn on the music and the sound features
 */
public class OptionsMenu extends ScreenAdapter {

    /** The game this screen belongs to */
    private FloatingBlocks game;

    /** Viewport width in meters. Height depends on screen ratio */
    public static final int VIEWPORT_WIDTH = 30;


    /** Scale factor to convert from pixels to meters */
    public static final float PIXEL_TO_METER = 0.0207f;


    /** The camera used to show the viewport. */
    private final OrthographicCamera camera;

    private Button mutegame, mutesoundFx, go_back;
    private Stage stage;
    private Music music;

    /**
     * The class constructor that initializes the camera and the textures needed for this screen
     *
     * @param game The game this screen belongs to
     */
    public OptionsMenu(FloatingBlocks game) {
        this.game = game;

        this.camera = createCamera();
        loadAssets();

        music = game.getAssetManager().get("sandys_floatingblocks.mp3", Music.class);

        this.stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        init_buttons_optionsmenu();

    }


    /**
     * Creates the camera used to show the viewport
     *
     * @return the camera
     */
    private OrthographicCamera createCamera() {
        float ratio = ((float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth());
        OrthographicCamera camera = new OrthographicCamera(VIEWPORT_WIDTH / PIXEL_TO_METER, VIEWPORT_WIDTH / PIXEL_TO_METER * ratio);

        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        camera.update();

        return camera;
    }

    /**
     * Loads the assets needed by this screen.
     */
    private void loadAssets() {
        this.game.getAssetManager().load ("menu_background.png", Texture.class);
        this.game.getAssetManager().load( "mute_game_button.png" , Texture.class);
        this.game.getAssetManager().load( "mute_fx_button.png" , Texture.class);
        this.game.getAssetManager().load( "go_back_button.png" , Texture.class);
        this.game.getAssetManager().load("sandy_animation.png", Texture.class);

        this.game.getAssetManager().finishLoading();
    }

    /**
     * Initializes the option menu buttons: to mute the game, mute the cound features and go back to main menu.
     * Adds the button to the stage
     */
    private void init_buttons_optionsmenu() {
        Sprite mutegame_spr = new Sprite(new Texture ("mute_game_button.png"));
        this.mutegame = new Button( new SpriteDrawable(mutegame_spr));

        mutegame.addListener( new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                mute_music();
            }
        });



        Sprite mutesoundFx_spr = new Sprite(new Texture ("mute_fx_button.png"));
        this.mutesoundFx= new Button( new SpriteDrawable(mutesoundFx_spr));

        mutesoundFx.addListener( new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                game.change_sound_features();
            }
        });


        Sprite go_back_spr = new Sprite(new Texture ("go_back_button.png"));
        this.go_back= new Button( new SpriteDrawable(go_back_spr));

        go_back.addListener( new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                game.setMenuView();
            }
        });


        float x_btn_position = Gdx.graphics.getWidth()/2 - mutegame.getWidth() / 2 + 50;
        float y_btn_position = Gdx.graphics.getHeight()/3  - mutegame.getHeight() /2 + 200;

        mutegame.setPosition(x_btn_position,y_btn_position);
        y_btn_position -= mutegame.getHeight() * 2.25;

        mutesoundFx.setPosition(x_btn_position,y_btn_position);
        y_btn_position -= mutegame.getHeight() * 2.25;

        go_back.setPosition(x_btn_position, y_btn_position);

        stage.addActor(mutegame);
        stage.addActor(mutesoundFx);
        stage.addActor(go_back);
    }

    /**
     * Renders this screen.
     * @param delta time since last renders in seconds.
     */
    @Override
    public void render(float delta) {
        game.getBatch().setProjectionMatrix(camera.combined);

        // Clears the screen
        Gdx.gl.glClearColor(103 / 255f, 69 / 255f, 117 / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        game.getBatch().begin();
        SandyView.update();

        drawBackground();
        drawEntities();

        game.getBatch().end();

        stage.draw();
    }
    /**
     * Draws the entities to the screen.
     */
    private void drawEntities() {
        SandyModel sandy = GameModel.getInstance().getSandy();
        SandyView sandy_view = (SandyView) ViewFactory.makeView (game, sandy);
        sandy_view.update(sandy);

        sandy_view.draw(game.getBatch());
    }

    /**
     * Handles any inputs and passes them to the controller.
     * */
    private void handleInputs() {
        if(go_back.isPressed())
            game.setMenuView();

        if (mutegame.isPressed())
            mute_music();

        if (mutesoundFx.isPressed()) {
            game.change_sound_features();
        }
    }

    /**
     * Draws the background
     */
    private void drawBackground() {
        Texture background = game.getAssetManager().get("menu_background.png", Texture.class);
        game.getBatch().draw(background, 0,0,camera.viewportWidth,camera.viewportHeight);
    }

    /**
     * Disposes of all assets
     */
    @Override
    public void dispose () {
        super.dispose();
        stage.dispose();
    }

    /**
     * Sets the input processor to this stage
     */
    public void setScreen() {
        Gdx.input.setInputProcessor(stage);
    }

    /**
     * Mutes or stops the music according to the current music's state
     */
    private void mute_music() {

        if (music.isPlaying())
            music.stop();
        else
            music.play();
    }
}
