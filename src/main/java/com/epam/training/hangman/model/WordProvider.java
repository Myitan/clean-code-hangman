package com.epam.training.hangman.model;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class WordProvider {

    private static final List<String> WORDS = Arrays.asList(
            "hangman", "apple", "bee", "clean", "computer", "office", "recursion"
    );

    private final Random random = new Random();

    public String getRandomWord(){
        return WORDS.get(random.nextInt(WORDS.size()));
    }
}
