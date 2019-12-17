package fr.gregoire.listener.defaults;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import fr.gregoire.commands.AdminCommands;

public class PlayerInteractEntity implements Listener {
	@EventHandler(priority = EventPriority.LOWEST)
	public static void onPlayerInteractEntityEvent(PlayerInteractEntityEvent e) {
		Player p = e.getPlayer();
		if (p.getItemInHand() == null || p.getItemInHand().getType() == Material.BOOK) {
			if (e.getRightClicked() instanceof Player) {
				Player target = (Player)e.getRightClicked();
				if (AdminCommands.admin.contains(p.getName())) {
					p.openInventory(target.getInventory());
				}
			} 
		} else if ((p.getItemInHand() == null || p.getItemInHand().getType() == Material.IRON_FENCE) && e.getRightClicked() instanceof Player) {
			Player target = (Player)e.getRightClicked();
			if (AdminCommands.admin.contains(p.getName())) {
				if (target.getFlySpeed() == 0.1F) {
					target.setFlySpeed(0.0F);
					target.setWalkSpeed(0.0F);
				} else {
					target.setFlySpeed(0.1F);
					target.setWalkSpeed(0.2F);
				}  
			}
	    } 
	}
}
