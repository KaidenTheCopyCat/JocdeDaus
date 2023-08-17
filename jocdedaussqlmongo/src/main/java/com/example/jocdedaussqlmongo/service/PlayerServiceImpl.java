package com.example.jocdedaussqlmongo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jocdedaussqlmongo.model.Game;
import com.example.jocdedaussqlmongo.model.Player;
import com.example.jocdedaussqlmongo.repository.PlayerRepository;

@Service
public class PlayerServiceImpl implements PlayerService {

	private final PlayerRepository playerRepository;

	@Autowired
	public PlayerServiceImpl(PlayerRepository playerRepository) {
		this.playerRepository = playerRepository;
	}

	public Player createPlayer(String playerName) {
		Player player = new Player(playerName);
		return playerRepository.save(player);
	}

	@Override
	public Player updatePlayerName(Integer playerId, String newName) {
		Player player = playerRepository.findById(playerId).orElse(null);
		if (player != null) {
			player.setName(newName);
			return playerRepository.save(player);
		}
		return null;
	}

	@Override
	public void addGameToPlayer(Integer playerId, Game game) {
		Player player = playerRepository.findById(playerId).orElse(null);
		if (player != null) {
			player.addGame(game);
			playerRepository.save(player);
		}
	}

	@Override
	public List<Player> getAllPlayers() {
		return playerRepository.findAll();
	}

	@Override
	public List<Game> getPlayerGames(Integer playerId) {
		Player player = playerRepository.findById(playerId).orElse(null);
		if (player != null) {
			return player.getGames();
		}
		return null;
	}

	@Override
	public Player getPlayerById(Integer playerId) {
		return playerRepository.findById(playerId).orElse(null);
	}

	@Override
	public void deletePlayer(Integer playerId) {
		playerRepository.deleteById(playerId);
	}

}
