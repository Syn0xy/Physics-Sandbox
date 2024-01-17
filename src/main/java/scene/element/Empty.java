package scene.element;

import java.awt.Color;

import scene.GameScene;

public class Empty extends Element {
    
    public Empty() {
        this.color = Color.GRAY;
    }

    @Override
    public void update(GameScene gameScene, int x, int y) {
        return;
    }

}
