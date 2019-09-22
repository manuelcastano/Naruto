package ui;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import model.*;

public class Main {
	
	private Scanner reader;
	private Game theGame;

	public Main() {
		reader = new Scanner(System.in);
		try {
			System.out.println("Welcome to the program");
			System.out.println("Loading the data...");
			theGame = new Game();
			menu();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public void menu() {
		int option = -1;
		while(option != 0) {
			System.out.println("0. Exit");
			System.out.println("1. Add a clan");
			System.out.println("2. Add a character");
			System.out.println("3. Add a technique");
			System.out.println("4. Update a clan");
			System.out.println("5. Update a character");
			System.out.println("6. Update a technique");
			System.out.println("7. Delete a clan");
			System.out.println("8. Delete a character");
			System.out.println("9. Delete a technique");
			System.out.println("10. Order clans");
			System.out.println("11. Order characters");
			System.out.println("12. Find a clan");
			System.out.println("13. Find a character");
			System.out.println("14. Find a technique");
			try {
				option = reader.nextInt();
				reader.nextLine();
			}catch(InputMismatchException e) {
				reader.nextLine();
				System.out.println("Please select a correct option");
			}
			switch(option) {
			case 0:
				System.out.println("See you later");
				break;
			case 1:
				addClan();
				break;
			case 2:
				addCharacter();
				break;
			case 3:
				addTechnique();
				break;
			case 4:
				updateClan();
				break;
			case 5:
				updateCharacter();
				break;
			case 6:
				updateTechnique();
				break;
			case 7:
				deleteClan();
				break;
			case 8:
				deleteCharacter();
				break;
			case 9:
				deleteTechnique();
				break;
			case 10:
				orderClans();
				break;
			case 11:
				orderCharacters();
				break;
			case 12:
				findClan();
				break;
			case 13:
				findCharacter();
				break;
			case 14:
				findTechnique();
				break;
			default:
				System.out.println("Please select a correct option");
				break;
			}
		}
	}
	
	public void addClan() {
		System.out.println("Name of the clan:");
		String nameClan = reader.nextLine();
		Clan e = new Clan(nameClan);
		try {
			theGame.addClan(e);
			System.out.println("The clan were added successfuly");
		}catch(Exception e1) {
			System.out.println(e1.getMessage());
		}
	}
	
	public void addCharacter() {
		System.out.println("Name of the character:");
		String nameCharacter = reader.nextLine();
		System.out.println("Personality:");
		String personality = reader.nextLine();
		System.out.println("Creation date (yyyy/mm/dd):");
		String creationDate = reader.nextLine();
		System.out.println("Power:");
		double power = 0.0;
		boolean correct = false;
		while(!correct) {
			try {
				power = reader.nextDouble();
				reader.nextLine();
				correct = true;
			}catch(InputMismatchException e) {
				System.out.println("Please write a correct power");
			}
		}
		System.out.println("Name of the clan:");
		String nameClan = reader.nextLine();
		Ninja e = new Ninja(nameCharacter, personality, creationDate, power);
		try {
			if(!theGame.addNinja(e, nameClan)) {
				System.out.println("The clan doesn't exist");
			}else {
				System.out.println("The character were added successfuly");
			}
		} catch (SameName | IOException e1) {
			System.out.println(e1.getMessage());;
		}
	}
	
	public void addTechnique() {
		System.out.println("Name of the technique:");
		String nameTechnique = reader.nextLine();
		System.out.println("Factor:");
		double factor = 0.0;
		boolean correct = false;
		while(!correct) {
			try {
				factor = reader.nextDouble();
				reader.nextLine();
				correct = true;
			}catch(InputMismatchException e) {
				System.out.println("Please write a correct power");
			}
		}
		System.out.println("Name of the Character:");
		String nameCharacter = reader.nextLine();
		Technique e = new Technique(nameTechnique, factor);
		try {
			if(!theGame.addTechnique(e, nameCharacter)) {
				System.out.println("The character doesn't exist");
			}else {
				System.out.println("The technique were added successfuly");
			}
		} catch (SameName | IOException e1) {
			System.out.println(e1.getMessage());;
		}
	}
	
	public void updateClan() {
		System.out.println("name of the clan");
		String nameClan = reader.nextLine();
		System.out.println("New name");
		String newName = reader.nextLine();
		try {
			if(!theGame.updateClanByName(nameClan, newName)) {
				System.out.println("The clan doesn't exist");
			}
			else {
				System.out.println("The clan were updated successfuly");
			}
		} catch (IOException | SameName e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void updateCharacter() {
		int option = -1;
		while(option != 0) {
			System.out.println("What do you want to update?");
			System.out.println("0. Back");
			System.out.println("1. Name");
			System.out.println("2. Personality");
			System.out.println("3. Creation date");
			System.out.println("4. Power");
			try {
				option = reader.nextInt();
				reader.nextLine();
			}catch(InputMismatchException e) {
				System.out.println("Please select a correct option");
			}
			switch(option) {
			case 0:
				break;
			case 1:
				System.out.println("Name of the charater");
				String nameNinja = reader.nextLine();
				System.out.println("New name");
				String newName = reader.nextLine();
				try {
					if(!theGame.updateNinjaByName(nameNinja, newName)) {
						System.out.println("The character doesn't exist");
					}
					else {
						System.out.println("The character were updated successfuly");
					}
				} catch (IOException | SameName e) {
					System.out.println(e.getMessage());
				}
				break;
			case 2:
				System.out.println("Name of the charater");
				nameNinja = reader.nextLine();
				System.out.println("New personality");
				String personality = reader.nextLine();
				try {
					if(!theGame.updateNinjaByPersonality(nameNinja, personality)) {
						System.out.println("The character doesn't exist");
					}
					else {
						System.out.println("The character were updated successfuly");
					}
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 3:
				System.out.println("Name of the charater");
				nameNinja = reader.nextLine();
				System.out.println("New creation date (yyyy/mm/dd)");
				String creationDate = reader.nextLine();
				try {
					if(!theGame.updateNinjaByCreationDate(nameNinja, creationDate)) {
						System.out.println("The character doesn't exist");
					}
					else {
						System.out.println("The character were updated successfuly");
					}
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 4:
				System.out.println("Name of the charater");
				nameNinja = reader.nextLine();
				System.out.println("New power");
				double power = 0.0;
				boolean correct = false;
				while(!correct) {
					try {
						power = reader.nextDouble();
						reader.nextLine();
						correct = true;
					}catch(InputMismatchException e) {
						System.out.println("Please write a correct power");
					}
				}
				try {
					if(!theGame.updateNinjaByPower(nameNinja, power)) {
						System.out.println("The character doesn't exist");
					}
					else {
						System.out.println("The character were updated successfuly");
					}
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
				break;
			default:
				System.out.println("Please select a correct option");
				break;
			}
		}
	}
	
	public void updateTechnique() {
		int option = -1;
		while(option != 0) {
			System.out.println("What do you want to update?");
			System.out.println("0. Back");
			System.out.println("1. Name");
			System.out.println("2. Factor");
			try {
				option = reader.nextInt();
				reader.nextLine();
			}catch(InputMismatchException e) {
				System.out.println("Please select a correct option");
			}
			switch(option) {
			case 0:
				break;
			case 1:
				System.out.println("Name of the tehcnique");
				String nameTechnique = reader.nextLine();
				System.out.println("New name");
				String newName = reader.nextLine();
				try {
					if(!theGame.updateTechniqueByName(nameTechnique, newName)) {
						System.out.println("The technique doesn't exist");
					}
					else {
						System.out.println("The technique were updated successfuly");
					}
				} catch (IOException | SameName e) {
					System.out.println(e.getMessage());
				}
				break;
			case 2:
				System.out.println("Name of the technique");
				nameTechnique = reader.nextLine();
				System.out.println("New factor:");
				double factor = 0.0;
				boolean correct = false;
				while(!correct) {
					try {
						factor = reader.nextDouble();
						reader.nextLine();
						correct = true;
					}catch(InputMismatchException e) {
						System.out.println("Please write a correct power");
					}
				}
				try {
					if(!theGame.updateTechniqueByFactor(nameTechnique, factor)) {
						System.out.println("The technique doesn't exist");
					}
					else {
						System.out.println("The technique were updated successfuly");
					}
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
				break;
			default:
				System.out.println("Please select a correct option");
				break;
			}
		}
	}
	
	public void deleteClan() {
		System.out.println("Name of the clan");
		String nameClan = reader.nextLine();
		try {
			if(!theGame.deleteClan(nameClan)) {
				System.out.println("The clan doesn't exist");
			}else {
				System.out.println("The clan were deleted successfuly");
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void deleteCharacter() {
		System.out.println("Name of the character");
		String nameNinja = reader.nextLine();
		try {
			if(!theGame.deleteNinja(nameNinja)) {
				System.out.println("The character doesn't exist");
			}else {
				System.out.println("The character were deleted successfuly");
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void deleteTechnique() {
		System.out.println("Name of the technique");
		String nameTechnique = reader.nextLine();
		try {
			if(!theGame.deleteTechnique(nameTechnique)) {
				System.out.println("The character doesn't exist");
			}else {
				System.out.println("The character were deleted successfuly");
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void orderClans() {
		theGame.orderClans();
		System.out.println(theGame.theClans());
	}
	
	public void orderCharacters() {
		theGame.orderNinjas();
		System.out.println(theGame.theNinjas());
	}
	
	public void findClan() {
		System.out.println("Name of the clan");
		String nameClan = reader.nextLine();
		Clan e = theGame.findClan(nameClan);
		if(e != null) {
			System.out.println(e);
		}else {
			System.out.println("The clan doesn't exist");
		}
	}
	
	public void findCharacter() {
		System.out.println("Name of the character");
		String nameNinja = reader.nextLine();
		Ninja e = theGame.findNinja(nameNinja);
		if(e != null) {
			System.out.println(e);
		}else {
			System.out.println("The character doesn't exist");
		}
	}
	
	public void findTechnique() {
		System.out.println("Name of the technique");
		String nameTechnique = reader.nextLine();
		Technique e = theGame.findTechnique(nameTechnique);
		if(e != null) {
			System.out.println(e);
		}else {
			System.out.println("The technique doesn't exist");
		}
	}
	
	public static void main(String[] args) {
		Main m = new Main();
	}

}
