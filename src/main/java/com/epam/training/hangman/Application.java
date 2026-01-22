package com.epam.training.hangman;

import com.epam.training.hangman.game.HangmanLogic;
import com.epam.training.hangman.io.UserInputHandler;
import com.epam.training.hangman.model.GameUI;
import com.epam.training.hangman.model.State;
import com.epam.training.hangman.model.WordProvider;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        WordProvider wordProvider = new WordProvider();
        String targetWord = wordProvider.getRandomWord();
        HangmanLogic game = new HangmanLogic(targetWord);

        try (Scanner scanner = new Scanner(System.in)) {
            UserInputHandler inputHandler = new UserInputHandler(scanner);
            GameUI.displayWelcomeMessage();
            playGame(game, inputHandler);
            GameUI.displayGameResult(game);
        }
    }

    private static void playGame(HangmanLogic game, UserInputHandler inputHandler) {
        while (game.getState() == State.IN_PROGRESS) {
            GameUI.displayGameState(game);
            char guess = inputHandler.getValidGuess();

            try {
                game.guess(guess);
            } catch (IllegalArgumentException e) {
                GameUI.displayError(e.getMessage());
            }
        }
    }
}