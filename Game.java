import java.util.Random;
import java.util.Scanner;

// Base class
class Game {
    protected static final int MAX_NUMBER = 100;
    protected static final int MAX_TRIES = 5;
    protected int targetNumber;
    protected int remainingTries;
    protected final Random random;
    protected int previousGuess;
    protected boolean closeGuess;

    public Game() {
        random = new Random();
        targetNumber = random.nextInt(MAX_NUMBER) + 1;
        remainingTries = MAX_TRIES;
        previousGuess = random.nextInt(MAX_NUMBER) + 1;
        closeGuess = false;
    }

    protected int getGuess() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your guess: ");
        return scanner.nextInt();
    }

    protected void askToRetry() {
        System.out.println("Would you like to play again? (yes/no)");
        Scanner scanner = new Scanner(System.in);
        String response = scanner.next();
        if (response.equalsIgnoreCase("yes")) {
            NumberGuessingGame game = new NumberGuessingGame();
            game.playGame();
        } else {
            System.out.println("Thank you for playing!");
        }
    }

    protected int sumOfDigits(int number) {
        int sum = 0;
        while (number != 0) {
            sum = sum + number % 10;
            number = number / 10;
        }
        return sum;
    }

    protected boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}

