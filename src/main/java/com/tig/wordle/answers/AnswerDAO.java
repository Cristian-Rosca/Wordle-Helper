package com.tig.wordle.answers;

import com.tig.wordle.user.User;

import java.util.List;

public interface AnswerDAO {
    List<User> getAllAnswers();
    User getAnswerById(Integer id);
    Integer addAnswerToTable(User user);
    Integer deleteAnswerById(Integer id);
    Integer updateAnswerById(Integer id, User user);
}
