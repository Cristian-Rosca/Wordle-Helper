package com.tig.wordle.answers;

import com.tig.wordle.user.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
@Repository("postgres")
public class AnswerDataAccessService implements AnswerDAO{
    private JdbcTemplate jdbcTemplate;
    private RowMapper<Answer> answerRowMapper = (rs, rowNum) -> {
        Answer answer = new Answer(
                rs.getInt("id"),
                rs.getDate("date_of_given_answer").toLocalDate(),
                rs.getString("actual_word"),
                rs.getInt("machine_guess")
        );
        return answer;
    };

    public AnswerDataAccessService(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Answer> getAllAnswers() {
        String sql = """
                SELECT id, date_of_given_answer, actual_word, machine_guess
                FROM actual_answers
                """;
        List<Answer> answers = jdbcTemplate.query(sql, answerRowMapper);
        return answers;
    }

    @Override
    public Answer getAnswerById(Integer id) {
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
    public Integer updateAnswerById(Integer id, Answer answer) {
        return null;
    }
}
