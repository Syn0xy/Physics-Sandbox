package view.gui;

import java.awt.Color;
import java.awt.Graphics;

public abstract class GUIComponent extends GUIContainer {

    private static final Color DEFAULT_COLOR = Color.LIGHT_GRAY;

    protected Color color;
    
    protected boolean hover;

    protected GUIComponent(int positionx, int positiony, int width, int height){
        super(positionx, positiony, width, height);
    }

    protected GUIComponent(int width, int height){
        super(width, height);
    }

    protected GUIComponent(){
        this.color = DEFAULT_COLOR;
    }

    protected abstract void paint(Graphics graphics, GUIContainer container);

    public void setColor(Color color){
        this.color = color;
    }
    
    protected boolean isHover(int mx, int my){
        return mx >= positionx && mx <= positionx + width && my >= positiony && my <= positiony + height;
    }
}
