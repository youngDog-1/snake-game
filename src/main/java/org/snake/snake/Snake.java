package org.snake.snake;

import javafx.scene.paint.Color;

import java.util.LinkedList;
import java.util.List;

import static org.snake.snake.SnakeApplication.*;

public class Snake {
    private List<Block> snake;
    private double directionX;
    private double directionY;

    public Snake(Block startBlock) {
        this.snake = new LinkedList<>();
        this.grow(startBlock);
        this.directionX = 0;
        this.directionY = TILE_SIZE;
    }

    public int getSize() {
        return snake.size();
    }

    public List<Block> getSnake() {
        return this.snake;
    }

    public Block getHead() {
        return this.snake.getFirst();
    }

    public Block grow(Block block) {
        Block part = new Block(-99,-99,block.getWidth(),block.getHeight());
        part.setFill(Color.AZURE);
        this.snake.add(part);
        return part;
    }

    public void move() {
        Block head = this.snake.getFirst();
        double currX = head.getTranslateX();
        double currY = head.getTranslateY();
        double x = head.getTranslateX()+this.directionX;
        double y = head.getTranslateY()+this.directionY;
        head.setTranslateX(x);
        head.setTranslateY(y);

        if(x>=WIDTH) head.setTranslateX(0);
        if(x<0) head.setTranslateX(WIDTH);
        if(y>=HEIGHT) head.setTranslateY(0);
        if(y<0) head.setTranslateY(HEIGHT);

        for(Block part : this.snake.subList(1,this.snake.size())) {
            double tempX = currX, tempY = currY;
            currX = part.getTranslateX();
            currY = part.getTranslateY();
            part.setTranslateX(tempX);
            part.setTranslateY(tempY);
        }
//        System.out.println(this.directionX + " " + this.directionY);
//        System.out.println(x + " " + y);

    }

    public void turnRight() {
        turnLeft();
        if(this.directionX!=0) this.directionX = -this.directionX;
        if(this.directionY!=0) this.directionY = -this.directionY;
    }

    public void turnLeft() {
        if(this.directionX==0 && this.directionY==TILE_SIZE) {
            this.directionX = TILE_SIZE;
            this.directionY = 0;
        } else if(this.directionX==TILE_SIZE && this.directionY==0) {
            this.directionX = 0;
            this.directionY = -TILE_SIZE;
        } else if(this.directionX==0 && this.directionY==-TILE_SIZE) {
            this.directionX = -TILE_SIZE;
            this.directionY = 0;
        } else if(this.directionX==-TILE_SIZE && this.directionY==0) {
            this.directionX = 0;
            this.directionY = TILE_SIZE;
        }
    }

}
