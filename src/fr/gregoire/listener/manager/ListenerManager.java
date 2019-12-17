package fr.gregoire.listener.manager;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import fr.gregoire.Main;
import fr.gregoire.listener.defaults.EntityDamage;
import fr.gregoire.listener.defaults.PlayerInteract;
import fr.gregoire.listener.defaults.PlayerInteractEntity;
import fr.gregoire.listener.defaults.PlayerMove;
import fr.gregoire.listener.defaults.PlayerQuit;
import fr.gregoire.listener.objects.Verif_ObjectsListener;

public class ListenerManager {
	private static ArrayList<Listener> listeners = new ArrayList<Listener>();
  
	private static void setup() {
		listeners.add(new EntityDamage());
		listeners.add(new PlayerInteract());
		listeners.add(new PlayerInteractEntity());
		listeners.add(new PlayerMove());
		listeners.add(new PlayerQuit());
		listeners.add(new Verif_ObjectsListener());
	}
  
	public static void registerEvents() {
		setup();
		for (Listener l : listeners)
			Bukkit.getPluginManager().registerEvents(l, Main.getInstance()); 
	}
}
