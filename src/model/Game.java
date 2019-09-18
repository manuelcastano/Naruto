package model;

import java.io.*;
import java.util.ArrayList;

public class Game {
	
	public final static String ARCHIVE = "Data.txt";
	
	private ArrayList<Clan> clans;

	public Game() throws ClassNotFoundException, IOException {
		clans = new ArrayList<Clan>();
		loadData();
	}

	public ArrayList<Clan> getClans() {
		return clans;
	}

	public void setClans(ArrayList<Clan> clans) {
		this.clans = clans;
	}
	
	public boolean sameClan(Clan e) {
		boolean same = false;
		for(int i = 0; i < clans.size() && !same; i++) {
			if(clans.get(i).compareTo(e) == 0) {
				same = true;
			}
		}
		return same;
	}
	
	public boolean addClan(Clan e) throws SameName, FileNotFoundException, IOException {
		boolean added = false;
		if(!sameClan(e)) {
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
			if(clans.get(i).sameNinja(e)) {
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
}