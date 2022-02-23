package com.tig.wordle.answers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("answers")
public class AnswerController {
    private AnswerService answerService;
    public AnswerController(AnswerService answerService){
        this.answerService = answerService;
    }
    @GetMapping
    public List<Answer> getAllAnswers(){
        return answerService.getAllAnswers();
    }
    @GetMapping (path = "getAnswerById/{id}")
    public Answer getAnswerById (@PathVariable("id") Integer id){
        return answerService.getAnswerById(id);
    }
}
