package model;

public class Clan implements Comparable<Clan>{
	
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
			e.setNext(first);
			first = e;
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
}
