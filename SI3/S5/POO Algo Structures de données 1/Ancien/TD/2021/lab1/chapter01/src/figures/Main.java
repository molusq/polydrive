package figures;


/**
 * @author <a ref="mailto:sander@unice.fr">Peter Sander</a>
 * @author florian Latapie
 */
class Main {
    public static void main(String[] args) {
        /*Circle circle = new Circle();
        circle.makeVisible();

        Square square = new Square();
        square.makeVisible();

        Triangle triangle = new Triangle();
        triangle.makeVisible();


        Circle circle1 = new Circle();
        circle1.moveDown();
        circle1.moveDown();
        circle1.moveDown();
        circle1.makeVisible();
        circle1.makeInvisible();
        circle1.makeInvisible();
        circle1.makeVisible();
        //circle1.moveHorizontal(100);

        circle1.moveHorizontal(-20);
        circle1.changeColor("red");

        Circle circle2 = new Circle();
        circle2.changeColor("yellow");
        circle2.changeSize(200);
        circle2.makeVisible();*/

        Square square0 = new Square();
        square0.moveHorizontal(-310);
        square0.moveVertical(-120);
        square0.changeColor("blue");
        square0.changeSize(500);
        square0.makeVisible();

        Square square1 = new Square();
        square1.moveHorizontal(-100);
        square1.moveVertical(130);
        square1.changeColor("red");
        square1.changeSize(150);
        square1.makeVisible();

        Square square2 = new Square();
        square2.moveHorizontal(-75);
        square2.moveVertical(150);
        square2.changeColor("black");
        square2.changeSize(50);
        square2.makeVisible();

        Triangle triangle = new Triangle();
        triangle.moveHorizontal(75);
        triangle.moveVertical(60);
        triangle.changeColor("green");
        triangle.changeSize(50,200);
        triangle.makeVisible();

        Circle circle = new Circle();
        circle.moveHorizontal(125);
        circle.moveVertical(0);
        circle.changeColor("yellow");
        circle.changeSize(50);
        circle.makeVisible();
    }
}
