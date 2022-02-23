package com.tig.wordle.answers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class AnswerService {
    private AnswerDAO answerDAO;
    public AnswerService(@Qualifier("answer") AnswerDAO answerDAO){
        this.answerDAO = answerDAO;
    }

    public List<Answer> getAllAnswers(){
        return answerDAO.getAllAnswers();
    }

    public Answer getAnswerById(Integer id){
        return answerDAO.getAnswerById(id);
    }

    public Integer addAnswerToTable(Answer answer){
        // Get the current answers from the table
        List<Answer> currentAnswers = getAllAnswers();
        // Empty list for the dates
        List<LocalDate> dates = new ArrayList<>();
        // Fill list of dates
        for (int i = 0; i < currentAnswers.size(); i++) {
            dates.add(currentAnswers.get(i).getDateOfAnswer());
        }
        // Check if our new answer date is included in current list of dates
        if (dates.contains(answer.getDateOfAnswer())){
            throw new AnswerDateTakenException("Could not add answer to table as given date already exists");
        }
        return answerDAO.addAnswerToTable(answer);
    }

    public Integer deleteAnswerById(Integer id) {
        if (getAnswerById(id) == null){
            throw new AnswerNotFoundException("No answer with given id exists");
        }
        return answerDAO.deleteAnswerById(id);
    }

    public Integer updateAnswerById(Integer id, Answer answer) {
        // Get the current answers from the table
        List<Answer> currentAnswers = getAllAnswers();
        // Empty list for the dates
        List<LocalDate> dates = new ArrayList<>();
        // Fill list of dates
        for (int i = 0; i < currentAnswers.size(); i++) {
            dates.add(currentAnswers.get(i).getDateOfAnswer());
        }
        // Check if our new answer date is included in current list of dates
        if (dates.contains(answer.getDateOfAnswer())){
            throw new AnswerDateTakenException("Could not update answer in table as given date already exists");
        }
        if (getAnswerById(id) == null){
            throw new AnswerNotFoundException("No answer with given id exists");
        }
        return answerDAO.updateAnswerById(id, answer);
    }
}
