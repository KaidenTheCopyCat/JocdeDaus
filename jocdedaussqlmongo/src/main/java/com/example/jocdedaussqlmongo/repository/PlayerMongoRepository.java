package com.example.jocdedaussqlmongo.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.jocdedaussqlmongo.model.Player;

@Repository
public interface PlayerMongoRepository extends MongoRepository<Player, Integer> {
	Player findByName(String name);

	Optional<Player> findById(Integer playerId);
}
