package house;

/**
 * This class represents a simple picture. You can draw the picture using
 * the draw method. But wait, there's more: being an electronic picture, it
 * can be changed. You can set it to black-and-white display and back to
 * colors (only after it's been drawn, of course).
 *
 * This class was written as an early example for teaching Java with BlueJ.
 * 
 * @author  Michael Koelling and David J. Barnes
 * @version 2016.02.29
 */
class Picture {
    private Square wall;
    private Square window;
    private Triangle roof;
    private Circle sun;
    private Circle sun2;
    private boolean drawn;

    /**
     * Constructor for objects of class Picture
     */
    Picture() {
        wall = new Square();
        window = new Square();
        roof = new Triangle();  
        sun = new Circle();
        sun2 = new Circle();
        drawn = false;
    }

    /**
     * Draw this picture.
     */
    void draw() {
        if (!drawn) {
            wall.moveHorizontal(-140);
            wall.moveVertical(20);
            wall.changeSize(120);
            wall.makeVisible();
            
            window.changeColor("black");
            window.moveHorizontal(-120);
            window.moveVertical(40);
            window.changeSize(40);
            window.makeVisible();
    
            roof.changeSize(60, 180);
            roof.moveHorizontal(20);
            roof.moveVertical(-60);
            roof.makeVisible();
    
            sun.changeColor("blue");
            sun.moveHorizontal(100);
            sun.moveVertical(-40);
            sun.changeSize(80);
            sun.makeVisible();

            sun2.changeColor("yellow");
            sun2.moveHorizontal(100);
            sun2.moveVertical(-40);
            sun2.changeSize(80);
            sun2.makeVisible();
            sun2.slowMoveVertical(300);
            drawn = true;
        }
    }

    /**
     * Change this picture to black/white display
     */
    void setBlackAndWhite() {
        wall.changeColor("black");
        window.changeColor("white");
        roof.changeColor("black");
        sun.changeColor("black");
    }

    /**
     * Change this picture to use color display
     */
    void setColor() {
        wall.changeColor("red");
        window.changeColor("black");
        roof.changeColor("green");
        sun.changeColor("yellow");
    }
}
