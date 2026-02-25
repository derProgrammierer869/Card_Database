package com.example.SpringBootApp.Service;

import com.example.SpringBootApp.Repository.CardsRepository;
import com.example.SpringBootApp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.SpringBootApp.UserEntity.User;
import com.example.SpringBootApp.Entity.Cards;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private final CardsRepository cardsRepository;
    private final UserRepository userRepository;

    @Autowired
    public AdminService(CardsRepository cardsRepository, UserRepository userRepository) {
        this.cardsRepository = cardsRepository;
        this.userRepository = userRepository;
    }

    public void deleteAllCards(Long id) {
        cardsRepository.deleteAllCardsFromUser(id);
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
    }


}
