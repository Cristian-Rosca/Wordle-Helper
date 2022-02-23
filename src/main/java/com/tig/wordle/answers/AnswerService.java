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
        return answerDAO.getAnswerById(id); }

    public Integer addAnswerToTable(Answer answer){
        return answerDAO.addAnswerToTable(answer);

    }

    public Integer deleteAnswerById(Integer id) {
        return answerDAO.deleteAnswerById(id);

    }
}
