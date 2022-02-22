//package com.tig.wordle.words;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//class ComputeWordScoreTest {
//
//    private WordService underTest;
//
//    @BeforeEach
//    void setup() {
//        this.underTest = new WordService();
//    }
//
//    @Test
//    void computeWordScore() {
//
//        // Given
//        Word guess1 = new Word(1, "mouse", 0.25, 1.0 );
//        Word guess2 = new Word(1, "pears", 0.25, 1.0 );
//        Word guess3 = new Word(1, "pairs", 0.25, 1.0 );
//        Word guess4 = new Word(1, "glass", 0.25, 1.0 );
//        List<Word> wordList = new ArrayList<>();
//        wordList.add(guess1);
//        wordList.add(guess2);
//        wordList.add(guess3);
//        wordList.add(guess4);
//
//        //When
//        Double actual = underTest.computeWordScore(guess4, wordList);
//
//        //Then
//        Double expected = 2.0;
//        assertThat(actual).isEqualTo(expected);
//
//    }
//}