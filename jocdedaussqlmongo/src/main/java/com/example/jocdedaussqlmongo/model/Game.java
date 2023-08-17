package com.example.jocdedaussqlmongo.model;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Document
@Entity
@Table(name = "games")
public class Game {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer game_id;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_player_id", referencedColumnName = "player_id")
	private Player player;

	@Column(name = "dice1_value")
	private int dice1;

	@Column(name = "dice2_value")
	private int dice2;

	@Column(name = "is_win")
	private boolean isWin;

	public Game(Integer game_id, Player player, int dice1, int dice2, boolean isWin) {
		super();
		this.game_id = game_id;
		this.player = player;
		this.dice1 = dice1;
		this.dice2 = dice2;
		this.isWin = isWin;
	}

	public Game() {
	}

	public Game(int dice1, int dice2, boolean isWin) {
		this.dice1 = dice1;
		this.dice2 = dice2;
		this.isWin = isWin;
	}

	public Integer getId() {
		return game_id;
	}

	public void setId(Integer id) {
		this.game_id = id;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public int getDice1() {
		return dice1;
	}

	public void setDice1(int dice1) {
		this.dice1 = dice1;
	}

	public int getDice2() {
		return dice2;
	}

	public void setDice2(int dice2) {
		this.dice2 = dice2;
	}

	public boolean isWin() {
		return isWin;
	}

	public void setWin(boolean isWin) {
		this.isWin = isWin;
	}

}
