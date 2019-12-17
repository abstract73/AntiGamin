package fr.gregoire.listener.defaults;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMove  implements Listener {
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if (p.getFlySpeed() < 0.1F && (e.getFrom().getBlockX() != e.getTo().getBlockX() || e.getFrom().getBlockZ() != e.getTo().getBlockZ()))
			p.teleport(e.getFrom()); 
	}
}
