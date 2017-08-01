package net.kryptonox.factiondrugs.event;

import org.bukkit.event.Listener;

import net.kryptonox.factiondrugs.FactionDrugs;

public class JoinListenerEvent implements Listener {
	
	FactionDrugs plugin;
	public JoinListenerEvent(FactionDrugs plugin) {
		this.plugin = plugin;
		
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
}
