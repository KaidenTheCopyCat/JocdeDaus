package com.example.jocdedaussqlmongo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.jocdedaussqlmongo.model.Game;
import com.example.jocdedaussqlmongo.model.Player;
import com.example.jocdedaussqlmongo.service.PlayerService;

@RestController
@RequestMapping("/players")
public class PlayerController {

	private final PlayerService playerService;

	@Autowired
	public PlayerController(PlayerService playerService) {
		this.playerService = playerService;
	}

	@GetMapping("/")
	public String getAllPlayers(Model model) {
		List<Player> players = playerService.getAllPlayers();
		model.addAttribute("players", players);
		return "player-list"; // Renderitza la plantilla player-list.html
	}

	@GetMapping("/{playerId}")
	public String getPlayerDetails(@PathVariable Integer playerId, Model model) {
		Player player = playerService.getPlayerById(playerId);
		model.addAttribute("player", player);
		return "player"; // player.html
	}

	@PostMapping("/")
	public String createPlayer(@RequestParam String playerName) {
		playerService.createPlayer(playerName);
		return "redirect:/players"; // Redirigeix a la llista de jugadors
	}

	@PutMapping("/{playerId}")
	public String updatePlayerName(@PathVariable Integer playerId, @RequestParam String newName) {
		playerService.updatePlayerName(playerId, newName);
		return "redirect:/players"; // Redirigeix a la llista de jugadors
	}

	@GetMapping("/{playerId}/games")
	public String getPlayerGames(@PathVariable Integer playerId, Model model) {
		List<Game> games = playerService.getPlayerGames(playerId);
		model.addAttribute("games", games);
		return "player-games"; // player-games.html
	}

	@DeleteMapping("/{playerId}")
	public String deletePlayer(@PathVariable Integer playerId) {
		playerService.deletePlayer(playerId);
		return "redirect:/players"; // Redirigeix a la llista de jugadors
	}

}
