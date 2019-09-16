package ui;

import java.util.Scanner;

import model.*;

public class Main {
	
	private Scanner reader;
	private Game theGame;

	public Main() {
		reader = new Scanner(System.in);
		theGame = new Game();
	}

	public static void main(String[] args) {
		Main m = new Main();
	}

}
