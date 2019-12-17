package fr.gregoire.utils.runnable;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import fr.gregoire.Main;

public class TPSRunnable implements Runnable {
    long sec;
    long currentSec;
    public int ticks;
    int delay;
    public static double tps = 20.0;
    int index = -1;

    public TPSRunnable() {
        Bukkit.getScheduler().runTaskTimer((Plugin)Main.getInstance(), (Runnable)this, 0L, 1L);
    }

    @Override
    public void run() {
        this.sec = System.currentTimeMillis() / 1000L;
        if (this.currentSec == this.sec) {
            ++this.ticks;
        } else {
            this.currentSec = this.sec;
            tps = tps == 0.0 ? (double)this.ticks : tps + (double)this.ticks / 2.0;
            this.ticks = 0;
        }
    }
}
