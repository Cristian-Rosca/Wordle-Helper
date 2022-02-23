package com.tig.wordle.answers;

import com.tig.wordle.user.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("postgres")
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
    public Integer addAnswerToTable(Answer answer) {
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
