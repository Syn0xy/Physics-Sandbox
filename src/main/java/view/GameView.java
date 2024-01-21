package view;

import java.awt.Point;

import scene.GameScene;
import view.gui.GUI;
import view.util.Observer;
import view.util.Subject;

public class GameView extends View implements Observer {

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
    public String title() {
        return TITLE;
    }

    @Override
    public Point position() {
        return center();
    }

    @Override
    public void view() {
        GameCanvas canvas = new GameCanvas(gameScene);
        GUI gui = new GameGUI(this, canvas, gameScene);
        canvas.add(gui);
        add(canvas);
    }

    @Override
    public void update(Subject subj) {
        update();        
    }

    @Override
    public void update(Subject subj, Object data) {
        update();
    }

    public void update(){
        repaint();
        refreshFrames();
    }
    
}