package com.tig.wordle.games;

import com.tig.wordle.user.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("games")
public class GameDataAccessService implements GameDAO {
    private JdbcTemplate jdbcTemplate;
    private RowMapper<Game> gameRowMapper = (rs, rowNum) -> {
        Game game = new Game(
                rs.getInt("id"),
                rs.getInt("user_id"),
                rs.getInt("actual_answers_id"),
                rs.getInt("guesses_taken")
        );
        return game;
    };
    GameDataAccessService(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Game> getAllGames() {
        String sql = """
                SELECT id, user_id, actual_answers_id, guesses_taken
                FROM all_games
                """;
        List<Game> games = jdbcTemplate.query(sql, gameRowMapper);
        return games;
    }

    @Override
    public Game getGameById(Integer id) {
        String sql = """
                SELECT id, user_id, actual_answers_id, guesses_taken
                FROM all_games
                WHERE id=?
                """;
        Game game = jdbcTemplate.query(sql, gameRowMapper, id).get(0);
        return game;
    }

    @Override
    public Integer addGameToTable(Game game) {
        String sql = """
                INSERT INTO all_games (user_id, actual_answers_id, guesses_taken) 
                VALUES (?, ?, ?)
                """;
        Integer rowsAffected = jdbcTemplate.update(sql,
                game.getUserId(),
                game.getAnswerId(),
                game.getUserGuesses());
        return rowsAffected;
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
