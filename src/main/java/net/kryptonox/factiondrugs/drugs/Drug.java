package net.kryptonox.factiondrugs.drugs;

import java.util.ArrayList;
import java.util.List;

import net.kryptonox.factiondrugs.FactionDrugs;

public abstract class Drug {
	
	FactionDrugs plugin;
	public Drug(FactionDrugs plugin, String name) {
		this.plugin = plugin;
		
		createRecipe(name);
	}
	
	public List<String> lore = new ArrayList<String>();
	
	public abstract void createRecipe(String name);

}
