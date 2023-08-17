package com.example.jocdedaussqlmongo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jocdedaussqlmongo.model.Game;
import com.example.jocdedaussqlmongo.model.Player;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer>{
	List<Game> findByPlayerId(Integer playerId);
	void deleteByPlayer(Player player);
}
