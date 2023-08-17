package com.example.jocdedaussqlmongo.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Document
@Entity
@Table(name = "players")
public class Player {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer player_id;

	private String name;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "registration_date")
	private Date registrationDate;

	@Column(name = "success_rate")
	private Double successRate;
	
	@OneToMany(mappedBy = "player", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Game> games;

	
	public Player(Integer player_id, String name, Date registrationDate, Double successRate, List<Game> games) {
		super();
		this.player_id = player_id;
		this.name = name;
		this.registrationDate = registrationDate;
		this.successRate = successRate;
		this.games = games;
	}
	
	public Player(String name) {
		this.name = name;
		this.registrationDate= new Date();
		this.successRate = 0.0;
		this.games = null;
	}

	public Player() {}

	
	public Integer getId() {
		return player_id;
	}

	public void setId(Integer id) {
		this.player_id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date date) {
		this.registrationDate = date;
	}

	public Double getSuccessRate() {
		return successRate;
	}

	public void setSuccessRate(Double successRate) {
		this.successRate = successRate;
	}

	public List<Game> getGames() {
		return games;
	}
	
	public void setGames(List<Game> games) {
		this.games = games;
	}

	public void addGame(Game game) {
		game.setPlayer(this);
		games.add(game);
	}

	public void clearGames() {
		games.clear();		
	}
	
}
