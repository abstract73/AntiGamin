package fr.gregoire.utils.functions;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PlayerWrapper {
	private static final Reflection.FieldAccessor<Integer> getConnection = Reflection.getField("{nms}.EntityPlayer", "ping", int.class);
	private static final Reflection.MethodInvoker getPlayerHandle = Reflection.getMethod("{obc}.entity.CraftPlayer", "getHandle", new Class[0]);
	public static HashMap<UUID, PlayerWrapper> players = new HashMap<UUID, PlayerWrapper>(); public int clicks; public int clicks2; public int clicks3; public int clicks4; public int clicks5;
	public int clicks6;
  
	public PlayerWrapper(Player p) {
		this.clicks = 0;
		this.clicks2 = 0;
		this.clicks3 = 0;
		this.clicks4 = 0;
		this.clicks5 = 0;
		this.clicks6 = 0;
		this.nAlertsAutoClick = 0;
		this.maxClicks = 0;
		this.lastBlockInteraction = 0L;
		this.lastAlert = 0L;
		this.Connexion = 0L;
		this.pseudo = "";
		players.put(p.getUniqueId(), this);
		this.pseudo = p.getName();
		this.Connexion = System.currentTimeMillis();
	}
	
	public int nAlertsAutoClick;
	public int maxClicks; 
	public long lastBlockInteraction; 
	public long lastAlert; 
	public long Connexion; 
	public String pseudo;
  
	public String getName() { return this.pseudo; }
	public Player getPlayer() { return Bukkit.getPlayer(this.pseudo); }

	public static PlayerWrapper getByPlayer(Player p) {
		for (PlayerWrapper pw : players.values()) {
			if (pw.getName().equals(p.getName())) {
				return pw;
			}
		} 
		return null;
	}
  
	public static PlayerWrapper getByString(String name) {
		for (PlayerWrapper pw : players.values()) {
			if (pw.getName().equals(name)) {
				return pw;
			}
		} 
		return null;
	}

	public static void removePlayer(Player p) { players.remove(p.getUniqueId()); }

	public int getPing() { 
		return ((Integer)getConnection.get(getPlayerHandle.invoke(Bukkit.getPlayer(this.pseudo), new Object[0]))).intValue();
	}
}
