package com.example.jocdedaussqlmongo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jocdedaussqlmongo.model.Game;
import com.example.jocdedaussqlmongo.model.Player;
import com.example.jocdedaussqlmongo.repository.PlayerRepository;

@Service
public class RankingServiceImpl implements RankingService {

	private final PlayerRepository playerRepository;

	@Autowired
	public RankingServiceImpl(PlayerRepository playerRepository) {
		this.playerRepository = playerRepository;
	}

	@Override
	public List<Player> getAllPlayersWithSuccessPercentage() {
		List<Player> players = playerRepository.findAll();

		for (Player player : players) {
			calculateSuccessPercentage(player);
		}
		return players;
	}

	public Double getAverageSuccessPercentage() {
		List<Player> players = playerRepository.findAll();
		double totalSuccessPercentage = 0.0;
		for (Player player : players) {
			totalSuccessPercentage += calculateSuccessPercentage(player);
		}
		return players.isEmpty() ? 0.0 : totalSuccessPercentage / players.size();
	}

	@Override
	public Player getLoserPlayer() {
		List<Player> players = playerRepository.findAll();
		Player loser = null;
		double minSuccessPercentage = Double.MAX_VALUE;
		for (Player player : players) {
			double successPercentage = calculateSuccessPercentage(player);
			if (successPercentage < minSuccessPercentage) {
				minSuccessPercentage = successPercentage;
				loser = player;
			}
		}
		return loser;
	}

	@Override
	public Player getWinnerPlayer() {
		List<Player> players = playerRepository.findAll();
		Player winner = null;
		double maxSuccessPercentage = Double.MIN_VALUE;
		for (Player player : players) {
			double successPercentage = calculateSuccessPercentage(player);
			if (successPercentage > maxSuccessPercentage) {
				maxSuccessPercentage = successPercentage;
				winner = player;
			}
		}
		return winner;
	}

	private double calculateSuccessPercentage(Player player) {
		List<Game> games = player.getGames();
		int totalGames = games.size();
		if (totalGames == 0) {
			return 0.0;
		}

		int totalWins = 0;
		for (Game game : games) {
			if (game.isWin()) {
				totalWins++;
			}
		}

		return (double) totalWins / totalGames * 100;
	}

}
