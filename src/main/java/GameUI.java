import java.util.Scanner;

public class GameUI {
    private final GameEngine engine;
    private final Scanner scanner;

    public GameUI(GameEngine engine, Scanner scanner) {
        this.engine = engine;
        this.scanner = scanner;
    }

    public void start() {
<<<<<<< HEAD
        while (!engine.isGameWon() && !engine.hasUserQuit()) {
            System.out.print("Guess a number between " + engine.getMin() + " and " + engine.getMax() + " (or negative to exit): ");
=======
        while (!engine.isGameWon() && !engine.isGameOver()) {
            System.out.print("Guess a number between " + engine.getMin() + " and " + engine.getMax() + ": ");
>>>>>>> e367776 (Implement max attempts logic and game over condition)
            int guess = Utils.readInt(scanner);

            GuessResult result = engine.makeGuess(guess);
            System.out.println(result.getMessage());
        }
    }
}
