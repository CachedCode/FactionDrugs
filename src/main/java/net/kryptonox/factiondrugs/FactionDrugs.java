package net.kryptonox.factiondrugs;

import org.bukkit.plugin.java.JavaPlugin;

import net.kryptonox.factiondrugs.drugs.Cocaine;
import net.kryptonox.factiondrugs.drugs.Steroids;
import net.kryptonox.factiondrugs.event.DrugUseEvent;

public class FactionDrugs extends JavaPlugin {
	
	public void onEnable() {
		new Cocaine(this, "&cCocaine");
		new Steroids(this, "&8Steroids");
		new DrugUseEvent(this);
	}

}
