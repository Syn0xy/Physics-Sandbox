package view.gui;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.swing.JPanel;

public abstract class GUI extends JPanel implements MouseListener, MouseMotionListener {

    protected static final int LEFT_BUTTON = 1;

    protected static final int MIDDLE_BUTTON = 2;
    
    protected static final int RIGHT_BUTTON = 3;
    
    protected static final int FIRST_SIDE_BUTTON = 4;
    
    protected static final int SECOND_SIDE_BUTTON = 5;

    protected GUIContainer container;
    
    private List<GUIComponent> components;
    
    public GUI(Container container){
        this.container = new GUIContainer(container.getWidth(), container.getHeight());
        this.components = new ArrayList<>();
        addMouseListener(this);
        addMouseMotionListener(this);
        init();
    }

    protected abstract void init();

    public void mousePressed(int button, int x, int y){}
    public void mouseClicked(int button, int x, int y){}
    public void mouseEntered(int x, int y){}
    public void mouseExited(int x, int y){}
    public void mouseReleased(int button, int x, int y){}
    public void mouseDragged(int button, int x, int y){}
    public void mouseMoved(int x, int y){}

    public boolean addComponent(GUIComponent component){
        return components.add(component);
    }

    public boolean removeComponent(GUIComponent component){
        return components.remove(component);
    }

    @Override
    public final void paint(Graphics g) {        
        for(GUIComponent component : components){
            component.paint(g, container);
        }
    }

    private boolean clickComponents(int mx, int my){
        GUIComponent hover = hoverComponents(mx, my);
        boolean isNotNull = hover != null;
        
        if(isNotNull && hover instanceof Button){
            return ((Button)hover).click(mx, my);
        }

        return isNotNull;
    }

    private GUIComponent hoverComponents(int mx, int my){
        Stack<GUIComponent> crntComponents = new Stack<>();
        GUIComponent lastComponent = null;
        
        for(GUIComponent component : components){
            component.hover = false;
            if(component.isHover(mx, my)){
                crntComponents.add(component);
            }
        }
        
        if(!crntComponents.isEmpty()){
            lastComponent = crntComponents.lastElement();
            lastComponent.hover = true;
        }

        return lastComponent;
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        if(!clickComponents(mx, my)){
            mousePressed(e.getButton(), mx, my);
        }
    }
    
    @Override
    public final void mouseClicked(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        mouseClicked(e.getButton(), mx, my);
    }

    @Override
    public final void mouseEntered(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        mouseEntered(mx, my);
    }

    @Override
    public final void mouseExited(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        mouseExited(mx, my);
    }

    @Override
    public final void mouseReleased(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        mouseReleased(e.getButton(), mx, my);
    }

    @Override
    public final void mouseDragged(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        mouseDragged(e.getButton(), mx, my);
    }

    @Override
    public final void mouseMoved(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        hoverComponents(mx, my);
        mouseMoved(mx, my);
    }
}
