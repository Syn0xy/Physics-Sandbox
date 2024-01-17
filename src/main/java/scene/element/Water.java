package scene.element;

import java.awt.Color;

import scene.GameScene;

public class Water extends Element {

    private boolean freeFalling;
    
    public Water() {
        this.color = variation(new Color(0, 200, 255), 20, 20, 50);
        this.freeFalling = true;
    }

    @Override
    public void update(GameScene gameScene, int x, int y) {
        int ox = 0;
        int oy = 0;
        boolean lx = gameScene.isEmpty(x - 1, y);
        boolean rx = gameScene.isEmpty(x + 1, y);

        gameScene.freeFall(x, y);
        if(rx){
            ox = 1;
        }else if(lx){
            ox = - 1;
        }
        gameScene.switchCase(x, y, ox, oy);
    }
}
