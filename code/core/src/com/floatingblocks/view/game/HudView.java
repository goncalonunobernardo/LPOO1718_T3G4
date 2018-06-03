package com.floatingblocks.view.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.floatingblocks.FloatingBlocks;
import com.floatingblocks.model.GameModel;

import static com.floatingblocks.view.game.GameView.PIXEL_TO_METER;
import static com.floatingblocks.view.game.GameView.VIEWPORT_WIDTH;


/**
 * An abstraction of a hub of this game: shows the player's score and a button to go bach to the main menu
 */
public class HudView {

    private Stage stage;

    /** The button to allow the user to go back */
    private TextButton go_back;

    /** THe label to show the current score of the user */
    private Label score;

    /** The font used in both the button and the label */
    private BitmapFont font;

    /** The game this hub belongs to */
    private final FloatingBlocks game;


    /**
     * Hubview constructor: initializes the font, the buttons and adds its events listeners
     *
     * @param game The game this hub belongs to
     */
    HudView(final FloatingBlocks game) {

        float ratio = ((float) Gdx.graphics.getHeight() / (float)Gdx.graphics.getWidth());

        FillViewport viewport = new FillViewport(VIEWPORT_WIDTH / PIXEL_TO_METER, VIEWPORT_WIDTH * ratio / PIXEL_TO_METER, new OrthographicCamera());

        stage = new Stage (viewport, game.getBatch());

        viewport.getCamera().position.set(viewport.getWorldWidth() / 2f, viewport.getWorldHeight() / 2f, 0);

        this.game = game;

        initialize_font();

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = font;
        style.fontColor = new Color( 34f / 255, 139f / 255, 34f / 255, 0.9f);


        go_back = new TextButton("Back", style);
        go_back.setPosition(viewport.getWorldWidth() - 300, viewport.getWorldHeight() - 200);


        Label.LabelStyle label_style = new Label.LabelStyle();
        label_style.font = font;
        label_style.fontColor = new Color ( 34f / 255, 139f / 255, 34f / 255, 0.9f);

        score = new Label("Score", label_style);
        score.setPosition(200, viewport.getWorldHeight() - 200);

        go_back.addListener( new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                game.setMenuView();
            }
        });

        stage.addActor(go_back);
        stage.addActor(score);
    }

    /**
     * Initializes the fonts needed by the game
     */
    private void initialize_font () {

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("score_font.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 115;
        parameter.color = new Color ( 34f / 255, 139f / 255, 34f / 255, 0.9f);
        font = generator.generateFont(parameter);

    }



    /**
     * Updates the value of the score
     */
    public void update_score () {
        score.setText("Score: " + GameModel.getInstance().getScore());
    }

    public Camera getCamera () {
        return stage.getCamera();
    }

    public void draw () {
        stage.draw();
    }

    public Stage getStage () {
        return stage;
    }

    public void dispose () {
        stage.dispose();
        font.dispose();
    }

}
