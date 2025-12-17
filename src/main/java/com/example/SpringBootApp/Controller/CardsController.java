package com.example.SpringBootApp.Controller;

import com.example.SpringBootApp.DTOs.CardsRequestDTO;
import com.example.SpringBootApp.DTOs.CardsResponseDTO;
import com.example.SpringBootApp.DTOs.UserRequestDTO;
import com.example.SpringBootApp.DTOs.UserResponseDTO;
import com.example.SpringBootApp.Entity.Cards;
import com.example.SpringBootApp.Service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import com.example.SpringBootApp.Mappers.ManualMapper;

@RestController
@RequestMapping("/api")
public class CardsController {

    private final CardService cardService;

    @Autowired
    public CardsController(CardService cardService) {
        this.cardService = cardService;
    }

    @Autowired
    private ManualMapper manualMapper;

    //Create a new card
    @PostMapping("/card")
    public ResponseEntity<CardsResponseDTO> saveCards(@RequestBody CardsRequestDTO dto) {
        Cards newCards = manualMapper.mapToCardsRequest(dto);
        Cards savedCards = cardService.saveCards(newCards);
        CardsResponseDTO responseDTO = manualMapper.mapToCardsResponse(savedCards);
        return ResponseEntity.ok(responseDTO);
    }

    //Get all cards
    @GetMapping("/cards")
    public List<CardsResponseDTO> getAllCards() {
        return cardService.getAllCards().stream().map(manualMapper::mapToCardsResponse).toList();
    }

    //Get card by ID
    @GetMapping("/cards/{id}")
    public ResponseEntity<CardsResponseDTO> getCards(@PathVariable Long id) {
        // maybe delete this later? Optional<Cards> card = cardService.getCards(id);
                                                            //if optional is empty, this is ran
        //return card.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        return cardService.getCards(id).map(manualMapper::mapToCardsResponse).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
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
