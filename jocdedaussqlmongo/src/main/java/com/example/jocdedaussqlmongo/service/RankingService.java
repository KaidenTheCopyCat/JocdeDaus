package com.example.jocdedaussqlmongo.service;

import java.util.List;

import com.example.jocdedaussqlmongo.model.Player;

public interface RankingService {

	List<Player> getAllPlayersWithSuccessPercentage();

	Player getLoserPlayer();

	Player getWinnerPlayer();

	Double getAverageSuccessPercentage();
}
