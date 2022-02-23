package com.tig.wordle.user;

import com.tig.wordle.words.Word;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("postgres")
public class UserDataAccessService implements UserDAO{
    private JdbcTemplate jdbcTemplate;
    private RowMapper<User> userRowMapper = (rs, rowNum) -> {
        User user = new User(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getString("username")
        );
        return user;
    };
    public UserDataAccessService(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public User getUserById(Integer id) {
        return null;
    }

    @Override
    public Integer addUserToTable(User user) {
        return null;
    }

    @Override
    public Integer deleteUserById(Integer id) {
        return null;
    }

    @Override
    public Integer updateUserById(Integer id, User user) {
        return null;
    }
}
