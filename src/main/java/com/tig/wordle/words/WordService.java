package com.tig.wordle.words;

import java.util.List;

public class WordService {
    public List<Word> setUniformProbabilities(List<Word> wordList){
        // For our list of words set probabilities to be equal
        // Placeholder
        Word currentWord;
        for (int i = 0; i < wordList.size(); i++){
            currentWord = wordList.get(i);
            currentWord.setProbability(1.0/wordList.size());
            wordList.set(i, currentWord);
        }
        return wordList;
    }
}
