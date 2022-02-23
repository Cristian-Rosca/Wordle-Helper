package com.tig.wordle.answers;

import org.springframework.web.bind.annotation.*;

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
    @PostMapping (path = "addAnswerToTable")
    public Integer addAnswerToTable (@RequestBody Answer answer) {
        return answerService.addAnswerToTable(answer);
    }
    @DeleteMapping (path = "deleteAnswerById/{id}")
    public Integer deleteAnswerById (@PathVariable("id") Integer id) {
        return answerService.deleteAnswerById(id);
    }
}
