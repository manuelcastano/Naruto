package model;

import java.io.Serializable;
import java.util.Comparator;

public class Ninja implements Comparable<Ninja>, Comparator<Ninja>, Serializable{
	
	private String name;
	private String personality;
	private String creationDate;
	private double power;
	private double score;
	private Ninja previous;
	private Ninja next;
	private Technique first;
	
	public Ninja(String name, String personality, String creationDate, double power) {
		this.name = name;
		this.personality = personality;
		this.creationDate = creationDate;
		this.power = power;
		setScore();
	}

	@Override
	public String toString() {
		return "name=" + name + ", personality=" + personality + ", creationDate=" + creationDate + ", power="
				+ power + ", score=" + score;
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

	public double getScore() {
		return score;
	}

	public void setScore() {
		score = 0.0;
		Technique actual = first;
		while(actual != null) {
			score += power*actual.getFactor();
			actual = actual.getNext();
		}
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

	@Override
	public int compareTo(Ninja e) {
		return name.compareTo(e.getName());
	}
	
	public boolean addTechnique(Technique e) throws SameName {
		boolean added = false;
		if(!sameTechnique(e.getName())) {
			if(first == null) {
				first = e;
				added = true;
			}
			else if(first.compare(first, e) > 0) {
				e.setNext(first);
				first = e;
				added = true;
			}
			else {
				Technique actual = first;
				while(actual != null && actual.getNext() != null && !added) {
					if(actual.getNext().compare(actual.getNext(), e) > 0) {
						actual.insertAfter(e);
						added = true;
					}
					actual = actual.getNext();
				}
				if(!added && actual != null) {
					actual.setNext(e);
					added = true;
				}
			}
		}
		else {
			throw new SameName();
		}
		setScore();
		return added;
	}
	
	public boolean sameTechnique(String nameTechnique) {
		boolean same = false;
		Technique actual = first;
		while(actual != null && !same) {
			if(actual.getName().equals(nameTechnique)) {
				same = true;
			}
			actual = actual.getNext();
		}
		return same;
	}
	
	public boolean deleteTechnique(String nameTechnique) {
		Technique actual = first;
		boolean deleted = false;
		if(actual != null && actual.getName().equals(nameTechnique)) {
			first = first.getNext();
			deleted = true;
		}
		else {
			while(actual != null && actual.getNext() != null && !deleted) {
				if(actual.getNext().getName().equals(nameTechnique)) {
					actual.setNext(actual.getNext().getNext());
					deleted = true;
				}
				actual = actual.getNext();
			}
		}
		setScore();
		return deleted;
	}
	
	public boolean updateTechniqueByFactor(String nameTechnique, double factor) {
		Technique actual = first;
		boolean updated = false;
		while(actual != null && !updated) {
			if(actual.getName().equals(nameTechnique)) {
				actual.setFactor(factor);
				updated = true;
			}
			actual = actual.getNext();
		}
		setScore();
		return updated;
	}
	
	public boolean updateTechniqueByName(String nameTechnique, String newName) throws SameName {
		Technique actual = first;
		boolean updated = false;
		if(!sameTechnique(newName)) {
			while(actual != null && !updated) {
				if(actual.getName().equals(nameTechnique)) {
					actual.setName(newName);
					updated = true;
				}
				actual = actual.getNext();
			}
		}
		else {
			throw new SameName();
		}
		return updated;
	}
	
	public Technique findTechnique(String nameTechnique) {
		Technique e = null;
		Technique actual = first;
		boolean finded = false;
		while(actual != null && !finded) {
			if(actual.getName().equals(nameTechnique)) {
				e = actual;
				finded = true;
			}
			actual = actual.getNext();
		}
		return e;
	}

	@Override
	public int compare(Ninja o1, Ninja o2) {
		int substraction = 0;
		double x = o1.getPower()-o2.getPower();
		if(x > 0) {
			substraction = 1;
		}else if(x < 0) {
			substraction = -1;
		}
		return substraction;
	}
	
	public int techniquesNumber() {
		int number = 0;
		Technique actual = first;
		while(actual != null) {
			number++;
			actual = actual.getNext();
		}
		return number;
	}
	
	public String theTechniques() {
		String msg = "";
		Technique actual = first;
		while(actual != null) {
			msg += actual+"\n";
			actual = actual.getNext();
		}
		return msg;
	}
}
