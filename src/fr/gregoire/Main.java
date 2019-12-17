package fr.gregoire;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import fr.gregoire.loader.PluginLoader;
import fr.gregoire.utils.functions.PlayerWrapper;
import fr.gregoire.utils.runnable.TPSRunnable;

public class Main extends JavaPlugin {
	static Main instance;
	public static Main getInstance() {
		return instance;
	}
	
	@SuppressWarnings("deprecation")
	public void onEnable() {
		instance = this;
		PluginLoader.LOAD();
        for (Player p : Bukkit.getOnlinePlayers()) {
            new fr.gregoire.utils.functions.PlayerWrapper(p);
        }
	}

	@SuppressWarnings("deprecation")
	public void onDisable() {
		for (Player p : Bukkit.getOnlinePlayers()) {
			PlayerWrapper.removePlayer(p);
		}
		instance = null;
		PluginLoader.UNLOAD();
	}

	public double getTps() {
		return TPSRunnable.tps + 1.0;
	}
}
