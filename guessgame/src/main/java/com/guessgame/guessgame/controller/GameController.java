package com.guessgame.guessgame.controller;

import com.guessgame.guessgame.dto.GuessRequest;
import com.guessgame.guessgame.dto.GuessResponse;
import com.guessgame.guessgame.dto.NewGameResponse;
import com.guessgame.guessgame.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/game")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @PostMapping("/start")
    public NewGameResponse startNewGame() {
        return gameService.startNewGame();
    }

    @PostMapping("/{id}/guess")
    public GuessResponse makeGuess(@PathVariable Long id, @RequestBody GuessRequest request) {
        return gameService.makeGuess(id, request);
    }
}
