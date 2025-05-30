package com.guessgame.guessgame.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GuessResponse {
    private String message;
    private int attemptsLeft;
    private boolean isGameOver;
}
