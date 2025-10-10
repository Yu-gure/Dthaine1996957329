public class GameEngine {
    private final int min;
    private final int max;
    private int target;
    private int attempts;
    private boolean gameWon;

    private boolean userQuit;

    private boolean hintsEnabled = true;
    private String lastHint = "";


    public GameEngine(int min, int max) {
        this.min = min;
        this.max = max;
        this.attempts = 0;
        this.gameWon = false;

        this.userQuit = false;

        this.hintsEnabled = true;

        reset();
    }

    public GuessResult makeGuess(int guess) {
        // Check if user wants to quit (negative number)
        if (guess < 0) {
            userQuit = true;
            return new GuessResult(false, "Exiting game...", attempts);
        }

        attempts++;
	//correct
        if (guess == target) {
            gameWon = true;
            return new GuessResult(true, "Correct! You guessed it in " + attempts + " attempts.", attempts);

        } else if (guess < target) {
    String hint = getHint(guess);
    String msg = "Too low! Try a higher number." + hint; // hint already includes leading space
    GuessResult result = new GuessResult(false, msg, attempts);
    result.setHint(hint.trim()); // optional: makes getHint() not start with a space
    return result;

} else { // guess > target
    String hint = getHint(guess);
    String msg = "Too high! Try a lower number." + hint;
    GuessResult result = new GuessResult(false, msg, attempts);
    result.setHint(hint.trim());
    return result;
}
}

    public void reset() {
        target = Utils.randomInt(min, max);
        attempts = 0;
        gameWon = false;
        userQuit = false;
        lastHint = "";
    }

    public boolean isGameWon() {
        return gameWon;
    }

    public boolean hasUserQuit() {
        return userQuit;
    }

    public int getAttempts() {
        return attempts;
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
        if (!hintsEnabled) {
            return "";
        }

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
