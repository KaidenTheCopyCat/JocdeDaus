package com.example.jocdedaussqlmongo.service;

import java.util.List;

import com.example.jocdedaussqlmongo.model.Game;
import com.example.jocdedaussqlmongo.model.Player;

public interface PlayerService {

	public Player createPlayer(String playerName);

	public Player updatePlayerName(Integer playerId, String newName);

	public void addGameToPlayer(Integer playerId, Game game);

	public List<Player> getAllPlayers();

	public List<Game> getPlayerGames(Integer playerId);

	public Player getPlayerById(Integer playerId);

	public void deletePlayer(Integer playerId);

}
