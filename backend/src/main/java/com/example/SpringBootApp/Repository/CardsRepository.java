package com.example.SpringBootApp.Repository;

import com.example.SpringBootApp.Entity.Cards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
                                //name of the entity and the Long is the primary key
public interface CardsRepository extends JpaRepository<Cards, Long> {
    //checks to see if the cards already exist or not
    boolean existsByUserIdAndCardNameAndSetNameAndCardNumberAndOwnedCount(Long userId, String cardName, String setName, String cardNumber, Integer ownedCount);

    Optional<Cards> findByUserIdAndCardNameAndSetNameAndCardNumber(
            Long userId,
            String cardName,
            String setName,
            String cardNumber
    );

    @Modifying
    @Transactional
    @Query("delete from Cards c where c.user.id = :userId")
    void deleteAllCardsFromUser(@Param("userId") Long userId);
}


