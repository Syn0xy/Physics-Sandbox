package view;

import java.awt.Color;
import java.awt.Container;
import java.util.ArrayList;
import java.util.List;

import scene.GameScene;
import scene.Shape;
import scene.element.Element;
import scene.element.Empty;
import scene.element.Sand;
import scene.element.Smoke;
import scene.element.Wall;
import scene.element.Water;
import view.gui.Button;
import view.gui.GUI;
import view.gui.Panel;
import view.gui.Text;
import view.gui.TextHorizontalAlignment;
import view.gui.TextVerticalAlignment;

public class GameGUI extends GUI {
    
    private static Shape[] shapes = Shape.values();

    private static List<Class<? extends Element>> elements = new ArrayList<>(){{
        add(Empty.class);
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
                gameScene.setSelectedElement(element);
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
                gameScene.setSelectedShape(shape);
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
            gameScene.setAction(gameCanvas.getLocation(x, y));
        }
    }

}
