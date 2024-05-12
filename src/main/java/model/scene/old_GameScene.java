// package model.scene;

// import model.Shape;
// import model.element.Element;
// import model.element.Empty;
// import model.element.Sand;
// import model.element.Wall;
// import model.element.Water;
// import utils.Vector2;
// import view.GameView;

// public class old_GameScene extends Scene {
    
//     private static final int WIDTH = 200;

//     private static final int HEIGHT = 200;
    
//     private static final Class<? extends Element> DEFAULT_ELEMENT = Wall.class;

//     private static final Shape DEFAULT_SHAPE = Shape.PIXEL;
    
//     private static final int DIVIDE_SHAPE_SIZE = 8;

//     private Element[][] elements;

//     private Class<? extends Element> crntElement;
//     private Shape crntShape;
//     private int shapeSize;

//     public old_GameScene(int width, int height){
//         this.elements = new Element[width][height];
//         this.crntElement = DEFAULT_ELEMENT;
//         this.crntShape = DEFAULT_SHAPE;
//         this.shapeSize = width / DIVIDE_SHAPE_SIZE;
//         init();
//     }

//     public old_GameScene(){
//         this(WIDTH, HEIGHT);
//     }

//     public Element[][] getElements(){
//         return elements;
//     }

//     private void init(){
//         for (int i = 0; i < elements.length; i++) {
//             for (int j = 0; j < elements[i].length; j++) {
//                 elements[i][j] = new Empty();
//             }
//         }

//         int ox = elements.length / 4;
//         int mx = ox / 2;
//         for (int i = ox; i < elements.length - ox; i++) {
//             if(i < elements.length / 2 - mx || i > elements.length / 2 + mx){
//                 int oy = elements[i].length / 4;
//                 for (int j = oy; j < elements[i].length - oy; j++) {
//                     elements[i][j] = new Wall();
//                 }
//             }
//         }
//     }

//     public void setSelectedElement(Class<? extends Element> element){
//         crntElement = element;
//     }

//     public void setSelectedShape(Shape shape){
//         crntShape = shape;
//     }

//     public void setAction(Vector2 vec2){        
//         long crntTime = System.nanoTime();
//         switch(crntShape){
//             case CIRCLE: cercle(vec2); break;
//             case PIXEL: pixel(vec2); break;
//             case SQUARE: square(vec2); break;
//             case TRIANGLE: triangle(vec2); break;
//             default: break;
//         }
        
//         long finalTime = System.nanoTime() - crntTime;
//         System.out.println("Le temps prit pour dessiner [" + crntShape + "] taille [" + shapeSize + "] est de " + finalTime / 1000000.0 + " ms");
//     }
    
//     private void pixel(int x, int y){
//         if(isValidLocation(x, y) && (isEmpty(x, y) || crntElement == Empty.class)){
//             try{
//                 elements[x][y] = (Element)crntElement.getDeclaredConstructor().newInstance(new Object[0]);
//             }catch(Exception e){
//                 System.out.println("pas bon");
//             }
//         }
//     }

//     private void pixel(Vector2 vec2){
//         pixel(vec2.x, vec2.y);
//     }

//     private void square(Vector2 vec2){
//         int so = - shapeSize / 2;
//         int eo = shapeSize / 2 + 1;
//         for (int i = so; i < eo; i++) {
//             for (int j = so; j < eo; j++) {
//                 int px = i + vec2.x;
//                 int py = j + vec2.y;
//                 pixel(px, py);
//             }
//         }
//     }

//     private void cercle(Vector2 vec2){
//         int radius = shapeSize / 2;
//         int so = - radius;
//         int eo = radius + 1;
//         for (int i = so; i < eo; i++) {
//             for (int j = so; j < eo; j++) {
//                 int px = i + vec2.x;
//                 int py = j + vec2.y;
//                 if(vec2.distance(px, py) < radius){
//                     pixel(px, py);
//                 }
//             }
//         }
//     }

//     private void triangle(Vector2 vec2){
//         int eo = shapeSize + 1;
//         for (int i = 0; i < eo; i++) {
//             for (int j = 0; j < eo; j++) {
//                 if(isInside(shapeSize, i, j)){
//                     int px = i + vec2.x - shapeSize / 2;
//                     int py = j + vec2.y - shapeSize / 2;
//                     pixel(px, py);
//                 }
//             }
//         }
//     }

//     public boolean isInside(int triangleSize, int x, int y){
//         return isInside(triangleSize, x, y, 0, 0, triangleSize / 2, triangleSize, triangleSize, 0);
//     }

//     public boolean isInside(int triangleSize, int x, int y, int ax, int ay, int bx, int by, int cx, int cy){
//         float denominator = (by - cy)*(ax - cx) + (cx - bx)*(ay - cy);
//         float lambda1 = ((by - cy)*(x - cx) + (cx - bx)*(y - cy)) / denominator;
//         float lambda2 = ((cy - ay)*(x - cx) + (ax - cx)*(y - cy)) / denominator;
//         float lambda3 = 1 - lambda1 - lambda2;

//         return 0 <= lambda1 && lambda1 <= 1 && 0 <= lambda2 && lambda2 <= 1 && 0 <= lambda3 && lambda3 <= 1;
//     }

//     private boolean isValidLocation(int x, int y){
//         return x < elements.length && x >= 0 && y < elements[x].length && y >= 0;
//     }

//     @Override
//     public void start(int framesPerSecond) {
//         super.start(framesPerSecond);
//         new GameView(this);
//     }

//     @Override
//     public void update() {
//         next();
//     }

//     public void next(){
//         for (int i = 0; i < elements.length; i++) {
//             for (int j = 0; j < elements[i].length; j++) {
//                 elements[i][j].update(this, i, j);
//             }
//         }

//         int ox = elements.length / 8;
//         int oy = (int)(elements[0].length / 1.25);

//         if(elements.length > 0){
//             Class<? extends Element> tmp = crntElement;
//             crntElement = Water.class;
//             pixel(elements.length / 8, oy);
//             crntElement = Sand.class;
//             pixel(elements.length - ox, oy);
//             pixel(elements.length - ox - 2, oy);
//             pixel(elements.length - ox + 2, oy);
//             crntElement = tmp;
//         }

//         notifyObservers();
//     }

//     public boolean isEmpty(int x, int y){
//         return isElement(Empty.class, x, y);
//     }

//     public boolean isSand(int x, int y){
//         return isElement(Sand.class, x, y);
//     }

//     public boolean isElement(Class<? extends Element> classes, int x, int y){
//         if(isValidLocation(x, y)){
//             return elements[x][y].getClass() == classes;
//         }
//         return false;
//     }

//     public boolean freeFall(int x, int y){
//         if(y > 0 && isEmpty(x, y - 1)){
//             return switchCase(x, y, 0, - 1);
//         }
//         return false;
//     }

//     public boolean switchCase(int x, int y, int ox, int oy){
//         if(isValidLocation(x, y)){
//             int px = x + ox;
//             int py = y + oy;
//             if(isValidLocation(px, py)){
//                 Element tmp = elements[px][py];
//                 elements[px][py] = elements[x][y];
//                 elements[x][y] = tmp;
//                 return true;
//             }
//         }
//         return false;
//     }
// }
