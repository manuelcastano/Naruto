import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Clan;
import model.Ninja;
import model.SameName;

class TestClan {

	private Clan clan;
	
	public void setupStage() {
		clan = new Clan("Hokage");
	}
	
	@Test
	public void testOrderNinja() {
		setupStage();
		Ninja e = new Ninja("Manuel", "nice", "2019/02/18", 25.2);
		Ninja e1 = new Ninja("Mayor", "nice", "2019/02/18", 12.2);
		Ninja e2 = new Ninja("Fiat", "nice", "2019/02/18", 29);
		try {
			clan.addNinja(e);
			clan.addNinja(e1);
			clan.addNinja(e2);
		} catch (SameName e3) {
			e3.printStackTrace();
		}
		clan.orderNinjas();
		System.out.println(clan.theNinjas());
	}

}
