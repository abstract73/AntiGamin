package fr.gregoire.utils.runnable;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import fr.gregoire.utils.Lists;
import fr.gregoire.utils.PlayersInventory;
import fr.gregoire.utils.functions.PlayerWrapper;

public class VerifRunnable extends BukkitRunnable {
    public void run() {
        for (Player verifier : Lists.verifiers.keySet()) {
            if (verifier.getOpenInventory().getTopInventory() != null && verifier.getOpenInventory().getTopInventory().getTitle().startsWith("§8§lVerif »")) {
                String v = verifier.getOpenInventory().getTopInventory().getName().split("» ")[1];
                if (Bukkit.getPlayer((String)v) == null) continue;
                Player p = Bukkit.getPlayer((String)v);
                verifier.getOpenInventory().getTopInventory().setItem(0, p.getInventory().getHelmet());
                verifier.getOpenInventory().getTopInventory().setItem(1, p.getInventory().getChestplate());
                verifier.getOpenInventory().getTopInventory().setItem(2, p.getInventory().getLeggings());
                verifier.getOpenInventory().getTopInventory().setItem(3, p.getInventory().getBoots());
                PlayerWrapper wp = Lists.verifiers.get((Object)verifier);
                int nalert = 1;
                if (wp.nAlertsAutoClick > 0) {
                    nalert = wp.nAlertsAutoClick;
                }
                int ping = wp.getPing();
                Long l = wp.Connexion;
                Long l2 = System.currentTimeMillis();
                long diffMs = l2 - l;
                long diffHours = diffMs / 3600000L;
                if (diffHours != 0L) {
                    long diffMins = diffMs / 3600000L % 60L;
                    String[] lore = new String[]{"§7Connected since §6" + diffHours + " §7hours & §6" + diffMins + " §7min."};
                    PlayersInventory.setItemWithLoreList(verifier.getOpenInventory().getTopInventory(), new ItemStack(Material.NAME_TAG, ping > 64 ? 64 : ping), 6, "§7Ping: §6" + ping, lore);
                } else {
                    long diffSec = diffMs / 1000L;
                    long min = diffSec / 60L;
                    String[] lore = new String[]{"§7Connected since §6" + min + " §7min."};
                    PlayersInventory.setItemWithLoreList(verifier.getOpenInventory().getTopInventory(), new ItemStack(Material.NAME_TAG, ping > 64 ? 64 : ping), 6, "§7Ping: §6" + ping, lore);
                }
                PlayersInventory.setItemWithLoreList(verifier.getOpenInventory().getTopInventory(), new ItemStack(Material.GOLD_NUGGET, wp.clicks2 > 64 ? 64 : wp.clicks2), 4, "§7Clicks the last 5 seconds:", new String[]{"§7-§e " + wp.clicks2, "§7-§e " + wp.clicks3, "§7-§e " + wp.clicks4, "§7-§e " + wp.clicks5, "§7-§e " + wp.clicks6});
                PlayersInventory.setItem(verifier.getOpenInventory().getTopInventory(), new ItemStack(Material.NETHER_STALK, nalert > 64 ? 64 : nalert), 5, "§7Number of alerts: §c" + wp.nAlertsAutoClick);
                PlayersInventory.setItemWithLoreList(verifier.getOpenInventory().getTopInventory(), new ItemStack(Material.STONE_SWORD, wp.clicks > 64 ? 64 : wp.clicks), 7, "§7Clicks Currently: §9" + wp.clicks, new String[]{"§7Clicks", "§7Currently: §9" + wp.maxClicks});
                PlayersInventory.setItemWithLore(verifier.getOpenInventory().getTopInventory(), new ItemStack(Material.DIAMOND, wp.maxClicks > 64 ? 64 : wp.maxClicks), 8, " ", "§7Max of clicks: §3" + wp.maxClicks);
                int slot = 8;
                for (ItemStack itemStack : p.getInventory().getContents()) {
                    verifier.getOpenInventory().getTopInventory().setItem(++slot, itemStack);
                }
                continue;
            }
            Lists.verifiers.remove((Object)verifier);
        }
    }
}
