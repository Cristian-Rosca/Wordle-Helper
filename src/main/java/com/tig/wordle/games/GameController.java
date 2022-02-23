package com.tig.wordle.games;

import com.tig.wordle.answers.AnswerService;
import com.tig.wordle.user.UserService;
import com.tig.wordle.words.WordService;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
@RequestMapping("game")
public class GameController {
    private GameService gameService;
    private UserService userService;
    private WordService wordService;
    private AnswerService answerService;

    public GameController(GameService gameService,
                          UserService userService,
                          WordService wordService,
                          AnswerService answerService) {
        this.gameService = gameService;
        this.userService = userService;
        this.wordService = wordService;
        this.answerService = answerService;
    }
}
