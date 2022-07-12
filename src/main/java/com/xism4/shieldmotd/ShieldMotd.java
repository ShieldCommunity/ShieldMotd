package com.xism4.shieldmotd;

import com.xism4.shieldmotd.command.ShieldMotdCommand;
import com.xism4.shieldmotd.enums.StatusHandlerEnum;
import com.xism4.shieldmotd.listeners.LoginHandlerListener;
import com.xism4.shieldmotd.listeners.ProxyHandlerListener;
import com.xism4.shieldmotd.manager.ChannelHandlerManager;
import com.xism4.shieldmotd.manager.ConfigurationManager;
import com.xism4.shieldmotd.manager.MotdManager;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

public final class ShieldMotd extends Plugin {

    private ChannelHandlerManager channelHandlerManager;
    private ConfigurationManager configurationManager;
    private MotdManager motdManager;
    private StatusHandlerEnum statusHandlerEnum;

    @Override
    public void onEnable() {
        this.configurationManager = new ConfigurationManager(this, "config.yml");
        this.motdManager = new MotdManager(this);
        this.channelHandlerManager = new ChannelHandlerManager();
        loadCommands();
        loadEvents();

        getLogger().info("ShieldMotd has been loaded successfully, running on" +
                ProxyServer.getInstance().getName());
    }

    @Override
    public void onDisable() {
        getLogger().info("ShieldMotd has been disabled, thanks for using it");
    }

    public ChannelHandlerManager getChannelHandlerManager() {
        return channelHandlerManager;
    }

    public ConfigurationManager getConfigurationManager() {
        return configurationManager;
    }

    public MotdManager getMotdManager() {
        return this.motdManager;
    }

    public void loadEvents() {
        getProxy().getPluginManager().registerListener(this, new ProxyHandlerListener(this));
        getProxy().getPluginManager().registerListener(this, new LoginHandlerListener(this));
    }

    public void loadCommands() {
        getProxy().getPluginManager().registerCommand(this, new ShieldMotdCommand(this));
    }

    public StatusHandlerEnum getStatusManager() {
        return statusHandlerEnum;
    }
}
