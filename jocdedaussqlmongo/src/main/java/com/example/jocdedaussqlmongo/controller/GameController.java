package com.example.jocdedaussqlmongo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.jocdedaussqlmongo.model.Game;
import com.example.jocdedaussqlmongo.model.Player;
import com.example.jocdedaussqlmongo.service.GameService;
import com.example.jocdedaussqlmongo.service.PlayerService;

@RestController
@RequestMapping("/players/{playerId}/games")
public class GameController {

	private final GameService gameService;
	private final PlayerService playerService;

	@Autowired
	public GameController(GameService gameService, PlayerService playerService) {
		this.gameService = gameService;
		this.playerService = playerService;
	}

	@GetMapping("/")
	public String getPlayerGames(@PathVariable Integer playerId, Model model) {
		List<Game> games = playerService.getPlayerGames(playerId);
		model.addAttribute("games", games);
		return "player-games"; // player-games.html
	}

	@PostMapping("/")
    public String createGame(@PathVariable Integer playerId, @ModelAttribute("game") Game game) {
        Player player = playerService.getPlayerById(playerId);
        gameService.addGameToPlayer(player, game);
        return "redirect:/players/" + playerId + "/games";  // Redirige a la lista de partidas del jugador
    }
	
	@DeleteMapping("/")
	public String deletePlayer(@PathVariable Integer playerId) {
		Player player = playerService.getPlayerById(playerId);
		gameService.deleteAllGamesByPlayer(player);
		return "redirect:/players/" + playerId + "/games"; // Redirigeix a la llista de jugadors
	}
	
	@PostMapping("/roll")
    public String rollDice(@PathVariable Integer playerId) {
        Player player = playerService.getPlayerById(playerId);
        if (player != null) {
            int dice1 = (int) (Math.random() * 6) + 1;;
            int dice2 = (int) (Math.random() * 6) + 1;;
            int diceSum = dice1+dice2;
            boolean win = (diceSum == 7);

            Game game = new Game(dice1, dice2, win);
            gameService.addGameToPlayer(player, game);
        }

        return "redirect:/players/" + playerId;  // Redirige de nuevo a la vista de detalles del jugador
    }

}
