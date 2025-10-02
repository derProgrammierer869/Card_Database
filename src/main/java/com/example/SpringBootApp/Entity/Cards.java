package com.example.SpringBootApp.Entity;
import jakarta.persistence.*;


@Entity
@Table(name= "cards")
public class Cards {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cardName", nullable = false)
    private String cardName;

    @Column(name = "setName", nullable = false)
    private String setName;

    @Column(name ="cardNumber", nullable = false)
    private String cardNumber;


    //getters
    public Long getId() {
        return id;
    }

    public String getCardName() {
        return cardName;
    }

    public String getSetName() {
        return setName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    //setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }
    public void setSetName(String setName) {
        this.setName = setName;
    }
    public void setCardNumber(String cardNumber) {this.cardNumber = cardNumber;}

}

