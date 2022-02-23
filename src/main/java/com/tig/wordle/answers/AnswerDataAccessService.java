package com.tig.wordle.answers;

import com.tig.wordle.user.User;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class AnswerDataAccessService implements AnswerDAO{
    private JdbcTemplate jdbcTemplate;
    public AnswerDataAccessService(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> getAllAnswers() {
        return null;
    }

    @Override
    public User getAnswerById(Integer id) {
        return null;
    }

    @Override
    public Integer addAnswerToTable(User user) {
        return null;
    }

    @Override
    public Integer deleteAnswerById(Integer id) {
        return null;
    }

    @Override
    public Integer updateAnswerById(Integer id, User user) {
        return null;
    }
}
