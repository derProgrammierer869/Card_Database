package com.example.SpringBootApp.UserEntity;

import com.example.SpringBootApp.Entity.Cards;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

//possibly add email signup notifications
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;


    //mapping relations
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    //this will init a new list so that the app doesnt crash when making a new user with saveUser in the user controller
    private List<Cards> cards = new ArrayList<>();

    //getters
    public Long getId() {return id;}

    public String getUsername() {return username;}
    public String getPassword() {return password;}
    public List<Cards> getCards() {return cards;}

    //setters
    public void setId(Long id) {this.id = id;}

    public void setUsername(String username) {this.username = username;}
    public void setPassword(String password) {this.password = password;}
    public void setCards(List<Cards> cards) {this.cards = cards;}

}
