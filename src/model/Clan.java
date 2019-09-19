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

	public boolean sameNinja(Ninja e) {
		boolean same = false;
		Ninja actual = first;
		while(actual != null && !same) {
			if(actual.compareTo(e) == 0) {
				same = true;
			}
			actual = actual.getNext();
		}
		return same;
	}
	
	public boolean addNinja(Ninja e) throws SameName {
		boolean added = false;
		if(!sameNinja(e)) {
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
	
	public boolean ninjaExist(String nameNinja) {
		boolean exist = false;
		Ninja actual = first;
		while(actual != null && !exist) {
			if(actual.getName().equals(nameNinja)) {
				exist = true;
			}
			actual = actual.getNext();
		}
		return exist;
	}
	
	public boolean deleteNinja(String nameNinja) {
		boolean deleted = false;
		Ninja actual = first;
		if(first != null && first.getName().equals(nameNinja)) {
			first = first.getNext();
			first.setPrevious(null);
			deleted = true;
		}
		else {
			while(actual != null && !deleted) {
				if(actual.getName().equals(nameNinja)) {
					actual = actual.getPrevious();
					actual.setNext(actual.getNext().getNext());
					actual.getNext().setPrevious(actual);
					deleted = true;
				}
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
		Ninja actual = first;
		while(actual != null && !updated) {
			if(actual.getName().equals(nameNinja)) {
				actual.setName(newName);
				updated = true;
			}
			actual = actual.getNext();
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
				updated = true;
			}
			actual = actual.getNext();
		}
		return updated;
	}
	
	public boolean updateNinjaByScore(String nameNinja, double score) {
		boolean updated = false;
		Ninja actual = first;
		while(actual != null && !updated) {
			if(actual.getName().equals(nameNinja)) {
				actual.setScore(score);
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
	
	public boolean updateTechniqueByName(String nameTechnique, String newName) {
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
		}
		return e;
	}
	
	public void orderNinjas() {
		ArrayList<Ninja> ninjas = new ArrayList<Ninja>();
		Ninja actual = first;
		while(actual != null) {
			ninjas.add(actual);
			actual = actual.getNext();
		}
		for(int i = 1; i < ninjas.size(); i++) {
			for(int j = i; j > 0; j--) {
				if(ninjas.get(j-1).compareTo(ninjas.get(j)) > 0) {
					Ninja aux = ninjas.get(j);
					ninjas.set(j, ninjas.get(j-1));
					ninjas.set(j-1, aux);
				}
			}
		}
		if(!ninjas.isEmpty()) {
			first = ninjas.get(0);
		}
		for(int i = 0; i < ninjas.size(); i++) {
			boolean added = false;
			Ninja actual1 = first;
			while(actual1 != null && !added) {
				if(actual1.getNext() == null) {
					actual1.setNext(ninjas.get(i));
					ninjas.get(i).setPrevious(actual1);
					added = true;
				}
				actual = actual.getNext();
			}
		}
	}
}
