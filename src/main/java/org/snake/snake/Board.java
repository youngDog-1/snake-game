package org.snake.snake;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

import static org.snake.snake.SnakeApplication.TILE_SIZE;
import static org.snake.snake.SnakeApplication.TOTAL_TILES;

public class Board {
    List<List<Block>> board;

    public Board() {
        this.board = new ArrayList<>(TOTAL_TILES);
    }

    public Block getBlock(int col, int row) {
        return this.board.get(col).get(row);
    }

    public Pane getView() {
        Pane view = new Pane();
        for (int i = 0; i < TOTAL_TILES; i++) {
            this.board.add(i, new ArrayList<>());
            for (int j = 0; j < TOTAL_TILES; j++) {
                Block block = new Block(i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                block.setFill(Color.GRAY);
                block.setStroke(Color.WHITE);
                block.setStrokeWidth(0.5);

                this.board.get(i).add(block);
                view.getChildren().add(block);
            }
        }
        return view;
    }
}
