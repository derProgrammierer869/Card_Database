package com.example.SpringBootApp.Entity;

import com.example.SpringBootApp.UserEntity.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

//For unit testing
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name= "cards")
public class Cards {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "cardName", nullable = false)
    private String cardName;

    //add this image upload feature later
    //@Column(name = "cardImage", nullable = true, columnDefinition = "BLOB")
    //private byte[] cardImage;

    @NotBlank
    @Column(name = "setName", nullable = false)
    private String setName;

    @Column(name ="cardNumber", nullable = false)
    private String cardNumber;

    @Column(name = "ownedCount", nullable = false)
    private Integer ownedCount;


    //make comments explaining this later
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;


    //getters
    public Long getId() {return id;}

    public String getCardName() {return cardName;}
    //public byte[] getCardImage() {return cardImage;}
    public String getSetName() {return setName;}
    public String getCardNumber() {return cardNumber;}
    public Integer getOwnedCount() {return ownedCount;}
    public User getUser() {return user;}

    //setters
    public void setId(Long id) {this.id = id;}

    public void setCardName(String cardName) {this.cardName = cardName;}
    //public void setCardImage(byte[] cardImage) {this.cardImage = cardImage;}
    public void setSetName(String setName) {this.setName = setName;}
    public void setCardNumber(String cardNumber) {this.cardNumber = cardNumber;}
    public void setOwnedCount(Integer ownedCount) {this.ownedCount = ownedCount;}
    public void setUser(User user) {this.user = user;}

}

