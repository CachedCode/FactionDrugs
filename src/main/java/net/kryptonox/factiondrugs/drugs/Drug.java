package net.kryptonox.factiondrugs.drugs;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.kryptonox.factiondrugs.FactionDrugs;

public abstract class Drug {
	
	static FactionDrugs plugin;
	public Drug(FactionDrugs plugin, String name) {
		this.plugin = plugin;
		
		createRecipe(name);
	}
	
	Random purity = new Random();
	
	public List<String> lore = new ArrayList<String>();
	
	public abstract void createRecipe(String name);

}
