package model;

import java.util.ArrayList;

public class Game {
	
	private ArrayList<Clan> clans;

	public Game() {
		clans = new ArrayList<Clan>();
	}

	public ArrayList<Clan> getClans() {
		return clans;
	}

	public void setClans(ArrayList<Clan> clans) {
		this.clans = clans;
	}

}
