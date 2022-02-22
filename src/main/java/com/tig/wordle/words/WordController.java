package com.tig.wordle.words;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("getwords")
public class WordController {

    private WordService wordService;

    public WordController(WordService wordService) {
        this.wordService = wordService;
    }

    @GetMapping
    public List <Word> getAllWords () {
        return wordService.getAllWords();
    }

    @GetMapping (path = "{numOfWords}")
    public List <Word> getTopWords (@PathVariable("numOfWords") Integer numOfWords) {
        return wordService.getTopWords(numOfWords);
    }



}
