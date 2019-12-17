package fr.gregoire.commands;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.gregoire.utils.PlayersInventory;

public class AdminCommands implements CommandExecutor {
	public static ArrayList<String> admin = new ArrayList<String>();

	public boolean onCommand(CommandSender sender, Command command, String label, String[] array) {
		Player p = (Player)sender;
		if (label.equalsIgnoreCase("admin")) {
			if (p.hasPermission("command.admin")) {
				if (admin.contains(p.getName())) {
					admin.remove(p.getName());
					p.sendMessage("§cAdmin §8| §cOff");
					PlayersInventory.Join(p);
				} else {
					admin.add(p.getName());
					p.sendMessage("§aAdmin §8| §aOn");
					PlayersInventory.JoinAdmin(p);
				} 
			} else {
				p.sendMessage("§cWarning §7» §cInsufficient permissions!");
			} 
	    }
	    return false;
	}
}
