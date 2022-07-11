package com.xism4.shieldmotd.listeners;

import com.xism4.shieldmotd.ShieldMotd;
import com.xism4.shieldmotd.manager.ChannelHandlerManager;
import io.netty.channel.ChannelHandlerContext;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Cancellable;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.net.InetSocketAddress;

public class ProxyHandlerListener implements Listener {

    private ShieldMotd core;

    public ProxyHandlerListener(ShieldMotd core) {
        this.core = core;
    }

    @EventHandler(priority = 64)
    public void proxyHandler(ProxyPingEvent event) {
        ServerPing pingHandler = event.getResponse();
        String address = event.getConnection().getAddress().getAddress().getHostAddress();

        if (pingHandler == null) {
            core.getChannelHandlerManager().closeChannel((ChannelHandlerContext) this,address);
            core.getLogger().warning("An handshake was null and cancelled");
            return;
        }

        pingHandler.setDescriptionComponent(
                new TextComponent(ChatColor.translateAlternateColorCodes('&',
                        core.getConfigurationManager().getStringList("motd.lines"))));
    }
}
