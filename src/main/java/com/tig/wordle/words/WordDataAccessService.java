package com.tig.wordle.words;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("postgres")
public class WordDataAccessService implements WordDAO {

    private JdbcTemplate jdbcTemplate;

    public WordDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // SelectAllWords method
    @Override
    public List<Word> selectAllWords() {
        String sql = """
                SELECT id, word, probability, score
                FROM original_word_list
                """;

        RowMapper<Word> wordRowMapper = (rs, rowNum) -> {
            Word word = new Word(
                    rs.getInt("id"),
                    rs.getString("word"),
                    rs.getDouble("probability"),
                    rs.getDouble("score")
            );
            return word;
        };
        List<Word> gameWordList = jdbcTemplate.query(sql, wordRowMapper);
        return gameWordList;
    }

    @Override
    public List<Word> selectAllWordsRankedByScore() {
        return null;
    }


    //    //hardcoded to select 10 words only
    //is id needed? if not, remove id from select and from rowmapper
    @Override
    public List<Word> selectTopWords(Integer numOfWords) {
        String sql = """
                SELECT id, word, probability, score
                FROM original_word_list ORDER BY score DESC limit ?
                """;

        RowMapper<Word> wordRowMapper = (rs, rowNum) -> {
            Word word = new Word(
                    rs.getInt("id"),
                    rs.getString("word"),
                    rs.getDouble("probability"),
                    rs.getDouble("score")
            );
            return word;
        };
        List<Word> gameWordList = jdbcTemplate.query(sql, wordRowMapper, numOfWords);
        return gameWordList;
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
