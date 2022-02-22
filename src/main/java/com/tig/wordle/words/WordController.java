package com.tig.wordle.words;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WordController {

    private WordService wordService;

    public WordController(WordService wordService) {
        this.wordService = wordService;
    }

    @GetMapping (path = "getwords")
    public List <Word> getAllWords () {
        return wordService.getAllWords();
    }



}
