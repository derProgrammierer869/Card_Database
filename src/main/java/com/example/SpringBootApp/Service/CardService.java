package com.example.SpringBootApp.Service;

import com.example.SpringBootApp.Mappers.ManualMapper;
import com.example.SpringBootApp.Entity.Cards;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.SpringBootApp.Repository.CardsRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Sort;


@Service
public class CardService {
    private final CardsRepository cardsRepository;
    private final ManualMapper manualMapper;

    @Autowired
    public CardService(CardsRepository cardsRepository, ManualMapper manualMapper) {
        this.cardsRepository = cardsRepository;
        this.manualMapper = manualMapper;
    }

    //save a card

    public Cards saveCards(Cards cards) {
        return cardsRepository.save(cards);
    }

    //get all the cards. returns a list of the cards

    public List<Cards> getAllCards() {
        return cardsRepository.findAll(Sort.by(Sort.Direction.ASC, "cardName"));
    }

    //get card based on id
    //update method name to getCardsById
    public Optional<Cards> getCards(Long id) {
        return cardsRepository.findById(id);
    }

    //updating a card item

    public Cards updateCards(Long id, Cards updatedCards) {
        Optional<Cards> existingCards = cardsRepository.findById(id);
        if (existingCards.isPresent()) {
            Cards cards = existingCards.get();
            cards.setCardName(updatedCards.getCardName());
            cards.setSetName(updatedCards.getSetName());
            cards.setCardNumber(updatedCards.getCardNumber());
            return cardsRepository.save(cards);
        }
        else {
            throw new RuntimeException("Card not found!");
        }
    }

    //delete a card

    public void deleteCard(Long id) {
        cardsRepository.deleteById(id);
    }
}
