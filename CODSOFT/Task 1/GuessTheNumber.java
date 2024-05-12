import java.util.Random;
import java.util.Scanner;

public class GuessTheNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int min = 1;
        int max = 100;
        int attemptsLimit = 5;
        int score = 0;
        System.out.println("Welcome to Guess the Number");
        boolean playAgain = true;
        while (playAgain) {
            int randomNumber = random.nextInt(max - min + 1) + min;
            int attempts = 0;
            boolean guessedCorrectly = false;
            System.out.println("\nl have seleted a random number between " + min + "and " + max + ".");
            System.out.println("You have" + attemptsLimit + "attempts to guess it");
            while (attempts < attemptsLimit && !guessedCorrectly) {
                System.out.println("\nEnter your guess");
                int guess = scanner.nextInt();
                attempts++;
                if (guess == randomNumber) {
                    System.out.println("Congratulations! You guessed the Number" + randomNumber + "correctly in"
                            + attempts + "attempts.");
                    guessedCorrectly = true;
                    score++;
                } else if (guess < randomNumber) {
                    System.out.println("Too Low !! Try again");
                } else {
                    System.out.println("Too High !! Try again");
                }
            }
            if (!guessedCorrectly) {
                System.out.println("Sorry. you uesd all the attempts.The correct number was" + randomNumber + ".");
            }
            System.out.println("\nDo you want to play again? (yes/no):");
            String playAgainInput = scanner.next();
            playAgain = playAgainInput.equalsIgnoreCase("yes");
        }
        System.out.println("\nThanks you for playing! your final score is:" + score);
        scanner.close();
    }
}
