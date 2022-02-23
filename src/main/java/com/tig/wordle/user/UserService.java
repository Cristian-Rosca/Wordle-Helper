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


    //this exception doesnt get thrown!
    private User confirmUserOrThrow(Integer userId) {
        User user = userDAO.getUserById(userId);
        if (user == null) {
            throw new IllegalArgumentException("User with id " + userId + " does not exist");
        }
        return user;
    }

    //loops through all the users and sees if there is a user matching the given id
    private boolean doesUserWithIdExists(Integer id) {
        return userDAO
                .getAllUsers()
                .stream()
                .anyMatch(p -> p.getId().equals(id));  // returns boolean
    }

    public User selectUserByID(Integer userId) {
        //atm this cannot be tested??
        if (userId == null){
            throw new IllegalStateException("ID cannot be null");
        }

        //if id entered is not a number?


//        throws exception if id entered doesnt exist
        boolean exists = doesUserWithIdExists(userId);
        if(exists==false) {
            throw new IllegalStateException("User with ID " + userId + " does not exist");
        }
        User user = userDAO.getUserById(userId);
        return user;

        //This method doesnt throw exception for some reason! even when I put the whole confirmorthrow method inside here
//        User user = confirmUserOrThrow(userId);
//        return user;
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
        if (userId == null){
            throw new IllegalStateException("Id cannot be null");
        }
        User userToDelete = userDAO.getUserById(userId);

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
