package model.element;

import java.awt.Color;

import model.scene.GameScene;
import utils.Vector2;

public class Smoke extends Element {
    
    public Smoke(Vector2 position) {
        super(position);
    }

    @Override
    protected Color initColor() {
        return variation(Color.WHITE, 50, 50, 50);
    }

    @Override
    public void update(GameScene gameScene) {
        // if(gameScene.isEmpty(x, y + 1)){
        //     gameScene.switchCase(x, y, 0, 1);           
        // }
    }

}
