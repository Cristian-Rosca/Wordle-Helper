package com.tig.wordle.words;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class WordServiceTest {
    private WordService underTest;
    private WordDAO wordDAO;

    @BeforeEach
    void setUp(){
        this.wordDAO = Mockito.mock(WordDAO.class);
        this.underTest = new WordService(wordDAO);
    }

    @Test
    void testGetAllRankedByScore(){
        // Given
        Word testWord1 = new Word(1, "hello", 0.25, 2.0);
        Word testWord2 = new Word(2, "image", 0.25, 1.0);
        Word testWord3 = new Word(3, "opens", 0.25, 0.5);
        Word testWord4 = new Word(4, "fares", 0.25, 0.25);
        List<Word> wordsOrderedByScore = new ArrayList<>();
        wordsOrderedByScore.add(testWord1);
        wordsOrderedByScore.add(testWord2);
        wordsOrderedByScore.add(testWord3);
        wordsOrderedByScore.add(testWord4);
        given(wordDAO.selectAllWordsRankedByScore()).willReturn(wordsOrderedByScore);
        // When
        List<Word> actual = underTest.getAllWordsRankedByScore();
        // Then
        List<Word> expected = wordsOrderedByScore;
        assertThat(actual).isEqualTo(expected);
    }

}
