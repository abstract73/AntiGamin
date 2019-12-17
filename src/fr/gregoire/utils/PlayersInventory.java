package fr.gregoire.utils;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;

import fr.gregoire.Main;

public class PlayersInventory {
	public static void clearInv(Player p) {
	    p.getInventory().clear();
	    p.getInventory().setHelmet(null);
	    p.getInventory().setChestplate(null);
	    p.getInventory().setLeggings(null);
	    p.getInventory().setBoots(null);
	    for (PotionEffect pf : p.getActivePotionEffects()) {
	    	p.removePotionEffect(pf.getType());
	    }
	    p.updateInventory();
	}
	  
	public static void Join(final Player p) {
		clearInv(p);
	}
	  
	public static void JoinAdmin(final Player p) {
	    clearInv(p);
	    Bukkit.getScheduler().runTaskLater(Main.getInstance(), ()-> {
	    	PlayersInventory.clearInv(p);
	    	setItem(p.getInventory(), new ItemStack(Material.GHAST_TEAR, 1), 0, "§8Vanish");
	    	setItem(p.getInventory(), new ItemStack(Material.BLAZE_POWDER, 1), 1, "§8God");
	    	setItem(p.getInventory(), new ItemStack(Material.FEATHER, 1), 2, "§8Fly");
	    	setItem(p.getInventory(), new ItemStack(Material.SUGAR, 1), 3, "§8Speed");
	    	setItem(p.getInventory(), new ItemStack(Material.WATCH, 1), 4, "§8Random teleport");
	    	setItem(p.getInventory(), new ItemStack(Material.BOOK, 1), 5, "§8Check inv");
	    	setItem(p.getInventory(), new ItemStack(Material.IRON_FENCE, 1), 6, "§8Freeze Player");
	    	setItem(p.getInventory(), new ItemStack(Material.INK_SACK, 1, (short)1), 8, "§cBack");
	    }, 5L);
	}
	
	public static void setItem(Inventory inv, ItemStack item, int slot, String name) {
	    ItemStack itemstack = item;
	    ItemMeta ismeta = itemstack.getItemMeta();
	    ismeta.setDisplayName(name);
	    itemstack.setItemMeta(ismeta);
	    inv.setItem(slot, itemstack);
	}
	
	public static void setItemWithLore(Inventory inv, ItemStack item, int slot, String name, String lore) {
	    ItemStack itemstack = item;
	    ItemMeta ismeta = itemstack.getItemMeta();
	    ismeta.setDisplayName(name);
	    ArrayList<String> lorea = new ArrayList<String>();
	    lorea.add(lore);
	    ismeta.setLore(lorea);
	    itemstack.setItemMeta(ismeta);
	    inv.setItem(slot, itemstack);
	}
	
	public static void setItemWithLoreList(Inventory inv, ItemStack item, int slot, String name, String[] strings) {
	    ItemStack itemstack = item;
	    ItemMeta ismeta = itemstack.getItemMeta();
	    ismeta.setDisplayName(name);
	    String[] l = strings;
	    ismeta.setLore(Arrays.asList(l));
	    itemstack.setItemMeta(ismeta);
	    inv.setItem(slot, itemstack);
	}
	
	public static void addItemWithLore(Inventory inv, ItemStack item, String name, String lore) {
	    ItemStack itemstack = item;
	    ItemMeta ismeta = itemstack.getItemMeta();
	    ismeta.setDisplayName(name);
	    ArrayList<String> lorea = new ArrayList<String>();
	    lorea.add(lore);
	    ismeta.setLore(lorea);
	    itemstack.setItemMeta(ismeta);
	    inv.addItem(new ItemStack[] { itemstack });
	}
}
