import java.util.Random;
import java.util.Scanner;

public class WordGuessingGame {

    // Array of words to choose from
    private static final String[] WORDS = {"elephant", "giraffe", "penguin", "kangaroo", "rhinoceros"};
    private static final int MAX_ATTEMPTS = 7;

    public static void main(String[] args) {
        WordGuessingGame game = new WordGuessingGame();

        String wordToGuess = game.selectRandomWord();
        String hiddenWord = game.hideWord(wordToGuess);
        int attemptsLeft = MAX_ATTEMPTS;

        System.out.println("Welcome to the Word Guessing Game!");

        while (attemptsLeft > 0 && hiddenWord.contains("|...|")) {
            System.out.println("Word to guess: " + hiddenWord);
            System.out.println("Attempts left: " + attemptsLeft);

            char guess = game.getPlayerGuess();

            if (game.isGuessCorrect(wordToGuess, guess)) {
                hiddenWord = game.updateHiddenWord(wordToGuess, hiddenWord, guess);
                System.out.println("Correct guess!");
            } else {
                attemptsLeft--;
                System.out.println("Incorrect guess!");
            }
        }

        game.displayGameResult(wordToGuess, hiddenWord, attemptsLeft);
    }

    // Select a random word from the WORDS array
    public String selectRandomWord() {
        Random random = new Random();
        return WORDS[random.nextInt(WORDS.length)];
    }

    // Hide the word by returning underscores
    public String hideWord(String word) {
        StringBuilder hidden = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            hidden.append("_");
        }
        return hidden.toString();
    }

    // Get the player's guess
    public char getPlayerGuess() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your guess: ");
        return scanner.next().charAt(0);
    }

    // Check if the guess is correct
    public boolean isGuessCorrect(String word, char guess) {
        return word.indexOf(guess) >= 0;
    }

    // Update the hidden word with the correctly guessed characters
    public String updateHiddenWord(String word, String hiddenWord, char guess) {
        StringBuilder updatedHiddenWord = new StringBuilder(hiddenWord);
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == guess) {
                updatedHiddenWord.setCharAt(i, guess);
            }
        }
        return updatedHiddenWord.toString();
    }

    // Display the game result
    public void displayGameResult(String wordToGuess, String hiddenWord, int attemptsLeft) {
        if (hiddenWord.equals(wordToGuess)) {
            System.out.println("Congratulations! You guessed the word: " + wordToGuess);
        } else {
            System.out.println("Game over! You've run out of attempts.");
            System.out.println("The word was: " + wordToGuess);
        }
    }
}