import scene.GameScene;
import view.GameView;

public class Main{
    public static void main(String[] args) {
        GameScene gameScene = new GameScene(192 * 2, 108 * 2);
        new GameView(gameScene);
        gameScene.start();
    }
}