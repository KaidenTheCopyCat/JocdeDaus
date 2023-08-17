package com.example.jocdedaussqlmongo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jocdedaussqlmongo.model.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {
	Player findByName(String name);
	Optional<Player> findById(Integer playerId);
}
