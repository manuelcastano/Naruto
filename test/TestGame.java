import static org.junit.jupiter.api.Assertions.*;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import model.Clan;
import model.Game;
import model.Ninja;
import model.SameName;
import model.Technique;

class TestGame {
	
	private Game theGame;
	
	private void setupStage() {
		try {
			theGame = new Game();
		} catch (ClassNotFoundException e) {
			fail();
		} catch (IOException e) {
			fail();
		}
	}
	
	@Test
	public void testAddClan() {
		setupStage();
		Clan e = new Clan(""+(int)(Math.random()*1000000));
		try {
			theGame.addClan(e);
		} catch (SameName | IOException e1) {
			fail();
		}
		assertTrue(theGame.sameClan(e.getName()));
		try {
			File f = new File("Data.txt");
			ObjectInputStream o = new ObjectInputStream(new FileInputStream(f));
			ArrayList<Clan> clans = (ArrayList<Clan>)o.readObject();
			boolean finded = false;
			for(int i = 0; i < clans.size() && !finded; i++) {
				if(clans.get(i).getName().equals(e.getName())) {
					finded = true;
				}
			}
			assertTrue(finded);
			o.close();
		}catch(IOException | ClassNotFoundException e1) {
			fail();
		}
	}
	
	@Test
	public void testLoadData() {
		setupStage();
		assertTrue(theGame.getClans().size() > 0);
	}
	
	@Test
	public void testAddNinja() {
		setupStage();
		Clan a = new Clan("HU");
		try {
			theGame.addClan(a);
		} catch (FileNotFoundException e2) {
			fail();
		} catch (SameName e2) {
			
		} catch (IOException e2) {
			fail();
		}
		Ninja e = new Ninja("Naruto", "nice", "2019/02/18", 22.5);
		try {
			theGame.addNinja(e, "HU");
		} catch (SameName e1) {
			
		} catch (IOException e1) {
			fail();
		}
		try {
			File f = new File("Data.txt");
			ObjectInputStream o = new ObjectInputStream(new FileInputStream(f));
			ArrayList<Clan> clans = (ArrayList<Clan>)o.readObject();
			boolean finded = false;
			for(int i = 0; i < clans.size() && !finded; i++) {
				if(clans.get(i).getFirst() != null && clans.get(i).getFirst().getName().equals("Naruto")) {
					finded = true;
				}
			}
			assertTrue(finded);
			o.close();
		}catch(IOException | ClassNotFoundException e1) {
			fail();
		}
	}
	
	@Test
	public void testAddTechnique() {
		setupStage();
		Clan a = new Clan("HI");
		try {
			theGame.addClan(a);
		} catch (FileNotFoundException e2) {
			fail();
		} catch (SameName e2) {
			
		} catch (IOException e2) {
			fail();
		}
		Ninja e = new Ninja("Boruto", "nice", "2019/02/18", 22.5);
		try {
			theGame.addNinja(e, "HI");
		} catch (SameName e1) {
			
		} catch (IOException e1) {
			fail();
		}
		Technique t = new Technique("Rasengan", 2.5);
		try {
			theGame.addTechnique(t, "Boruto");
		} catch (SameName e2) {
			
		} catch (IOException e2) {
			fail();
		}
		try {
			File f = new File("Data.txt");
			ObjectInputStream o = new ObjectInputStream(new FileInputStream(f));
			ArrayList<Clan> clans = (ArrayList<Clan>)o.readObject();
			boolean finded = false;
			for(int i = 0; i < clans.size() && !finded; i++) {
				if(clans.get(i).getFirst() != null && clans.get(i).getFirst().sameTechnique("Rasengan")) {
					finded = true;
				}
			}
			assertTrue(finded);
			o.close();
		}catch(IOException | ClassNotFoundException e1) {
			fail();
		}
	}
	
	@Test
	public void testDeleteClan() {
		setupStage();
		Clan e = new Clan(""+(int)(Math.random()*1000000));
		try {
			theGame.addClan(e);
		} catch (SameName | IOException e1) {
			fail();
		}
		try {
			theGame.deleteClan(e.getName());
		} catch (IOException e2) {
			fail();
		}
		assertFalse(theGame.sameClan(e.getName()));
		try {
			File f = new File("Data.txt");
			ObjectInputStream o = new ObjectInputStream(new FileInputStream(f));
			ArrayList<Clan> clans = (ArrayList<Clan>)o.readObject();
			boolean finded = false;
			for(int i = 0; i < clans.size() && !finded; i++) {
				if(clans.get(i).getName().equals(e.getName())) {
					finded = true;
				}
			}
			assertFalse(finded);
			o.close();
		}catch(IOException | ClassNotFoundException e1) {
			fail();
		}
	}
	
	@Test
	public void testDeleteNinja() {
		setupStage();
		Clan a = new Clan("HU");
		try {
			theGame.addClan(a);
		} catch (FileNotFoundException e2) {
			fail();
		} catch (SameName e2) {
			
		} catch (IOException e2) {
			fail();
		}
		Ninja e = new Ninja("Naruto", "nice", "2019/02/18", 22.5);
		try {
			theGame.addNinja(e, "HU");
		} catch (SameName e1) {
			
		} catch (IOException e1) {
			fail();
		}
		try {
			theGame.deleteNinja("Naruto");
		} catch (IOException e2) {
			fail();
		}
		try {
			File f = new File("Data.txt");
			ObjectInputStream o = new ObjectInputStream(new FileInputStream(f));
			ArrayList<Clan> clans = (ArrayList<Clan>)o.readObject();
			boolean finded = false;
			for(int i = 0; i < clans.size() && !finded; i++) {
				if(clans.get(i).getFirst() != null && clans.get(i).getFirst().getName().equals("Naruto")) {
					finded = true;
				}
			}
			assertFalse(finded);
			o.close();
		}catch(IOException | ClassNotFoundException e1) {
			fail();
		}
	}
	
	@Test
	public void testDeleteTechnique() {
		setupStage();
		Clan a = new Clan("HI");
		try {
			theGame.addClan(a);
		} catch (FileNotFoundException e2) {
			fail();
		} catch (SameName e2) {
			
		} catch (IOException e2) {
			fail();
		}
		Ninja e = new Ninja("Boruto", "nice", "2019/02/18", 22.5);
		try {
			theGame.addNinja(e, "HI");
		} catch (SameName e1) {
			
		} catch (IOException e1) {
			fail();
		}
		Technique t = new Technique("Rasengan", 2.5);
		try {
			theGame.addTechnique(t, "Boruto");
		} catch (SameName e2) {
			
		} catch (IOException e2) {
			fail();
		}
		try {
			theGame.deleteTechnique("Rasengan");
		} catch (IOException e2) {
			fail();
		}
		try {
			File f = new File("Data.txt");
			ObjectInputStream o = new ObjectInputStream(new FileInputStream(f));
			ArrayList<Clan> clans = (ArrayList<Clan>)o.readObject();
			boolean finded = false;
			for(int i = 0; i < clans.size() && !finded; i++) {
				if(clans.get(i).getFirst() != null && clans.get(i).getFirst().sameTechnique("Rasengan")) {
					finded = true;
				}
			}
			assertFalse(finded);
			o.close();
		}catch(IOException | ClassNotFoundException e1) {
			fail();
		}
	}
	
	@Test
	public void testUpdateClanByName() {
		setupStage();
		Clan e = new Clan(""+(int)(Math.random()*1000000));
		try {
			theGame.addClan(e);
		} catch (SameName | IOException e1) {
			fail();
		}
		try {
			theGame.updateClanByName(e.getName(), "ASUS");
		} catch (IOException e2) {
			fail();
		} catch (SameName e2) {
			
		}
		assertTrue(theGame.sameClan("ASUS"));
		try {
			File f = new File("Data.txt");
			ObjectInputStream o = new ObjectInputStream(new FileInputStream(f));
			ArrayList<Clan> clans = (ArrayList<Clan>)o.readObject();
			boolean finded = false;
			for(int i = 0; i < clans.size() && !finded; i++) {
				if(clans.get(i).getName().equals("ASUS")) {
					finded = true;
				}
			}
			assertTrue(finded);
			o.close();
		}catch(IOException | ClassNotFoundException e1) {
			fail();
		}
	}
	
	public void testOrderClans() {
		setupStage();
		Clan e = new Clan("Manchas");
		Clan e1 = new Clan("Naruto");
		Clan e2 = new Clan("Sasuke");
		Clan e3 = new Clan("Goku");
		Clan e4 = new Clan("Casa");
		try {
			theGame.addClan(e);
			theGame.addClan(e1);
			theGame.addClan(e2);
			theGame.addClan(e3);
			theGame.addClan(e4);
		} catch (FileNotFoundException e5) {
			fail();
		} catch (SameName e5) {
		} catch (IOException e5) {
			fail();
		}
		theGame.orderClans();
		ArrayList<Clan> clans = theGame.getClans();
		for(int i = 0; i < clans.size()-1; i++) {
			assertTrue(clans.get(i).getName().compareTo(clans.get(i+1).getName()) < 0);
		}
	}
}
