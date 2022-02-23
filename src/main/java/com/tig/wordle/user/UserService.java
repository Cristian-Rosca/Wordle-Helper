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


    //loops through all the users and sees if there is a user matching the given id
    private boolean doesUserWithIdExists(Integer id) {
        return userDAO
                .getAllUsers()
                .stream()
                .anyMatch(p -> p.getId().equals(id));  // returns boolean
    }

    public User selectUserByID(Integer userId) {
        //redundant?
        if (userId == null){
            throw new IllegalStateException("ID cannot be null");
        }


        boolean exists = doesUserWithIdExists(userId);
        if(exists==false) {
            throw new IllegalStateException("User with ID " + userId + " does not exist");
        }
        User user = userDAO.getUserById(userId);
        return user;


    }

    public List<User> getAllUsers () {
        return userDAO.getAllUsers();
    }

    public Integer addUserToTable(User user) {
        if(user.getName() != null
                && user.getUserName() != null
                && user.getEmail() != null
                && user.getName() != ""
                && user.getUserName() != ""
                && user.getEmail() != "" ) {
                return userDAO.addUserToTable(user);
        }
        else {
            throw new IllegalStateException("Invalid entry. Fields cannot be empty");
        }
    }

    public Integer deleteUserById(Integer userId){
        //redundant?
        if (userId == null){
            throw new IllegalStateException("Id cannot be null");
        }


        boolean exists = doesUserWithIdExists(userId);
        if(exists==false) {
            throw new IllegalStateException("User with ID " + userId + " does not exist");
        }

        int result = userDAO.deleteUserById(userId);

        return result;
    }

//Better validations!!! check that all fields are entered
    public Integer updateUserByID(Integer userId, User updatedUser) {
        if (userId == null){
            throw new IllegalStateException("Id cannot be null");
        }
        User userToUpdate = userDAO.getUserById(userId);

        //this exception doesnt flag...? use other one (does user exist)
        if(userToUpdate== null) {
            throw new IllegalStateException("User with ID " + userId + " does not exist");
        }

        int result = userDAO.updateUserById(userId, updatedUser);

        return result;
    }

}
