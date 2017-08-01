package net.kryptonox.factiondrugs.event;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import net.kryptonox.factiondrugs.FactionDrugs;
import net.kryptonox.factiondrugs.drugs.Cocaine;
import net.kryptonox.factiondrugs.drugs.Steroids;
import net.md_5.bungee.api.ChatColor;

public class DrugUseEvent implements Listener {
	
	FactionDrugs plugin;
	public DrugUseEvent(FactionDrugs plugin) {
		this.plugin = plugin;
		
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onDrugUse(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		Action a = e.getAction();
		ItemStack itemInHand = p.getItemInHand();
		
		if((a == Action.RIGHT_CLICK_BLOCK || a == Action.RIGHT_CLICK_AIR) && itemInHand != null) {
			if(itemInHand.getType().equals(Material.SUGAR) && (itemInHand.getItemMeta().hasDisplayName() &&
			   itemInHand.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&cCocaine")))) {
				//Player, Peak (High), Low (Come Down)
				itemInHand.setAmount(itemInHand.getAmount() - 1);
				Cocaine.giveEffects(p, 20, 45);
			}
			
			if(itemInHand.getType().equals(Material.GHAST_TEAR) && (itemInHand.getItemMeta().hasDisplayName() &&
					   itemInHand.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&8Steroids")))) {
						//Player, Peak (High), Low (Come Down)
						itemInHand.setAmount(itemInHand.getAmount() - 1);
						Steroids.giveEffects(p, 35, 20);
					}
		}
	}

}
