package net.kryptonox.factiondrugs.drugs;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import net.kryptonox.factiondrugs.FactionDrugs;

public class Cocaine extends Drug implements Listener {
	
	public Cocaine(FactionDrugs plugin) {
		super(plugin, "&cCocaine");
	}

	public void createRecipe(String name) {
		ItemStack cocaine = new ItemStack(Material.SUGAR);
		ItemMeta cocaineMeta = cocaine.getItemMeta();
	
		cocaineMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
		if(lore.isEmpty()) {
			return;
		}
		cocaineMeta.setLore(lore);
		cocaine.setItemMeta(cocaineMeta);
		
		ShapedRecipe cocaineRecipe = new ShapedRecipe(cocaine);
		cocaineRecipe.shape("###", "#S#", "###");
		cocaineRecipe.setIngredient('#', Material.SUGAR_CANE).setIngredient('S', Material.SUGAR);
	}

}
