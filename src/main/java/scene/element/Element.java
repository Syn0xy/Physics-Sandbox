package scene.element;

import java.awt.Color;

import scene.GameScene;

public abstract class Element {

    protected Color color;
    
    public abstract void update(GameScene gameScene, int x, int y);

    public Color getColor() {
        return color;
    }
    
    protected Color variation(Color color, int va, int vg, int vb){
        int r = clamp((int)(Math.random() * va + color.getRed() - va / 2), 0, 255);
        int g = clamp((int)(Math.random() * vg + color.getGreen() - vg / 2), 0, 255);
        int b = clamp((int)(Math.random() * vb + color.getBlue() - vb / 2), 0, 255);
        return new Color(r, g, b);
    }

    private int clamp(int value, int min, int max){
        if(value < min){
            return min;
        }else if(value > max){
            return max;
        }
        return value;
    }
}