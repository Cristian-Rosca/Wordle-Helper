package com.tig.wordle.games;

import com.tig.wordle.answers.AnswerService;
import com.tig.wordle.user.UserService;
import com.tig.wordle.words.WordService;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
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
    @GetMapping(path = "all")
    public List<Game> getAllGames(){
        return gameService.getAllGames();
    }
}
