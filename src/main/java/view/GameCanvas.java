package view;

import java.awt.BorderLayout;
import java.awt.Color;
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
    private int mapWidth;
    private int mapHeight;

    private int elementWidth;
    private int elementHeight;

    public GameCanvas(GameScene gameScene){
        this.elements = gameScene.getElements();
        this.mapWidth = elements.length;
        this.mapHeight = elements[0].length;
        setLayout(new BorderLayout());
    }

    public Vector2 getLocation(int x, int y){
        return new Vector2(
            (x * mapWidth) / width,
            ((height - y) * mapHeight) / height
        );
    }

    private Vector2 getLocationInScreen(int x, int y){
        return new Vector2(
            (width * x) / mapWidth,
            (height * y) / mapHeight
        );
    }
    
    @Override
    public void paint(Graphics g) {
        width = getWidth();
        height = getHeight();
        elementWidth = width / mapWidth + 1;
        elementHeight = height / mapHeight + 1;

        clearScreen(g);
        draw(g);
        paintChildren(g);
    }

    private void clearScreen(Graphics g){
        g.setColor(BACKGROUND_COLOR);
        g.fillRect(0, 0, width, height);
    }

    private void draw(Graphics g){
        for (int i = 0; i < mapWidth; i++) {
            for (int j = 0; j < mapHeight; j++) {
                drawElement(g, elements[i][j], i, mapHeight - j - 1);
            }
        }
    }

    private void drawElement(Graphics g, Element element, int x, int y){
        Vector2 vec2 = getLocationInScreen(x, y);
        g.setColor(element.getColor());
        g.fillRect(vec2.x, vec2.y, elementWidth, elementHeight);
    }
}
