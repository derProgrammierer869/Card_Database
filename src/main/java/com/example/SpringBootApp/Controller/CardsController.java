package com.example.SpringBootApp.Controller;

import com.example.SpringBootApp.Entity.Cards;
import com.example.SpringBootApp.Service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CardsController {

    private final CardService cardService;

    @Autowired
    public CardsController(CardService cardService) {
        this.cardService = cardService;
    }

    //Create a new card

    @PostMapping("/card")
    public ResponseEntity<Cards> saveCards(@RequestBody Cards cards) {
        Cards newCards = cardService.saveCards((cards));
        return ResponseEntity.ok(newCards);
    }

    //Get all cards

    @GetMapping("/cards")
    public List<Cards> getAllCards() {
        return cardService.getAllCards();
    }

    //Get card by ID

    @GetMapping("/cards/{id}")
    public ResponseEntity<Cards> getCards(@PathVariable Long id) {
        Optional<Cards> card = cardService.getCards(id);
                                                            //if optional is empty, this is ran
        return card.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Update card by ID

    @PutMapping("/cards/{id}")
    public ResponseEntity<Cards> updateCards(@PathVariable Long id, @RequestBody Cards cards) {
        Cards updatedCard = cardService.updateCards(id, cards);
        return ResponseEntity.ok(updatedCard);
    }

    //Delete card by ID

    @DeleteMapping("/cards/{id}")
    public ResponseEntity<String> deleteCard(@PathVariable Long id) {
        cardService.deleteCard(id);
        return ResponseEntity.ok("Card deleted!");
    }
}
