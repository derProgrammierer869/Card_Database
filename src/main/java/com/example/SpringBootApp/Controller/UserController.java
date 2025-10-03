package com.example.SpringBootApp.Controller;

import com.example.SpringBootApp.Entity.Cards;
import com.example.SpringBootApp.UserEntity.User;
import com.example.SpringBootApp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.SpringBootApp.Repository.CardsRepository;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //create new user
    @PostMapping("/user")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        User newUser = userService.saveUser(user);
        return ResponseEntity.ok(newUser);
    }

    //get all users
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    //get user by id
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //update user by id
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        User updatedUser = userService.updateUser(id, user);
        return ResponseEntity.ok(updatedUser);
    }

    //delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }


    //getter for users cards
    @GetMapping("/{id}/cards")
    public ResponseEntity<List<Cards>> getUserCards(@PathVariable Long id) {
        List<Cards> cards = userService.getUserCards(id);
        return ResponseEntity.ok(cards);
    }

    //post users cards
    @PostMapping("/{id}/cards")
    public ResponseEntity<Cards> addCardToUser(@PathVariable Long id, @RequestBody Cards card) {
        Cards newCard = userService.addCardToUser(id, card);
        return ResponseEntity.ok(newCard);
    }

    //testing method
    @GetMapping("/ping")
    public ResponseEntity<String> ping(){ return ResponseEntity.ok("pong"); }


}
