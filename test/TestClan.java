import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Clan;
import model.Ninja;
import model.SameName;
import model.Technique;

class TestClan {

	private Clan clan;
	
	private void setupStage() {
		clan = new Clan("Hokage");
	}
	
	@Test
	public void testAddNinja() {
		setupStage();
		Ninja e = new Ninja("Paolo", "Bravucon", "2019/02/14", 15.0);
		Ninja e1 = new Ninja("Naruto", "Bravucon", "2019/02/14", 15.0);
		Ninja e2 = new Ninja("Paolo", "Bravucon", "2019/02/14", 15.0);
		try {
			clan.addNinja(e);
			clan.addNinja(e1);
		} catch (SameName e3) {
			fail();
		}
		try {
			clan.addNinja(e2);
		} catch (SameName e3) {
			
		}
		assertTrue(clan.findNinja("Paolo") != null);
	}
	
	@Test
	public void testAddTechnique() {
		setupStage();
		Ninja e = new Ninja("Paolo", "Bravucon", "2019/02/14", 15.0);
		try {
			clan.addNinja(e);
		} catch (SameName e3) {
			fail();
		}
		Technique t = new Technique("Rasengan", 0.2);
		try {
			clan.addTechnique(t, "Paolo");
		} catch (SameName e1) {
			fail();
		}
		try {
			clan.addTechnique(t, "Paolo");
		} catch (SameName e1) {
			
		}
	}
	
	@Test
	public void testDeleteNinja() {
		setupStage();
		Ninja e = new Ninja("Paolo", "Bravucon", "2019/02/14", 15.0);
		Ninja e1 = new Ninja("Naruto", "Bravucon", "2019/02/14", 15.0);
		try {
			clan.addNinja(e);
			clan.addNinja(e1);
		} catch (SameName e3) {
			fail();
		}
		clan.deleteNinja("Naruto");
		Ninja actual = clan.getFirst();
		while(actual != null) {
			assertFalse(actual.getName().equals("Naruto"));
			actual = actual.getNext();
		}
		assertTrue(clan.getFirst().getName().equals("Paolo"));
	}
	
	@Test
	public void testDeleteTechnique() {
		setupStage();
		Ninja e = new Ninja("Paolo", "Bravucon", "2019/02/14", 15.0);
		try {
			clan.addNinja(e);
		} catch (SameName e3) {
			fail();
		}
		Technique t = new Technique("Rasengan", 0.2);
		try {
			clan.addTechnique(t, "Paolo");
		} catch (SameName e1) {
			fail();
		}
		assertTrue(clan.getFirst().getFirst() != null);
		clan.deleteTechnique("Rasengan");
		assertTrue(clan.getFirst().getFirst() == null);
	}
	
	@Test
	public void testUpdateNinjaByName() {
		setupStage();
		Ninja e = new Ninja("Paolo", "Bravucon", "2019/02/14", 15.0);
		try {
			clan.addNinja(e);
		} catch (SameName e3) {
			fail();
		}
		clan.updateNinjaByName("Paolo", "Manuel");
		assertTrue(clan.getFirst().getName().equals("Manuel"));
	}
	
	@Test
	public void testUpdateTechniqueByFactor() {
		setupStage();
		Ninja e = new Ninja("Paolo", "Bravucon", "2019/02/14", 15.0);
		try {
			clan.addNinja(e);
		} catch (SameName e3) {
			fail();
		}
		Technique t = new Technique("Rasengan", 0.2);
		try {
			clan.addTechnique(t, "Paolo");
		} catch (SameName e1) {
			fail();
		}
		clan.updateTechniqueByFactor("Rasengan", 15.5);
		assertTrue(clan.getFirst().getFirst().getFactor() == 15.5);
	}
	
	@Test
	public void testFindNinja() {
		setupStage();
		Ninja e = new Ninja("Paolo", "Bravucon", "2019/02/14", 15.0);
		try {
			clan.addNinja(e);
		} catch (SameName e3) {
			fail();
		}
		assertTrue(clan.findNinja("Paolo") != null);
		assertTrue(clan.findNinja("Manuel") == null);
	}
	
	@Test
	public void testFindTechnique() {
		setupStage();
		Ninja e = new Ninja("Paolo", "Bravucon", "2019/02/14", 15.0);
		try {
			clan.addNinja(e);
		} catch (SameName e3) {
			fail();
		}
		Technique t = new Technique("Rasengan", 0.2);
		try {
			clan.addTechnique(t, "Paolo");
		} catch (SameName e1) {
			fail();
		}
		assertTrue(clan.findTechnique("Rasengan") != null);
		assertTrue(clan.findTechnique("Manuel") == null);
	}
	
	@Test
	public void testOrderNinjas() {
		setupStage();
		Ninja e = new Ninja("Paolo", "Bravucon", "2019/02/14", 15.0);
		Ninja e1 = new Ninja("Naruto", "Bravucon", "2019/02/14", 2.5);
		Ninja e2 = new Ninja("gfgdgg", "Bravucon", "2019/02/14", 100);
		Ninja e3 = new Ninja("Paogfdbslo", "Bravucon", "2019/02/14", 5);
		Ninja e4 = new Ninja("ntdunbtd", "Bravucon", "2019/02/14", 4);
		Ninja e5 = new Ninja("bsrbb", "Bravucon", "2019/02/14", 49);
		try {
			clan.addNinja(e);
			clan.addNinja(e1);
			clan.addNinja(e2);
			clan.addNinja(e3);
			clan.addNinja(e4);
			clan.addNinja(e5);
		} catch (SameName e8) {
			fail();
		}
		clan.orderNinjas();
		Ninja actual = clan.getFirst();
		while(actual != null) {
			if(actual.getNext() != null && actual.getPower() > actual.getNext().getPower()) {
				fail();
			}
			actual = actual.getNext();
		}
	}
}
