package org.snake.snake;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Block extends Rectangle {

    public Block(double x, double y, double width, double length) {
        super(width, length);
        this.setTranslateX(x);
        this.setTranslateY(y);
    }

    public boolean collide(Shape shape) {
        Shape collidedArea = Shape.intersect(this,shape);
        return collidedArea.getBoundsInLocal().getWidth() != -1;
    }
}
