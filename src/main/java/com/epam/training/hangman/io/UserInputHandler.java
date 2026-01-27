package com.epam.training.hangman.io;

import java.util.Scanner;

public class UserInputHandler {

    private final Scanner scanner;

    public UserInputHandler(Scanner scanner) {
        this.scanner = scanner;
    }

    public char getValidGuess() {
        while (true) {
            System.out.print("Enter your guess: ");
            String input = scanner.next().trim();

            if (input.length() != 1) {
                System.out.println("Error: please enter a single character only!");
                System.out.println();
                continue;
            }

            return input.charAt(0);
        }
    }

    public static void validateWord(String word) {
        if (word == null || word.isEmpty()) {
            throw new IllegalArgumentException("Word cannot be null or empty");
        }
        if (!word.matches("[a-zA-Z]+")) {
            throw new IllegalArgumentException("Word must contain only letters from English alphabet");
        }
    }

    public static void validateGuess(char letter) {
        if (!Character.isLetter(letter)) {
            throw new IllegalArgumentException("Guess must be a letter from English alphabet");
        }
    }
}