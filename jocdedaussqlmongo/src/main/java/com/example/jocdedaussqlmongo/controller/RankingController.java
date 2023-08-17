package com.example.jocdedaussqlmongo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jocdedaussqlmongo.model.Player;
import com.example.jocdedaussqlmongo.service.RankingService;

@RestController
@RequestMapping("/players/ranking")
public class RankingController {

	private final RankingService rankingService;

	@Autowired
	public RankingController(RankingService rankingService) {
		this.rankingService = rankingService;
	}

	@GetMapping("/")
	public String getRankingWithSuccessPercentage(Model model) {
		List<Player> ranking = rankingService.getAllPlayersWithSuccessPercentage();
		Double averagePercentage = rankingService.getAverageSuccessPercentage();

		model.addAttribute("ranking", ranking);
		model.addAttribute("averagePercentage", averagePercentage);

		return "ranking"; // ranking.html
	}

	@GetMapping("/average")
	public String getAverageSuccessPercentage(Model model) {
		Double averagePercentage = rankingService.getAverageSuccessPercentage();
		model.addAttribute("averagePercentage", averagePercentage);
		return "average-Percentage"; // average-Percentage.html
	}

	@GetMapping("/loser")
	public String getLoser(Model model) {
		Player loser = rankingService.getLoserPlayer();
		model.addAttribute("loser", loser);
		return "loser-page"; // loser-page.html
	}

	@GetMapping("/winner")
	public String getWinner(Model model) {
		Player winner = rankingService.getWinnerPlayer();
		model.addAttribute("winner", winner);
		return "winner-page"; // winner-page.html
	}
}
