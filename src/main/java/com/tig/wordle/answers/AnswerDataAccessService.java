package com.tig.wordle.answers;

import com.tig.wordle.user.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
@Repository("answers")
public class AnswerDataAccessService implements AnswerDAO{
    private JdbcTemplate jdbcTemplate;
    private RowMapper<Answer> answerRowMapper = (rs, rowNum) -> {
        Answer answer = new Answer(
                rs.getInt("id"),
                rs.getDate("date_of_given_answer").toLocalDate(),
                rs.getString("actual_word"),
                rs.getInt("machine_guesses")
        );
        return answer;
    };

    public AnswerDataAccessService(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Answer> getAllAnswers() {
        String sql = """
                SELECT id, date_of_given_answer, actual_word, machine_guesses
                FROM actual_answers
                """;
        List<Answer> answers = jdbcTemplate.query(sql, answerRowMapper);
        return answers;
    }

    @Override
    public Answer getAnswerById(Integer id) {
        String sql = """
                SELECT id, date_of_given_answer, actual_word, machine_guesses
                FROM actual_answers
                WHERE id=?
                """;
        Answer answerById = jdbcTemplate.query(sql, answerRowMapper, id).get(0);
        return answerById;

    }

    @Override
    public Integer addAnswerToTable(Answer answer) {
        String sql = """
                INSERT INTO actual_answers(date_of_given_answer, actual_word, machine_guesses) VALUES
                (?, ?, ?)
                """;
        Integer rowsAffected = jdbcTemplate.update(sql, Date.valueOf(answer.getDateOfAnswer()), answer.getAnswerOfDay(), answer.getMachineResult());
        return rowsAffected;
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
