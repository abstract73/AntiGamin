package fr.gregoire.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import fr.gregoire.utils.Lists;
import fr.gregoire.utils.functions.PlayerWrapper;

public class VerifCommands implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command command, String label, String[] array) {
	    Player p = (Player)sender;
	    if (label.equalsIgnoreCase("verif")) {
	    	if (p.hasPermission("command.verif")) {
	    		if (array.length == 1) {
	    			String pseudo = array[0];
	    			if (Bukkit.getPlayer(pseudo) != null) {
	    				Inventory inv = Bukkit.createInventory(null, 54, "§8§lVerif § " + pseudo);
	    				Lists.verifiers.put(p, PlayerWrapper.getByPlayer(Bukkit.getPlayer(pseudo)));
	    				p.openInventory(inv);
	    			} else {
	    				p.sendMessage("§cThis player is Offline.");
	    			} 
	    		} else {
	    			p.sendMessage("§cSyntax Error! please use /verif [player]");
	    		} 
	    	} else {
	    		p.sendMessage("§cWarning §7» §cInsufficient permissions!");
	    	} 
	    }
	    return false;
	}
}
