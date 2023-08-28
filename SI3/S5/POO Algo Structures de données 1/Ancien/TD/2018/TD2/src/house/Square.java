package house;

import java.awt.*;

/**
 * A square that can be manipulated and that draws itself on a canvas.
 * 
 * @author Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

class Square {
    private int size;
    private int xPosition;
    private int yPosition;
    private String color;
    private boolean isVisible;

    /**
     * Create a new square at default position with default color.
     */
    Square() {
        size = 60;
        xPosition = 310;
        yPosition = 120;
        color = "red";
        isVisible = false;
    }

    /**
     * Make this square visible. If it was already visible, do nothing.
     */
    void makeVisible() {
        isVisible = true;
        draw();
    }

    /**
     * Make this square invisible. If it was already invisible, do nothing.
     */
    void makeInvisible() {
        erase();
        isVisible = false;
    }

    /**
     * Move the square a few pixels to the right.
     */
    void moveRight() {
        moveHorizontal(20);
    }

    /**
     * Move the square a few pixels to the left.
     */
    void moveLeft() {
        moveHorizontal(-20);
    }

    /**
     * Move the square a few pixels up.
     */
    void moveUp() {
        moveVertical(-20);
    }

    /**
     * Move the square a few pixels down.
     */
    void moveDown() {
        moveVertical(20);
    }

    /**
     * Move the square horizontally by 'distance' pixels.
     */
    void moveHorizontal(int distance) {
        erase();
        xPosition += distance;
        draw();
    }

    /**
     * Move the square vertically by 'distance' pixels.
     */
    void moveVertical(int distance) {
        erase();
        yPosition += distance;
        draw();
    }

    /**
     * Slowly move the square horizontally by 'distance' pixels.
     */
    void slowMoveHorizontal(int distance) {
        int delta;

        if (distance < 0) {
            delta = -1;
            distance = -distance;
        } else {
            delta = 1;
        }

        for (int i = 0; i < distance; i++) {
            xPosition += delta;
            draw();
        }
    }

    /**
     * Slowly move the square vertically by 'distance' pixels.
     */
    void slowMoveVertical(int distance) {
        int delta;

        if (distance < 0) {
            delta = -1;
            distance = -distance;
        } else {
            delta = 1;
        }

        for (int i = 0; i < distance; i++) {
            yPosition += delta;
            draw();
        }
    }

    /**
     * Change the size to the new size (in pixels). Size must be >= 0.
     */
    void changeSize(int newSize) {
        erase();
        size = newSize;
        draw();
    }

    /**
     * Change the color. Valid colors are "red", "yellow", "blue", "green",
     * "magenta" and "black".
     */
    void changeColor(String newColor) {
        color = newColor;
        draw();
    }

    /**
     * Draw the square with current specifications on screen.
     */
    private void draw() {
        if (isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, color, new Rectangle(xPosition, yPosition, size,
                    size));
            canvas.wait(10);
        }
    }

    /**
     * Erase the square on screen.
     */
    private void erase() {
        if (isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }
}
