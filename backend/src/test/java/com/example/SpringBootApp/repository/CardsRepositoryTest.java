package com.example.SpringBootApp.repository;

import com.example.SpringBootApp.Repository.CardsRepository;
import com.example.SpringBootApp.Entity.Cards;
//import org.junit.jupiter.api.Assertions;
import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CardsRepositoryTest {
    @Autowired
    private CardsRepository cardsRepository;
    @Test
    public void CardsRepository_SaveAll_ReturnedData() {

        //Arrange
        Cards cardsArrange = Cards.builder()
                .cardName("Test card name")
                .setName("Test set name")
                .cardNumber("2")
                .ownedCount(1).build();

        //Act
        Cards savedCards = cardsRepository.save(cardsArrange);


        //Assert
        Assertions.assertThat(savedCards).isNotNull();
        Assertions.assertThat(savedCards.getId()).isGreaterThan(0);
    }
}
