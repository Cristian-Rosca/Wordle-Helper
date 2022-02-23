package com.tig.wordle.games;

import com.tig.wordle.answers.AnswerService;
import com.tig.wordle.user.UserService;
import com.tig.wordle.words.WordService;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping(path = "{id}")
    public Game getGameById(@PathVariable("id") Integer id){
        return gameService.getGameById(id);
    }
    @PostMapping(path = "addgame")
    public Integer addGameToTable(@RequestBody Game game){
        return gameService.addGameToTable(game);
    }
    @DeleteMapping(path = "{id}")
    public Integer deleteGameById(@PathVariable("id") Integer id){
        return gameService.deleteGameById(id);
    }
    @PutMapping(path = "{id}")
    public Integer updateGameById(@PathVariable("id") Integer id,
                                  @RequestBody Game game){
        return gameService.updateGameById(id, game);
    }
}
