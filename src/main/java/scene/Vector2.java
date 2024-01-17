package scene;

public class Vector2 {
    public int x;
    public int y;

    public Vector2(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Vector2(){
        this(0, 0);
    }

    public double distance(int x, int y){
        return Math.sqrt(Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2));
    }
}
