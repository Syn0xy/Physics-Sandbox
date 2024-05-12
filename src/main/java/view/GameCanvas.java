package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JComponent;

import model.element.Element;
import model.scene.GameScene;
import utils.Vector2;

public class GameCanvas extends JComponent {
    
    private static final Color BACKGROUND_COLOR = Color.WHITE;
    
    private List<Element> elements;
    
    private int width;
    
    private int height;
    
    private int gameWidth;
    
    private int gameHeight;
    
    private double reelSize;

    private int elementSize;
    
    public GameCanvas(GameScene gameScene) {
        this.elements = gameScene.getElements();
        this.gameWidth = gameScene.getWidth();
        this.gameHeight = gameScene.getHeight();
        setLayout(new BorderLayout());
    }

    public Vector2 getLocation(int x, int y) {
        return new Vector2(
            (int)(x / reelSize),
            (int)((this.height - y - 1) / reelSize)
        );
    }
    
    private double getReelSize() {
        int minScreenSize = Math.min(this.width, this.height);
        int maxGameSize = Math.max(this.gameWidth, this.gameHeight);
        return (double)minScreenSize / (double)maxGameSize;
    }
    
    private int getReelX(int x) {
        return (int)(x * this.reelSize);
    }
    
    private int getReelY(int y) {
        return (int)((this.gameHeight - y - 1) * this.reelSize);
    }
    
    @Override
    public void paint(Graphics g) {
        this.width = getWidth();
        this.height = getHeight();
        this.reelSize = getReelSize();
        this.elementSize = (int)this.reelSize + 1;

        clearScreen(g);
        // g.translate(this.width / 2, 0);
        draw(g);
        // g.translate(- this.width / 2, 0);
        paintChildren(g);
    }

    private void clearScreen(Graphics g) {
        g.setColor(BACKGROUND_COLOR);
        g.fillRect(0, 0, this.width, this.height);
    }

    private void draw(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(
            0,
            0,
            getReelX(this.gameWidth),
            getReelY(-1)
        );

        this.elements.forEach(element -> drawElement(g, element));
    }

    private void drawElement(Graphics g, Element element) {
        Vector2 position = element.getPosition();
        g.setColor(element.getColor());
        g.fillRect(
            getReelX(position.x),
            getReelY(position.y),
            this.elementSize,
            this.elementSize
        );
    }

}
