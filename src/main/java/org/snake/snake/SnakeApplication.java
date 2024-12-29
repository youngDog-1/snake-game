package org.snake.snake;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class SnakeApplication extends Application {
    public static final int WIDTH= 600;
    public static final int HEIGHT= 600;
    public static final int TOTAL_TILES=15;
    public static final double TILE_SIZE = (double) WIDTH / TOTAL_TILES;

    @Override
    public void start(Stage stage) throws IOException {
        Board board = new Board();
        Pane pane = board.getView();
        pane.setPrefSize(WIDTH,HEIGHT);
        Text scoreText = new Text(10,20, "Points: 0");

        Snake snake = new Snake(board.getBlock((int) TOTAL_TILES/2,(int) TOTAL_TILES/2));
        Block b = board.getBlock((int) TOTAL_TILES/2+1,(int) TOTAL_TILES/2);

        pane.getChildren().add(snake.getHead());
        pane.getChildren().add(snake.grow(b));
        pane.getChildren().add(scoreText);

        List<Food> food = new ArrayList<>(1);

        Scene scene = new Scene(pane);

        scene.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.LEFT) {
                snake.turnLeft();
            }
            if(event.getCode() == KeyCode.RIGHT) {
                snake.turnRight();
            }
        });

        AtomicInteger scores = new AtomicInteger();

        new AnimationTimer() {
            private long lastUpdate = 0;
            private Random random = new Random();

            @Override
            public void handle(long now) {
                if(now-lastUpdate>=200000000) {
                    animate();
                    lastUpdate = now;
                }

//                Spawn food
                if (food.isEmpty() && random.nextDouble() < 0.01) {
                    int randCol = random.nextInt(TOTAL_TILES);
                    int randRow = random.nextInt(TOTAL_TILES);
                    Food f = new Food(board.getBlock(randCol,randRow));
//                    System.out.println(f.getTranslateX());
                    pane.getChildren().add(f);
                    food.add(f);
                }
            }

            private void animate() {
                snake.move();
//                Eat food
                if(!food.isEmpty() && snake.getHead().collide(food.getFirst())) {
                    pane.getChildren().remove(food.getFirst());
                    food.removeFirst();
                    pane.getChildren().add(snake.grow(snake.getHead()));
                    scoreText.setText("Points: " + scores.incrementAndGet());
                }
//
                for(Block part : snake.getSnake().subList(1,snake.getSize())) {
                    if(snake.getHead().collide(part)) {
                        pane.getChildren().add(new Text((double) WIDTH /2, (double) HEIGHT /2, "Game over"));
                        stop();
                    }
                }
            }
        }.start();

        stage.setTitle("Snake game");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}