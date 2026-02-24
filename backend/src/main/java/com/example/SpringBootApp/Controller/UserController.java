package com.example.SpringBootApp.Controller;

import com.example.SpringBootApp.DTOs.CardsRequestDTO;
import com.example.SpringBootApp.DTOs.CardsResponseDTO;
import com.example.SpringBootApp.DTOs.UserRequestDTO;
import com.example.SpringBootApp.DTOs.UserResponseDTO;
import com.example.SpringBootApp.Entity.Cards;
import com.example.SpringBootApp.Service.CardService;
import com.example.SpringBootApp.UserEntity.User;
import com.example.SpringBootApp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.SpringBootApp.Repository.CardsRepository;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import com.example.SpringBootApp.Mappers.ManualMapper;
import org.springframework.security.core.Authentication;


@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final CardService cardService;

    @Autowired
    public UserController(UserService userService, CardService cardService) {
        this.userService = userService;
        this.cardService = cardService;
    }

    @Autowired
    private ManualMapper manualMapper;


    //get all users
    @GetMapping
    public List<UserResponseDTO> getAllUsers() {
        return userService.getAllUsers().stream().map(manualMapper::mapToUserResponse).toList();
    }

    //get user by id
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        return userService.getUserById(id).map(manualMapper::mapToUserResponse).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    //update user by id
    // update
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        User updatedUser = userService.updateUser(id, user);
        return ResponseEntity.ok(updatedUser);
    }


    //change this so that it only deletes the current logged in user
    //add this feature to all methods, only allow user to see their own cards by default
    //they have to use another method to see other peoples cards
    //delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }

    //this should only delete the current users cards
    @DeleteMapping("/{id}/deleteallcards")
    public ResponseEntity<Void> deleteUserCards(@PathVariable Long id) {
        cardService.deleteAllCards(id);
        return ResponseEntity.noContent().build();
    }

    //getter for users cards
    // this may not work!!!!!!!!!
    // check this later to see if works!!!!!!!!
    @GetMapping("/{id}/cards")
    public ResponseEntity<List<CardsResponseDTO>> getUserCards(
            @PathVariable Long id,
            Authentication authentication // Spring injects the logged-in user here
    ) {
        // Get the logged-in User object
        User loggedInUser = (User) authentication.getPrincipal();

        // Check that the requested id matches the logged-in user
        if (!loggedInUser.getId().equals(id)) {
            return ResponseEntity.status(403).build(); // Forbidden
        }

        List<Cards> cards = userService.getUserCards(id);
        List<CardsResponseDTO> dtos = cards.stream()
                .map(manualMapper::mapToCardsResponse)
                .toList();

        return ResponseEntity.ok(dtos);
    }


    //post users cards
    @PostMapping("/{id}/cards")
    public ResponseEntity<CardsResponseDTO> addCardToUser(@PathVariable Long id, @RequestBody CardsRequestDTO dto) {
        Cards card = manualMapper.mapToCardsRequest(dto);
        Cards newCard = userService.addCardToUser(id, card);
        CardsResponseDTO responseDTO = manualMapper.mapToCardsResponse(newCard);
        return ResponseEntity.ok(responseDTO);
    }

    //testing method  DELETE LATER
    @GetMapping("/ping")
    public ResponseEntity<String> ping(){ return ResponseEntity.ok("pong"); }

//    //get all logged in users getLoggedInUsers()
//    @GetMapping("/loggedInUsers")
//    public List<UserResponseDTO> getLoggedInUsers() {
//        return userService.getLoggedInUsers().stream().map(manualMapper::mapToUserResponse).toList();
//    }



}
