package com.tig.wordle;

import com.tig.wordle.words.WordDataAccessService;
import org.springframework.jdbc.core.JdbcTemplate;

public class Main {

    public static void main(String[] args) {
        WordDataAccessService wordDataAccessService = new WordDataAccessService(new JdbcTemplate());
        System.out.println(wordDataAccessService.selectAllWords());
    }
}
