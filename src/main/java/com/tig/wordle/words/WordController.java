package com.tig.wordle.words;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("getwords")
public class WordController {

    private WordService wordService;
    private List<Word> gameList;

    public WordController(WordService wordService) {
        this.wordService = wordService;
    }

    @GetMapping
    public List <Word> getAllWords () {
        return wordService.getAllWords();
    }

    @GetMapping("ranked")
    public List <Word> getAllWordsRankedByScore () {
        return wordService.getAllWordsRankedByScore();
    }

    @GetMapping (path = "ranked/{numOfWords}")
    public List <Word> getTopWords (@PathVariable("numOfWords") Integer numOfWords) {
        return wordService.getTopWords(numOfWords);
    }

    @GetMapping (path = "wordbyid/{id}")
    public Word getWordById (@PathVariable("id") Integer id) {
        return wordService.getWordById(id);
    }

    @GetMapping (path = "wordbyname/{nameofword}")
    public Word getWordByName(@PathVariable("nameofword") String nameOfWord) {
        return wordService.getWordByName(nameOfWord);
    }


}
