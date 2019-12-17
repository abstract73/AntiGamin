package fr.gregoire.listener.defaults;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import fr.gregoire.commands.AdminCommands;
import fr.gregoire.utils.PlayersInventory;
import fr.gregoire.utils.functions.PlayerWrapper;

public class PlayerInteract implements Listener {
	public static ArrayList<String> vanish = new ArrayList<String>();
	public static ArrayList<String> god = new ArrayList<String>();

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerInteractEvent(PlayerInteractEvent e) {
		if (e.getAction() == Action.LEFT_CLICK_AIR) {
			Player player = e.getPlayer();
			PlayerWrapper wp = PlayerWrapper.getByPlayer(player);
			if (player.getTargetBlock(null, 100).getLocation().distance(player.getLocation()) < 6.0D && wp.lastBlockInteraction > System.currentTimeMillis() && wp.clicks >= 10) {
				e.setCancelled(true);
			}
			if (wp.clicks > wp.maxClicks) {
				wp.maxClicks = wp.clicks;
			}
			wp.clicks++;
		} else if (e.getAction() == Action.LEFT_CLICK_BLOCK) {
			Player player = e.getPlayer();
			PlayerWrapper wp = PlayerWrapper.getByPlayer(player);
			wp.lastBlockInteraction = System.currentTimeMillis() + 5000L;
	    } 
	}
	  
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerInteractEvent1(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if ((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) && AdminCommands.admin.contains(p.getName()) && e.getItem() != null) {
			ItemStack item = e.getItem();
		    if (item.getType() == Material.WATCH) {
		    	ArrayList<Player> players = new ArrayList<Player>();
	            for (Player all : Bukkit.getOnlinePlayers()) {
	                if (all == p) continue;
	                players.add(all);
	            }
	            if (players.size() != 0) {
	                Random rand = new Random();
	                int random = rand.nextInt(players.size() - 1 + 1) + 1;
	                Player player = (Player)players.get(random - 1);
	                if (player != null) {
	                    p.teleport(player.getLocation());
	                }
	                p.sendMessage("§aYou were teleported randomly!");
	            } else {
	                p.sendMessage("§cNo players found!");
	            }
		    } else if (item.getType() == Material.GHAST_TEAR) {
	            if (vanish.contains(p.getName())) {
	                vanish.remove(p.getName());
	                for (Player all : Bukkit.getOnlinePlayers()) {
	                    all.showPlayer(p);
	                }
	                p.sendMessage("§cVanish §8| §cOff");
	            } else {
	                vanish.add(p.getName());
	                for (Player all : Bukkit.getOnlinePlayers()) {
	                    all.hidePlayer(p);
	                    if (!all.hasPermission("bypass")) continue;
	                    all.showPlayer(p);
	                }
	                p.sendMessage("§aVanish §8| §aOn");
	            }
	        } else if (item.getType() == Material.BLAZE_POWDER) {
	            if (god.contains(p.getName())) {
	                god.remove(p.getName());
	                p.sendMessage("§cGodMode §8| §cOff");
	            } else {
	                god.add(p.getName());
	                p.sendMessage("§aGodMode §8| §aOn");
	            }
	        } else if (item.getType() == Material.SUGAR) {
	            if (p.getFlySpeed() == 0.1f) {
	                p.setFlySpeed(0.5f);
	                p.setWalkSpeed(0.5f);
	            } else {
	                p.setFlySpeed(0.1f);
	                p.setWalkSpeed(0.2f);
	            }
	            p.sendMessage("§7Speed: §6" + (int)(p.getFlySpeed() * 10.0f));
	        } else if (item.getType() == Material.FEATHER) {
	            if (p.getAllowFlight()) {
	                p.setAllowFlight(false);
	                p.setFlying(false);
	                p.sendMessage("§cFly §8| §cOff");
	            } else if (!p.getAllowFlight()) {
	                p.setAllowFlight(true);
	                p.setFlying(true);
	                p.sendMessage("§aFly §8| §aOn");
	            }
	        } else if (p.getItemInHand().getItemMeta() != null && p.getItemInHand().getItemMeta().getDisplayName() != null && p.getItemInHand().getItemMeta().getDisplayName().equals("§cBack") && AdminCommands.admin.contains(p.getName())) {
	            AdminCommands.admin.remove(p.getName());
	            p.sendMessage("§cAdmin §8| §cOff");
	            PlayersInventory.Join(p);
	        }
		} 
	}
}
