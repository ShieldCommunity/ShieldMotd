package com.xism4.shieldmotd.listeners;

import java.util.logging.Level;

import com.xism4.shieldmotd.ShieldMotd;
import com.xism4.shieldmotd.utils.FastMotdException;
import io.netty.channel.ChannelHandlerContext;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.protocol.BadPacketException;
import net.md_5.bungee.protocol.OverflowPacketException;

public class ProxyHandlerListener implements Listener {

    private ShieldMotd core;
    private static long lastInitialException;
    private Throwable cause;

    public ProxyHandlerListener(ShieldMotd core) {
        this.core = core;
    }

    @EventHandler(priority = 64)
    public void proxyHandler(ProxyPingEvent event) {
        ServerPing pingHandler = event.getResponse();
        String address = event.getConnection().getAddress().getAddress().getHostAddress();

        if (pingHandler == null) {
            core.getChannelHandlerManager().closeChannel((ChannelHandlerContext) this, address);
            core.getLogger().warning("An handshake was null and cancelled");
            return;
        }

        if (cause instanceof FastMotdException || cause instanceof BadPacketException || cause instanceof OverflowPacketException) {
            core.getChannelHandlerManager().closeChannel((ChannelHandlerContext) this, address);
            core.getInstance().getLogger().log(Level.INFO,
                    "The connection -> {0} rate-limited bad-joins, we suggest blacklist", address);
            return;
        }

        pingHandler.setDescriptionComponent(
                new TextComponent(ChatColor.translateAlternateColorCodes('&',
                        core.getConfigurationManager().getStringList("motd.lines"))));
    }
}
