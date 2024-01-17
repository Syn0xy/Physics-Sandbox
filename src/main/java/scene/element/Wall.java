package scene.element;

import java.awt.Color;

import scene.GameScene;

public class Wall extends Element {
    
    public Wall() {
        this.color = variation(Color.LIGHT_GRAY, 30, 30, 30);
    }

    @Override
    public void update(GameScene gameScene, int x, int y) {
        return;
    }

}
