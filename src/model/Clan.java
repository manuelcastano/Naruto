package model;
import java.io.Serializable;

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
}
