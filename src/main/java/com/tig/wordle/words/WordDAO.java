package com.tig.wordle.words;

import java.util.List;

public interface WordDAO {
    List<Word> selectAllWords();
    List<Word> selectAllWordsRankedByScore();
    List<Word> selectTopWords(Integer numberOfWords);
    Word selectWordById(Integer id);
    Word selectWordByName(String nameOfWord);
}
