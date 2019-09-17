package model;

public class SameName extends Exception{
	
	public SameName() {
		super("There is another element with the same name");
	}
}
