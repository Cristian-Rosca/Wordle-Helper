package com.tig.wordle.words;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class WordService {
    public List<Word> setUniformProbabilities(List<Word> wordList) {
        // For our list of words set probabilities to be equal
        // Placeholder
        Word currentWord;
        for (int i = 0; i < wordList.size(); i++) {
            currentWord = wordList.get(i);
            currentWord.setProbability(1.0 / wordList.size());
            wordList.set(i, currentWord);
        }
        return wordList;
    }

    public LinkedHashMap<String, String> generateWordPattern(Word word, Word targetWord) {
        // Initialise empty map
        LinkedHashMap<String, String> pattern = new LinkedHashMap<>();
        // Set arrays for the characters
        Character[] lettersInWord = new Character[word.getWord().length()];
        Character[] lettersInTarget = new Character[targetWord.getWord().length()];
        // Loop over and check for green letters
        for (int i = 0; i < lettersInWord.length; i++) {
            lettersInWord[i] = word.getWord().charAt(i);
            lettersInTarget[i] = targetWord.getWord().charAt(i);
            if (lettersInWord[i] == lettersInTarget[i]) {
                // Set this letter to green in the pattern
                pattern.put(String.valueOf(lettersInWord[i]) + i, "green");
                // Indicate these letters are spent
                lettersInWord[i] = null;
                lettersInTarget[i] = null;
            }
        }
        // Loop over and check for orange letters
        for (int i = 0; i < lettersInWord.length; i++) {
            if (lettersInWord[i] != null) {
                // Loop over letters in target word
                for (int j = 0; j < lettersInTarget.length; j++) {
                    if (lettersInWord[i] == lettersInTarget[j]) {
                        // Set this letter to orange in the pattern
                        pattern.put(String.valueOf(lettersInWord[i]) + i, "orange");
                        // Indicate these letters are spent
                        lettersInWord[i] = null;
                        lettersInTarget[j] = null;
                        break;
                    }
                }
            }
        }
        // Loop over and check for greys
        for (int i = 0; i < lettersInWord.length; i++) {
            if (lettersInWord[i] != null) {
                // Set letter to grey in pattern
                pattern.put(String.valueOf(lettersInWord[i]) + i, "grey");
            }
        }
        return pattern;
    }

    public boolean checkPatternMatch(Word word, Word targetWord, LinkedHashMap<String, String> pattern) {
        // Pattern for target word
        LinkedHashMap<String, String> newPattern = generateWordPattern(word, targetWord);
        // Compares patterns
        boolean result = pattern.equals(newPattern);
        return result;
    }

    public List<Word> findMatchingWords(Word guess, List<Word> wordList, LinkedHashMap<String, String> pattern) {
        // Initialise list of remaining words
        List<Word> remainingWords = new ArrayList<>();
        // Loop through word list
        for (int i = 0; i < wordList.size(); i++) {
            if (checkPatternMatch(guess, wordList.get(i), pattern)) {
                remainingWords.add(wordList.get(i));
            }
        }
        return remainingWords;
    }

    public Double computePatternProbability(Word guess, List<Word> wordList, LinkedHashMap<String, String> pattern) {
        // Initialise sum variable
        Double sum = 0.0;
        // Loop through remaining word list made using findMatchingWords method (above), get probabilities, and sum together
        List<Word> remainingWords = findMatchingWords(guess, wordList, pattern);
        for (Word remainingWord : remainingWords) {
            sum += remainingWord.getProbability();
        }
        return sum;
    }
}
