package com.example.jocdedaussqlmongo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.jocdedaussqlmongo.model.Game;
import com.example.jocdedaussqlmongo.model.Player;
import com.example.jocdedaussqlmongo.repository.GameRepository;
import com.example.jocdedaussqlmongo.repository.PlayerRepository;

@Service
@Transactional
public class GameServiceImpl implements GameService {

	private final GameRepository gameRepository;
	private final PlayerRepository playerRepository;

	@Autowired
	public GameServiceImpl(GameRepository gameRepository,PlayerRepository playerRepository) {
		this.gameRepository = gameRepository;
		this.playerRepository=playerRepository;
	}

	@Override
	public void saveGame(Game game) {
		gameRepository.save(game);
	}

	@Override
	public void addGameToPlayer(Player player, Game game) {
		determineGameResult(game);
		player.addGame(game);
		gameRepository.save(game);
	}

	@Override
	public void deleteAllGamesByPlayer(Player player) {
		List<Game> games = player.getGames();
		for (Game game : games) {
			gameRepository.delete(game);
		}
		player.clearGames();
		playerRepository.save(player);
	}

	private void determineGameResult(Game game) {
		int diceSum = game.getDice1() + game.getDice2();
		game.setWin(diceSum == 7);
	}

}
