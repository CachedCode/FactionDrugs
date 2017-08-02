package net.kryptonox.factiondrugs.drugs;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import net.kryptonox.factiondrugs.FactionDrugs;

public class Heroin extends Drug {

	static int task;
	int pure;
	
	public Heroin(FactionDrugs plugin, String name) {
		super(plugin, name);
	}
	
	@EventHandler
	public void onCraftingTableOpen(PlayerInteractEvent e) {
		Block b = e.getClickedBlock();
		Action a = e.getAction();
		
		if(a == Action.RIGHT_CLICK_BLOCK && b.getType().equals(Material.WORKBENCH)) {
			createRecipe("&2Heroin");
		}
	}

	//C21 H23 NO5
	
	@SuppressWarnings("deprecation")
	@Override
	public void createRecipe(String name) {
		ItemStack heroin = new ItemStack(Material.RABBIT_STEW, 1);
		ItemMeta heroinMeta = heroin.getItemMeta();
	
		pure = purity.nextInt(50) + 1;
		heroinMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
		lore.add(ChatColor.translateAlternateColorCodes('&', "&7" + pure + "% Pure"));
		lore.add(ChatColor.translateAlternateColorCodes('&', "&7Right Click To Swallow!"));
		heroinMeta.setLore(lore);
		lore.clear();
		heroin.setItemMeta(heroinMeta);
		
		ShapelessRecipe heroinRecipe = new ShapelessRecipe(heroin);
		heroinRecipe.addIngredient(Material.POTION, (short) 0);
		heroinRecipe.addIngredient(Material.COAL, (byte) 1);
		heroinRecipe.addIngredient(Material.GLASS_BOTTLE);
		plugin.getServer().addRecipe(heroinRecipe);
	}
	
	@SuppressWarnings("deprecation")
	public static void giveEffects(final Player p, final int peakTime, final int comedownTime) {
		p.sendMessage(ChatColor.GREEN + "You begin to feel drowsy...");
		
		p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, peakTime * 20, 1));
		p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, peakTime * 20, 1));
		p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, peakTime * 20, 1));
		
		 task = Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new BukkitRunnable() {
			public void run() {
				p.sendMessage(ChatColor.RED + "You begin to feel weak and tired...");
				p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, comedownTime * 10, 1));
				p.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, comedownTime * 20, 1));
				p.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, comedownTime * 20, 1));
				Bukkit.getScheduler().cancelTask(task);
			}
		}, (peakTime + 10) * 20L);
		 
	}

}
