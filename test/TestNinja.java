import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Ninja;
import model.SameName;
import model.Technique;

class TestNinja {

	private Ninja ninja;
	
	private void setupStage() {
		ninja = new Ninja("Manuel", "nice", "2019/5/8", 20.5);
	}
	
	@Test
	public void testAddTechnique() {
		setupStage();
		Technique e = new Technique("Rasengan", 2.6);
		Technique e1 = new Technique("Kamehameha", 15);
		Technique e2 = new Technique("jutsu", 1.5);
		Technique e3 = new Technique("zorro", 34);
		Technique e4 = new Technique("Invisibilidad", 7);
		try {
			ninja.addTechnique(e);
			ninja.addTechnique(e1);
			ninja.addTechnique(e2);
			ninja.addTechnique(e3);
			ninja.addTechnique(e4);
		} catch (SameName e5) {
			fail();
		}
		try {
			ninja.addTechnique(e);
		} catch (SameName e5) {
			
		}
		Technique actual = ninja.getFirst();
		while(actual != null) {
			if(actual.getNext() != null && actual.getFactor() > actual.getNext().getFactor()) {
				fail();
			}
			actual = actual.getNext();
		}
	}
	
	@Test
	public void testDeleteTechnique() {
		setupStage();
		Technique e = new Technique("Rasengan", 2.6);
		Technique e1 = new Technique("Kamehameha", 15);
		try {
			ninja.addTechnique(e);
			ninja.addTechnique(e1);
		} catch (SameName e2) {
			fail();
		}
		ninja.deleteTechnique("Rasengan");
		Technique actual = ninja.getFirst();
		while(actual != null) {
			if(actual.getName().equals("Rasengan")) {
				fail();
			}
			actual = actual.getNext();
		}
	}
	
	@Test
	public void testUpdateTechniqueByFactor() {
		setupStage();
		Technique e = new Technique("Rasengan", 2.6);
		try {
			ninja.addTechnique(e);
		} catch (SameName e2) {
			fail();
		}
		ninja.updateTechniqueByFactor("Rasengan", 59);
		Technique a = ninja.findTechnique("Rasengan");
		assertTrue(a.getFactor() == 59);
	}
	
	@Test
	public void testUpdateTechniqueByName() {
		setupStage();
		Technique e = new Technique("Rasengan", 2.6);
		try {
			ninja.addTechnique(e);
		} catch (SameName e2) {
			fail();
		}
		try {
			ninja.updateTechniqueByName("Rasengan", "Jutsu");
		} catch (SameName e1) {
			fail();
		}
		Technique a = ninja.findTechnique("Jutsu");
		assertTrue(a != null);
	}
	
	@Test
	public void testFindTechnique() {
		setupStage();
		Technique e = new Technique("Rasengan", 2.6);
		try {
			ninja.addTechnique(e);
		} catch (SameName e2) {
			fail();
		}
		Technique a = ninja.findTechnique("Rasengan");
		assertTrue(a != null && a.getFactor() == 2.6);
	}
}
