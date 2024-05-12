package model.scene;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import model.Shape;
import model.element.Element;
import model.element.Sand;
import model.element.Wall;
import model.element.Water;
import utils.Vector2;
import view.GameView;

public class GameScene extends Scene {

    private static final int DEFAULT_WIDTH = 200;
    
    private static final int DEFAULT_HEIGHT = 200;

    private static final Class<? extends Element> DEFAULT_ELEMENT = Water.class;
    
    private static final Shape DEFAULT_SHAPE = Shape.PIXEL;
    
    private static final int DEFAULT_SHAPE_SIZE_RATIO = 8;

    private int width;
    
    private int height;

    private List<Element> elements;

    private Class<? extends Element> currentElement;

    private Shape currentShape;

    private int currentShapeSize;

    public GameScene(int width, int height) {
        this.width = width;
        this.height = height;
        this.elements = new CopyOnWriteArrayList<>();
        this.currentElement = DEFAULT_ELEMENT;
        this.currentShape = DEFAULT_SHAPE;
        this.currentShapeSize = width / DEFAULT_SHAPE_SIZE_RATIO;
        init();
    }

    public GameScene() {
        this(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
    public List<Element> getElements() {
        return elements;
    }

    public void setCurrentElement(Class<? extends Element> currentElement) {
        this.currentElement = currentElement;
    }

    public void setCurrentShape(Shape currentShape) {
        this.currentShape = currentShape;
    }

    public void setCurrentShapeSize(int currentShapeSize) {
        this.currentShapeSize = currentShapeSize;
    }

    @Override
    public void start(int framesPerSecond) {
        super.start(framesPerSecond);
        new GameView(this);
    }

    @Override
    public void update() {
        int ox = this.width / 8;
        int oy = (int)(this.height / 1.25);
        
        Class<? extends Element> tmp = currentElement;
        currentElement = Water.class;
        pixel(ox, oy);
        currentElement = Sand.class;
        pixel(this.width - ox, oy);
        pixel(this.width - ox - 2, oy);
        pixel(this.width - ox + 2, oy);
        currentElement = tmp;
        
        this.elements.forEach(element -> element.update(this));
    }

    private void init() {
        int ox = this.width / 4;
        int mx = ox / 2;
        for (int i = ox; i < this.width - ox; i++) {
            if(i < this.width / 2 - mx || i > this.width / 2 + mx){
                int oy = this.height / 4;
                for (int j = oy; j < this.height - oy; j++) {
                    this.elements.add(new Wall(new Vector2(i, j)));
                }
            }
        }
    }

    public void action(Vector2 vec2) {
        long startTime = System.nanoTime();
        
        switch (currentShape) {
            case PIXEL: pixel(vec2); break;
            case SQUARE: square(vec2); break;
            case CIRCLE: cercle(vec2); break;
            case TRIANGLE: triangle(vec2); break;
            default: break;
        }

        long endTime = System.nanoTime() - startTime;
        System.out.println("[" + currentShape + "] de taille [" + currentShapeSize + "] en " + (endTime / 1000000.0)  + " ms");
    }

    private void pixel(int x, int y) {
        this.pixel(new Vector2(x, y));
    }
    
    private void pixel(Vector2 vec2) {
        boolean isEmpty = elements.stream().anyMatch(element -> !element.getPosition().equals(vec2));
        
        if (isEmpty) {
            try {
                this.elements.add(
                    currentElement.getConstructor(Vector2.class).newInstance(vec2)
                );
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private void square(Vector2 vec2) {
        int sp = - currentShapeSize / 2;
        int ep = currentShapeSize / 2 + 1;
        
        for (int i = sp; i < ep; i++) {
            for (int j = sp; j < ep; j++) {
                pixel(vec2.plusCopy(i, j));
            }
        }
    }

    private void cercle(Vector2 vec2) {
        int radius = currentShapeSize / 2;
        int sp = - radius;
        int ep = radius + 1;

        for (int i = sp; i < ep; i++) {
            for (int j = sp; j < ep; j++) {
                Vector2 p = vec2.plusCopy(i, j);
                if (vec2.distance(p) < radius) {
                    pixel(p);
                }
            }
        }
    }

    private void triangle(Vector2 vec2) {
        int sp = - currentShapeSize / 2;
        
        for (int i = 0; i < currentShapeSize; i++) {
            for (int j = 0; j < currentShapeSize; j++) {
                if (isInside(currentShapeSize, i, j)) {
                    pixel(vec2.plusCopy(
                        sp + i,
                        sp + j
                    ));
                }
            }
        }
    }

        public boolean isInside(int triangleSize, int x, int y){
            return isInside(
                triangleSize, x, y,
                0, 0,
                triangleSize / 2, triangleSize,
                triangleSize, 0
            );
        }
    
        public boolean isInside(int triangleSize, int x, int y, int ax, int ay, int bx, int by, int cx, int cy){
            float denominator = (by - cy)*(ax - cx) + (cx - bx)*(ay - cy);
            float lambda1 = ((by - cy)*(x - cx) + (cx - bx)*(y - cy)) / denominator;
            float lambda2 = ((cy - ay)*(x - cx) + (ax - cx)*(y - cy)) / denominator;
            float lambda3 = 1 - lambda1 - lambda2;
    
            return 0 <= lambda1 && lambda1 <= 1 && 0 <= lambda2 && lambda2 <= 1 && 0 <= lambda3 && lambda3 <= 1;
        }

}
