module org.snake.snake {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;


    opens org.snake.snake to javafx.fxml;
    exports org.snake.snake;
}