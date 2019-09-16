package model;

public class Ninja {
	
	private String name;
	private String personality;
	private String creationDate;
	private double power;
	private Ninja previous;
	private Ninja next;
	private Technique first;
	
	public Ninja(String name, String personality, String creationDate, double power) {
		this.name = name;
		this.personality = personality;
		this.creationDate = creationDate;
		this.power = power;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPersonality() {
		return personality;
	}

	public void setPersonality(String personality) {
		this.personality = personality;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public double getPower() {
		return power;
	}

	public void setPower(double power) {
		this.power = power;
	}

	public Ninja getPrevious() {
		return previous;
	}

	public void setPrevious(Ninja previous) {
		this.previous = previous;
	}

	public Ninja getNext() {
		return next;
	}

	public void setNext(Ninja next) {
		this.next = next;
	}

	public Technique getFirst() {
		return first;
	}

	public void setFirst(Technique first) {
		this.first = first;
	}
}
