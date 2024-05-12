package model.element;

import java.awt.Color;

import model.scene.GameScene;
import utils.Vector2;

public class Water extends Element {

    private boolean freeFalling;
    
    public Water(Vector2 position) {
        super(position);
        this.freeFalling = true;
    }

    @Override
    protected Color initColor() {
        return variation(new Color(0, 200, 255), 20, 20, 50);
    }

    @Override
    public void update(GameScene gameScene) {
        // int ox = 0;
        // int oy = 0;
        // boolean lx = gameScene.isEmpty(x - 1, y);
        // boolean rx = gameScene.isEmpty(x + 1, y);

        // gameScene.freeFall(x, y);
        // if(rx){
        //     ox = 1;
        // }else if(lx){
        //     ox = - 1;
        // }
        // gameScene.switchCase(x, y, ox, oy);
    }
}
