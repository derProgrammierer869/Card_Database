package com.example.SpringBootApp.Repository;

import com.example.SpringBootApp.Entity.Cards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
                                //name of the entity and the Long is the primary key
public interface CardsRepository extends JpaRepository<Cards, Long> {
}


