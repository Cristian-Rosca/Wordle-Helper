package com.tig.wordle.answers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class AnswerServiceTest {
    private AnswerService underTest;
    private AnswerDAO answerDAO;

    @BeforeEach
    void setUp(){
        this.answerDAO = Mockito.mock(AnswerDAO.class);
        this.underTest = new AnswerService(answerDAO);
    }

    @Test
    void getAllAnswers() {
        // Create objects
        Answer answer1 = new Answer(1, LocalDate.of(2019, 1, 20), "glass", 2);
        Answer answer2 = new Answer(2, LocalDate.of(2019, 1, 21), "grass", 3);
        Answer answer3 = new Answer(3, LocalDate.of(2019, 1, 22), "horse", 4);

        // Placeholder for list
        List<Answer> answerList = new ArrayList<>();
        answerList.add(answer1);
        answerList.add(answer2);
        answerList.add(answer3);

        // Given
        given(answerDAO.getAllAnswers()).willReturn(answerList);
        // When
        List<Answer> actual = underTest.getAllAnswers();
        // Then
        List<Answer> expected = answerList;
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void getAnswerById() {
        // Create objects
        Answer answer1 = new Answer(1, LocalDate.of(2019, 1, 20), "glass", 2);
        Answer answer2 = new Answer(2, LocalDate.of(2019, 1, 21), "grass", 3);
        Answer answer3 = new Answer(3, LocalDate.of(2019, 1, 22), "horse", 4);

        // Given
        given(answerDAO.getAnswerById(2)).willReturn(answer2);
        // When
        Answer actual = underTest.getAnswerById(2);
        // Then
        Answer expected = answer2;
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void addAnswerToTable() {
    }

    @Test
    void deleteAnswerById() {
    }

    @Test
    void updateAnswerById() {
    }
}

