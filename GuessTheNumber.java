import javax.swing.*;
import java.util.Random;

public class GuessTheNumber  {
    public static void main(String[] args) {
        boolean playAgain = true;
        int totalScore = 0;

        while (playAgain) {
            // Select difficulty level
            String[] options = {"Easy (1-50)", "Medium (1-100)", "Hard (1-200)"};
            int difficulty = JOptionPane.showOptionDialog(null, "Select Difficulty Level", "Difficulty",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            int maxNumber;
            int maxAttempts;

            switch (difficulty) {
                case 0: // Easy
                    maxNumber = 50;
                    maxAttempts = 10;
                    break;
                case 1: // Medium
                    maxNumber = 100;
                    maxAttempts = 10;
                    break;
                case 2: // Hard
                    maxNumber = 200;
                    maxAttempts = 15;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "No difficulty selected. Exiting.");
                    return;
            }

            // Generate a random number
            Random random = new Random();
            int randomNumber = random.nextInt(maxNumber) + 1;
            int attempts = 0;
            boolean hasGuessedCorrectly = false;

            JOptionPane.showMessageDialog(null, "Guess the number between 1 and " + maxNumber + "!");

            // Game loop
            while (attempts < maxAttempts && !hasGuessedCorrectly) {
                String input = JOptionPane.showInputDialog("Enter your guess (Attempt " + (attempts + 1) + " of " + maxAttempts + "):");
                if (input == null) {
                    // User cancelled the input
                    JOptionPane.showMessageDialog(null, "Game cancelled.");
                    return;
                }

                try {
                    int guess = Integer.parseInt(input);
                    attempts++;

                    if (guess < 1 || guess > maxNumber) {
                        JOptionPane.showMessageDialog(null, "Please enter a number between 1 and " + maxNumber + ".");
                    } else if (guess < randomNumber) {
                        JOptionPane.showMessageDialog(null, "Too low! Try again.");
                    } else if (guess > randomNumber) {
                        JOptionPane.showMessageDialog(null, "Too high! Try again.");
                    } else {
                        hasGuessedCorrectly = true;
                        JOptionPane.showMessageDialog(null, "Congratulations! You guessed the number " + randomNumber + " in " + attempts + " attempts.");
                        totalScore += (maxAttempts - attempts + 1); // Score based on attempts
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.");
                }
            }

            if (!hasGuessedCorrectly) {
                JOptionPane.showMessageDialog(null, "Sorry, you've used all your attempts. The number was " + randomNumber + ".");
            }

            // Ask if the user wants to play again
            int response = JOptionPane.showConfirmDialog(null, "Your current score is: " + totalScore + ". Do you want to play again?", "Play Again", JOptionPane.YES_NO_OPTION);
            playAgain = (response == JOptionPane.YES_OPTION);
        }

        JOptionPane.showMessageDialog(null, "Thank you for playing! Your final score is: " + totalScore);
    }
}