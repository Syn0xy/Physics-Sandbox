package view;

import java.awt.Component;

import model.scene.GameScene;
import utils.Updatable;
import view.gui.GUI;

public class GameView extends View implements Updatable {

    private static final int WIDTH = (int)(SCREEN_WIDTH * (2.0 / 3.0));

    private static final int HEIGHT = (int)(SCREEN_HEIGHT * (2.0 / 3.0));
    
    private static final String TITLE = "Physics Sandbox";

    private GameScene gameScene;

    public GameView(GameScene gameScene){
        this.gameScene = gameScene;
        gameScene.attach(this);
        init(WIDTH, HEIGHT);
    }

    @Override
    public String getTitle() {
        return TITLE;
    }
    
    @Override
    public Component getContent() {
        GameCanvas canvas = new GameCanvas(gameScene);
        GUI gui = new GameGUI(this, canvas, gameScene);
        canvas.add(gui);
        return canvas;
    }

    @Override
    public void update() {
        repaint();
        updateFPSCounter();
    }

}