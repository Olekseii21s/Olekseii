package com.guessgame.guessgame.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NewGameResponse {
    private Long gameId;
    private int attemptsLeft;
}
