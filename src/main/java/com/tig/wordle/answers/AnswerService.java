package com.tig.wordle.answers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class AnswerService {
    private AnswerDAO answerDAO;
    public AnswerService(@Qualifier("answers") AnswerDAO answerDAO){
        this.answerDAO = answerDAO;
    }

    public List<Answer> getAllAnswers(){
        return answerDAO.getAllAnswers();
    }

    public Answer getAnswerById(Integer id){
        return answerDAO.getAnswerById(id);
    }

    public Integer addAnswerToTable(Answer answer){
        return answerDAO.addAnswerToTable(answer);
    }

    public Integer deleteAnswerById(Integer id) {
        if (getAnswerById(id) == null){
            throw new AnswerNotFoundException("No answer with given id exists");
        }
        return answerDAO.deleteAnswerById(id);
    }

    public Integer updateAnswerById(Integer id, Answer answer) {
        if (getAnswerById(id) == null){
            throw new AnswerNotFoundException("No answer with given id exists");
        }
        return answerDAO.updateAnswerById(id, answer);
    }
}
