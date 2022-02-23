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
}
