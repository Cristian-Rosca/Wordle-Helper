package com.tig.wordle.words;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("postgres")
public class WordDataAccessService implements WordDAO{

    private JdbcTemplate jdbcTemplate;

    public WordDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Word> selectAllWords() {
        return null;
    }

    @Override
    public List<Word> selectAllWordsRankedByScore() {
        return null;
    }

    @Override
    public List<Word> selectTopWords(Integer numberOfWords) {
        return null;
    }

    @Override
    public Word selectWordById(Integer id) {
        return null;
    }

    @Override
    public Word selectWordByName(String nameOfWord) {
        return null;
    }
}
