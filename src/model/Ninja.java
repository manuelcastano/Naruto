package model;

import java.io.Serializable;

public class Ninja implements Comparable<Ninja>, Serializable{
	
	private String name;
	private String personality;
	private String creationDate;
	private double power;
	private double score;
	private Ninja previous;
	private Ninja next;
	private Technique first;
	
	public Ninja(String name, String personality, String creationDate, double power, double score) {
		this.name = name;
		this.personality = personality;
		this.creationDate = creationDate;
		this.power = power;
		this.score = score;
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

	public void setScore(double score) {
		this.score = score;
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
		if(!sameTechnique(e)) {
			if(first == null) {
				first = e;
				added = true;
			}
			else if(first.compare(first, e) < 0) {
				e.setNext(first);
				first = e;
				added = true;
			}
			else {
				Technique actual = first;
				while(actual != null && actual.getNext() != null && !added) {
					if(actual.getNext().compare(actual.getNext(), e) < 0) {
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
		return added;
	}
	
	public boolean sameTechnique(Technique e) {
		boolean same = false;
		Technique actual = first;
		while(actual != null && !same) {
			if(actual.compareTo(e) == 0) {
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
		return updated;
	}
	
	public boolean updateTechniqueByName(String nameTechnique, String newName) {
		Technique actual = first;
		boolean updated = false;
		while(actual != null && !updated) {
			if(actual.getName().equals(nameTechnique)) {
				actual.setName(newName);
				updated = true;
			}
			actual = actual.getNext();
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
}
