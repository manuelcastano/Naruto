package model;
import java.io.Serializable;
import java.util.ArrayList;

public class Clan implements Comparable<Clan>, Serializable{
	
	private String name;
	private Ninja first;

	public Clan(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Ninja getFirst() {
		return first;
	}

	public void setFirst(Ninja first) {
		this.first = first;
	}

	public boolean sameNinja(String nameNinja) {
		boolean same = false;
		Ninja actual = first;
		while(actual != null && !same) {
			if(actual.getName().equals(nameNinja)) {
				same = true;
			}
			actual = actual.getNext();
		}
		return same;
	}
	
	public boolean addNinja(Ninja e) throws SameName {
		boolean added = false;
		if(!sameNinja(e.getName())) {
			if(first == null) {
				first = e;
			}
			else {
				e.setNext(first);
				first.setPrevious(e);
				first = e;
			}
			added = true;
		}
		else {
			throw new SameName();
		}
		return added;
	}

	@Override
	public int compareTo(Clan e) {
		return name.compareTo(e.getName());
	}
	
	public boolean addTechnique(Technique e, String nameNinja) throws SameName {
		boolean added = false;
		Ninja actual = first;
		while(actual != null && !added) {
			if(actual.getName().equals(nameNinja)) {
				added = actual.addTechnique(e);
				actual = actual.getNext();
			}
		}
		return added;
	}
	
	public boolean deleteNinja(String nameNinja) {
		boolean deleted = false;
		Ninja actual = first;
		if(first != null && first.getName().equals(nameNinja)) {
			first = first.getNext();
			if(first != null) {
				first.setPrevious(null);
			}
			deleted = true;
		}
		else {
			while(actual != null && !deleted) {
				if(actual.getName().equals(nameNinja)) {
					actual = actual.getPrevious();
					actual.setNext(actual.getNext().getNext());
					if(actual.getNext() != null) {
						actual.getNext().setPrevious(actual);
					}
					deleted = true;
				}
				actual = actual.getNext();
			}
		}
		return deleted;
	}
	
	public boolean deleteTechnique(String nameTechnique) {
		boolean deleted = false;
		Ninja actual = first;
		while(actual != null) {
			if(actual.deleteTechnique(nameTechnique)) {
				deleted = true;
			}
			actual = actual.getNext();
		}
		return deleted;
	}
	
	public boolean updateNinjaByName(String nameNinja, String newName) {
		boolean updated = false;
		if(!sameNinja(newName)) {
			Ninja actual = first;
			while(actual != null && !updated) {
				if(actual.getName().equals(nameNinja)) {
					actual.setName(newName);
					updated = true;
				}
				actual = actual.getNext();
			}
		}
		return updated;
	}
	
	public boolean updateNinjaByPersonality(String nameNinja, String personality) {
		boolean updated = false;
		Ninja actual = first;
		while(actual != null && !updated) {
			if(actual.getName().equals(nameNinja)) {
				actual.setPersonality(personality);
				updated = true;
			}
			actual = actual.getNext();
		}
		return updated;
	}
	
	public boolean updateNinjaByCreationDate(String nameNinja, String creationDate) {
		boolean updated = false;
		Ninja actual = first;
		while(actual != null && !updated) {
			if(actual.getName().equals(nameNinja)) {
				actual.setCreationDate(creationDate);
				updated = true;
			}
			actual = actual.getNext();
		}
		return updated;
	}
	
	public boolean updateNinjaByPower(String nameNinja, double power) {
		boolean updated = false;
		Ninja actual = first;
		while(actual != null && !updated) {
			if(actual.getName().equals(nameNinja)) {
				actual.setPower(power);
				actual.setScore();
				updated = true;
			}
			actual = actual.getNext();
		}
		return updated;
	}
	
	public boolean updateTechniqueByFactor(String nameTechnique, double factor) {
		boolean updated = false;
		Ninja actual = first;
		while(actual != null) {
			if(actual.updateTechniqueByFactor(nameTechnique, factor)) {
				updated = true;
			}
			actual = actual.getNext();
		}
		return updated;
	}
	
	public boolean updateTechniqueByName(String nameTechnique, String newName) throws SameName {
		boolean updated = false;
		Ninja actual = first;
		while(actual != null) {
			if(actual.updateTechniqueByName(nameTechnique, newName)) {
				updated = true;
			}
			actual = actual.getNext();
		}
		return updated;
	}
	
	public Ninja findNinja(String nameNinja) {
		Ninja e = null;
		Ninja actual = first;
		boolean finded = false;
		while(actual != null && !finded) {
			if(actual.getName().equals(nameNinja)) {
				e = actual;
				finded = true;
			}
			actual = actual.getNext();
		}
		return e;
	}
	
	public Technique findTechnique(String nameTechnique) {
		Technique e = null;
		Ninja actual = first;
		boolean finded = false;
		while(actual != null && !finded) {
			e = actual.findTechnique(nameTechnique);
			if(e != null) {
				finded = true;
			}
			actual = actual.getNext();
		}
		return e;
	}
	
	//Bubble
	public void orderNinjas() {
		int ninjas = 0;
		Ninja actual = first;
		while(actual != null) {
			Ninja next = actual.getNext();
			while(next != null) {
				if(actual.compare(actual, next) > 0) {
					if(actual.getPrevious() != null) {
						actual.getPrevious().setNext(next);
					}
					if(next.getNext() != null) {
						next.getNext().setPrevious(actual);
					}
					actual.setNext(next.getNext());
					next.setPrevious(actual.getPrevious());
					actual.setPrevious(next);
					next.setNext(actual);
					next = actual.getNext();
				}
				else {
					actual = actual.getNext();
					next = next.getNext();
				}
			} 
			ninjas++;
			if(ninjas < ninjasNumber()) {
				findTheFirst();
				actual = first;
				if(actual.getNext() != null) {
					next = actual.getNext();
				}
			}
			else {
				actual = null;
			}
		}
		findTheFirst();
	}
	
	public void findTheFirst() {
		Ninja actual = first;
		Ninja theFirst = null;
		while(actual != null) {
			theFirst = actual;
			actual = actual.getPrevious();
		}
		first = theFirst;
	}
	
	public String theNinjas() {
		String msg = "";
		Ninja actual = first;
		while(actual != null) {
			msg += actual+"\n";
			actual = actual.getNext();
		}
		return msg;
	}
	
	public int ninjasNumber() {
		int ninjas = 0; 
		Ninja actual = first;
		while(actual != null) {
			ninjas++;
			actual = actual.getNext();
		}
		return ninjas;
	}
	
	public String theTechniques() {
		String msg = "";
		Ninja actual = first;
		while(actual != null) {
			msg += actual.theTechniques();
			actual = actual.getNext();
		}
		return msg;
	}

	@Override
	public String toString() {
		return "name = " + name;
	}
}
