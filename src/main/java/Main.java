import scene.GameScene;
import view.GameView;

public class Main{
    public static void main(String[] args) {
        GameScene gameScene = new GameScene();
        new GameView(gameScene);
        gameScene.start();
    }
}