package org.snake.snake;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import static org.snake.snake.SnakeApplication.TILE_SIZE;

public class Food extends Circle {

    public Food (Block block) {
        super(block.getTranslateX()+block.getWidth()/2,block.getTranslateY()+block.getHeight()/2, TILE_SIZE / 4);
        this.setFill(Color.RED);
    }
}
