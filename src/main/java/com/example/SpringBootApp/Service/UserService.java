package com.example.SpringBootApp.Service;

import com.example.SpringBootApp.Entity.Cards;
import com.example.SpringBootApp.Repository.UserRepository;
import com.example.SpringBootApp.Repository.CardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.SpringBootApp.UserEntity.User;
import com.example.SpringBootApp.Mappers.ManualMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final CardsRepository cardsRepository;
    private final ManualMapper manualMapper;

    @Autowired
    public UserService(UserRepository userRepository, CardsRepository cardsRepository, ManualMapper manualMapper) {
        this.userRepository = userRepository;
        this.cardsRepository = cardsRepository;
        this.manualMapper = manualMapper;
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


    @Transactional
    public Cards addCardToUser(Long userId, Cards card) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found."));

        Optional<Cards> existingCard =
                cardsRepository.findByUserIdAndCardNameAndSetNameAndCardNumber(
                        userId,
                        card.getCardName(),
                        card.getSetName(),
                        card.getCardNumber()
                );
        boolean exists = cardsRepository.existsByUserIdAndCardNameAndSetNameAndCardNumberAndOwnedCount(userId, card.getCardName(), card.getSetName(), card.getCardNumber(), card.getOwnedCount());

        if(existingCard.isPresent()) {
            Cards existing = existingCard.get();
            existing.setOwnedCount(existing.getOwnedCount() + 1);
            return cardsRepository.save(existing);
        }

        card.setUser(user);
        card.setOwnedCount(1);
        //this is meant to update the other side of the relationship; each card has a user and each user has a card etc.
        user.getCards().add(card);  //maybe delete this later!!!!!!!
        return cardsRepository.save(card);
    }
}
