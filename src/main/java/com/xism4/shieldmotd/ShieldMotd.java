package com.xism4.shieldmotd;

import com.xism4.shieldmotd.manager.ChannelHandlerManager;
import com.xism4.shieldmotd.manager.ConfigurationManager;
import net.md_5.bungee.api.ProxyServer;


public final class ShieldMotd extends ShieldMotdManager {

    public static ChannelHandlerManager channelHandlerManager;
    public static ConfigurationManager configurationManager;

    @Override
    public void onEnable() {
        loadConfiguration();
        loadCommands(this);
        loadCommands(this);

        getLogger().info("ShieldMotd has been loaded sucesfully, running " +
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
}
