package ui;

import java.io.IOException;
import java.util.Scanner;
import model.*;

public class Main {
	
	private Scanner reader;
	private Game theGame;

	public Main() {
		reader = new Scanner(System.in);
		try {
			theGame = new Game();
			menu();
		} catch (ClassNotFoundException | IOException | SameName e) {
			e.printStackTrace();
		}
	}
	
	public void menu() {
		
	}
	
	public static void main(String[] args) {
		Main m = new Main();
	}

}
