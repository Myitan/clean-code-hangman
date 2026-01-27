package com.epam.training.hangman;

import com.epam.training.hangman.io.UserInputHandler;

import java.util.ArrayList;
import java.util.List;


public class HangmanLogic {
    public static final int MAXGUESSCOUNT = 7;
    public final String targetWord;
    public final List<Character> guessedLetters;
    public int wrongGuessCount;
    public State state;

    public HangmanLogic(String word){
        UserInputHandler.validateWord(word);
        this.targetWord = word.toLowerCase();
        this.guessedLetters = new ArrayList<>();
        wrongGuessCount = 0;
        state = State.IN_PROGRESS;
    }

    public void guess(char letter) {
        UserInputHandler.validateGuess(letter);
        char normalizedLetter = normalizeLetter(letter);
        checkIfLetterAlreadyGuesed(normalizedLetter);
        addLetterToGuessedLetters(normalizedLetter);
        updateGameState(normalizedLetter);
    }

    private void addLetterToGuessedLetters(char normalizedLetter) {
        guessedLetters.add(normalizedLetter);
    }

    private char normalizeLetter(char letter) {
        return Character.toLowerCase(letter);
    }

    private void checkIfLetterAlreadyGuesed(char letter) {
        if (guessedLetters.contains(letter)){
            throw new IllegalArgumentException("the provided character has already benn guessed");
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
        return List.copyOf(guessedLetters);
    }

    public int getWrongGuessesLeft() {
        return MAXGUESSCOUNT - wrongGuessCount;
    }

    public String getTargetWord(){ return targetWord; }
}