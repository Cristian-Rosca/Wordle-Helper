package com.tig.wordle.games;

import com.tig.wordle.user.UserNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameService {
    private GameDAO gameDAO;
    public GameService(@Qualifier("games") GameDAO gameDAO){
        this.gameDAO = gameDAO;
    }
    public List<Game> getAllGames(){
        if(gameDAO.getAllGames().size() == 0) {
            throw new GameNotFoundException("No games found");
        }
        return gameDAO.getAllGames();
    }
    public Game getGameById(Integer id){
        return gameDAO.getGameById(id);
    }
    public Integer addGameToTable(Game game){
        if(game.getUserId() != null
        && game.getAnswerId()!= null
        && game.getUserGuesses() != null) {
            return gameDAO.addGameToTable(game);
        }
        else throw new InputMissingForGameException("Fields cannot be empty");
    }
    public Integer deleteGameById(Integer id){
        if (gameDAO.getGameById(id) == null){
            throw new GameNotFoundException("No game with this id exists");
        }
        return gameDAO.deleteGameById(id);
    }
    public Integer updateGameById(Integer id, Game game){
        if (gameDAO.getGameById(id) == null){
            throw new GameNotFoundException("No game with this id exists");
        }
        return gameDAO.updateGameById(id, game);
    }

    public List<GameResults> getAllResultsVsMachineForDate (LocalDate date) {
        return gameDAO.getUserGuessVsMachineResultsListForDate(date);
    }
    public GameResults getUserResultForDay(LocalDate date, String userName){
        // Get all for results for day
        List<GameResults> gameResultsForDay = getAllResultsVsMachineForDate(date);
        // Find all entries with that username
        List<GameResults> userResults = gameResultsForDay.stream()
                .filter(gameResult -> gameResult.getUserName().equals(userName))
                .collect(Collectors.toList());
        if (userResults.isEmpty()){
            return null;
        }
        return userResults.get(0);
    }
    public List<GameResults> getAllResultsVsMachineForUser(String userName){
        return gameDAO.getAllResultsForUser(userName);
    }
    public Double getAverageGuessesForUser(String username){
        List<GameResults> userResults = gameDAO.getAllResultsForUser(username);
        Double average = 0.0;
        for (int i = 0; i < userResults.size(); i++) {
            average += userResults.get(i).getGuessesTaken();
        }
        average = average/userResults.size();
        return average;
    }
}
