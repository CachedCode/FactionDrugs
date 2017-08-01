package net.kryptonox.factiondrugs;

import org.bukkit.plugin.java.JavaPlugin;

import net.kryptonox.factiondrugs.drugs.Cocaine;

public class FactionDrugs extends JavaPlugin {
	
	public void onEnable() {
		new Cocaine(this);
	}

}
