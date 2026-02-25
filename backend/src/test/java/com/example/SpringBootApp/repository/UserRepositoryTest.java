package com.example.SpringBootApp.repository;


import com.example.SpringBootApp.Repository.UserRepository;
import com.example.SpringBootApp.UserEntity.User;
//import org.junit.jupiter.api.Assertions;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Test
    public void UserRepository_SaveAll_ReturnData() {

        //Arrange
        User userArrange = User.builder()
                .username("Test Name")
                .password("Test Password")
                .email("test@gmail.com").build();

        //Act
        User savedUser = userRepository.save(userArrange);


        //Assert
        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void UserRepository_FindAll_ReturnsData() {
        //Arrange
        User userArrange = User.builder()
                .username("Test Name")
                .password("Test Password")
                .email("test@gmail.com").build();

        User userArrange2 = User.builder()
                .username("Test Name2")
                .password("Test Password")
                .email("test@gmail.com").build();
        userRepository.save(userArrange);
        userRepository.save(userArrange2);

        //Act
        List<User> userList = userRepository.findAll();

        //Assert
        Assertions.assertThat(userList).isNotNull();
        Assertions.assertThat(userList.size()).isEqualTo(2);

    }

    @Test
    public void UserRepository_Delete_ReturnData() {
        //Arrange
        User userArrange = User.builder()
                .username("Test Name")
                .password("Test Password")
                .email("test@gmail.com").build();
        User savedUser = userRepository.save(userArrange);


        //Act
        userRepository.delete(userArrange);
        List<User> userList = userRepository.findAll();

        //Assert
        Assertions.assertThat(userList).doesNotContain(savedUser);
        Assertions.assertThat(userList.size()).isEqualTo(0);
    }

}
