package com.example.SpringBootApp.Controller;

import com.example.SpringBootApp.Service.AdminService;
import com.example.SpringBootApp.Service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/adminFunctions")
public class AdminController {

    private final AdminService adminService;
    private final CardService cardService;

    @Autowired
    public AdminController(AdminService adminService, CardService cardService) {
        this.adminService = adminService;
        this.cardService = cardService;
    }

    @DeleteMapping("/deleteAllUsers")
    public ResponseEntity<String> deleteAllUsers() {
        adminService.deleteAllUsers();
        return ResponseEntity.ok("All users have been deleted!");
    }

    //this should be able to delete any users cards as opposed to the basic user method
    //in the UserController
    @DeleteMapping("/{id}/deleteallcards")
    public ResponseEntity<Void> deleteUserCards(@PathVariable Long id) {
        cardService.deleteAllCards(id);
        return ResponseEntity.noContent().build();
    }

}
