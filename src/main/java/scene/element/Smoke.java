package scene.element;

import java.awt.Color;

import scene.GameScene;

public class Smoke extends Element {
    
    public Smoke() {
        this.color = variation(Color.WHITE, 50, 50, 50);
    }

    @Override
    public void update(GameScene gameScene, int x, int y) {
        if(gameScene.isEmpty(x, y + 1)){
            gameScene.switchCase(x, y, 0, 1);           
        }
    }

}
