package fr.gregoire.listener.defaults;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamage implements Listener {
	@EventHandler
	public void onEntityDamageEvent(EntityDamageEvent e) {
		if (e.getEntity() instanceof Player) {
			Player p = (Player)e.getEntity();
			if (PlayerInteract.god.contains(p.getName()))
				e.setCancelled(true); 
		} 
	}
}
