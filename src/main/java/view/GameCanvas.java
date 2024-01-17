package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import scene.GameScene;
import scene.Vector2;
import scene.element.Element;

public class GameCanvas extends JPanel {
    private static final Color BACKGROUND_COLOR = Color.WHITE;
    
    private Element[][] elements;

    private int width;
    private int height;

    public GameCanvas(GameScene gameScene){
        this.elements = gameScene.getElements();
        this.width = elements.length;
        this.height = elements[0].length;
    }

    protected Vector2 getLocationInMap(int x, int y){
        return new Vector2(
            (x * elements.length) / getWidth(),
            ((getHeight() - y) * elements[0].length) / getHeight()
        );
    }

    private Vector2 getLocationInScreen(int x, int y){
        return new Vector2(
            (getWidth() * x) / elements.length,
            (getHeight() * y) / elements[0].length
        );
    }
    
    @Override
    public void paint(Graphics g) {
        clearScreen(g);
        draw(g);
    }

    private void clearScreen(Graphics g){
        g.setColor(BACKGROUND_COLOR);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    private void draw(Graphics g){
        int height;
        for (int i = 0; i < elements.length; i++) {
            height = elements[i].length;
            for (int j = 0; j < height; j++) {
                drawElement(g, elements[i][j], i, height - j - 1);
            }
        }
    }

    private void drawElement(Graphics g, Element element, int x, int y){
        int sx = getWidth() / elements.length + 1;
        int sy = getHeight() / elements[0].length + 1;
        Vector2 vec2 = getLocationInScreen(x, y);
        g.setColor(element.getColor());
        g.fillRect(vec2.x, vec2.y, sx, sy);
    }
}
