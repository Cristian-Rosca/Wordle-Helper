package com.tig.wordle.user;

import com.tig.wordle.words.WordDAO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserDAO userDAO;


    public UserService(@Qualifier("user") UserDAO userDAO)  {
        this.userDAO = userDAO;
    }

    public List<User> getAllUsers () {
        return userDAO.getAllUsers();
    }

}
