package com.tig.wordle.games;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class GameService {
    private GameDAO gameDAO;
    public GameService(@Qualifier("games") GameDAO gameDAO){
        this.gameDAO = gameDAO;
    }
}
