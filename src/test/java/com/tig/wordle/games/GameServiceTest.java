package com.tig.wordle.games;

import com.tig.wordle.answers.Answer;
import com.tig.wordle.words.Word;
import com.tig.wordle.words.WordDAO;
import com.tig.wordle.words.WordService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class GameServiceTest {
    private GameService underTest;
    private GameDAO gameDAO;


    @BeforeEach
    void setUp(){
        this.gameDAO = Mockito.mock(GameDAO.class);
        this.underTest = new GameService (gameDAO);
    }

    @Test
    void getAllGames (){
        // Given
        Game testGame1 = new Game(1, 2, 3, 4);
        Game testGame2 = new Game(5, 6, 7, 8);
        Game testGame3 = new Game(9, 10, 11, 12);
        Game testGame4 = new Game(13, 14, 15, 16);

        List<Game> allGames = new ArrayList<>();
        allGames.add(testGame1);
        allGames.add(testGame2);
        allGames.add(testGame3);
        allGames.add(testGame4);
        given(gameDAO.getAllGames()).willReturn(allGames);
        // When
        List<Game> actual = underTest.getAllGames();
        // Then
        List<Game> expected = allGames;
        assertThat(actual).isEqualTo(expected);

    }

}
