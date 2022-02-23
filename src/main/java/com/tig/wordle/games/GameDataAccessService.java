package com.tig.wordle.games;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("games")
public class GameDataAccessService implements GameDAO {
    private JdbcTemplate jdbcTemplate;
    GameDataAccessService(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Game> getAllGames() {
        return null;
    }

    @Override
    public Game getGameById(Integer id) {
        return null;
    }

    @Override
    public Integer addGameToTable(Game game) {
        return null;
    }

    @Override
    public Integer deleteGameById(Integer id) {
        return null;
    }

    @Override
    public Integer updateGameById(Integer id, Game game) {
        return null;
    }
}
