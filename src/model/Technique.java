package model;
import java.io.Serializable;
import java.util.Comparator;

public class Technique implements Comparator<Technique>, Comparable<Technique>, Serializable{
	
	private String name;
	private double factor;
	private Technique next;
	
	public Technique(String name, double factor) {
		this.name = name;
		this.factor = factor;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getFactor() {
		return factor;
	}

	public void setFactor(double factor) {
		this.factor = factor;
	}

	public Technique getNext() {
		return next;
	}

	public void setNext(Technique next) {
		this.next = next;
	}
	
	public void insertAfter(Technique e) {
		e.next = next;
		next = e;
	}

	@Override
	public int compare(Technique e, Technique e1) {
		int substraction = 0;
		double x = e.getFactor()-e1.getFactor();
		if(x > 0) {
			substraction = 1;
		}else if(x < 0) {
			substraction = -1;
		}
		return substraction;
	}

	@Override
	public int compareTo(Technique e) {
		return name.compareTo(e.getName());
	}
}
