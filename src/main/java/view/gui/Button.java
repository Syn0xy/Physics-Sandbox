package view.gui;

import java.awt.Color;
import java.awt.Graphics;

public class Button extends Text {

    private static final String DEFAULT_TEXT = Button.class.getSimpleName();

    private static final int DEFAULT_WIDTH = 80;
    
    private static final int DEFAULT_HEIGHT = 80;

    private static final boolean DEFAULT_FILL = false;

    private static final Color DEFAULT_BACKGROUND_COLOR = Color.BLACK;

    private static final Color DEFAULT_HOVER_COLOR = Color.WHITE;
    
    private Runnable action;

    protected boolean fill;

    private Color backgroundColor;

    private Color hoverColor;

    public Button(String text, Runnable action, int width, int height){
        super(text);
        this.action = action;
        this.width = width;
        this.height = height;
        this.hover = false;
        this.fill = DEFAULT_FILL;
        this.backgroundColor = DEFAULT_BACKGROUND_COLOR;
        this.hoverColor = DEFAULT_HOVER_COLOR;
    }

    public Button(String text, Runnable action){
        this(text, action, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
    
    public Button(Runnable action){
        this(DEFAULT_TEXT, action);
    }

    public void setAction(Runnable action) {
        this.action = action;
    }

    public void setFill(boolean fill) {
        this.fill = fill;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setHoverColor(Color hoverColor) {
        this.hoverColor = hoverColor;
    }

    public void action() {
        action.run();
    }

    protected boolean click(int mx, int my){
        if(hover){
            action();
            return true;
        }
        return false;
    }

    @Override
    protected void paint(Graphics graphics, GUIContainer container) {
        if(hover){
            graphics.setColor(hoverColor);
        }else{
            graphics.setColor(backgroundColor);
        }
        
        if(fill){
            graphics.fillRect(positionx, positiony, width, height);
        }else{
            graphics.drawRect(positionx, positiony, width - 1, height - 1);
        }
        
        super.paint(graphics, this);
    }

}
