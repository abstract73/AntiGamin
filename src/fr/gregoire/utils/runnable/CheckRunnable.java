package fr.gregoire.utils.runnable;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.scheduler.BukkitRunnable;

import fr.gregoire.utils.cancellable.AutoclickAlertEvent;
import fr.gregoire.utils.functions.PlayerWrapper;

public class CheckRunnable extends BukkitRunnable {
    private int timeBetweenAlerts;
    private int maxCps;
    private String AlertMessage;

    public CheckRunnable(int secondes, int maxCps, String AlertMessage) {
        this.timeBetweenAlerts = secondes;
        this.AlertMessage = AlertMessage;
        this.maxCps = maxCps;
    }

    @SuppressWarnings("deprecation")
	public void run() {
        for (PlayerWrapper wp : PlayerWrapper.players.values()) {
            int ping = wp.getPing();
            if (wp.clicks >= this.maxCps && wp.lastAlert + (long)this.timeBetweenAlerts * 1000L < System.currentTimeMillis()) {
                AutoclickAlertEvent event = new AutoclickAlertEvent(wp.pseudo, wp.clicks, ping);
                Bukkit.getServer().getPluginManager().callEvent((Event)event);
                wp.lastAlert = System.currentTimeMillis();
                if (!event.isCancelled()) {
                    for (Player all : Bukkit.getOnlinePlayers()) {
                        if (!all.hasPermission("antikids.alerts")) continue;
                        all.sendMessage(this.AlertMessage.replace("%username", wp.pseudo).replace("%cps", String.valueOf(wp.clicks)).replace("%ms", String.valueOf(ping)).replace("&", "§"));
                    }
                    if (wp.clicks >= 50) {
                        for (Player all : Bukkit.getOnlinePlayers()) {
                            if (!all.hasPermission("antikids.alerts")) continue;
                            all.sendMessage("§cAntiKids §8| §7Kicking Player §c" + wp.getName() + " §7for §cMacros§7. Please invstigate this!");
                        }
                    }
                    PlayerWrapper playerWrapper = wp;
                    ++playerWrapper.nAlertsAutoClick;
                }
            }
            wp.clicks6 = wp.clicks5;
            wp.clicks5 = wp.clicks4;
            wp.clicks4 = wp.clicks3;
            wp.clicks3 = wp.clicks2;
            wp.clicks2 = wp.clicks;
            wp.clicks = 0;
        }
    }
}

