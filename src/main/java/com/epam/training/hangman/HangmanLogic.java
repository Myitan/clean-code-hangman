package com.epam.training.hangman;

import java.util.ArrayList;
import java.util.List;

public class HangmanLogic {
    public String word;
    public List<Character> lettersTried;
    public int wrongGuessCount;

    public String state;
    public HangmanLogic(String w) {
        if (w == null) throw new RuntimeException("Error: an error occurred");
        char[] abc = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        int b = 0;
        for (int c = 0; c < w.length(); c++) {
            for (int a = 0; a < 26; a++) {
                if (abc[a] == w.charAt(c)) b = b + 1;
            }
        }
        if (w.equals("") || b < w.length()) throw new IllegalArgumentException("Error: an error occurred");

        this.word = w;
        lettersTried = new ArrayList<>();
        wrongGuessCount = 0;
    }

    public void guess(char c) {
        // valid?
        char[] abc = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        boolean v = false;
        for (int a = 0; a < 26; a++) {
            if (abc[a] == c) v = true;
        }
        if (!v || lettersTried.contains(c)) {
            throw new IllegalArgumentException("Error: an error occurred");
        }

        // logic
        lettersTried.add(c);
        if (word.contains(String.valueOf(c))) {
            int a = 0;
            for (int y = 0; y < word.length(); y++) {
                for (int x = 0; x < lettersTried.size(); x++)
                    if (!(lettersTried.get(x) != word.charAt(y))) {
                        a = a + 1;
                    }
            }
            if (a == word.length()) {state = "Won";}
            else {state = "In progress";}
        } else {
            wrongGuessCount++;
            if (wrongGuessCount >= 7) {
                state = "Lost";
            } else {
                state = "In progress";
            }
        }
    }

    public String getDisplayedWord() {
        if(state == "Lost"){
            return word;
        }
        String dw = "";
        for (int q = 0; q < word.length(); q++) {
            if (lettersTried.contains(word.charAt(q))) {
                dw = dw + word.charAt(q);
            } else {
                dw = dw + '_';
            }
        }
        return dw;
    }

    public State getState() {
        if (state == "Won") return State.WON;
        else if (state == "Lost") return State.LOST;
        else return State.IN_PROGRESS;
    }

    public List<Character> getLettersTried() {
        return lettersTried;
    }

    public int getWrongGuessesLeft() {
        return 7 - wrongGuessCount;
    }
}