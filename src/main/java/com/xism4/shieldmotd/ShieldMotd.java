package com.xism4.shieldmotd;

import com.xism4.shieldmotd.command.ShieldMotdCommand;
import com.xism4.shieldmotd.enums.StatusHandlerEnum;
import com.xism4.shieldmotd.listeners.MotdListener;
import com.xism4.shieldmotd.manager.ConfigurationHandler;
import com.xism4.shieldmotd.manager.ConfigurationManager;
import com.xism4.shieldmotd.manager.MotdManager;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

import javax.security.auth.login.Configuration;
import java.io.File;

public final class ShieldMotd extends Plugin {

    private ConfigurationManager configurationManager;
    private MotdManager motdManager;
    private StatusHandlerEnum statusHandlerEnum;

    @Override
    public void onEnable() {
        this.configurationManager = new ConfigurationManager(this, "config.yml");
        this.motdManager = new MotdManager(this);
        loadCommands();
        loadEvents();

        getLogger().info("ShieldMotd has been loaded successfully, running on " +
                ProxyServer.getInstance().getName());

       //ConfigurationHandler.IMP.reload(new File("resources", "config.yml"));
    }

    @Override
    public void onDisable() {
        getLogger().info("ShieldMotd has been disabled, thanks for using it");
    }

    public ConfigurationManager getConfigurationManager() {
        return configurationManager;
    }

    public MotdManager getMotdManager() {
        return this.motdManager;
    }

    public void loadEvents() {
        getProxy().getPluginManager().registerListener(this, new MotdListener(this));
    }

    public void loadCommands() {
        getProxy().getPluginManager().registerCommand(this, new ShieldMotdCommand(this));
    }

    public StatusHandlerEnum getStatusManager() {
        return statusHandlerEnum;
    }
}
