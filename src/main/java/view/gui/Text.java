package view.gui;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class Text extends GUIComponent {

    private static final String DEFAULT_FONT = Font.SERIF;

    private static final int DEFAULT_FONT_STYLE = Font.PLAIN;

    private static final int DEFAULT_FONT_SIZE = 20;

    private static final TextHorizontalAlignment DEFAULT_TEXT_ANCHOR_X = TextHorizontalAlignment.LEFT;

    private static final TextVerticalAlignment DEFAULT_TEXT_ANCHOR_Y = TextVerticalAlignment.UPPER;

    private String fontName;

    private int fontStyle;

    private int fontSize;

    protected TextHorizontalAlignment horizontalAlignment;
    
    protected TextVerticalAlignment verticalAlignment;

    private String text;

    public Text(String text, TextHorizontalAlignment horizontalAlignment, TextVerticalAlignment verticalAlignment){
        this.fontName = DEFAULT_FONT;
        this.fontStyle = DEFAULT_FONT_STYLE;
        this.fontSize = DEFAULT_FONT_SIZE;
        this.horizontalAlignment = horizontalAlignment;
        this.verticalAlignment = verticalAlignment;
        this.text = text;
    }

    public Text(String text){
        this(text, DEFAULT_TEXT_ANCHOR_X, DEFAULT_TEXT_ANCHOR_Y);
    }

    public void setFontName(String fontName) {
        this.fontName = fontName;
    }

    public void setFontStyle(int fontStyle) {
        this.fontStyle = fontStyle;
    }

    public void setFontSize(int size) {
        this.fontSize = size;
    }

    public void setHorizontalAlignment(TextHorizontalAlignment horizontalAlignment) {
        this.horizontalAlignment = horizontalAlignment;
    }

    public void setVerticalAlignment(TextVerticalAlignment verticalAlignment) {
        this.verticalAlignment = verticalAlignment;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    @Override
    protected void paint(Graphics graphics, GUIContainer container) {
        Font font = new Font(fontName, fontStyle, fontSize);
        
        graphics.setColor(color);
        graphics.setFont(font);
        
        FontMetrics metrics = graphics.getFontMetrics(font);

        int px = container.positionx;
        int py = container.positiony;

        switch (horizontalAlignment) {
            case LEFT: break;
            case CENTER: px += (container.width - metrics.stringWidth(text)) / 2; break;
            case RIGHT: px += container.width - metrics.stringWidth(text); break;
        }

        switch (verticalAlignment) {
            case UPPER: py += metrics.getAscent(); break;
            case MIDDLE: py += (container.height - metrics.getHeight()) / 2 + metrics.getAscent(); break;
            case LOWER: py += container.height - metrics.getAscent() / 2; break;
        }
        
        graphics.drawString(text, px, py);
    }

}
