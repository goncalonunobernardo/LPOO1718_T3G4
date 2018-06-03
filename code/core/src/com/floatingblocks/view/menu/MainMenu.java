package com.floatingblocks.view.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
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


public class MainMenu extends ScreenAdapter {

    /** The game this screen belongs to */
    private FloatingBlocks game;

    /** Viewport width in meters. Height depends on screen ratio */
    public static final int VIEWPORT_WIDTH = 30;


    /** Scale factor to convert from pixels to meters */
    public static final float PIXEL_TO_METER = 0.0207f;


    /** The camera used to show the viewport. */
    private final OrthographicCamera camera;

    /** The button to go to the game screen */
    private Button  play;

    /** The button to go to the options menu */
    private Button options;

    /** The button to exit the game */
    private Button exit;

    /** The stage the buttons belong to */
    private Stage stage;

    /** The music in mainmenu & optionsmenu*/
    public Music music;
    /**
     * The class constructor that initializes the camera and the textures needed for this screen
     *
     * @param game The game this screen belongs to
     */
    public MainMenu(FloatingBlocks game) {
        this.game = game;

        this.camera = createCamera();
        loadAssets();

        music = game.getAssetManager().get("sandys_floatingblocks.mp3", Music.class);

        Sprite play_spr = new Sprite(new Texture ("play_button.png"));
        this.play = new Button( new SpriteDrawable(play_spr));

        Sprite options_spr = new Sprite(new Texture ("options_button.png"));
        this.options = new Button( new SpriteDrawable(options_spr));

        Sprite exit_spr = new Sprite(new Texture ("exit_button.png"));
        this.exit = new Button( new SpriteDrawable(exit_spr));

        this.stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        music.play();
        music.setLooping(true);

        init_buttons_menu();
    }


    /**
     * Creates the camera used to show the viewport.
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
        this.game.getAssetManager().load( "play_button.png" , Texture.class);
        this.game.getAssetManager().load( "options_button.png" , Texture.class);
        this.game.getAssetManager().load( "exit_button.png" , Texture.class);
        this.game.getAssetManager().load("sandy_animation.png", Texture.class);
        this.game.getAssetManager().load("sandys_floatingblocks.mp3", Music.class);
        this.game.getAssetManager().finishLoading();
    }

    private void init_buttons_menu() {


        float x_btn_position = Gdx.graphics.getWidth()/2 - play.getWidth() / 2 + 50;
        float y_btn_position = Gdx.graphics.getHeight()/3  - play.getHeight() /2 + 200;

        play.setPosition(x_btn_position,y_btn_position);
        y_btn_position -= play.getHeight() * 2.25;

        play.addListener( new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                game.setGameView();
            }
        });


        options.setPosition(x_btn_position,y_btn_position);
        y_btn_position -= options.getHeight() * 2.25;

        options.addListener( new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                game.setOptionsMenu();
            }
        });


        exit.setPosition(x_btn_position, y_btn_position);
        exit.addListener( new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                game.exitGame();
            }
        });


        stage.addActor(play);
        stage.addActor(options);
        stage.addActor(exit);
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
        SandyView sandy_view = (SandyView)ViewFactory.makeView (game, sandy);
        sandy_view.update(sandy);

        sandy_view.draw(game.getBatch());
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
}
