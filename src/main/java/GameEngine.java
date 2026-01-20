public class GameEngine {
    private static final int MAX_ATTEMPTS = 10;

    private final int min;
    private final int max;
    private int target;
    private int attempts;
    private boolean gameWon;

    private boolean userQuit;
    private boolean gameOver;

    private boolean hintsEnabled = true;
    private String lastHint = "";

    public GameEngine(int min, int max) {
        this.min = min;
        this.max = max;
        this.attempts = 0;
        this.gameWon = false;
        this.userQuit = false;
        this.gameOver = false;
        this.hintsEnabled = true;
        reset();
    }

    public GuessResult makeGuess(int guess) {
        // Quit (negative number)
        if (guess < 0) {
            userQuit = true;
            return new GuessResult(false, "Exiting game...", attempts);
        }

        // If game already ended
        if (gameOver || gameWon) {
            return new GuessResult(false, "Game Over. The number was " + target + ".", attempts);
        }

        attempts++;

        // Correct guess
        if (guess == target) {
            gameWon = true;
            return new GuessResult(true, "Correct! You guessed it in " + attempts + " attempts.", attempts);
        }

        // If last attempt used up, end game
        if (attempts >= MAX_ATTEMPTS) {
            gameOver = true;
            return new GuessResult(false, "Game Over. The number was " + target + ".", attempts);
        }

        int remaining = MAX_ATTEMPTS - attempts;

        // Wrong guess message + hint (uses your existing getHint thresholds)
        String hint = getHint(guess);
        String msg;

        if (guess < target) {
            msg = "Too low! Try a higher number. " + remaining + " attempts remaining" + hint;
        } else {
            msg = "Too high! Try a lower number. " + remaining + " attempts remaining" + hint;
        }

        GuessResult result = new GuessResult(false, msg, attempts);
        result.setHint(hint.trim());
        return result;
    }

    public void reset() {
        target = Utils.randomInt(min, max);
        attempts = 0;
        gameWon = false;
        userQuit = false;
        gameOver = false;
        lastHint = "";
    }

    public boolean isGameWon() {
        return gameWon;
    }

    public boolean hasUserQuit() {
        return userQuit;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public int getAttempts() {
        return attempts;
    }

    public int getMaxAttempts() {
        return MAX_ATTEMPTS;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public boolean isHintsEnabled() {
        return hintsEnabled;
    }

    public void setHintsEnabled(boolean enabled) {
        this.hintsEnabled = enabled;
    }

    private String getHint(int guess) {
        if (!hintsEnabled) return "";

        int diff = Math.abs(target - guess);
        if (attempts >= 3 && diff <= 10) {
            return " HINT: You're very close!";
        } else if (attempts >= 5 && diff <= 20) {
            return " HINT: Getting warmer!";
        }
        return "";
    }

    // For testing purposes only
    protected void setTarget(int target) {
        this.target = target;
    }

    protected int getTarget() {
        return target;
    }
}
