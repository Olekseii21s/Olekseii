package com.guessgame.guessgame.service;

import com.guessgame.guessgame.dto.GuessRequest;
import com.guessgame.guessgame.dto.GuessResponse;
import com.guessgame.guessgame.dto.NewGameResponse;
import com.guessgame.guessgame.model.Game;
import com.guessgame.guessgame.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;
    private final Random random = new Random();

    public NewGameResponse startNewGame() {
        int number = random.nextInt(100) + 1;

        Game game = Game.builder()
                .targetNumber(number)
                .attemptsLeft(10)
                .isWon(false)
                .build();

        game = gameRepository.save(game);

        return new NewGameResponse(game.getId(), game.getAttemptsLeft());
    }

    public GuessResponse makeGuess(Long gameId, GuessRequest request) {
        Optional<Game> optionalGame = gameRepository.findById(gameId);

        if (optionalGame.isEmpty()) {
            return new GuessResponse("Game not found!", 0, true);
        }

        Game game = optionalGame.get();

        if (game.isWon() || game.getAttemptsLeft() <= 0) {
            return new GuessResponse("Game is already over!", game.getAttemptsLeft(), true);
        }

        int guess = request.getGuessedNumber();
        int target = game.getTargetNumber();

        game.setAttemptsLeft(game.getAttemptsLeft() - 1);

        if (guess == target) {
            game.setWon(true);
            gameRepository.save(game);
            return new GuessResponse("🎉 Congratulations! You guessed the number!", game.getAttemptsLeft(), true);
        }

        String message = guess < target ? "🔼 The number is higher." : "🔽 The number is lower.";
        boolean isOver = game.getAttemptsLeft() <= 0;

        if (isOver) message = "💥 Game over! The number was " + target;

        gameRepository.save(game);
        return new GuessResponse(message, game.getAttemptsLeft(), isOver);
    }
}
