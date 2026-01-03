package com.example.SpringBootApp.DTOs;
import java.util.List;

public record UserResponseDTO(Long id, String username, List<CardsResponseDTO> cards, int userCards) {
}
