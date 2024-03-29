package fr.gregoire.utils.cancellable;

import org.bukkit.Bukkit;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public class AutoclickAlertEvent extends PlayerEvent implements Cancellable {
	private static final HandlerList handlers = new HandlerList();
	private int CPS;
	private int MS;
	private boolean cancelled = false;
  
	public AutoclickAlertEvent(String name, int CPS, int MS) {
		super(Bukkit.getPlayerExact(name));
		this.CPS = CPS;
		this.MS = MS;
	}
	
	public HandlerList getHandlers() { return handlers; }
	public boolean isCancelled() { return this.cancelled; }
	public void setCancelled(boolean arg0) { this.cancelled = arg0; }
	public int getCPS() { return this.CPS; }
	public int getMS() { return this.MS; }
}
