package model.element;

import java.awt.Color;

import model.scene.GameScene;
import utils.Vector2;

public class Sand extends Element {

    private boolean freeFalling;
    
    public Sand(Vector2 position) {
        super(position);
        this.freeFalling = true;
    }

    @Override
    protected Color initColor() {
        return variation(Color.YELLOW, 80, 50, 50);
    }

    @Override
    public void update(GameScene gameScene) {
        if (this.position.y > 0) {
            this.position.y--;
        } else {
            if (Math.random() < 0.5) {
                this.position.x++;
            } else {
                this.position.x--;
            }
            if (Math.random() < 0.5) {
                this.position.y++;
            }
        }
        // int ox = 0;
        // int oy = 0;
        // boolean by = gameScene.isEmpty(x, y - 1);
        // boolean lx = gameScene.isEmpty(x - 1, y - 1);
        // boolean rx = gameScene.isEmpty(x + 1, y - 1);

        // if(freeFalling){
        //     if(by){
        //         oy = -1;
        //     }else if(lx && !rx){
        //         ox = 1;
        //         oy = -1;
        //     }else if(rx && !lx){
        //         ox = -1;
        //         oy = -1;
        //     }
        //     if(ox != 0 || oy != 0){
        //         gameScene.switchCase(x, y, ox, oy);
        //     }
        // }
        // freeFalling = gameScene.freeFall(x, y);
    }

}
