package com.example.SpringBootApp.Repository;

import com.example.SpringBootApp.UserEntity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//name of the entity and the Long is the primary key
public interface UserRepository extends JpaRepository<User, Long> {
}