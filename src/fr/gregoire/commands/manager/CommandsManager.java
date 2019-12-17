package fr.gregoire.commands.manager;

import java.util.HashMap;

import org.bukkit.command.CommandExecutor;

import fr.gregoire.Main;
import fr.gregoire.commands.AdminCommands;
import fr.gregoire.commands.VerifCommands;

public class CommandsManager {
	private static HashMap<String, CommandExecutor> commandexecutors = new HashMap<String, CommandExecutor>();
	  
	private static void fill() {
		commandexecutors.put("admin", new AdminCommands());
		commandexecutors.put("verif", new VerifCommands());
	}
	  
	public static void registerCommands() {
		fill();
		for (String s : commandexecutors.keySet())
			Main.getInstance().getCommand(s).setExecutor((CommandExecutor)commandexecutors.get(s)); 
	}
}
