package com.epam.training.hangman.model;

import com.epam.training.hangman.HangmanLogic;
import com.epam.training.hangman.State;

public class GameUI {

    public static void displayWelcomeMessage() {
        System.out.println("Welcome to the Hangman game!");
        System.out.println();
    }

    public static void displayGameState(HangmanLogic game) {
        System.out.println("The word: " + formatDisplayedWord(game.getDisplayedWord()));
        System.out.println("Letters tried: " + game.getLettersTried());
        System.out.println("Wrong guesses until game over: " + game.getWrongGuessesLeft());
        System.out.println();
    }

    public static void displayGameResult(HangmanLogic game) {
        System.out.println();
        if (game.getState() == State.WON) {
            System.out.println("Congratulations, you won!");
            System.out.println("The word was: " + game.getTargetWord());
        } else {
            System.out.println("You have no more tries left, you lost the game");
            System.out.println("The word was: " + game.getTargetWord());
        }
        System.out.println();
    }

    private static String formatDisplayedWord(String word) {
        return word.replace("", " ").trim();
    }

    public static void displayError(String message) {
        System.out.println(message);
        System.out.println();
    }
}