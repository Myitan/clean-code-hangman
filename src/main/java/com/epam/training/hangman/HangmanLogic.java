package com.epam.training.hangman;

import com.epam.training.hangman.model.State;

import java.util.ArrayList;
import java.util.List;

public class HangmanLogic {
    public static final int MAXGUESSCOUNT = 7;
    public final String targetWord;
    public final List<Character> guessedLetters;
    public int wrongGuessCount;
    public State state;

    public HangmanLogic(String word){
        validateWord(word);
        this.targetWord = word.toLowerCase();
        this.guessedLetters = new ArrayList<>();
        wrongGuessCount = 0;
        state = State.IN_PROGRESS;
    }

    public void guess(char letter) {
        validateGuess(letter);
        char normalizedLetter = Character.toLowerCase(letter);
        if (guessedLetters.contains(normalizedLetter)){
            throw new IllegalArgumentException("the provided character has already benn guessed");
        }
        guessedLetters.add(normalizedLetter);
        updateGameState(normalizedLetter);
    }

    private void validateWord(String word) {
        if (word == null || word.isEmpty()) {
            throw new IllegalArgumentException("Word cannot be null or empty");
        }
        if (!word.matches("[a-zA-Z]+")) {
            throw new IllegalArgumentException("Word must contain only letters from English alphabet");
        }
    }

    private void validateGuess(char letter) {
        if (!Character.isLetter(letter)) {
            throw new IllegalArgumentException("Guess must be a letter from English alphabet");
        }
    }

    public void updateGameState(char letter){
        if (targetWord.contains(String.valueOf(letter))) {
            checkWinCondition();
        } else {
            wrongGuessCount++;
            checkLossCondition();
        }
    }

    public void checkWinCondition(){
        boolean allLettersGuessed = true;
        for (char c : targetWord.toCharArray()){
            if (!guessedLetters.contains(c)){
                allLettersGuessed = false;
                break;
            }
        }
        if (allLettersGuessed){
            state = State.WON;
        }
    }

    public void checkLossCondition(){
        if (wrongGuessCount >= MAXGUESSCOUNT){
            state = State.LOST;
        }
    }
    public String getDisplayedWord(){
        if (state == State.LOST){
            return targetWord;
        }

        StringBuilder displayedWord = new StringBuilder();
        for (char c : targetWord.toCharArray()){
            if (guessedLetters.contains(c)){
                displayedWord.append(c);
            }else {
                displayedWord.append("_");
            }
        }
        return displayedWord.toString().trim();
    }

    public State getState() {
        return state;
    }

    public List<Character> getLettersTried() {
        return guessedLetters;
    }

    public int getWrongGuessesLeft() {
        return MAXGUESSCOUNT - wrongGuessCount;
    }

    public String getTargetWord(){ return targetWord; }
}