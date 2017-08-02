package net.kryptonox.factiondrugs.drugs;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import net.kryptonox.factiondrugs.FactionDrugs;

public class Cocaine extends Drug implements Listener {
	
	static int task;
	int pure;
	
	public Cocaine(FactionDrugs plugin, String name) {
		super(plugin, name);
		
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onCraftingTableOpen(PlayerInteractEvent e) {
		Block b = e.getClickedBlock();
		Action a = e.getAction();
		
		if(a == Action.RIGHT_CLICK_BLOCK && b.getType().equals(Material.WORKBENCH)) {
			createRecipe("&cCocaine");
		}
	}
	

	@SuppressWarnings("deprecation")
	public void createRecipe(String name) {
		ItemStack cocaine = new ItemStack(Material.SUGAR, 5);
		ItemMeta cocaineMeta = cocaine.getItemMeta();
	
		pure = purity.nextInt(100) + 1;
		cocaineMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
		lore.add(ChatColor.translateAlternateColorCodes('&', "&7" + pure + "% Pure"));
		lore.add(ChatColor.translateAlternateColorCodes('&', "&7Right Click To Snort!"));
		cocaineMeta.setLore(lore);
		lore.clear();
		cocaine.setItemMeta(cocaineMeta);
		
		ShapelessRecipe cocaineRecipe = new ShapelessRecipe(cocaine);
		cocaineRecipe.addIngredient(Material.GLASS_BOTTLE);
		cocaineRecipe.addIngredient(Material.COAL, (byte) 1);
		cocaineRecipe.addIngredient(Material.SUGAR);
		plugin.getServer().addRecipe(cocaineRecipe);
	}
	
	@SuppressWarnings("deprecation")
	public static void giveEffects(final Player p, final int peakTime, final int comedownTime) {
		p.sendMessage(ChatColor.GREEN + "You begin to feel euphoric...");
		
		p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, peakTime * 20, 1));
		p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, peakTime * 20, 1));
		p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, peakTime * 20, 1));
		
		 task = Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new BukkitRunnable() {
			public void run() {
				p.sendMessage(ChatColor.RED + "You begin to fall into a deep depression...");
				p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, comedownTime * 20, 1));
				p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, comedownTime * 20, 1));
				p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, comedownTime * 20, 1));
				Bukkit.getScheduler().cancelTask(task);
			}
		}, (peakTime + 10) * 20L);
		 
	}

}
