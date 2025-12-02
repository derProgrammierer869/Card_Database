package com.example.SpringBootApp.DTOs;
import java.util.List;

public record UserResponseDTO(Long id, String username, String password, List<CardsRequestDTO> cards) {
}
