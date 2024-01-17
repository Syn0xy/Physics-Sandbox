package scene.element;

import java.awt.Color;

import scene.GameScene;

public class Sand extends Element {

    private boolean freeFalling;
    
    public Sand() {
        this.color = variation(Color.YELLOW, 80, 50, 50);
        this.freeFalling = true;
    }

    @Override
    public void update(GameScene gameScene, int x, int y) {
        int ox = 0;
        int oy = 0;
        boolean by = gameScene.isEmpty(x, y - 1);
        boolean lx = gameScene.isEmpty(x - 1, y - 1);
        boolean rx = gameScene.isEmpty(x + 1, y - 1);

        if(freeFalling){
            if(by){
                oy = -1;
            }else if(lx && !rx){
                ox = 1;
                oy = -1;
            }else if(rx && !lx){
                ox = -1;
                oy = -1;
            }
            if(ox != 0 || oy != 0){
                gameScene.switchCase(x, y, ox, oy);
            }
        }
        // freeFalling = gameScene.freeFall(x, y);
    }

}
