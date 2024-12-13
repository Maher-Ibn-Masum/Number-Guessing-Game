// Derived class
public class NumberGuessingGame extends Game {

    public void playGame() {
        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("You have " + remainingTries + " tries to guess the number between 1 and " + MAX_NUMBER + ".");

        while (remainingTries > 0) {
            showHint();
            int guess = getGuess();
            if (guess == targetNumber) {
                System.out.println("Congratulations! You guessed the number in " + (MAX_TRIES - remainingTries + 1) + " tries.");
                askToRetry();
                return;
            }
            previousGuess = guess;
            remainingTries--;
        }

        System.out.println("You ran out of tries. The number was " + targetNumber + ".");
        askToRetry();
    }

    private void showHint() {
        if (previousGuess != 0) {
            if (Math.abs(targetNumber - previousGuess) <= 10) {
                if (!closeGuess) {
                    System.out.println("Ummm! Smelling success.");
                    closeGuess = true;
                }
            } else if (closeGuess) {
                System.out.println("You are getting away.");
                closeGuess = false;
            }
            switch (MAX_TRIES - remainingTries) {
                case 0:
                    if (targetNumber % 2 == 0) {
                        System.out.println("Hint: The number is " + (previousGuess < targetNumber ? "greater" : "less") + " than " + previousGuess + " and it is an even one.");
                    } else {
                        System.out.println("Hint: The number is " + (previousGuess < targetNumber ? "greater" : "less") + " than " + previousGuess + " and it is an odd one like you.");
                    }
                    break;
                case 1:
                    if (isPrime(previousGuess) == isPrime(targetNumber)) {
                        if (isPrime(targetNumber)) {
                            int lowerBound = Math.max(1, targetNumber - 15);
                            int upperBound = Math.min(MAX_NUMBER, targetNumber + 15);
                            System.out.println("Ooh! It surely is a prime number but think between " + lowerBound + " and " + upperBound + ".");
                        } else {
                            int divisor = smallestDivisor(targetNumber);
                            System.out.println("On the right track! It surely is not a prime number and it is divisible by " + divisor + ".");
                        }
                    } else {
                        System.out.println("Hint: The number is " + (isPrime(targetNumber) ? "a prime number" : "not a prime number") + ", unlike your guess.");
                    }
                    break;
                case 2:
                    System.out.println("Hint: The sum of the digits of the number is " + sumOfDigits(targetNumber));
                    break;
                case 3:
                    int lowerBound = Math.max(1, targetNumber - 5);
                    int upperBound = Math.min(MAX_NUMBER, targetNumber + 5);
                    System.out.println("Hint: The number is between " + lowerBound + " and " + upperBound);
                    break;
                case 4:
                    System.out.println("Final Hint: The unit place digit of the number is " + (targetNumber % 10));
                    break;
            }
        }
    }

    private int smallestDivisor(int number) {
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return i;
            }
        }
        return number;
    }

    public static void main(String[] args) {
        NumberGuessingGame game = new NumberGuessingGame();
        game.playGame();
    }
}
