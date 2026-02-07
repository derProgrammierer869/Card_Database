package com.example.SpringBootApp.DTOs;

public record CardsResponseDTO(Long id, String cardName, String setName, String cardNumber, Integer ownedCount) {
}
