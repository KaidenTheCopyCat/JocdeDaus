package com.example.jocdedaussqlmongo.service;

import com.example.jocdedaussqlmongo.model.Game;
import com.example.jocdedaussqlmongo.model.Player;

public interface GameService {

	public void saveGame(Game game);

	public void addGameToPlayer(Player player, Game game);

	void deleteAllGamesByPlayer(Player player);

}
