package fr.gregoire.listener.defaults;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.gregoire.utils.functions.PlayerWrapper;

public class PlayerQuit implements Listener {
	@SuppressWarnings("deprecation")
	@EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        int ping = (int)((double)((CraftPlayer)p).getHandle().ping / 2.0);
        if (p.getFlySpeed() < 0.1f) {
            for (Player all : Bukkit.getOnlinePlayers()) {
                all.sendMessage("§c" + p.getName() + " §7has §cdisconnect §7while it §cfrozen§7. (Ping: §c" + ping + "§7)");
            }
        }
        PlayerWrapper.removePlayer(e.getPlayer());
    }
}
