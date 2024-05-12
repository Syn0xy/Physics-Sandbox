package view;

import java.awt.Container;
import java.util.ArrayList;
import java.util.List;

import model.Shape;
import model.element.Element;
import model.element.Sand;
import model.element.Smoke;
import model.element.Wall;
import model.element.Water;
import model.scene.GameScene;
import view.gui.Button;
import view.gui.GUI;
import view.gui.TextHorizontalAlignment;
import view.gui.TextVerticalAlignment;

public class GameGUI extends GUI {
    
    private static Shape[] shapes = Shape.values();

    private static List<Class<? extends Element>> elements = new ArrayList<>() {{
        add(Wall.class);
        add(Water.class);
        add(Smoke.class);
        add(Sand.class);
    }};
    
    private GameCanvas gameCanvas;
    
    private GameScene gameScene;
    
    public GameGUI(Container container, GameCanvas gameCanvas, GameScene gameScene){
        super(container);
        this.gameCanvas = gameCanvas;
        this.gameScene = gameScene;
    }

    @Override
    protected void init(){
        int crntButton = 0;
        
        for(Class<? extends Element> element : elements){
            Button elementButton = new Button(element.getSimpleName(), () -> {
                gameScene.setCurrentElement(element);
                System.out.println("Element selectionné: " + element.getSimpleName());
            });
            
            elementButton.setHorizontalAlignment(TextHorizontalAlignment.CENTER);
            elementButton.setVerticalAlignment(TextVerticalAlignment.MIDDLE);
            elementButton.setFill(true);
            int tox = (int)(elementButton.getWidth() * 1.2);
            int ox = tox - elementButton.getWidth();
            elementButton.setPosition(ox + tox * crntButton++, (int)(elementButton.getHeight() * 0.2));

            addComponent(elementButton);
        }

        crntButton = 0;
        for(Shape shape : shapes){
            Button shapeButton = new Button(shape.name().toLowerCase(), () -> {
                gameScene.setCurrentShape(shape);
                System.out.println("Forme selectionnée: " + shape);
            });

            shapeButton.setHorizontalAlignment(TextHorizontalAlignment.CENTER);
            shapeButton.setVerticalAlignment(TextVerticalAlignment.MIDDLE);
            shapeButton.setFill(true);
            int tox = (int)(shapeButton.getWidth() * 1.2);
            int ox = tox - shapeButton.getWidth();
            shapeButton.setPosition(ox + tox * crntButton++, (int)(shapeButton.getHeight() * 1.4));

            addComponent(shapeButton);
        }

        Button exitButton = new Button("Exit", () -> {
            System.exit(0);
        });

        exitButton.setHorizontalAlignment(TextHorizontalAlignment.CENTER);
        exitButton.setVerticalAlignment(TextVerticalAlignment.MIDDLE);
        exitButton.setPosition(container.getWidth() - exitButton.getWidth(), 0);

        addComponent(exitButton);
    }

    @Override
    public void mousePressed(int button, int x, int y) {
        if(button == LEFT_BUTTON){
            gameScene.action(gameCanvas.getLocation(x, y));
        }
    }

}
