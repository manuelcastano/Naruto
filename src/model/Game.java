package model;

import java.io.*;
import java.util.ArrayList;

public class Game {
	
	public final static String ARCHIVE = "Data.txt";
	
	private ArrayList<Clan> clans;

	public Game() throws ClassNotFoundException, IOException, SameName {
		clans = new ArrayList<Clan>();
		loadData();
	}

	public ArrayList<Clan> getClans() {
		return clans;
	}

	public void setClans(ArrayList<Clan> clans) {
		this.clans = clans;
	}
	
	public boolean sameClan(String nameClan) {
		boolean same = false;
		for(int i = 0; i < clans.size() && !same; i++) {
			if(clans.get(i).getName().equals(nameClan)) {
				same = true;
			}
		}
		return same;
	}
	
	public boolean addClan(Clan e) throws SameName, FileNotFoundException, IOException {
		boolean added = false;
		if(!sameClan(e.getName()
				)) {
			clans.add(e);
			added = true;
			writeClan(e);
		}
		else {
			throw new SameName();
		}
		return added;
	}
	
	public void writeClan(Clan e) throws FileNotFoundException, IOException {
		File f = new File(ARCHIVE);
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
		oos.writeObject(e);
		oos.close();
	}
	
	public void loadData() throws IOException, ClassNotFoundException {
		try {
			File f = new File(ARCHIVE);
			ObjectInputStream o = new ObjectInputStream(new FileInputStream(f));
			try {
				Clan aux = (Clan)o.readObject();
				while(aux != null) {
					clans.add(aux);
					aux = (Clan)o.readObject();
				}
				o.close();
			}catch(IOException e) {
				o.close();
			}
		}catch(EOFException e) {
			
		}
	}
	
	public void edit() throws IOException {
		new File(ARCHIVE).delete();
		new File(ARCHIVE).createNewFile();
		File f = new File(ARCHIVE);
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
		for(int i = 0; i < clans.size(); i++) {
			oos.writeObject(clans.get(i));
		}
		oos.close();
	}
	
	public boolean addNinja(Ninja e, String nameClan) throws SameName, IOException {
		for(int i = 0; i < clans.size(); i++) {
			if(clans.get(i).sameNinja(e.getName())) {
				throw new SameName();
			}
		}
		boolean added = false;
		for(int i = 0; i < clans.size() && !added; i++) {
			if(clans.get(i).getName().equals(nameClan)) {
				clans.get(i).addNinja(e);
				added = true;
				edit();
			}
		}
		return added;
	}
	
	public boolean addTechnique(Technique e, String nameNinja) throws SameName, IOException {
		boolean added = false;
		for(int i = 0; i < clans.size() && !added; i++) {
			if(clans.get(i).ninjaExist(nameNinja)) {
				added = clans.get(i).addTechnique(e, nameNinja);
				edit();
			}
		}
		return added;
	}
	
	public boolean deleteClan(String nameClan) throws IOException {
		boolean deleted = false;
		for(int i = 0; i < clans.size() && !deleted; i++) {
			if(clans.get(i).getName().equals(nameClan)) {
				clans.remove(i);
				deleted = true;
				edit();
			}
		}
		return deleted;
	}
	
	public boolean deleteNinja(String nameNinja) throws IOException {
		boolean deleted = false;
		for(int i = 0; i < clans.size() && !deleted; i++) {
			if(clans.get(i).ninjaExist(nameNinja)) {
				deleted = clans.get(i).deleteNinja(nameNinja);
			}
		}
		edit();
		return deleted;
	}
	
	public boolean deleteTechnique(String nameTechnique) throws IOException {
		boolean deleted = false;
		for(int i = 0; i < clans.size(); i++) {
			if(clans.get(i).deleteTechnique(nameTechnique)) {
				deleted = true;
			}
		}
		edit();
		return deleted;
	}
	
	public boolean updateClanByName(String nameClan, String newName) throws IOException, SameName {
		boolean updated = false;
		if(!sameClan(newName)) {
			for(int i = 0; i < clans.size() && !updated; i++) {
				if(clans.get(i).getName().equals(nameClan)) {
					clans.get(i).setName(newName);
					updated = true;
					edit();
				}
			}
		}
		else {
			throw new SameName();
		}
		return updated;
	}
	
	public boolean updateTechniqueByFactor(String nameTechnique, double factor) throws IOException {
		boolean updated = false;
		for(int i = 0; i < clans.size(); i++) {
			if(clans.get(i).updateTechniqueByFactor(nameTechnique, factor)) {
				updated = true;
			}
		}
		edit();
		return updated;
	}
	
	public boolean updateTechniqueByName(String nameTechnique, String newName) throws IOException, SameName {
		boolean updated = false;
		for(int i = 0; i < clans.size(); i++) {
			if(clans.get(i).updateTechniqueByName(nameTechnique, newName)) {
				updated = true;
			}
		}
		edit();
		return updated;
	}
	
	public boolean updateNinjaByName(String nameNinja, String newName) throws IOException {
		boolean updated = false;
		for(int i = 0; i < clans.size() && !updated; i++) {
			updated = clans.get(i).updateNinjaByName(nameNinja, newName);
			edit();
		}
		return updated;
	}
	
	public boolean updateNinjaByPersonality(String nameNinja, String personality) throws IOException {
		boolean updated = false;
		for(int i = 0; i < clans.size() && !updated; i++) {
			updated = clans.get(i).updateNinjaByPersonality(nameNinja, personality);
			edit();
		}
		return updated;
	}
	
	public boolean updateNinjaByCreationDate(String nameNinja, String creationDate) throws IOException {
		boolean updated = false;
		for(int i = 0; i < clans.size() && !updated; i++) {
			updated = clans.get(i).updateNinjaByCreationDate(nameNinja, creationDate);
			edit();
		}
		return updated;
	}
	
	public boolean updateNinjaByPower(String nameNinja, double power) throws IOException {
		boolean updated = false;
		for(int i = 0; i < clans.size() && !updated; i++) {
			updated = clans.get(i).updateNinjaByPower(nameNinja, power);
			edit();
		}
		return updated;
	}
	
	public void orderClansByName() {
		for(int i = 0; i < clans.size()-1; i++) {
			Clan less = clans.get(i);
			int aux= i;
			for(int j = i+1; j < clans.size(); j++) {
				if(less.compareTo(clans.get(j)) > 0) {
					less = clans.get(j);
					aux = j;
				}
			}
			Clan temp = clans.get(i);
			clans.set(i, less);
			clans.set(aux, temp);
		}
	}
	
	public Clan findBinaryClan(String nameClan) {
		Clan e = null;
		boolean finded = false;
		for(int i = 0; i< clans.size() && !finded; i++) {
			if(clans.get(i).getName().equals(nameClan)) {
				e = clans.get(i);
				finded = true;
			}
		}
		return e;
	}
	
	public Ninja findNinja(String nameNinja) {
		Ninja e = null;
		boolean finded = false;
		for(int i = 0; i < clans.size() && !finded; i++) {
			e = clans.get(i).findNinja(nameNinja);
			if(e != null) {
				finded = true;
			}
		}
		return e;
	}
	
	public Technique findTechnique(String nameTechnique) {
		Technique e = null;
		boolean finded = false;
		for(int i = 0; i < clans.size() && !finded; i++) {
			e = clans.get(i).findTechnique(nameTechnique);
			if(e != null) {
				finded = true;
			}
		}
		return e;
	}
	
	//selection
	public void orderClans() {
		for(int i = 0; i < clans.size()-1; i++) {
			Clan less = clans.get(i);
			int aux= i;
			for(int j = i+1; j < clans.size(); j++) {
				if(less.compareTo(clans.get(j)) > 0) {
					less = clans.get(j);
					aux = j;
				}
			}
			Clan temp = clans.get(i);
			clans.set(i, less);
			clans.set(aux, temp);
		}
	}
}