package view;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import scene.GameScene;
import scene.Shape;
import scene.Vector2;
import scene.element.Water;
import view.util.Observer;
import view.util.Subject;

public class GameView extends View implements Observer {
    private static final int WIDTH = (int)(SCREEN_WIDTH * (2.0 / 3.0));
    private static final int HEIGHT = (int)(SCREEN_HEIGHT * (2.0 / 3.0));
    private static final String TITLE = "Cellular Automata";

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
        canvas.addMouseListener(new MouseListener() {
            private int crntShape = 0;

            @Override
            public void mousePressed(MouseEvent e) {
                Vector2 vec2 = canvas.getLocationInMap(e.getX(), e.getY());
                Shape[] values = Shape.values();
                // gameScene.setSelectedElement(values[crntShape] == Shape.TRIANGLE ? Water.class : Sand.class);
                gameScene.setSelectedElement(Water.class);
                gameScene.setSelectedShape(values[crntShape]);
                gameScene.setAction(vec2);
                crntShape = (crntShape + 1) % values.length;
            }

            @Override public void mouseClicked(MouseEvent e) {}
            @Override public void mouseEntered(MouseEvent e) {}
            @Override public void mouseExited(MouseEvent e) {}
            @Override public void mouseReleased(MouseEvent e) {}
        });

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