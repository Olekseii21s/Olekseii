package com.guessgame.guessgame.repository;

import com.guessgame.guessgame.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}
