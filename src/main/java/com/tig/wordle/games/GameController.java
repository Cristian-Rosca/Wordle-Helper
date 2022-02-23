package com.tig.wordle.games;

import com.tig.wordle.answers.Answer;
import com.tig.wordle.answers.AnswerService;
import com.tig.wordle.user.UserService;
import com.tig.wordle.words.WordService;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.sql.Date;
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

    @PutMapping(path = "computemachinescores")
    public void computeMachineScores(){
        // Get list of answers from answer table
        List<Answer> answerList = answerService.getAllAnswers();
        // Placeholder for answers with machine scores computed
        Answer answerWithMachineGuesses;
        for (int i = 0; i < answerList.size(); i++){
//            todo: if statement below
//            if (answerList.get(i).getMachineResult() != null){
//                continue;
//            }
            // Add machine guesses for answer
            answerWithMachineGuesses = wordService.getGuessesForAnswer(answerList.get(i));
            System.out.println("id " + answerWithMachineGuesses.getId()
                    + ": " + answerWithMachineGuesses.getAnswerOfDay() + " guessed in "
                    + answerWithMachineGuesses.getMachineResult());
            // Update score in answer table
            answerService.updateAnswerById(answerWithMachineGuesses.getId(),
                    answerWithMachineGuesses);
        }
    }

    @GetMapping(path = "dailyresults/{date}")
    public List <GameResults> getAllResultsVsMachineForDate (@PathVariable("date") String date) {
        return gameService.getAllResultsVsMachineForDate(Date.valueOf(date).toLocalDate());
    }
    @GetMapping(path = "dailyresults/{date}/{username}")
    public GameResults getResultForGivenUserVsMachineForDate (@PathVariable("date") String date,
                                                             @PathVariable("username") String username) {
        return gameService.getUserResultForDay(Date.valueOf(date).toLocalDate(), username);
    }
    @GetMapping(path = "results/{username}")
    public List<GameResults> getUserResults(@PathVariable("username") String userName){
        return gameService.getAllResultsVsMachineForUser(userName);
    }
    @GetMapping(path = "averageresults/{username}")
    public Double getUserResultsAverage(@PathVariable("username") String userName){
        return gameService.getAverageGuessesForUser(userName);
    }
}
