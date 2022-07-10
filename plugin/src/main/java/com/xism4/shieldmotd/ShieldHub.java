package com.xism4.shieldmotd;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class ShieldMotd extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("ShieldMotd has been enabled " +
                Bukkit.getVersion());
    }

    @Override
    public void onDisable() {
        getLogger().warning("ShieldHub has been disabled, remember to clear data");
    }
}
