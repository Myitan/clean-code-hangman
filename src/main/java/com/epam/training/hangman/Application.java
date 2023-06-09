package com.epam.training.hangman;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Application extends Object {
    public String word;
    public List<Character> lettersTried;
    public int wrongGuessCount;

    public static void main(String[] args) {
        // Init
        // Warning: don't close this Scanner, that would close System.in as well,
        // making it inaccessible throughout the rest of the program.
        Scanner scnr = new Scanner(System.in, StandardCharsets.UTF_8);
        Random r = new Random();
        String[] db = {"hangman", "apple", "bee", "clean", "computer", "office", "recursion"};
        String w = db[r.nextInt(db.length)];
        HangmanLogic l = new HangmanLogic(w);
        System.out.println("Welcome to the Hangman game!");
        System.out.println();

        // main cycle
        String s;
        do {
            System.out.println("The word: " + l.getDisplayedWord());
            System.out.println("Letters tried: " + l.lettersTried);
            System.out.println("Wrong guesses until game over: " + (7 - l.wrongGuessCount));
            // inner cycle
            while (true) {
                String in = "";
                boolean stay = true;
                while (stay) {
                    System.out.print("Enter your guess: ");
                    in = scnr.next();
                    System.out.println();
                    if (in.length() == 1) stay = false;
                    else {
                        System.out.println("Error: please enter a single character only!");
                        System.out.println();
                    }
                }
                try {
                    // guessing
                    l.guess(in.charAt(0));
                    break;
                } catch(Exception e) {
                    System.out.println(e.getMessage());
                    System.out.println();
                }
            }
        } while (l.state == "In progress");

        // end
        if (l.state == "Won") {
            System.out.println("Congratulations, you won!");
            System.out.println("The word was: " + w);
        } else {
            System.out.println("You have no more tries left, you lost the game");
            System.out.println("The word was: " + w);
        }
    }

}