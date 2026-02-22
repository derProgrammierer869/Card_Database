package com.example.SpringBootApp.UserEntity;

import com.example.SpringBootApp.Entity.Cards;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.*;


//For unit testing
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @NotBlank
    @Column(name = "password", nullable = false)
    private String password;

    @Email
    @NotBlank
    @Column(name = "email", nullable = false)
    private String email;

    @CreationTimestamp
    @Column(name = "created_date", updatable = false)
    private Date createdDate;

    @UpdateTimestamp
    @Column(name = "last_updated")
    private Date lastUpdated;


    //mapping relations
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    //this will init a new list so that the app does not crash when making a new user with saveUser in the user controller. this is empty
    private List<Cards> cards = new ArrayList<>();

    //does not need to be stored in the DB. Doesnt need column,getter or setter
    @Transient
    public int userCards() {
        return cards.size();
    }

    //getters
    public Long getId() {return id;}

    public String getUsername() {return username;}
    public String getPassword() {return password;}
    public String getEmail() {return email;}
    public List<Cards> getCards() {return cards;}
    public Date getCreatedDate() {return createdDate;}
    public Date getLastUpdated() {return lastUpdated;}

    //setters
    public void setId(Long id) {this.id = id;}

    public void setUsername(String username) {this.username = username;}
    public void setPassword(String password) {this.password = password;}
    public void setEmail(String email) {this.email = email;}
    public void setCards(List<Cards> cards) {this.cards = cards;}
    public void setCreatedDate(Date createdDate) {this.createdDate = createdDate;}
    public void setLastUpdated(Date lastUpdated) {this.lastUpdated = lastUpdated;}
}
