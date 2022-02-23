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

    // Created private method
    private User confirmUserOrThrow(Integer userId) {
        User user = userDAO.getUserById(userId);
        if (user == null) {
            throw new IllegalArgumentException("User with id " + userId + " does not exist");
        }
        return user;
    }

    public User selectUserByID(Integer userId) {
        if (userId == null){
            throw new IllegalStateException("Id cannot be null");
        }
        User user = confirmUserOrThrow(userId);
        return user;
    }




    public List<User> getAllUsers () {
        return userDAO.getAllUsers();
    }


    public Integer addUserToTable(User user) {
        if(user.getName() != null && user.getUserName() != null && user.getEmail() != null) {
            return userDAO.addUserToTable(user);
        }
        else {
            throw new IllegalStateException("Invalid entry. Fields cannot be empty");
        }
    }

}
