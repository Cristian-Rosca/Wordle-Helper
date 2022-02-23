package com.tig.wordle.games;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {
    private GameDAO gameDAO;
    public GameService(@Qualifier("games") GameDAO gameDAO){
        this.gameDAO = gameDAO;
    }
    public List<Game> getAllGames(){
        return gameDAO.getAllGames();
    }
    public Game getGameById(Integer id){
        return gameDAO.getGameById(id);
    }
    public Integer addGameToTable(Game game){
        return gameDAO.addGameToTable(game);
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
}
