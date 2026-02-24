package com.example.SpringBootApp.Mappers;

import com.example.SpringBootApp.DTOs.CardsRequestDTO;
import com.example.SpringBootApp.DTOs.UserRequestDTO;
import com.example.SpringBootApp.DTOs.UserResponseDTO;
import com.example.SpringBootApp.DTOs.CardsResponseDTO;
import com.example.SpringBootApp.Entity.Cards;
import com.example.SpringBootApp.UserEntity.User;
import org.springframework.stereotype.Component;

//user should be able to add a card without needing to be signed in. also users should be unique, no duplicate usernames
@Component
public class ManualMapper {

    //maps outgoing user and cards
    public UserResponseDTO mapToUserResponse(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getCards().stream().map(this::mapToCardsResponse).toList(),
                user.userCards(),
                user.getCreatedDate(),
                user.getLastUpdated()
        );
    }

    public CardsResponseDTO mapToCardsResponse(Cards cards) {
        return new CardsResponseDTO(
                cards.getId(),
                cards.getCardName(),
                cards.getSetName(),
                cards.getCardNumber(),
                cards.getOwnedCount()
        );
    }

    //maps incoming user data
    public User mapToUserRequest(UserRequestDTO userRequestDTO) {
        User user = new User();
        user.setUsername(userRequestDTO.username());
        user.setPassword(userRequestDTO.password());
        return user;
    }

    //maps incoming card data !!!!!!!!!maybe delete later. see how to map this to user id!!!!!!!!!!!
    public Cards mapToCardsRequest(CardsRequestDTO cardsRequestDTO) {
        Cards cards = new Cards();
        cards.setCardName(cardsRequestDTO.cardName());
        cards.setSetName(cardsRequestDTO.setName());
        cards.setCardNumber(cardsRequestDTO.cardNumber());
        cards.setOwnedCount(cardsRequestDTO.ownedCount());
        return cards;
    }


}
