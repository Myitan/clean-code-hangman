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
}