package model.element;

import java.awt.Color;

import model.scene.GameScene;
import utils.Vector2;

public class Wall extends Element {
    
    public Wall(Vector2 position) {
        super(position);
    }

    @Override
    protected Color initColor() {
        return variation(Color.LIGHT_GRAY, 30, 30, 30);
    }

    @Override
    public void update(GameScene gameScene) {}

}
