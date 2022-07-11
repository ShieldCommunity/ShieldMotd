package com.xism4.shieldmotd;

import com.xism4.shieldmotd.command.ShieldMotdCommand;
import com.xism4.shieldmotd.listeners.ProxyHandlerListener;
import com.xism4.shieldmotd.manager.ConfigurationManager;
import net.md_5.bungee.api.plugin.Plugin;

public class ShieldMotdManager extends Plugin {

    public ConfigurationManager configurationManager;

    /**
     * Load base configuration for ShieldMotd
     * This class is to keep main thread smooth & readable.
     *
     * @throws IllegalAccessError if already it's replaced.
     */

    public void loadConfiguration() {
        configurationManager = new ConfigurationManager
                (
                        this,"config.yml"
                );
    }

    /**
     * Load events for ShieldMotd, to main class
     * After this, all uses of @exception
     *
     * @throws NullPointerException if does not find the events.
     */

    public void loadEvents(ShieldMotd event) {
        getProxy().getPluginManager().registerListener(this, new ProxyHandlerListener(event));
    }

    /**
     * Load commands for ShieldMotd, to main class
     * After this, all uses of @exception
     *
     * @throws NullPointerException if does not find the events.
     */

    public void loadCommands(ShieldMotd command) {
        getProxy().getPluginManager().registerCommand(this, new ShieldMotdCommand(command));
    }
}
