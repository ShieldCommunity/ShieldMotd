package com.xism4.shieldmotd.listeners;

import com.xism4.shieldmotd.ShieldMotd;
import com.xism4.shieldmotd.manager.ChannelHandlerManager;
import com.xism4.shieldmotd.utils.FastMotdException;
import io.netty.channel.ChannelHandlerContext;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.protocol.BadPacketException;
import net.md_5.bungee.protocol.OverflowPacketException;

import java.util.logging.Level;

public class ProxyHandlerListener implements Listener {

    private final ShieldMotd core;
    private long lastInitialException;
    private Throwable cause;


    public ProxyHandlerListener(ShieldMotd core) {
        this.core = core;
    }

    @EventHandler(priority = 64)
    public void proxyHandler(ProxyPingEvent event) {

        ChannelHandlerManager channelHandlerManager = core.getChannelHandlerManager();
        ServerPing pingHandler = event.getResponse();
        @SuppressWarnings("deprecation")
        String address = event.getConnection().getAddress().getAddress().getHostAddress();

        if (ProxyServer.getInstance().getName().equals(core.getConfigurationManager().getStringList("motd.ignore-proxy"))) {
            core.getLogger().log(Level.INFO,
                    "-> Disabled brand has been detected, ignoring checks");
            return;
        }

        if (pingHandler == null) {
            //TODO: wut
            //channelHandlerManager.closeChannel((ChannelHandlerContext) this, address);
            core.getLogger().warning("An handshake was null and cancelled");
            return;
        }

        if (cause instanceof FastMotdException || cause instanceof BadPacketException || cause instanceof OverflowPacketException) {
            // TODO: wut
            //channelHandlerManager.closeChannel((ChannelHandlerContext) this, address);
            core.getLogger().log(Level.INFO,
                    "The connection -> {0} rate-limited bad-joins, we suggest blacklist", address);
            return;
        }

        core.getMotdManager().setMotd(pingHandler, event.getResponse().getVersion().getProtocol());
    }
}
