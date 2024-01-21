package view.gui;

public class GUIContainer {

    private static final int DEFAULT_POSITION_X = 0;

    private static final int DEFAULT_POSITION_Y = 0;

    private static final int DEFAULT_WIDTH = 1;

    private static final int DEFAULT_HEIGHT = 1;

    protected int positionx;

    protected int positiony;

    protected int width;

    protected int height;

    protected GUIContainer(int positionx, int positiony, int width, int height){
        this.positionx = positionx;
        this.positiony = positiony;
        this.width = width;
        this.height = height;
    }

    protected GUIContainer(int width, int height){
        this(DEFAULT_POSITION_X, DEFAULT_POSITION_Y, width, height);
    }

    protected GUIContainer(){
        this(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public int getPositionx() {
        return positionx;
    }

    public int getPositiony() {
        return positiony;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setPositionx(int positionx) {
        this.positionx = positionx;
    }

    public void setPositiony(int positiony) {
        this.positiony = positiony;
    }

    public void setPosition(int positionx, int positiony){
        this.positionx = positionx;
        this.positiony = positiony;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setSize(int width, int height){
        this.width = width;
        this.height = height;
    }
    
}
