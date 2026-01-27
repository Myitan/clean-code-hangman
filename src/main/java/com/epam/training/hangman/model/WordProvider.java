package com.epam.training.hangman.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class WordProvider {
    private final List<String> words;
    private final Random random;

    public WordProvider() {
        this.words = Arrays.asList("hangman", "apple", "bee", "clean", "computer", "office", "recursion");
        this.random = new Random();
    }

    public WordProvider(List<String> words) {
        this.words = new ArrayList<>(words);
        this.random = new Random();
    }

    public String getRandomWord() {
        return words.get(random.nextInt(words.size()));
    }
}