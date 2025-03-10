package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.UserRepository;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserRepository userRepository;

    private final Logger l;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.l = LogManager.getLogger(UserServiceImpl.class);
    }

    @Override
    public List<User> retrieveAllUsers() { 
        l.info("In retrieveAllUsers()");
        List<User> users = new ArrayList<>();
        try {
            users = userRepository.findAll();
            l.info("Users retrieved successfully");
        } catch (Exception e) {
            l.error("Error in retrieveAllUsers(): " + e.getMessage());
        }
        return users;
    }

    @Override
    public User addUser(User u) {
        l.info("In addUser() : " + u);
        User utilisateur = null; 

        try {
            utilisateur = userRepository.save(u); 
            l.info("User added successfully: " + utilisateur);
        } catch (Exception e) {
            l.error("Error in addUser(): " + e.getMessage());
        }

        return utilisateur; 
    }

    @Override 
    public User updateUser(User u) {
        l.info("In updateUser() : " + u);
        User userUpdated = null; 

        try {
            userUpdated = userRepository.save(u); 
            l.info("User updated successfully: " + userUpdated);
        } catch (Exception e) {
            l.error("Error in updateUser(): " + e.getMessage());
        }

        return userUpdated; 
    }

    @Override
    public void deleteUser(String id) {
        l.info("In deleteUser() : " + id);

        try {
            userRepository.deleteById(Long.parseLong(id)); 
            l.info("User deleted successfully");
        } catch (Exception e) {
            l.error("Error in deleteUser(): " + e.getMessage());
        }
    }

    @Override
    public User retrieveUser(String id) {
        l.info("In retrieveUser() : " + id);
        User u = null;
        try {
            u = userRepository.findById(Long.parseLong(id)).orElse(null);
            if (u != null) {
                l.info("User retrieved successfully: " + u);
            } else {
                l.warn("No user found with id: " + id);
            }
        } catch (Exception e) {
            l.error("Error in retrieveUser(): " + e.getMessage());
        }
        return u;
    }
}
