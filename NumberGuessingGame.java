import java.util.Scanner;
import javax.swing.JOptionPane;

public class NumberGuessingGame {

    private static final int MAX_ATTEMPTS = 10;
    private static final int MAX_POINTS = 10;
    public static void main(String[] args) {
        // Generate a random number between 1 and 100
        int randomNumber = (int)(100 * Math.random()) + 1;

        int score = 0;
        int attempts = 0;

        while (attempts <= MAX_ATTEMPTS) {
            // Prompt the user to enter a number
            String numberString = JOptionPane.showInputDialog("Enter a number between 1 and 100: ");

            // Convert the string to a number
            int userGuess = Integer.parseInt(numberString);

            // Compare the user's guess to the random number
            if (userGuess == randomNumber) {
                JOptionPane.showMessageDialog(null,"Congratulations! You guessed the number correctly!");
                score = MAX_POINTS - attempts;
                break;
            } else if (userGuess > randomNumber) {
                JOptionPane.showMessageDialog(null,"Your guess is too high.");
            } else {
                JOptionPane.showMessageDialog(null,"Your guess is too low.");
            }

            attempts++;
        }

        if (attempts == MAX_ATTEMPTS) {
            JOptionPane.showMessageDialog(null,"Sorry, you ran out of attempts. The correct number was " + randomNumber);
        } else {
            JOptionPane.showMessageDialog(null,"You guessed the number correctly in " + attempts + " attempts. Your score is " + score);
        }

        // Prompt the user to play again
        String playAgain = JOptionPane.showInputDialog("Would you like to play again? (Y/N)");

        if (playAgain.toUpperCase().equals("Y")) {
            main(args);
        }
    }
}