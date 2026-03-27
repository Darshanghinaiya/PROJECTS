import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

import java.util.Optional;

public class MemoryTaskGame extends Application {

    private final int gridSize = 3;
    private int level = 1;
    private int sequenceLength = 3;

    private int[] sequence;
    private int currentStep = 0;
    private boolean acceptingInput = false;
    private Rectangle[] squares;

    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        squares = new Rectangle[gridSize * gridSize];

        for (int i = 0; i < gridSize * gridSize; i++) {
            squares[i] = createSquare(i);
            int row = i / gridSize;
            int col = i % gridSize;
            grid.add(squares[i], col, row);
        }

        Scene scene = new Scene(grid, 300, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Memory Task Game");
        primaryStage.show();

        startGame();
    }

    private void startGame() {
        currentStep = 0;
        acceptingInput = false;

        sequenceLength = 2 + level; 
        sequence = new int[sequenceLength];
        generateSequence();

        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION,
                    "Level " + level + "\nClick OK to see the sequence",
                    ButtonType.OK);
            alert.setTitle("Start Level");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                showFullSequence();
            }
        });
    }

    private Rectangle createSquare(int id) {
        Rectangle square = new Rectangle(90, 90);
        square.setFill(Color.LIGHTGRAY);
        square.setStroke(Color.BLACK);
        square.setOnMouseClicked(e -> handleUserInput(id));
        return square;
    }

    private void generateSequence() {
        for (int i = 0; i < sequenceLength; i++) {
            sequence[i] = (int) (Math.random() * (gridSize * gridSize));
        }
    }

    private void showFullSequence() {
        acceptingInput = false;
        currentStep = 0;
        playSequence(0);
    }

    
    private void playSequence(int index) {
        if (index >= sequenceLength) {
            acceptingInput = true;
            return;
        }

        int id = sequence[index];
        flashSquare(squares[id], 400);

        PauseTransition pause = new PauseTransition(Duration.seconds(0.6));
        pause.setOnFinished(e -> playSequence(index + 1));
        pause.play();
    }


    private void flashSquare(Rectangle square, int duration) {
        square.setFill(Color.YELLOW);

        PauseTransition pause = new PauseTransition(Duration.millis(duration));
        pause.setOnFinished(e -> square.setFill(Color.LIGHTGRAY));
        pause.play();
    }

    private void flashColor(Rectangle square, Color color) {
        square.setFill(color);

        PauseTransition pause = new PauseTransition(Duration.millis(300));
        pause.setOnFinished(e -> square.setFill(Color.LIGHTGRAY));
        pause.play();
    }

    private void handleUserInput(int id) {
        if (!acceptingInput)
            return;

        if (id == sequence[currentStep]) {
            flashColor(squares[id], Color.LIMEGREEN);

            currentStep++;

            if (currentStep == sequenceLength) {
                level++;
                showWinningMessage();
            }
        } else {
            flashColor(squares[id], Color.RED);
            showError();
        }
    }

    private void showError() {
        acceptingInput = false;

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Wrong Move");
        alert.setHeaderText(null);
        alert.setContentText("Incorrect sequence! Restarting level...");

        alert.showAndWait();

        currentStep = 0;
        startGame();
    }

    private void showWinningMessage() {
        acceptingInput = false;

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Level Complete");
        alert.setHeaderText(null);
        alert.setContentText("Great! Moving to next level.");
        alert.showAndWait();

        startGame();
    }

    public static void main(String[] args) {
        launch(args);
    }
}