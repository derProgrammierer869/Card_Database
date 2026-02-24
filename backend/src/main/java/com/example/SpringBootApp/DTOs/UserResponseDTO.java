package com.example.SpringBootApp.DTOs;
import java.util.Date;
import java.util.List;

public record UserResponseDTO(
        Long id,
        String username,
        String email,
        List<CardsResponseDTO> cards,
        int userCards,
        Date createDate,
        Date lastUpdated
) {
}
