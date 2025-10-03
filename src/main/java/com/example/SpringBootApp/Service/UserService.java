package com.example.SpringBootApp.Service;

import com.example.SpringBootApp.Entity.Cards;
import com.example.SpringBootApp.Repository.UserRepository;
import com.example.SpringBootApp.Repository.CardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.SpringBootApp.UserEntity.User;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final CardsRepository cardsRepository;

    @Autowired
    public UserService(UserRepository userRepository, CardsRepository cardsRepository) {
        this.userRepository = userRepository;
        this.cardsRepository = cardsRepository;
    }


    //save a user
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    //get a user
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    //get a user by id
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    //update a user
    public User updateUser(Long id, User updatedUser) {
        Optional<User> existingUser = userRepository.findById(id);
        if(existingUser.isPresent()) {
            User user = existingUser.get();
            user.setUsername(updatedUser.getUsername());
            user.setPassword((updatedUser.getPassword()));
            return userRepository.save(user);
        } else {
            throw new RuntimeException("User not found");
        }
    }

    //delete a user
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    //get users cards
    public List<Cards> getUserCards(Long userId) {
        return userRepository.findById(userId).map(User::getCards).orElseThrow(() -> new RuntimeException("User not found."));
    }

    public Cards addCardToUser(Long userId, Cards card) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found."));
        card.setUser(user);
        return cardsRepository.save(card);
    }
}
