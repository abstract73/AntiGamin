package fr.gregoire.listener.objects;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class Verif_ObjectsListener implements Listener {
	@EventHandler
	public void onInventoryClickEvent(InventoryClickEvent e) {
		if (e.getInventory() != null && e.getInventory().getTitle().startsWith("§8§lVerif »"))
			e.setCancelled(true); 
	}
}
