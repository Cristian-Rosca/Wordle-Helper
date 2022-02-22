//package com.tig.wordle.words;
//
//import org.assertj.core.api.SoftAssertions;
//import org.junit.jupiter.api.Test;
//
//import java.util.LinkedHashMap;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//
//class generateWordPatternTest {
//
//    @Test
//    void canGenerateWordPatternForGreen() {
//        // Given
//        WordService underTest = new WordService();
//
//        // Generate test word
//        Word word = new Word(1, "tares", 0.1, 1.1);
//
//        // Generate test targetWord
//        Word targetWord = new Word(2, "tolls", 0.1, 1.1);
//
//        LinkedHashMap<String, String> actual = underTest.generateWordPattern(word,targetWord);
//
//        // When
//        // we need a way to verify that the corresponding value to t and s is "green"
//        String actualLabelAtIndex0 = actual.get("t0");
//        String actualLabelAtIndex5 = actual.get("s4");
//
//        // Then
//        String expected = "green";
//
//        assertThat(actualLabelAtIndex0).isEqualTo(expected);
//        assertThat(actualLabelAtIndex5).isEqualTo(expected);
//
//    }
//    @Test
//    void canGenerateWordPatternForOrange() {
//        // Given
//        WordService underTest = new WordService();
//
//        // Generate test word
//        Word word = new Word(1, "tares", 0.1, 1.1);
//
//        // Generate test targetWord
//        Word targetWord = new Word(2, "apple", 0.1, 1.1);
//
//        LinkedHashMap<String, String> actual = underTest.generateWordPattern(word,targetWord);
//
//        // When
//        // we need a way to verify that the corresponding value to t and s is "green"
//        String actualLabelAtIndex1 = actual.get("a1");
//        String actualLabelAtIndex3 = actual.get("e3");
//
//        // Then
//        String expected = "orange";
//
//        assertThat(actualLabelAtIndex1).isEqualTo(expected);
//        assertThat(actualLabelAtIndex3).isEqualTo(expected);
//
//    }
//
//    @Test
//    void canGenerateWordPatternForGrey() {
//        // Given
//        WordService underTest = new WordService();
//
//        // Generate test word
//        Word word = new Word(1, "tares", 0.1, 1.1);
//
//        // Generate test targetWord
//        Word targetWord = new Word(2, "apple", 0.1, 1.1);
//
//        LinkedHashMap<String, String> actual = underTest.generateWordPattern(word,targetWord);
//
//        // When
//        // we need a way to verify that the corresponding value to t and s is "green"
//        String actualLabelAtIndex0 = actual.get("t0");
//        String actualLabelAtIndex2 = actual.get("r2");
//        String actualLabelAtIndex4 = actual.get("s4");
//
//        // Then
//        String expected = "grey";
//
//        assertThat(actualLabelAtIndex0).isEqualTo(expected);
//        assertThat(actualLabelAtIndex2).isEqualTo(expected);
//        assertThat(actualLabelAtIndex4).isEqualTo(expected);
//
//    }
//
//}