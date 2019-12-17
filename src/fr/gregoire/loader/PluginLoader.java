package fr.gregoire.loader;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import fr.gregoire.Main;
import fr.gregoire.commands.manager.CommandsManager;
import fr.gregoire.listener.manager.ListenerManager;
import fr.gregoire.utils.Lists;
import fr.gregoire.utils.runnable.CheckRunnable;
import fr.gregoire.utils.runnable.VerifRunnable;

public class PluginLoader {
	public static void LOAD() {
        System.out.println("[AntiKids] Starting to load.");
        CommandsManager.registerCommands();
        ListenerManager.registerEvents();
        new fr.gregoire.utils.runnable.TPSRunnable();
        new VerifRunnable().runTaskTimerAsynchronously((Plugin)Main.getInstance(), 0L, 1L);
        new CheckRunnable(3, 20, "§cAntiKids §8| &c%username &7has clicked §c%cps &7times in 1 second! (MS: §c%ms)").runTaskTimerAsynchronously((Plugin)Main.getInstance(), 0L, 20L);
        Lists.taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)Main.getInstance(), new Runnable(){

            @SuppressWarnings("deprecation")
			@Override
            public void run() {
                for (Player target : Bukkit.getOnlinePlayers()) {
                    if (!(target.getFlySpeed() < 0.1f)) continue;
                    target.sendMessage("§8-----------------------------------------------------");
                    target.sendMessage("§f####§c#§f####");
                    target.sendMessage("§f###§c###§f###");
                    target.sendMessage("§f##§c##§0#§c##§f##                     §6You are frozen !");
                    target.sendMessage("§f##§c##§0#§c##§f##           §eYou will check by a moderator.");
                    target.sendMessage("§f#§c###§0#§c###§f#      §6If you disconnect you will be banned !");
                    target.sendMessage("§f#§c#######§f#");
                    target.sendMessage("§c####§0#§c####");
                    target.sendMessage("§c#########");
                    target.sendMessage("§8-----------------------------------------------------");
                }
            }
        }, 1L, 120L);
        System.out.println("[AntiKids] Finished loading.");
    }

    public static void UNLOAD() {
        System.out.println("[AntiKids] Starting to unload.");
        Lists.stopTask();
        System.out.println("[AntiKids] Finished unloading.");
    }
}
