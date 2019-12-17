package fr.gregoire.utils;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import fr.gregoire.utils.functions.PlayerWrapper;

public class Lists {
	public static int taskId;
	public static HashMap<Player, PlayerWrapper> verifiers = new HashMap<Player, PlayerWrapper>();

	public static void stopTask() { Bukkit.getScheduler().cancelTask(taskId); }
}
