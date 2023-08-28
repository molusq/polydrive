package figures;

/**
 * @author <a ref="mailto:sander@unice.fr">Peter Sander</a>
 */
class Main {
    public static void main(String[] args) {
        Circle circle = new Circle();
        circle.changeColor("yellow");
        circle.changeSize(60);
        circle.moveHorizontal(200);
        circle.moveVertical(50);
        circle.makeVisible();

        /*Circle circle_2 = new Circle();
        Circle circle1 = new Circle();
        circle1.moveHorizontal(100);
        circle1.moveVertical(100);
        circle1.changeSize(20);
        circle1.changeColor("red");
        circle1.makeVisible();
        Circle circle_2 = new Circle();
        circle_2.changeColor("yellow");
        circle_2.changeSize(200);
        circle_2.moveHorizontal(-100);
        circle_2.makeVisible();
        //circle1.makeInvisible();
        //circle1.makeInvisible();
        */
        Square square = new Square();
        square.changeSize(100);
        square.changeColor("red");
        square.moveHorizontal(60);
        square.moveVertical(130);
        square.makeVisible();

        Square square2 = new Square();
        square2.changeSize(30);
        square2.changeColor("black");
        square2.moveHorizontal(80);
        square2.moveVertical(150);
        square2.makeVisible();
        
        Triangle triangle = new Triangle();
      	//circle.slowMoveVertical(-100);
        triangle.changeColor("red");
        triangle.makeVisible();

        Triangle triangle2 = new Triangle();
        triangle2.moveHorizontal(50);
        triangle2.makeVisible();
    }
}
